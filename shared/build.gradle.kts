plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
//        commonMain.dependencies {
//          // Ktor
//          implementation(libs.ktor.core)
//          implementation(libs.ktor.json)
//          implementation(libs.ktor.logging)
//          //
//          implementation(libs.ktx.json)
//          implementation(libs.coroutine.core)
//          implementation(libs.koin)
//        }

//        androidMain.get().dependsOn(commonMain.get())
//        androidMain.dependencies {
//          implementation(libs.ktor.okhttp)
//          implementation(libs.androidx.lifecycle.viewmodel.ktx)
//          implementation(libs.koin.android)
//        }

        all {
            languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
        }

        commonMain.dependencies {
            // Ktor
            implementation(libs.ktor.core)
            implementation(libs.ktor.json)
            implementation(libs.ktor.logging)
            implementation(libs.ktor.negotiation)
            //
            implementation(libs.ktx.json)
            implementation(libs.coroutine.core)
            implementation(libs.koin)
        }

        // no necessary for dependsOn(commonMain)
        androidMain.dependencies {
            implementation(libs.ktor.okhttp)
            implementation(libs.androidx.lifecycle.viewmodel.ktx)
            implementation(libs.koin.android)
        }

        iosMain.dependencies {
            implementation(libs.ktor.darwin)
        }

        // this will resolve issue `KotlinSourceSet with name 'iosMain' not found`
//        val iosX64Main by getting {
//            dependencies {
//                implementation(libs.ktor.darwin)
//            }
//        }
//        val iosArm64Main by getting {
//            dependencies {
//                implementation(libs.ktor.darwin)
//            }
//        }
//        val iosSimulatorArm64Main by getting {
//            dependencies {
//                implementation(libs.ktor.darwin)
//            }
//        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.tinyfight.emoji.kmp"
    compileSdk = 34
    defaultConfig {
        minSdk = 26
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
