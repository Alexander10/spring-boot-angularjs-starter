package org.ban.util;

import prefuse.data.Table;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * User: ban
 * Date: 3. 6. 2015
 * Time: 16:11
 */

public class AuthorSearch {
	private int countSingleAuthorFound = 0;
	private int countMultipleAuthorFound = 0;
	private int countAuthorNotFound = 0;

	/**
	 *
	 * @param uniqueAuthors A list of <b>unique</b> author names.

	 */
	public AuthorSearch(Set<String> uniqueAuthors) {
	//	this.authorInformationTable = getAuthorInformation(uniqueAuthors);
	}

	/**
	 * Download the author information and put it into a table.
	 */
	private Table getAuthorInformation(Set<String> uniqueAuthors) {

		AuthorRecordMergeTable mergeTable = new AuthorRecordMergeTable();

		for (String authorName : uniqueAuthors) {

			Collection<AuthorRecord> resultAuthorList = searchMatchedAuthors(authorName);

			if (resultAuthorList.isEmpty()) {

				countAuthorNotFound++;
			} else {
				if (resultAuthorList.size() == 1) {
					countSingleAuthorFound++;
				} else {
					countMultipleAuthorFound++;
				}

				/* Add records into merge table */
				for (AuthorRecord record : resultAuthorList) {
					mergeTable.addAuthorRecord(authorName, record);
				}
			}
		}
		return mergeTable.getTable();
	}

	/**
	 * @return A list of {@link AuthorRecord}s found from the {@code authorName}
	 *         or an empty list if an error occurs.

	 */
	private static Collection<AuthorRecord> searchMatchedAuthors(
			String authorName) {
		try {
			return GoogleScholarReaderHelper.searchAuthor(authorName);
		} catch (IOException e) {
			return Collections.emptyList();
		}
	}



	/**
	 * Gets the count single author found.
	 *
	 * @return the count single author found
	 */
	public int getCountSingleAuthorFound() {
		return countSingleAuthorFound;
	}

	/**
	 * Gets the count multiple author found.
	 *
	 * @return the count multiple author found
	 */
	public int getCountMultipleAuthorFound() {
		return countMultipleAuthorFound;
	}

	/**
	 * Gets the count author not found.
	 *
	 * @return the count author not found
	 */
	public int getCountAuthorNotFound() {
		return countAuthorNotFound;
	}
}