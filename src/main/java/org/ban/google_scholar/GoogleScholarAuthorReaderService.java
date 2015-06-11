package org.ban.google_scholar;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: ban
 * Date: 9. 6. 2015
 * Time: 13:20
 */
@Component
public class GoogleScholarAuthorReaderService extends GoogleScholarReader<List<AuthorRecord>> {


	/**
	 * @param authorName the author name
	 * @throws IOException If the URL cannot be read or connected to, or the downloaded
	 *                     HTML cannot be parsed.
	 */
	@Override
	public List<AuthorRecord> searchData(String authorName) throws IOException {
		Document htmldoc = getHtmlDocument(authorName, Constants.GCR_SEARCH_AUTHOR_URL);

		Elements authorElements = htmldoc
				.getElementsByClass(Constants.AUTHOR_ELEMENT_CLASS);
		List<AuthorRecord> records = new ArrayList<AuthorRecord>();

		for (Element authorElement : authorElements) {

			String name = getAuthorName(authorElement);
			String userId = getUserId(authorElement);

			String authorInfo = authorElement.parent().text();
			authorInfo = authorInfo.replace(name + " ", "");

			int emailIndex = authorInfo.indexOf(Constants.EMAIL_INFO);
			int citedByIndex = authorInfo.indexOf(Constants.CITED_INFO);

			String university = getUniversity(authorInfo, emailIndex);
			String email = getEmail(authorInfo, emailIndex, citedByIndex);
			String citedBy = getCitedBy(authorInfo, citedByIndex);

			AuthorRecord record = new AuthorRecord(name, userId, university,
					email, citedBy, authorName);

			records.add(record);
		}

		return records;
	}

	/**
	 * @param authorElement
	 * @return Author name returned by Google Scholar
	 */
	private String getAuthorName(Element authorElement) {
		return authorElement.text().trim();
	}

	/**
	 * @param authorElement
	 * @return Google citation user id
	 */
	private String getUserId(Element authorElement) {

		String userIdUrL = authorElement.attr("href");
		String userId = userIdUrL.substring(
				userIdUrL.indexOf("/citations?user=") + Constants.USER_URL_START_INDEX,
				userIdUrL.indexOf("/citations?user=") + Constants.USER_URL_END_INDEX);

		return userId;

	}

	/**
	 * Gets the email.
	 *
	 * @param authorInfo   the author info
	 * @param emailIndex   the email index
	 * @param citedByIndex the cited by index
	 * @return the email
	 */
	private String getEmail(String authorInfo, int emailIndex,
							int citedByIndex) {

		String email = null;

		if (emailIndex != -1 && citedByIndex != -1) {
			email = authorInfo.substring(emailIndex + Constants.EMAIL_INFO.length(),
					citedByIndex).trim();
		} else if (emailIndex != -1 && citedByIndex == -1) {
			email = authorInfo.substring(emailIndex + Constants.EMAIL_INFO.length(),
					authorInfo.length()).trim();
		}

		return email;

	}

	/**
	 * Gets the cited by.
	 *
	 * @param authorInfo   the author info
	 * @param citedByIndex the cited by index
	 * @return the cited by
	 */
	private String getCitedBy(String authorInfo, int citedByIndex) {
		String citedBy = null;
		if (citedByIndex != -1) {
			citedBy = authorInfo.substring(citedByIndex + Constants.CITED_INFO.length(),
					authorInfo.length()).trim();
		}
		return citedBy;
	}

	/**
	 * Gets the university.
	 *
	 * @param authorInfo the author info
	 * @param emailIndex the email index
	 * @return the university
	 */
	private String getUniversity(String authorInfo, int emailIndex) {
		if (emailIndex == -1) {
			return authorInfo;
		} else {
			return authorInfo.substring(0, emailIndex).trim();
		}
	}


	/**
	 * Gets the unique authors.
	 *
	 * @param delimiter The delimiter for values in the {@code authorColumnName} or
	 *                  {@code null} if no delimiter should be used.
	 * @return the unique authors
	 */
	public Set<String> getUniqueAuthors(String authorColumnName, String delimiter) {

		Set<String> uniqueAuthors = new HashSet<String>();
//		Iterator<?> rows = table.tuples();
//
//		while (rows.hasNext()) {
//			Tuple row = (Tuple) rows.next();
//
//			if (row.canGetString(authorColumnName)) {
//				String authorString = row.getString(authorColumnName);
//
//				if (delimiter == null) {
//					uniqueAuthors.add(authorString);
//				} else {
//					String escapedDelimiter = "\\" + delimiter;
//					String[] authorNames = authorString.split(escapedDelimiter);
//					for (String authorName : authorNames) {
//						uniqueAuthors.add(authorName);
//					}
//				}
//			}
//		}

		return uniqueAuthors;
	}

}
