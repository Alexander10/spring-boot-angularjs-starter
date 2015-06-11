package org.ban.google_scholar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * User: ban
 * Date: 9. 6. 2015
 * Time: 13:34
 */

public abstract class GoogleScholarReader<Element> {

	@Autowired
	private GoogleScholarConnector connector;

	public abstract Element searchData(String name) throws IOException, InterruptedException, ExecutionException;

	protected Document getHtmlDocument(String name, String googleScholarURL) throws IOException {
		String encodedAuthorName = name.trim().replace(" ", "+");
		return getHtmlDocument(googleScholarURL + encodedAuthorName);
	}

	protected Document getHtmlDocument(String googleScholarURL) throws IOException {
		URL url = new URL(googleScholarURL);
		InputStream iStream = connector.connect(url);

		return Jsoup.parse(iStream, "UTF-8", url.toString());
	}
}
