/*
 * Copyright (c) OSGi Alliance (2001, 2013). All Rights Reserved.
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

package java.rmi.server;
public abstract class RemoteObject implements java.io.Serializable, java.rmi.Remote {
	protected java.rmi.server.RemoteRef ref;
	protected RemoteObject() { } 
	protected RemoteObject(java.rmi.server.RemoteRef var0) { } 
	public java.rmi.server.RemoteRef getRef() { return null; }
	public static java.rmi.Remote toStub(java.rmi.Remote var0) throws java.rmi.NoSuchObjectException { return null; }
}
