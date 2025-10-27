import io.gitlab.arturbosch.detekt.Detekt

plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
    alias(libs.plugins.detekt)
}

android {
    defaultConfig {
        applicationId = libs.versions.applicationID.get()
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        compileSdk = libs.versions.compileSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()
        namespace = libs.versions.namespace.get()
        testInstrumentationRunner = libs.versions.testRunner.get()
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName(libs.versions.buildTypeNameRelease.get()) {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(libs.versions.proguardOptimize.get()),
                libs.versions.proguardRule.get()
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {

        resources {
            excludes.addAll(
                listOf(
                    "LICENSE.txt",
                    "META-INF/DEPENDENCIES",
                    "META-INF/ASL2.0",
                    "META-INF/NOTICE",
                    "META-INF/LICENSE",
                    "META-INF/gradle/incremental.annotation.processors",
                    "META-INF/LICENSE.md",
                    "META-INF/LICENSE-notice.md"
                )
            )
        }
    }
}
detekt {
    buildUponDefaultConfig = true
    autoCorrect = true
    parallel = true
    ignoreFailures = false
    config.setFrom(files("$rootDir/detekt.yml"))
}

tasks.withType<Detekt>().configureEach {
    val isCI = System.getenv("CI") == "true"
    reports {
        html.required.set(!isCI)
        xml.required.set(false)
        txt.required.set(false)
        sarif.required.set(false)
        md.required.set(false)
    }
}

dependencies {
    implementation(libs.androidx.tv.material)
    implementation(libs.bundles.appDependencies)
    ksp(libs.hilt.compiler)

    //Testing
    androidTestImplementation(libs.espressoCore)
    androidTestImplementation(libs.runner)
    androidTestImplementation(libs.androidx.rules)
    androidTestImplementation(libs.mockito.core)
    androidTestImplementation(libs.mockito.mockito.android)
    androidTestImplementation(libs.espressoCore)
    androidTestImplementation(libs.androidx.rules)

    //Detekt
    detektPlugins(project(":detekt-architecture-rules"))
    detektPlugins(libs.detekt.formatting)
}