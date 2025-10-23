plugins {
    kotlin("jvm") version "1.9.10"
}

dependencies {
    implementation("io.gitlab.arturbosch.detekt:detekt-api:1.23.6")
    testImplementation(kotlin("test"))
    testImplementation("io.gitlab.arturbosch.detekt:detekt-test:1.23.6")
}

kotlin {
    jvmToolchain(17)
}