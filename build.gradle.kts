import net.minecraftforge.gradle.userdev.UserDevExtension
import org.gradle.jvm.tasks.Jar
import java.util.Date
import java.text.SimpleDateFormat

buildscript {
    repositories {
        // These repositories are only for Gradle plugins, put any other repositories in the repository block further below
        maven("https://maven.minecraftforge.net")
        maven("https://repo.spongepowered.org/repository/maven-public/")
        mavenCentral()
    }
    dependencies {
        classpath("net.minecraftforge.gradle:ForgeGradle:5.1.+") {
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
    plugin( "org.spongepowered.mixin")
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
    mappings("official",  "1.18.2")
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


/*minecraft {
    // The mappings can be changed at any time and must be in the following format.
    // Channel:   Version:
    // snapshot   YYYYMMDD   Snapshot are built nightly.
    // stable     #          Stables are built at the discretion of the MCP team.
    // official   MCVersion  Official field/method names from Mojang mapping files
    //
    // You must be aware of the Mojang license when using the "official" mappings.
    // See more information here: https://github.com/MinecraftForge/MCPConfig/blob/master/Mojang.md
    //
    // Use non-default mappings at your own risk. They may not always work.
    // Simply re-run your setup task after changing the mappings to update your workspace.
    mappings channel: "official", version: "1.18.2"

    // accessTransformer = file("src/main/resources/META-INF/accesstransformer.cfg")

    // Default run configurations.
    // These can be tweaked, removed, or duplicated as needed.
    runs {
        client {
            workingDirectory project.file("run")

            // Recommended logging data for a userdev environment
            // The markers can be added/removed as needed separated by commas.
            // "SCAN": For mods scan.
            // "REGISTRIES": For firing of registry events.
            // "REGISTRYDUMP": For getting the contents of all registries.
            property "forge.logging.markers", "REGISTRIES"

            // Recommended logging level for the console
            // You can set various levels here.
            // Please read: https://stackoverflow.com/questions/2031163/when-to-use-the-different-log-levels
            property "forge.logging.console.level", "debug"

            property "forge.enabledGameTestNamespaces", "Obecraft"

            mods {
                obecraft {
                    source sourceSets.main
                }
            }
        }

        server {
            workingDirectory project.file("run")

            // Recommended logging data for a userdev environment
            // The markers can be added/removed as needed separated by commas.
            // "SCAN": For mods scan.
            // "REGISTRIES": For firing of registry events.
            // "REGISTRYDUMP": For getting the contents of all registries.
            property "forge.logging.markers", "REGISTRIES"

            // Recommended logging level for the console
            // You can set various levels here.
            // Please read: https://stackoverflow.com/questions/2031163/when-to-use-the-different-log-levels
            property "forge.logging.console.level", "debug"

            property "forge.enabledGameTestNamespaces", "Obecraft"

            mods {
                obecraft {
                    source sourceSets.main
                }
            }
        }

        gameTestServer {
            workingDirectory project.file("run")

            // Recommended logging data for a userdev environment
            // The markers can be added/remove as needed separated by commas.
            // "SCAN": For mods scan.
            // "REGISTRIES": For firing of registry events.
            // "REGISTRYDUMP": For getting the contents of all registries.
            property "forge.logging.markers", "REGISTRIES"

            // Recommended logging level for the console
            // You can set various levels here.
            // Please read: https://stackoverflow.com/questions/2031163/when-to-use-the-different-log-levels
            property "forge.logging.console.level", "debug"

            // Comma-separated list of namespaces to load gametests from. Empty = all namespaces.
            property "forge.enabledGameTestNamespaces", "Obecraft"

            mods {
                obecraft {
                    source sourceSets.main
                }
            }
        }

        data {
            workingDirectory project.file("run")

            // Recommended logging data for a userdev environment
            // The markers can be added/removed as needed separated by commas.
            // "SCAN": For mods scan.
            // "REGISTRIES": For firing of registry events.
            // "REGISTRYDUMP": For getting the contents of all registries.
            property "forge.logging.markers", "REGISTRIES"

            // Recommended logging level for the console
            // You can set various levels here.
            // Please read: https://stackoverflow.com/questions/2031163/when-to-use-the-different-log-levels
            property "forge.logging.console.level", "debug"

            // Specify the modid for data generation, where to output the resulting resource, and where to look for existing resources.
            args "--mod", "obecraft", "--all", "--output", file("src/generated/resources/"), "--existing", file("src/main/resources/")

            mods {
                obecraft {
                    source sourceSets.main
                }
            }
        }
    }
}*/

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
}

dependencies {
    // Specify the version of Minecraft to use. If this is any group other than "net.minecraft" it is assumed
    // that the dep is a ForgeGradle "patcher" dependency, and its patches will be applied.
    // The userdev artifact is a special name and will get all sorts of transformations applied to it.
    "minecraft"("net.minecraftforge:forge:1.18.2-40.0.52")

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
