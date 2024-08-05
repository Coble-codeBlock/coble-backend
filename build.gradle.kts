import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.7"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// jpa
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	// validation
	implementation("org.springframework.boot:spring-boot-starter-validation")

	// web
	implementation("org.springframework.boot:spring-boot-starter-web")

	// starter
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework:spring-context-support:5.3.9")

	// security
	implementation("org.springframework.boot:spring-boot-starter-security")

	// lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// mysql
	runtimeOnly("com.mysql:mysql-connector-j")

	// test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")

	// junit
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// mail
	implementation("org.springframework.boot:spring-boot-starter-mail")

	// redis
	implementation ("org.springframework.boot:spring-boot-starter-data-redis")

	// jwt
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

	// xml
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")

	// s3
	implementation ("org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE")
	implementation("com.amazonaws:aws-java-sdk-s3:1.12.174")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}


allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.MappedSuperclass")
	annotation("javax.persistence.Embeddable")
}

noArg {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.MappedSuperclass")
	annotation("javax.persistence.Embeddable")
}

tasks.getByName<Jar>("jar") {
	enabled = false
}

configurations {
	create("myConfiguration") {
		isCanBeResolved = true
		isCanBeConsumed = false
	}
}
