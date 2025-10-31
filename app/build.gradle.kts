plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
    alias(libs.plugins.detekt)
    id("jacoco")
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
    buildUponDefaultConfig = false
    autoCorrect = true
    parallel = true
    config.setFrom(files("$rootDir/detekt.yml"))
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    jvmTarget = "17"
    reports {
        parallel = true
        xml.required.set(false)
        html.required.set(true)
        html.outputLocation.set(file("$buildDir/reports/detekt/detekt.html"))
    }
}
jacoco {
    toolVersion = "0.8.11"
}

tasks.register<JacocoReport>("jacocoDebugReport") {
    dependsOn("testDebugUnitTest")

    val fileFilter = listOf(
        "**/R.class", "**/R$*.class", "**/BuildConfig.*",
        "**/Manifest*.*", "**/*Test*.*", "android/**/*.*"
    )

    val debugTree = fileTree("${buildDir}/tmp/kotlin-classes/debug") {
        exclude(fileFilter)
    }

    classDirectories.setFrom(files(debugTree))
    sourceDirectories.setFrom(files("src/main/java", "src/main/kotlin"))
    executionData.setFrom(file("${buildDir}/jacoco/testDebugUnitTest.exec"))

    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("reports/jacoco/html"))
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