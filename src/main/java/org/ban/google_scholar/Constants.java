package org.ban.google_scholar;

/**
 * User: ban
 * Date: 9. 6. 2015
 * Time: 13:25
 */

public class Constants {

	/** The Constant GCR_SEARCH_AUTHOR_URL. */
	public static final String GCR_SEARCH_AUTHOR_URL =
			"http://scholar.google.com/citations?hl=en&view_op=search_authors&mauthors=";


	public static final String GOOGLE_SCHOLAR_SEARCH_PAPERS_URL = "https://scholar.google.com/scholar?as_sdt=0,5&q=";

	public static final String GOOGLE_SCHOLAR_BASE_URL = "https://scholar.google.com";

	/** The Constant USER_URL_START_INDEX. */
	public static final int USER_URL_START_INDEX = 16;

	/** The Constant USER_URL_END_INDEX. */
	public static final int USER_URL_END_INDEX = 28;

	/** Email. */
	public static final String EMAIL_INFO = "Verified email at";

	public static final String CITED_INFO = "Cited by";

	public static final String CSS_PATH_ARTICLE_TITLE = ".gs_rt a";

	public static final String CSS_PATH_ARTICLE_AUTHORS = ".gs_a";

	public static final String CSS_PATH_CITATIONS_LINK = ".gs_fl a:first-child";

	public static final String CSS_PATH_PAGER = "gs_n";


	public static final String CITATION_RECORD_WRAPPER_CLASS = ".gs_ri"; // .gs_fl > a:first-child


	/** The class for the author element in the DOM. **/
	public static final String AUTHOR_ELEMENT_CLASS = "cit-dark-large-link";
}
