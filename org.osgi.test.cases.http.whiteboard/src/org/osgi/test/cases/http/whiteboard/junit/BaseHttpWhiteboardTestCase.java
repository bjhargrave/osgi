package org.osgi.test.cases.http.whiteboard.junit;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.osgi.framework.Bundle;
import org.osgi.test.support.OSGiTestCase;

public abstract class BaseHttpWhiteboardTestCase extends OSGiTestCase {

	protected abstract String[] getBundlePaths();

	protected void setUp() throws Exception {
		for (String bundlePath : getBundlePaths()) {
			Bundle bundle = install(bundlePath);

			bundle.start();

			bundles.add(bundle);
		}
	}

	protected void tearDown() throws Exception {
		for (Bundle bundle : bundles) {
			bundle.uninstall();
		}

		bundles.clear();
	}

	protected String drainInputStream(InputStream is) throws IOException {
		byte[] bytes = new byte[1024];

		StringBuffer buffer = new StringBuffer(1024);

		int length;
		while ((length = is.read(bytes)) != -1) {
			String chunk = new String(bytes, 0, length);
			buffer.append(chunk);
		}
		return buffer.toString().trim();
	}

	protected String request(String path) throws InterruptedException, IOException {
		URL serverURL = getServerURL(path);

		log("Requesting: " + serverURL.toString()); //$NON-NLS-1$

		HttpURLConnection connection = (HttpURLConnection) serverURL.openConnection();

		connection.setInstanceFollowRedirects(false);
		connection.setConnectTimeout(150 * 1000);
		connection.setReadTimeout(150 * 1000);
		connection.connect();

		int responseCode = connection.getResponseCode();

		InputStream stream;

		for (int i = 0; i < 9; i++) {
			if (responseCode >= 400) {
				if (i < 8) {
					connection.connect();

					responseCode = connection.getResponseCode();

					Thread.sleep(100);
				}
				else {
					stream = connection.getErrorStream();
				}
			}
			else {
				stream = connection.getInputStream();
			}
		}

		if (responseCode >= 400) {
			stream = connection.getErrorStream();
		}
		else {
			stream = connection.getInputStream();
		}

		try {
			return drainInputStream(stream);
		} finally {
			stream.close();
		}
	}

	protected Map<String, List<String>> request(String path, Map<String, List<String>> headers) throws InterruptedException, IOException {
		URL serverURL = getServerURL(path);

		log("Requesting: " + serverURL.toString()); //$NON-NLS-1$

		HttpURLConnection connection = (HttpURLConnection) serverURL.openConnection();

		if (headers != null) {
			for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
				for (String entryValue : entry.getValue()) {
					connection.setRequestProperty(entry.getKey(), entryValue);
				}
			}
		}

		int responseCode = connection.getResponseCode();

		Map<String, List<String>> map = new HashMap<String, List<String>>(connection.getHeaderFields());
		map.put("responseCode", Collections.singletonList(String.valueOf(responseCode)));

		InputStream stream;

		if (responseCode >= 400) {
			stream = connection.getErrorStream();
		}
		else {
			stream = connection.getInputStream();
		}

		try {
			map.put("responseBody", Arrays.asList(drainInputStream(stream)));

			return map;
		} finally {
			stream.close();
		}
	}

	protected void log(String message) {
		System.out.println(message);
	}

	protected URL getServerURL(String path) throws MalformedURLException {
		if (!path.startsWith("/")) {
			path = "/" + path;
		}

		return new URL(
				"http", getProperty("org.apache.felix.http.host"),
				getIntegerProperty("org.osgi.service.http.port", 8080), path);
	}

	protected List<Bundle>	bundles	= new ArrayList<Bundle>();

}