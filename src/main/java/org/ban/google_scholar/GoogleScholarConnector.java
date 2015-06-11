package org.ban.google_scholar;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * User: ban
 * Date: 3. 6. 2015
 * Time: 15:07
 * inspired by http://nwb.cns.iu.edu/svn/nwb/trunk/sci2/plugins/normal_plugins/edu.iu.sci2.reader.googlescholar/src/edu/iu/sci2/reader/googlescholar/search/GoogleScholarReaderHelper.java
 */
@Component
public final class GoogleScholarConnector {

	/**
	 * Connect to the url.
	 *
	 * @param url
	 *            the url
	 * @return the input stream
	 * @throws IOException
	 */
	public InputStream connect(URL url)
			throws IOException {

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setAllowUserInteraction(false);
		connection.setDoOutput(true);
		connection.addRequestProperty("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");

		return connection.getInputStream();
	}





}
