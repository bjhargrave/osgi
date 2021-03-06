/*******************************************************************************
 * Copyright (c) Contributors to the Eclipse Foundation
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
 *
 * SPDX-License-Identifier: Apache-2.0 
 *******************************************************************************/
/**
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

package org.osgi.test.cases.cdi.tb3;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import org.osgi.service.cdi.ConfigurationPolicy;
import org.osgi.service.cdi.annotations.BeanPropertyType;
import org.osgi.service.cdi.annotations.ComponentProperties;
import org.osgi.service.cdi.annotations.PID;
import org.osgi.service.cdi.annotations.Service;
import org.osgi.service.cdi.annotations.SingleComponent;
import org.osgi.test.cases.cdi.interfaces.BeanService;

@ConfigurationBeanA.Props
@PID(policy = ConfigurationPolicy.REQUIRED)
@Service(BeanService.class)
@SingleComponent
public class ConfigurationBeanA implements BeanService<Callable<int[]>> {

	@Retention(RUNTIME) @Target(TYPE )
    @BeanPropertyType
    public @interface Props {
		String bean() default "A";
	}

	@Override
	public String doSomething() {
		return config.color();
	}

	@Override
	public Callable<int[]> get() {
		return new Callable<int[]>() {
			@Override
			public int[] call() throws Exception {
				return config.ports();
			}
		};
	}

	@ComponentProperties
	@Inject
	Config config;

}
