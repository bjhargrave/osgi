/*
 * Copyright (c) OSGi Alliance (2015). All Rights Reserved.
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


package org.osgi.test.cases.component.tb24;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.test.cases.component.service.BaseService;
import org.osgi.test.cases.component.service.MultipleFieldTestService;
import org.osgi.test.cases.component.service.ScalarFieldTestService;
import org.osgi.test.cases.component.service.TestList;
import org.osgi.test.cases.component.service.TestSet;
import org.osgi.test.cases.component.service.TestSortedSet;

public class FinalFieldReceiver extends AbstractFieldReceiver
		implements ScalarFieldTestService<BaseService>, MultipleFieldTestService<BaseService> {
	private final BaseService									service			= null;
	private final Object										assignable		= null;
	private final ServiceReference<BaseService>					reference		= null;
	private final ComponentServiceObjects<BaseService>			serviceobjects	= null;
	private final Map<String, Object>							properties		= null;
	private final Map.Entry<Map<String, Object>, BaseService>	tuple			= null;
	private final Collection<BaseService>									collectionService;
	private final List<BaseService>											listService;
	private final Set<BaseService>											setService;
	private final Collection<ServiceReference<BaseService>>					collectionReference;
	private final List<ServiceReference<BaseService>>						listReference;
	private final Set<ServiceReference<BaseService>>						setReference;
	private final Collection<ComponentServiceObjects<BaseService>>			collectionServiceObjects;
	private final List<ComponentServiceObjects<BaseService>>				listServiceObjects;
	private final Set<ComponentServiceObjects<BaseService>>					setServiceObjects;
	private final Collection<Map<String, Object>>							collectionProperties;
	private final List<Map<String, Object>>									listProperties;
	private final Set<Map<String, Object>>									setProperties;
	private final Collection<Map.Entry<Map<String, Object>, BaseService>>	collectionTuple;
	private final List<Map.Entry<Map<String, Object>, BaseService>>			listTuple;
	private final Set<Map.Entry<Map<String, Object>, BaseService>>			setTuple;
	
	public FinalFieldReceiver() {
		collectionService = new TestList<BaseService>();
		listService = new TestList<BaseService>();
		setService = new TestSet<BaseService>();

		collectionReference = new TestList<ServiceReference<BaseService>>();
		listReference = new TestList<ServiceReference<BaseService>>();
		setReference = new TestSortedSet<ServiceReference<BaseService>>();

		collectionServiceObjects = new TestList<ComponentServiceObjects<BaseService>>();
		listServiceObjects = new TestList<ComponentServiceObjects<BaseService>>();
		setServiceObjects = new TestSet<ComponentServiceObjects<BaseService>>();

		collectionProperties = new TestList<Map<String, Object>>();
		listProperties = new TestList<Map<String, Object>>();
		setProperties = new TestSortedSet<Map<String, Object>>();

		collectionTuple = new TestList<Map.Entry<Map<String, Object>, BaseService>>();
		listTuple = new TestList<Map.Entry<Map<String, Object>, BaseService>>();
		setTuple = new TestSortedSet<Map.Entry<Map<String, Object>, BaseService>>();
	}

	public BaseService getService() {
		return service;
	}

	public Object getAssignable() {
		return assignable;
	}

	public ServiceReference<BaseService> getReference() {
		return reference;
	}

	public ComponentServiceObjects<BaseService> getServiceObjects() {
		return serviceobjects;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public Entry<Map<String, Object>, BaseService> getTuple() {
		return tuple;
	}

	public Collection<BaseService> getCollectionService() {
		return collectionService;
	}

	public List<BaseService> getListService() {
		return listService;
	}

	public Collection<BaseService> getCollectionSubtypeService() {
		return setService;
	}

	public Collection<ServiceReference<BaseService>> getCollectionReference() {
		return collectionReference;
	}

	public List<ServiceReference<BaseService>> getListReference() {
		return listReference;
	}

	public Collection<ServiceReference<BaseService>> getCollectionSubtypeReference() {
		return setReference;
	}

	public Collection<ComponentServiceObjects<BaseService>> getCollectionServiceObjects() {
		return collectionServiceObjects;
	}

	public List<ComponentServiceObjects<BaseService>> getListServiceObjects() {
		return listServiceObjects;
	}

	public Collection<ComponentServiceObjects<BaseService>> getCollectionSubtypeServiceObjects() {
		return setServiceObjects;
	}

	public Collection<Map<String, Object>> getCollectionProperties() {
		return collectionProperties;
	}

	public List<Map<String, Object>> getListProperties() {
		return listProperties;
	}

	public Collection<Map<String, Object>> getCollectionSubtypeProperties() {
		return setProperties;
	}

	public Collection<Entry<Map<String, Object>, BaseService>> getCollectionTuple() {
		return collectionTuple;
	}

	public List<Entry<Map<String, Object>, BaseService>> getListTuple() {
		return listTuple;
	}

	public Collection<Entry<Map<String, Object>, BaseService>> getCollectionSubtypeTuple() {
		return setTuple;
	}
}