/*
 * Copyright (c) OSGi Alliance (2012). All Rights Reserved.
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

package org.osgi.dto.framework.startlevel;

import org.osgi.dto.DTO;

/**
 * Data Transfer Object for a BundleStartLevel.
 * 
 * @version $Id$
 * @NotThreadSafe
 */
public class BundleStartLevelDTO implements DTO {
    /**
     * DTOs are serializable.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The assigned start level value for the bundle.
     */
    public int                startLevel;

    /**
     * The bundle's autostart setting indicates that the activation policy
     * declared in the bundle manifest must be used.
     */
    public boolean            activationPolicyUsed;

    /**
     * The bundle's autostart setting indicates it must be started.
     */
    public boolean            persistentlyStarted;
}