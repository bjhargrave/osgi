/*
 * Copyright (c) OSGi Alliance (2016, 2017). All Rights Reserved.
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

package org.osgi.service.cdi.dto;

import org.osgi.dto.DTO;
import org.osgi.framework.dto.BundleDTO;
import org.osgi.service.cdi.dto.model.ComponentModelDTO.Type;
import org.osgi.service.cdi.dto.model.ContainerModelDTO;

/**
 * A snapshot of the runtime state of a CDI container
 *
 * @NotThreadSafe
 * @author $Id$
 */
public class ContainerDTO extends DTO {
	/**
	 * The static description of this component as resolved at initialization time.
	 * <p>
	 * Must not be null
	 */
	public ContainerModelDTO		model;

	/**
	 * The bundle declaring the CDI container.
	 */
	public BundleDTO				bundle;

	/**
	 * The extension dependencies of this CDI container.
	 */
	public ExtensionDTO[]			extensions;

	/**
	 * The component factories defined by this CDI container.
	 * <p>
	 * Must not be null. The array always contains at least one element representing
	 * the {@link Type#APPLICATION APPLICATION}.
	 */
	public ComponentFactoryDTO[]	componentFactories;
}