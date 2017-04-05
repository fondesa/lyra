import org.gradle.api.Plugin
import org.gradle.api.Project

/*
 * Copyright (c) 2017 Fondesa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@SuppressWarnings("GroovyUnusedDeclaration")
class AndroidCommonPlugin implements Plugin<Project> {
    Closure applyConfiguration = { Project project ->
        // Load Android properties file.
        Properties props = new Properties()
        File propsFile = new File("android-version.properties")
        propsFile.withInputStream { props.load(it) }

        // Add Android extension.
        project.android {
            compileSdkVersion Integer.parseInt(props["COMPILE_SDK"])
            buildToolsVersion props["BUILD_TOOLS"]

            defaultConfig {
                minSdkVersion Integer.parseInt(props["MIN_SDK"])
                targetSdkVersion Integer.parseInt(props["TARGET_SDK"])
            }
        }
    }

    @Override
    void apply(Project project) {
        project.configure(project) {
            applyConfiguration(project)
        }
    }
}