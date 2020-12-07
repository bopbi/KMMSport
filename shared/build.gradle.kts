import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.squareup.sqldelight")
    id("kotlinx-serialization")
}

kotlin {
    android()
    ios {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                // for ktor on ios
	            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}") {
                    version {
                        strictly(Versions.kotlinCoroutines)
                    }
	            }
                implementation("com.badoo.reaktive:reaktive:1.1.18")
                implementation("com.badoo.reaktive:reaktive-annotations:1.1.18")
                implementation("com.badoo.reaktive:coroutines-interop:1.1.18")
                implementation(Serialization.core)
                implementation(SqlDelight.runtime)
                implementation(Ktor.core)
								implementation(Ktor.json)
                implementation(Ktor.clientSerialization)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation("com.badoo.reaktive:reaktive-testing:1.1.18")
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(SqlDelight.android)
                implementation(Ktor.android)
                implementation("com.google.android.material:material:1.2.1")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(SqlDelight.native)
                implementation(Ktor.ios)
            }
        }
        val iosTest by getting
    }
}

android {
    compileSdkVersion(29)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(29)
    }
}

val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
    val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
    val framework = kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}

tasks.getByName("build").dependsOn(packForXcode)

sqldelight {
    database("SportDatabase") {
        packageName = "com.bobbyprabowo.kmmsport.db"
        sourceFolders = listOf("sqldelight")
    }
}
