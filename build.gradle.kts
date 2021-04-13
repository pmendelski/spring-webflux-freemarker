plugins {
    id("java")
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
    implementation("org.springframework.boot:spring-boot-starter-webflux:2.4.4")
    implementation("org.springframework.boot:spring-boot-starter-freemarker:2.4.4")
    implementation("io.projectreactor.tools:blockhound:1.0.6.RELEASE")
}
