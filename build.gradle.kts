import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.32"
    id("org.jetbrains.kotlin.plugin.spring") version "1.4.32"
    id("application")
}

version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("com.sandbox.Application")
}

dependencies {
    implementation(kotlin("reflect"))
    implementation("org.springframework.boot:spring-boot-starter-webflux:2.4.4")
    implementation("io.projectreactor.tools:blockhound:1.0.6.RELEASE")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
        allWarningsAsErrors = true
    }
}
