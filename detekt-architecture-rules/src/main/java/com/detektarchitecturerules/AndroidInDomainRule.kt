package com.detektarchitecturerules

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.psi.KtImportDirective

/**
 * DOMAIN should be pure Kotlin: no android.* or androidx.*
 * Allows configurable exceptions with `allowedPrefixes` in detekt.yml
 */
class AndroidInDomainRule(config: Config) : Rule(config) {

    override val issue = Issue(
        id = "AndroidInDomain",
        severity = Severity.Defect,
        description = "The domain layer should not depend on android.* or androidx.* except for allowed exceptions.",
        debt = Debt.TWENTY_MINS
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

        // Optional DEBUG: see what is read
        println("Allowed prefixes for AndroidInDomainRule: $allowedPrefixes")

        val isAndroidImport =
            imp.startsWith("android.") || imp.startsWith("androidx.") || imp.startsWith("kotlinx.")

        // Important change here: we use contains() for greater flexibility
        val isAllowed = allowedPrefixes.any { allowed ->
            imp.contains(allowed, ignoreCase = false)
        }

        if (isAndroidImport && !isAllowed) {
            report(
                CodeSmell(
                    issue,
                    Entity.from(import),
                    "Android/AndroidX import in domain not allowed: $imp"
                )
            )
        }

        super.visitImportDirective(import)
    }
}