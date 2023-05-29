import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    war
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.spring") version "1.8.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.0.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.20.0")
    implementation("org.eclipse.jetty:jetty-webapp:11.0.14")
    implementation("org.hibernate:hibernate-core:6.1.7.Final")
    implementation("org.hsqldb:hsqldb:2.7.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.data:spring-data-jpa:3.0.3")
    implementation("org.springframework:spring-webmvc:6.0.6")
    implementation("org.thymeleaf:thymeleaf-spring6:3.1.1.RELEASE")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    runtimeOnly("org.hsqldb:hsqldb")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
