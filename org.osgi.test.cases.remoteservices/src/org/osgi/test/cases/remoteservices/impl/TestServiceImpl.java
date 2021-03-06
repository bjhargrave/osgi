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
package org.osgi.test.cases.remoteservices.impl;

import org.osgi.test.cases.remoteservices.common.A;
import org.osgi.test.cases.remoteservices.common.B;
import org.osgi.test.cases.remoteservices.common.C;


/**
 * @author <a href="mailto:tdiekman@tibco.com">Tim Diekmann</a>
 *
 */
public class TestServiceImpl implements A, B, C {

	/**
	 * @see org.osgi.test.cases.remoteservices.common.A#getA()
	 */
	@Override
	public String getA() {
		return "A";
	}

	/**
	 * @see org.osgi.test.cases.remoteservices.common.B#getB()
	 */
	@Override
	public String getB() {
		return "B";
	}

	/**
	 * @see org.osgi.test.cases.remoteservices.common.C#getC()
	 */
	@Override
	public String getC() {
		return "C";
	}
}
