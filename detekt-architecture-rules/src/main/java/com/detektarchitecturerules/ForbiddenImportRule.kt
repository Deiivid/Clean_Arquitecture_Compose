package com.detektarchitecturerules

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.psi.KtDotQualifiedExpression
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtUserType

/**
 * Blocks improper dependencies between layers.
 * Config:
 *  - rootPackage: root package of the project (e.g. es.clean.architecture)
 */
class ForbiddenImportRule(config: Config) : Rule(config) {

    override val issue = Issue(
        id = "ForbiddenImport",
        severity = Severity.Defect,
        description = "Prevents forbidden dependencies between layers (UI->DATA, DOMAIN->UI).",
        debt = Debt.TEN_MINS
    )

    private val root = valueOrDefault("rootPackage", "es.clean.architecture")

    override fun visitImportDirective(import: KtImportDirective) {
        val filePkg = import.containingKtFile.packageFqName.asString()
        val imp = import.importPath?.pathStr.orEmpty()

        if (!filePkg.startsWith("$root.domain")) return

        val allowedPrefixes = valueOrDefault("allowedPrefixes", "")
            .split(',')
            .map { it.trim() }
            .filter { it.isNotEmpty() }

        val isAndroidImport = imp.startsWith("android.") || imp.startsWith("androidx.")
        val isAllowed = allowedPrefixes.any { imp.startsWith(it) }

        if (isAndroidImport && !isAllowed) {
            report(
                CodeSmell(
                    issue, Entity.from(import),
                    "Android/AndroidX import in domain: $imp"
                )
            )
        }

        super.visitImportDirective(import)
    }


    override fun visitDotQualifiedExpression(expression: KtDotQualifiedExpression) {
        val filePkg = expression.containingKtFile.packageFqName.asString()
        val text = expression.text // e.g. es.clean.architecture.data.Foo.bar()
        if (filePkg.startsWith("$root.ui") && text.startsWith("$root.data.")) {
            report(
                CodeSmell(
                    issue, Entity.from(expression),
                    "UI layer cannot use (qualified) DATA ($text)"
                )
            )
        }
        if (filePkg.startsWith("$root.domain") && text.startsWith("$root.ui.")) {
            report(
                CodeSmell(
                    issue, Entity.from(expression),
                    "DOMAIN layer cannot use (qualified) UI ($text)"
                )
            )
        }
        super.visitDotQualifiedExpression(expression)
    }

    override fun visitUserType(type: KtUserType) {
        val filePkg = type.containingKtFile.packageFqName.asString()
        val ref = type.text
        if (filePkg.startsWith("$root.ui") && ref.startsWith("$root.data.")) {
            report(
                CodeSmell(
                    issue, Entity.from(type),
                    "UI layer cannot reference DATA type ($ref)"
                )
            )
        }
        if (filePkg.startsWith("$root.domain") && ref.startsWith("$root.ui.")) {
            report(
                CodeSmell(
                    issue, Entity.from(type),
                    "DOMAIN layer cannot reference UI type ($ref)"
                )
            )
        }
        super.visitUserType(type)
    }
}