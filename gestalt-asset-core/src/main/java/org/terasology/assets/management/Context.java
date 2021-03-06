/*
 * Copyright 2015 MovingBlocks
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

package org.terasology.assets.management;

import org.terasology.naming.Name;

/**
 * An AutoClosable token for the current module context. Should be closed when the context ends. Compatible with try-with-resource blocks.
 *
 * @author Immortius
 */
public interface Context extends AutoCloseable {

    /**
     * @return The name of the module of the context
     */
    Name getContext();

    @Override
    void close();
}
