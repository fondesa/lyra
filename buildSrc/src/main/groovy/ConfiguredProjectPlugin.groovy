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

@SuppressWarnings("GroovyUnusedDeclaration")
abstract class ConfiguredProjectPlugin implements Plugin<Project> {
    Project project

    @Override
    void apply(Project project) {
        this.project = project
        project.configure(project) {
            onProjectConfigured()
        }
    }

    abstract void onProjectConfigured()

    Properties loadProps(String fileName) {
        File[] files = new File[2]
        files[0] = new File(project.rootDir.path + File.separator + "${fileName}.properties")
        files[1] = new File(project.projectDir.path + File.separator + "${fileName}.properties")
        return loadProps(files)
    }

    static Properties loadProps(File... files) {
        Properties props = new Properties()
        files.each {
            if (it.exists()) {
                props.load(new FileInputStream(it))
            }
        }
        return props
    }

    String prop(String propName) {
        project.hasProperty(propName) ? project.property(propName) : ""
    }

    static String prop(Properties properties, String propName) {
        properties.getProperty(propName, "")
    }
}