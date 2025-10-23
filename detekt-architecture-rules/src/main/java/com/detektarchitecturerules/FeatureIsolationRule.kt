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
 * Ensures isolation between UI features by restricting cross-feature imports.
 *
 * This rule enforces that imports between different UI features under the specified base package
 * are disallowed, except when involving designated "bridge" features which are permitted to
 * interact with all features.
 *
 * Configuration parameters:
 *  - rootPackage: The root package of the project, e.g. "es.clean.architecture"
 *  - featuresBase: The base package under which UI features reside, e.g. "es.clean.architecture.ui.views"
 *  - allowedBridges: Comma-separated list of feature names that act as bridges, e.g. "navigation"
 */
class FeatureIsolationRule(config: Config) : Rule(config) {

    override val issue = Issue(
        id = "FeatureIsolation",
        severity = Severity.Defect,
        description = "One UI feature must not import another UI feature.",
        debt = Debt.TWENTY_MINS
    )

    private val root = valueOrDefault("rootPackage", "es.clean.architecture")
    private val featuresBase = valueOrDefault("featuresBase", "$root.ui.views")

    // Set of bridge features that are allowed to import and be imported by any other feature (e.g., navigation)
    private val allowedBridges: Set<String> =
        valueOrDefault("allowedBridges", "")
            .split(',')
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .toSet()

    /**
     * Visits each import directive in the Kotlin file and enforces the feature isolation rule.
     *
     * If the import originates from within the designated features base package and attempts to import
     * another feature (except for allowed bridge features), a code smell is reported.
     */
    override fun visitImportDirective(import: KtImportDirective) {
        val filePkg = import.containingKtFile.packageFqName.asString()
        val imp = import.importPath?.pathStr.orEmpty()

        if (!filePkg.startsWith(featuresBase)) return

        val currentFeature = filePkg.removePrefix("$featuresBase.")
            .substringBefore('.')
        val importedFeature = imp.removePrefix("$featuresBase.")
            .substringBefore('.')

        // Allow imports if either the current or imported feature is a designated bridge feature.
        if (currentFeature in allowedBridges || importedFeature in allowedBridges) {
            super.visitImportDirective(import)
            return
        }

        if (currentFeature.isNotBlank()
            && importedFeature.isNotBlank()
            && currentFeature != importedFeature
            && imp.startsWith(featuresBase)
        ) {
            report(
                CodeSmell(
                    issue,
                    Entity.from(import),
                    "Feature '$currentFeature' must not import '$importedFeature' ($imp)"
                )
            )
        }

        super.visitImportDirective(import)
    }
}