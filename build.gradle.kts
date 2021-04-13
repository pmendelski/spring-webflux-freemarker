import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.32"
    id("org.jetbrains.kotlin.plugin.spring") version "1.4.32"
    id("application")
}

group = "org.inkaust"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("com.sandbox.Application")
}

dependencies {
    implementation(kotlin("reflect"))
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("org.freemarker:freemarker:2.3.31")
    implementation("org.springframework.boot:spring-boot-starter-webflux:2.3.0.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-freemarker:2.3.0.RELEASE")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
        allWarningsAsErrors = true
    }
}
