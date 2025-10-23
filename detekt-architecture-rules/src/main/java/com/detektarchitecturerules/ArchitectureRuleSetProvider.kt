package com.detektarchitecturerules

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

/**
 * Provides the Architecture rule set for detekt static code analysis.
 */
class ArchitectureRuleSetProvider : RuleSetProvider {
    /**
     * The unique identifier of the rule set.
     */
    override val ruleSetId: String = "ArchitectureRules"

    /**
     * Creates an instance of the Architecture rule set containing specific rules related to architecture.
     *
     * @param config The configuration to be used by the rules.
     * @return A RuleSet containing the architecture-related rules.
     */
    override fun instance(config: Config): RuleSet = RuleSet(
        ruleSetId,
        listOf(
            ForbiddenImportRule(config),
            AndroidInDomainRule(config),
            FeatureIsolationRule(config),
        )
    )
}