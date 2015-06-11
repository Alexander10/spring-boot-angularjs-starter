package org.ban.google_scholar;

import java.util.List;

/**
 * User: ban
 * Date: 5. 6. 2015
 * Time: 9:48
 */

public class CitationRecord {
	private String title;
	private String authors;
	private Integer cntOfCitations;
	private Integer year;

	private List<CitationRecord> citedBy;

	public CitationRecord(String title, String authors, Integer cntOfCitations,
						  Integer year, List<CitationRecord> citedBy) {
		this.title = title;
		this.authors = authors;
		this.cntOfCitations = cntOfCitations;
		this.year = year;
		this.citedBy = citedBy;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthors() {
		return authors;
	}

	public Integer getCntOfCitations() {
		return cntOfCitations;
	}

	public Integer getYear() {
		return year;
	}
}
