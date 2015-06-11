package org.ban.google_scholar;

/**
 * User: ban
 * Date: 5. 6. 2015
 * Time: 9:45
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * CitationTableExtractor.
 *
 * Accepts one author name as a parameter and extracts the "Title", "Author",
 * "Cited by", and "Year" data shown for each document on that author's Google
 * Scholar page.
 *
 * Based on the spec at http://scholar.google.com/robots.txt clawling on google
 * scholar only allowed for URL with 'citations?user=' and '/citations?view_op=new_profile'
 * Also read Citation Export at http://scholar.google.com/scholar/help.html
 * For more information, see http://scholar.google.com/intl/en/scholar/inclusion.html
 *
 * Author: P632
 *
 * Code Review: P632
 */
public final class CitationTableExtractor {



	/** The Constant pageSize. */
	private static final int PAGE_SIZE = 100;

	/** The Constant ASCII_0. */
	private static final int ASCII_0 = 48;

	/** The Constant ASCII_9. */
	private static final int ASCII_9 = 57;

	/** The Constant GCR_FETCH_CITATIONS_URL_1. */
	private static final String GCR_FETCH_CITATIONS_URL_1 =
			"http://scholar.google.com/citations?user=";

	/** The Constant GCR_FETCH_CITATIONS_NEXT_PAGE. */
	private static final String GCR_FETCH_CITATIONS_NEXT_PAGE =
			"&hl=en&pagesize=" + PAGE_SIZE + "&view_op=list_works&cstart=";

	/** The HTML elements' id and CSS class. */
	private static final String HTML_CITATION_TABLE_CLASS = "cit-table";
	private static final String HTML_CITATION_LINK_CLASS = "cit-dark-large-link";
	private static final String HTML_CITATION_GRAY_CLASS = "cit-gray";
	private static final String HTML_CITATION_TITLE_COLUMN_ID = "col-title";
	private static final String HTML_CITED_BY_COLUMN_ID = "col-citedby";
	private static final String HTML_YEAR_COLUMN_ID = "col-year";

	/**
	 * Instantiates a new citation table extractor.
	 */
	private CitationTableExtractor() {
		// Utility class. Do not instantiate.
	}

	/**
	 * Gets the citations.
	 *
	 * @param userId
	 *            the userId

	 * @return the citations table

	 * @throws IOException
	 */
	public static List getCitations(String userId)
			throws  IOException {
		int nextPageCitationsStart = 0;
		List<CitationRecord> recordTable = new ArrayList<>();


		URL url = getCitationPageURL(userId, nextPageCitationsStart);
		InputStream iStream = null;// GoogleScholarConnector.connect(url);

		boolean doneReading = (iStream == null);
		while (!doneReading) {

			// Jsoup:Java HTML Parser
			Document htmldoc = Jsoup.parse(iStream, "UTF-8", url.toString());
			Elements table = htmldoc.getElementsByClass(HTML_CITATION_TABLE_CLASS);
			for (int i = 2; i < table.size(); i++) {

				Element trElement = table.get(i);
				String title = trElement.getElementById(HTML_CITATION_TITLE_COLUMN_ID)
						.getElementsByClass(HTML_CITATION_LINK_CLASS).text();
				Elements subElement = trElement.getElementById(HTML_CITATION_TITLE_COLUMN_ID)
						.getElementsByClass(HTML_CITATION_GRAY_CLASS);
				String authors = subElement.first().text();

				// Check if cited by entry is present
				Integer citedBy = 0;
				if (trElement.getElementById(HTML_CITED_BY_COLUMN_ID).hasText()) {
					citedBy = Integer
							.parseInt(removeInvalidCharInNumber(trElement
									.getElementById(HTML_CITED_BY_COLUMN_ID).text()));
				}

				// Check if year entry is present
				Integer year = 0;
				if (trElement.getElementById(HTML_YEAR_COLUMN_ID).hasText()) {
					year = Integer
							.parseInt(removeInvalidCharInNumber(trElement
									.getElementById(HTML_YEAR_COLUMN_ID).text()));
				}

				if (citedBy == 0) {
					citedBy = null;
				}


				if (year == 0) {
					year = null;
				}

				recordTable.add(
						null);
			}

			/* If there is next page */
			if (htmldoc.html().contains("Next")) {
				nextPageCitationsStart += PAGE_SIZE;
				url = getCitationPageURL(userId, nextPageCitationsStart);
				//iStream = GoogleScholarConnector.connect(url);
			} else {
				doneReading = true;
			}
		}

		return recordTable;
	}

	/**
	 * Gets the author citation.
	 *
	 * @param userId
	 *            the author name

	 * @return the author's citationTableMap

	 * @throws IOException
	 */
	public static List getAuthorCitation(String userId)
			throws IOException {
		// get citations from google scholar
		List citationsTable = getCitations(userId);

		return citationsTable;
	}

	private static URL getCitationPageURL(String userId, int nextPageCitationsStart)
			throws MalformedURLException {
		String urllink = GCR_FETCH_CITATIONS_URL_1
				+ userId
				+ GCR_FETCH_CITATIONS_NEXT_PAGE
				+ nextPageCitationsStart;
		return new URL(urllink);
	}

	/**
	 * Removes the invalid char in number.
	 *
	 * @param numberText
	 *            the number text
	 * @return the string
	 */
	private static String removeInvalidCharInNumber(String numberText) {
		// SOMEDAY maybe you want to check for unicode characters?
		for (int i = 0; i < numberText.length(); i++) {
			if (numberText.charAt(i) < ASCII_0
					|| numberText.charAt(i) > ASCII_9) {
				numberText = numberText.substring(0, i)
						+ numberText.substring(i + 1);
			}
		}
		return numberText;
	}
}