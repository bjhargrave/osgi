/*
 * Copyright (c) OSGi Alliance (2018). All Rights Reserved.
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

package org.osgi.service.cdi.annotations;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.CLASS;
import static org.osgi.service.cdi.PortableExtensionNamespace.CDI_EXTENSION_NAMESPACE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.osgi.annotation.bundle.Requirement;

/**
 * This annotation can be used to require a CDI Extension. It can be used
 * directly, or as a meta-annotation.
 *
 * @author $Id$
 */
@Documented
@Retention(CLASS)
@Target({
		TYPE, PACKAGE
})
@Requirement(namespace = CDI_EXTENSION_NAMESPACE)
public @interface RequireCdiExtension {

	/**
	 * The name of the extension required.
	 */
	String name();

	/**
	 * The version of the extension required.
	 */
	String version() default "";
}