plugins {
    alias(libs.plugins.javalib)
    alias(libs.plugins.eclipse)
    alias(libs.plugins.idea)
    alias(libs.plugins.moddevgradle)
    alias(libs.plugins.publisher)
    alias(libs.plugins.mcSafeResources)
}

val mod_version: String by project
val mod_group_id: String by project
val mod_id: String by project
val minecraft_version: String by project
val neo_version: String by project
val epicfight_version: String by project
val epicskills_version: String by project

val minecraft_version_range: String by project
val neo_version_range: String by project
val loader_version_range: String by project
val mod_name: String by project
val mod_license: String by project
val mod_authors: String by project
val mod_description: String by project

val modPascalCase: String = mod_id.split('_').joinToString("") { it.replaceFirstChar { char -> char.uppercase() } }


repositories {
    fun RepositoryHandler.strictMaven(url: String, repoName: String? = null, vararg groups: String) {
        exclusiveContent {
            forRepository {
                maven(url) {
                    if (repoName != null) name = repoName
                }
            }
            filter {
                groups.forEach { includeGroupAndSubgroups(it) }
            }
        }
    }

    strictMaven("https://code.redspace.io/releases", "Iron's Maven - Release", "io.redspace")
    strictMaven("https://cursemaven.com", "Curse Maven", "curse.maven")
    strictMaven("https://api.modrinth.com/maven", "Modrinth","maven.modrinth")


    flatDir {
        dir("./libs")
    }

    mavenCentral()
}

mcSafeResources {
    namespace.set(mod_id)
    outputPackage.set("yesman.${mod_id}.generated")
}

base {
    archivesName = mod_id
    version = mod_version
}

java.sourceSets.main.get().java.srcDirs(
    tasks.generateLangKeys.map { it.outputs.files.singleFile },
    tasks.generateSoundKeys.map { it.outputs.files.singleFile }
)


java.toolchain.languageVersion = JavaLanguageVersion.of(21)

neoForge {
    version = neo_version
    runs {
        create("client") {
            client()
            devLogin.set(true)
            systemProperty("neoforge.enabledGameTestNamespaces", mod_id)
        }

        create("clientNoAuth") {
            client()
            systemProperty("neoforge.enabledGameTestNamespaces", mod_id)
        }

        create("server") {
            server()
            systemProperty("neoforge.enabledGameTestNamespaces", mod_id)
        }

        configureEach {
            systemProperty("forge.logging.markers", "REGISTRIES")
            logLevel = org.slf4j.event.Level.DEBUG
        }
    }

    mods {
        register(mod_id) {
            sourceSet(sourceSets.main.get())
        }
    }
}

sourceSets.main {
    resources.srcDir(layout.projectDirectory.dir("src/generated/resources"))
}


dependencies {
    implementation(libs.epicFight)
    compileOnly(libs.iss)
    runtimeOnly(libs.moonlight)
    runtimeOnly(libs.dummy)
    implementation(libs.epicskills)
}

val generateModMetadata = tasks.register<ProcessResources>("generateModMetadata") {
    val replaceProperties = mapOf(
        "minecraft_version"       to minecraft_version,
        "minecraft_version_range" to minecraft_version_range,
        "neo_version"            to neo_version,
        "neo_version_range"      to neo_version_range,
        "loader_version_range"   to loader_version_range,
        "mod_id"                 to mod_id,
        "mod_name"               to mod_name,
        "mod_license"            to mod_license,
        "mod_version"            to mod_version,
        "mod_authors"            to mod_authors,
        "mod_description"        to mod_description
    )

    inputs.properties(replaceProperties)
    expand(replaceProperties)

    from("src/main/templates")
    into("build/generated/sources/modMetadata")
}

sourceSets.main.get().resources.srcDir(generateModMetadata)

val TaskContainer.jar: TaskProvider<Jar>
    get() = named<Jar>("jar")

tasks.named("publishMods") {
    dependsOn(tasks.named("signJar"))
}

publishMods {
    file.set(tasks.named<Jar>("jar").flatMap { it.archiveFile })
    changelog.set(file("changelog.md").readText())
    type.set(me.modmuss50.mpp.ReleaseType.ALPHA)
    modLoaders.add("neoforge")

    curseforge {

        projectId.set("your id")
        projectSlug.set("your slug")
        accessToken.set(providers.environmentVariable("CURSEFORGE_TOKEN"))
        minecraftVersions.add(minecraft_version)

        javaVersions.add(JavaVersion.VERSION_21)

        clientRequired.set(true)
        serverRequired.set(true)
    }

    modrinth {
        projectId.set("your id")
        accessToken.set(providers.environmentVariable("MODRINTH_TOKEN"))
        minecraftVersions.add(minecraft_version)
        requires("epic-fight")
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

