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

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Plugin used to have a common configuration between Android modules.
 * This plugin use the constants defined in {@code android-version.properties} file.
 */
@SuppressWarnings("GroovyUnusedDeclaration")
class AndroidCommonPlugin implements Plugin<Project> {

    /**
     * Name of the properties file without extension.
     */
    private static final def FILE_NAME = "android-config"

    @Override
    void apply(Project project) {
        project.configure(project) {
            // Load Android properties file.
            Properties props = new Properties()
            props.load(new FileInputStream("${FILE_NAME}.properties"))

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
    }
}