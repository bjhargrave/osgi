/*
 * Copyright 2008 Oracle Corporation
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.osgi.impl.bundle.jmx.permissionadmin;

import java.io.IOException;

import org.osgi.jmx.service.permissionadmin.PermissionAdminMBean;
import org.osgi.service.permissionadmin.PermissionAdmin;
import org.osgi.service.permissionadmin.PermissionInfo;

/** 
 * 
 */
public class PermissionManager implements PermissionAdminMBean {

	protected PermissionAdmin admin;

	public PermissionManager(PermissionAdmin admin) {
		this.admin = admin;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.jmx.compendium.PermissionManagerMBean#listLocations()
	 */
	public String[] listLocations() throws IOException {
		return admin.getLocations();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.jmx.compendium.PermissionManagerMBean#getPermissions(java.lang
	 * .String)
	 */
	public String[] getPermissions(String location) throws IOException {
		if (location == null) {
			throw new IOException("Locatin must not be null");
		}
		PermissionInfo[] permissions = admin.getPermissions(location);
		if (permissions == null) {
			return null;
		}
		String[] encodedPermissions = new String[permissions.length];
		int i = 0;
		for (PermissionInfo permission : permissions) {
			encodedPermissions[i++] = permission.getEncoded();
		}
		return encodedPermissions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.jmx.compendium.PermissionManagerMBean#setDefaultPermissions(
	 * java.lang.String[])
	 */
	public void setDefaultPermissions(String[] encodedPermissions)
			throws IOException {
		if (encodedPermissions == null) {
			throw new IOException("Encoded permissions must not be null");
		}
		PermissionInfo[] permissions = new PermissionInfo[encodedPermissions.length];
		int i = 0;
		for (String encodedPermission : encodedPermissions) {
			try {
				permissions[i] = new PermissionInfo(encodedPermission);
			} catch (Throwable e) {
				IOException iox = new IOException(
						"Invalid encoded permission: " + encodedPermission);
				iox.initCause(e);
				throw iox;
			}
		}
		admin.setDefaultPermissions(permissions);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.jmx.compendium.PermissionManagerMBean#listDefaultPermissions()
	 */
	public String[] listDefaultPermissions() throws IOException {
		PermissionInfo[] permissions = admin.getDefaultPermissions();
		if (permissions == null) {
			return null;
		}
		String[] encodedPermissions = new String[permissions.length];
		int i = 0;
		for (PermissionInfo permission : permissions) {
			encodedPermissions[i++] = permission.getEncoded();
		}
		return encodedPermissions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.jmx.compendium.PermissionManagerMBean#setPermissions(java.lang
	 * .String, java.lang.String[])
	 */
	public void setPermissions(String location, String[] encodedPermissions)
			throws IOException {
		if (location == null) {
			throw new IOException("Location must not be null");
		}
		if (encodedPermissions == null) {
			throw new IOException("Encoded permissions must not be null");
		}
		PermissionInfo[] permissions = new PermissionInfo[encodedPermissions.length];
		int i = 0;
		for (String encodedPermission : encodedPermissions) {
			try {
				permissions[i] = new PermissionInfo(encodedPermission);
			} catch (Throwable e) {
				IOException iox = new IOException(
						"Invalid encoded permission: " + encodedPermission);
				iox.initCause(e);
				throw iox;
			}
		}
		admin.setPermissions(location, permissions);
	}

}