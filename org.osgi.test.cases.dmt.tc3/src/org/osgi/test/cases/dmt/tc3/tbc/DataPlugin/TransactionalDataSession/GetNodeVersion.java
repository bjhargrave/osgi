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

/*
 * REVISION HISTORY:
 *
 * Date          Author(s)
 * CR            Headline
 * ============  ==============================================================
 * Mar 04, 2005  Andre Assad
 * 11		     Implement DMT Use Cases
 * ============  ==============================================================
 */

package org.osgi.test.cases.dmt.tc3.tbc.DataPlugin.TransactionalDataSession;

import org.osgi.service.dmt.DmtException;
import org.osgi.service.dmt.DmtSession;
import org.osgi.test.cases.dmt.tc3.tbc.DmtTestControl;
import org.osgi.test.cases.dmt.tc3.tbc.DataPlugin.TestDataPlugin;
import org.osgi.test.cases.dmt.tc3.tbc.DataPlugin.TestDataPluginActivator;
import org.osgi.test.support.compatibility.DefaultTestBundleControl;

import junit.framework.TestCase;

/**
 * @author Andre Assad
 * 
 * This test case validates the implementation of <code>getNodeVersion</code> method, 
 * according to MEG specification
 */
public class GetNodeVersion {
	private DmtTestControl tbc;

	public GetNodeVersion(DmtTestControl tbc) {
		this.tbc = tbc;
	}

	public void run() {
        testGetNodeVersion001();
        testGetNodeVersion002();
	}

	/**
	 * Asserts that DmtAdmin correctly forwards the call of getNodeVersion to the correct plugin.
	 * 
	 * @spec ReadableDataSession.getNodeVersion(String[])
	 */
	public void testGetNodeVersion001() {
		DmtSession session = null;
		try {
			DefaultTestBundleControl.log("#testGetNodeVersion001");
			session = tbc.getDmtAdmin().getSession(TestDataPluginActivator.ROOT,
					DmtSession.LOCK_TYPE_ATOMIC);
			
			int nodeVersion = session.getNodeVersion(TestDataPluginActivator.ROOT);
			TestCase.assertEquals("Asserts that DmtAdmin fowarded "+ TestDataPlugin.GETNODEVERSION
					+" to the correct plugin",TestDataPlugin.GETNODEVERSION_VALUE,nodeVersion);
			
		} catch (Exception e) {
			DmtTestControl.failUnexpectedException(e);
		} finally {
			tbc.cleanUp(session,true);
		}
	}

	/**
	 * Asserts that DmtAdmin correctly forwards the DmtException thrown by the plugin
	 * 
	 * @spec ReadableDataSession.getNodeVersion(String[])
	 */
	public void testGetNodeVersion002() {
		DmtSession session = null;
		try {
			DefaultTestBundleControl.log("#testGetNodeVersion002");
			
			session = tbc.getDmtAdmin().getSession(TestDataPluginActivator.ROOT,
					DmtSession.LOCK_TYPE_ATOMIC);
			session.getNodeVersion(TestDataPluginActivator.INTERIOR_NODE_EXCEPTION);
			DefaultTestBundleControl.failException("", DmtException.class);
		} catch (DmtException e) {
			
			TestCase.assertEquals("Asserts that DmtAdmin fowarded the DmtException with the correct subtree: ", TestDataPluginActivator.INTERIOR_NODE_EXCEPTION, e
					.getURI());			
			TestCase.assertEquals("Asserts that DmtAdmin fowarded the DmtException with the correct code: ", DmtException.COMMAND_FAILED, e
					.getCode());
			TestCase.assertTrue("Asserts that DmtAdmin fowarded the DmtException with the correct message. ", e
					.getMessage().indexOf(TestDataPlugin.GETNODEVERSION)>-1);
		} catch (Exception e) {
			DmtTestControl.failExpectedOtherException(DmtException.class, e);		
			} finally {
				tbc.cleanUp(session,true);
		}
	}
    
}
