/*
 * (C) Copyright 2001 Sun Microsystems, Inc.
 * Copyright (c) OSGi Alliance (2001, 2009). All Rights Reserved.
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

package java.security.acl;
public interface Acl extends java.security.acl.Owner {
	boolean addEntry(java.security.Principal var0, java.security.acl.AclEntry var1) throws java.security.acl.NotOwnerException;
	boolean checkPermission(java.security.Principal var0, java.security.acl.Permission var1);
	java.util.Enumeration<java.security.acl.AclEntry> entries();
	java.lang.String getName();
	java.util.Enumeration<java.security.acl.Permission> getPermissions(java.security.Principal var0);
	boolean removeEntry(java.security.Principal var0, java.security.acl.AclEntry var1) throws java.security.acl.NotOwnerException;
	void setName(java.security.Principal var0, java.lang.String var1) throws java.security.acl.NotOwnerException;
	java.lang.String toString();
}
