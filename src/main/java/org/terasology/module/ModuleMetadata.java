/*
 * Copyright 2014 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.terasology.module;

import com.google.common.collect.Lists;
import org.terasology.i18n.I18nMap;
import org.terasology.naming.Name;
import org.terasology.naming.Version;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Information describing a module.
 *
 * @author Immortius
 */
public class ModuleMetadata {

    private Name id;
    private Version version;
    private I18nMap displayName = new I18nMap("");
    private I18nMap description = new I18nMap("");
    private List<DependencyInfo> dependencies = Lists.newArrayList();
    private final Map<String, Object> extensions;

    public ModuleMetadata() {
        this.extensions = Collections.emptyMap();
    }

    /**
     * @return The identifier of the module
     */
    public Name getId() {
        return id;
    }

    /**
     * Sets the identifier of the module
     *
     * @param id The new identifier
     */
    public void setId(Name id) {
        this.id = id;
    }

    /**
     * @return The version of the module
     */
    public Version getVersion() {
        return version;
    }

    /**
     * Sets the version of the module
     *
     * @param version The new version
     */
    public void setVersion(Version version) {
        this.version = version;
    }

    /**
     * @return A displayable name of the module
     */
    public I18nMap getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName The new human-readable name of the module
     */
    public void setDisplayName(I18nMap displayName) {
        this.displayName = displayName;
    }

    /**
     * @return A human readable description of the module
     */
    public I18nMap getDescription() {
        return description;
    }

    /**
     * @param description The new human-readable description of the module
     */
    public void setDescription(I18nMap description) {
        this.description = description;
    }

    /**
     * @return A list of dependencies of the module
     */
    public List<DependencyInfo> getDependencies() {
        if (dependencies == null) {
            dependencies = Lists.newArrayList();
        }
        return dependencies;
    }

    /**
     * @param dependencyId The id of the module to get dependency information on
     * @return The depdendency information for a specific module, or null if no such dependency exists
     */
    public DependencyInfo getDependencyInfo(Name dependencyId) {
        for (DependencyInfo dependency : dependencies) {
            if (Objects.equals(dependencyId, dependency.getId())) {
                return dependency;
            }
        }
        return null;
    }

    /**
     * @param extensionId  The identifier of the extension
     * @param expectedType The expected type of the extension
     * @param <T>          The expected type of the extension
     * @return The extension object, or null if it is missing or of an incompatible type
     */
    public <T> T getExtension(String extensionId, Class<T> expectedType) {
        Object extension = extensions.get(extensionId);
        if (expectedType.isInstance(extension)) {
            return expectedType.cast(extension);
        }
        return null;
    }

    /**
     * Sets the value of an extension
     *
     * @param extensionId The identifier of the extension
     * @param extension   The extension object
     */
    public void setExtension(String extensionId, Object extension) {
        extensions.put(extensionId, extension);
    }

}
