import net.minecraftforge.gradle.userdev.DependencyManagementExtension
import net.minecraftforge.gradle.userdev.UserDevExtension
import org.gradle.jvm.tasks.Jar
import java.text.SimpleDateFormat
import java.util.*

buildscript {
    repositories {
        // These repositories are only for Gradle plugins, put any other repositories in the repository block further below
        maven("https://maven.minecraftforge.net")
        maven ("https://maven.parchmentmc.org")
        maven("https://repo.spongepowered.org/repository/maven-public/")
        mavenCentral()
    }
    dependencies {
        classpath("net.minecraftforge.gradle:ForgeGradle:5.1.+") {
            isChanging = true
        }
        classpath("org.parchmentmc:librarian:1.+") {
            isChanging = true
        }
        classpath("org.spongepowered:mixingradle:0.7.32")
    }
}

plugins {
    java
}

apply {
    plugin("net.minecraftforge.gradle")
    plugin("org.parchmentmc.librarian.forgegradle")
    plugin("org.spongepowered.mixin")
}

group = "fr.obelouix"
version = "1.0-SNAPSHOT"

java {
    //archivesBaseName = "Obecraft"
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

configure<UserDevExtension> {
    // the mappings can be changed at any time, and must be in the following format.
    // snapshot_YYYYMMDD   snapshot are built nightly.
    // stable_#            stables are built at the discretion of the MCP team.
    // Use non-default mappings at your own risk. they may not always work.
    // simply re-run your setup task after changing the mappings to update your workspace.
    mappings("parchment", "2022.03.13-1.18.2")
    //mappings = mappingVersion
    // makeObfSourceJar = false // an Srg named sources jar is made by default. uncomment this to disable.

    accessTransformer(file("src/main/resources/META-INF/accesstransformer.cfg"))

    // Default run configurations.
    // These can be tweaked, removed, or duplicated as needed.
    runs {
        create("client") {
            workingDirectory(project.file("run"))

            // Recommended logging data for a userdev environment
            property("forge.logging.markers", "SCAN,REGISTRIES,REGISTRYDUMP")

            // Recommended logging level for the console
            property("forge.logging.console.level", "debug")

            mods {
                create("obecraft") {
                    sourceSets.main.get().resources { srcDir("src/generated/resources") }
                    source(sourceSets.main.get())
                }
            }
        }

        create("server") {
            workingDirectory(project.file("run"))

            // Recommended logging data for a userdev environment
            property("forge.logging.markers", "SCAN,REGISTRIES,REGISTRYDUMP")

            // Recommended logging level for the console
            property("forge.logging.console.level", "debug")
        }

        create("gameTestServer") {
            workingDirectory(project.file("run"))

            // Recommended logging data for a userdev environment
            // The markers can be added/remove as needed separated by commas.
            // "SCAN": For mods scan.
            // "REGISTRIES": For firing of registry events.
            // "REGISTRYDUMP": For getting the contents of all registries.
            property("forge.logging.markers", "REGISTRIES")

            // Recommended logging level for the console
            // You can set various levels here.
            // Please read: https://stackoverflow.com/questions/2031163/when-to-use-the-different-log-levels
            property("forge.logging.console.level", "debug")

            // Comma-separated list of namespaces to load gametests from. Empty = all namespaces.
            property("forge.enabledGameTestNamespaces", "Obecraft")

            /*mods {
                obecraft {
                    source(sourceSets.main)
                }
            }*/
        }

        create("data") {
            workingDirectory(project.file("run"))

            // Recommended logging data for a userdev environment
            // The markers can be added/removed as needed separated by commas.
            // "SCAN": For mods scan.
            // "REGISTRIES": For firing of registry events.
            // "REGISTRYDUMP": For getting the contents of all registries.
            property("forge.logging.markers", "REGISTRIES")

            // Recommended logging level for the console
            // You can set various levels here.
            // Please read: https://stackoverflow.com/questions/2031163/when-to-use-the-different-log-levels
            property("forge.logging.console.level", "debug")

            // Specify the modid for data generation, where to output the resulting resource, and where to look for existing resources.
            args("--mod", "obecraft", "--all", "--output", file("src/generated/resources/"), "--existing", file("src/main/resources/"))

            /*mods {
                obecraft {
                    source(sourceSets.main)
                }
            }*/
        }
    }
}
task("mixin") {
    sourceSets.main to "Obecraft.refmap.json"

    //sourceSets.main.config("Obecraft.mixins.json")
}

// Include resources generated by data generators.
//sourceSets.main.resources { srcDir("src/generated/resources") }

repositories {
    // Put repositories for dependencies here
    // ForgeGradle automatically adds the Forge maven and Maven Central for you

    // If you have mod jar dependencies in ./libs, you can declare them as a repository like so:
    // flatDir {
    //     dir "libs"
    // }

    // JEI
    maven("https://dvs1.progwml6.com/files/maven")
    //TOP
    maven("https://cursemaven.com")
    maven("https://modmaven.dev")
}

dependencies {
    // Specify the version of Minecraft to use. If this is any group other than "net.minecraft" it is assumed
    // that the dep is a ForgeGradle "patcher" dependency, and its patches will be applied.
    // The userdev artifact is a special name and will get all sorts of transformations applied to it.
    "minecraft"("net.minecraftforge:forge:1.19.2-43.1.34")

    // Real mod deobf dependency examples - these get remapped to your current mappings
    // compileOnly fg.deobf("mezz.jei:jei-${mc_version}:${jei_version}:api") // Adds JEI API as a compile dependency
    // runtimeOnly fg.deobf("mezz.jei:jei-${mc_version}:${jei_version}") // Adds the full JEI mod as a runtime dependency
    // implementation fg.deobf("com.tterrag.registrate:Registrate:MC${mc_version}-${registrate_version}") // Adds registrate as a dependency

    // Examples using mod jars from ./libs
    // implementation fg.deobf("blank:coolmod-${mc_version}:${coolmod_version}")

    // For more info...
    // http://www.gradle.org/docs/current/userguide/artifact_dependencies_tutorial.html
    // http://www.gradle.org/docs/current/userguide/dependency_management.html

    annotationProcessor("org.spongepowered:mixin:0.8.5:processor")

    val fg = project.extensions.findByType(DependencyManagementExtension::class.java)!!

    val mc_version: String by properties
    val jei_version: String by properties

    // compile against the JEI API but do not include it at runtime
    compileOnly(fg.deobf("mezz.jei:jei-${mc_version}:${jei_version}:api"))
    // at runtime, use the full JEI jar
    runtimeOnly(fg.deobf("mezz.jei:jei-${mc_version}:${jei_version}"))

    runtimeOnly(fg.deobf("curse.maven:the-one-probe-245211:3665852"))
    runtimeOnly(fg.deobf("curse.maven:configured-457570:3721946"))

    fileTree("mods").also { it.include("*.jar") }.forEach {
        val match = "^(?<B>(?<A>[^-]*)(?:-[0-9.]*)?)-(?<C>.*)$".toPattern().toRegex().find(it.nameWithoutExtension)
        if (match != null) {
            val name = "${match.groups["A"]!!.value}:${match.groups["B"]!!.value}:${match.groups["C"]!!.value}"
            implementation(fg.deobf(name))
        }
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release.set(JavaLanguageVersion.of(17).asInt())
}

// processResources
val Project.minecraft: UserDevExtension
    get() = extensions.getByName<UserDevExtension>("minecraft")
tasks.withType<Jar> {

    val buildTime: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())

    manifest {
        attributes(mapOf(
            "Specification-Title" to "obecraft",
            "Specification-Vendor" to "Obelouix",
            "Specification-Version" to "1", // We are version 1 of ourselves
            "Implementation-Title" to project.name,
            "Implementation-Version" to project.version,
            "Implementation-Vendor" to "Obelouix",
            "Implementation-Timestamp" to buildTime,
        ))
    }
    finalizedBy("reobfJar")
    //baseName = modBaseName
}
