package org.ban.google_scholar;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * User: ban
 * Date: 9. 6. 2015
 * Time: 13:20
 */
@Service(value = "citationReader")
public class GoogleScholarCitationReaderService extends GoogleScholarReader<CitationRecord> {

	@Override
	public CitationRecord searchData(String name) throws IOException, InterruptedException, ExecutionException {
		Document htmlDoc = getHtmlDocument(name, Constants.GOOGLE_SCHOLAR_SEARCH_PAPERS_URL);
		Elements citation = htmlDoc.select(Constants.CITATION_RECORD_WRAPPER_CLASS);
		CitationRecord record = null;

		if (citation.size() > 0) {

			String title = readArticleTitle(citation.get(0));
			String authors = readAuthors(citation.get(0));
			String link = readLinkToCitations(citation.get(0));
			Integer cntOfCitations = readCntOfCitations(citation.get(0));

			Future<List<CitationRecord>> records = null;
			if (!link.isEmpty()) {
				records = readCitations(link, cntOfCitations);
				while (!records.isDone()) {
					Thread.sleep(100);
				}
			}
			record = new CitationRecord(title, authors, cntOfCitations, 100, records.get());
		}

		return record;
	}


	private String readArticleTitle(Element element) {
		return element.select(Constants.CSS_PATH_ARTICLE_TITLE).text();
	}

	private String readAuthors(Element element) {
		String authors = element.select(Constants.CSS_PATH_ARTICLE_AUTHORS).text();
		return authors;
	}

	private String readLinkToCitations(Element element) {
		String citedBy = element.select(Constants.CSS_PATH_CITATIONS_LINK).text();
		if (citedBy.contains(Constants.CITED_INFO)) {
			return element.select(Constants.CSS_PATH_CITATIONS_LINK).attr("href");
		}
		return "";
	}

	private Integer readCntOfCitations(Element element) {
		String citedBy = element.select(Constants.CSS_PATH_CITATIONS_LINK).text();
		if (citedBy.contains(Constants.CITED_INFO)) {
			String text = element.select(Constants.CSS_PATH_CITATIONS_LINK).text();
			return Integer.parseInt(text.replaceAll("[\\D]", ""));
		}
		return 0;
	}

	private String readNextPageURL() {
		 Elements pager =
	}

	/**
	 * Think about paging
	 *
	 * @param query
	 * @return
	 * @throws IOException
	 */
	@Async
	private Future<List<CitationRecord>> readCitations(String query, int cntOfCitations) throws IOException {
		List<CitationRecord> records = new ArrayList<>();
		int page = 0;
		String url = Constants.GOOGLE_SCHOLAR_BASE_URL + query;

		for (int i = 0; i < cntOfCitations; i = page * 10) {

			Document htmlDoc = getHtmlDocument(url);
			Elements citations = htmlDoc.select(Constants.CITATION_RECORD_WRAPPER_CLASS);

			if (citations.size() > 0) {
				for (Element citation : citations) {
					String title = readArticleTitle(citation);
					String authors = readAuthors(citation);
					records.add(new CitationRecord(title, authors, 0, 0, null));
				}
			}

			page++;
		}
		return new AsyncResult<>(records);
	}
}
