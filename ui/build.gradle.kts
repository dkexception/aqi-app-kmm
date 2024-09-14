import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("org.jetbrains.kotlin.multiplatform") version ("2.0.20")
    id("com.android.library") version ("8.6.0")
    id("org.jetbrains.compose") version ("1.7.0-alpha02")
    id("org.jetbrains.kotlin.plugin.compose") version ("2.0.20")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version ("2.0.1")
}

compose.resources {
    publicResClass = true
    generateResClass = auto
}

kotlin {

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    sourceSets {

        androidMain.dependencies {

//            Compose
            implementation(compose.preview)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

//            Navigation
            //noinspection UseTomlInstead
            implementation("androidx.navigation:navigation-compose:2.8.0")

//            Coil
            //noinspection UseTomlInstead
            implementation("io.coil-kt:coil-compose:2.6.0")
        }

        commonMain.dependencies {
            implementation(compose.components.resources)
        }
    }
}

android {

    namespace = "io.github.dkexception.ui"
    compileSdk = 35

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")

    defaultConfig {
        minSdk = 26
        lint {
            targetSdk = 35
        }
        consumerProguardFiles("consumer-rules.pro")
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}
