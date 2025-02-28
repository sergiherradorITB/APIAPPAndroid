    plugins {
        id("com.android.application")
        id("org.jetbrains.kotlin.android")
        id("kotlin-kapt")
    }

    android {
        namespace = "com.tartita.apilist_sergiherrador"
        compileSdk = 34

        defaultConfig {
            applicationId = "com.tartita.apilist_sergiherrador"
            minSdk = 24
            targetSdk = 34
            versionCode = 1
            versionName = "1.0"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            vectorDrawables {
                useSupportLibrary = true
            }
        }

        buildTypes {
            release {
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

        kotlinOptions {
            jvmTarget = "17"
        }

        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = "1.4.3"
        }
        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }

    dependencies {
        implementation("androidx.core:core-ktx:1.9.0")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
        implementation("androidx.activity:activity-compose:1.8.2")
        implementation(platform("androidx.compose:compose-bom:2023.03.00"))
        implementation("androidx.compose.ui:ui")
        implementation("androidx.compose.ui:ui-graphics")
        implementation("androidx.compose.ui:ui-tooling-preview")
        implementation("androidx.compose.material3:material3")
        implementation("androidx.leanback:leanback:1.0.0")
        implementation("com.google.android.material:material:1.11.0")
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
        androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
        androidTestImplementation("androidx.compose.ui:ui-test-junit4")
        debugImplementation("androidx.compose.ui:ui-tooling")
        debugImplementation("androidx.compose.ui:ui-test-manifest")
        implementation("androidx.navigation:navigation-compose:2.7.5")
        //LIVEDATA
        implementation("androidx.compose.runtime:runtime-livedata:1.6.0")
        //RETROFIT
        implementation ("com.squareup.retrofit2:retrofit:2.9.0")
        implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
        implementation ("com.squareup.okhttp3:logging-interceptor:4.9.3")
        //CORRUTINES
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
        //GLIDE
        implementation("com.github.bumptech.glide:compose:1.0.0-beta01")
        // BottomNavigation
        implementation("androidx.compose.material:material:1.6.1")

        // ROOM
        implementation("androidx.room:room-runtime:2.6.1")
        annotationProcessor("androidx.room:room-compiler:2.6.1")
        kapt("androidx.room:room-compiler:2.6.1")
        implementation("androidx.room:room-ktx:2.6.1")

        //Material Design 3
        implementation("androidx.compose.material3:material3:1.1.2")
    }