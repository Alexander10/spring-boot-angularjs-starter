package org.ban.google_scholar;

import org.ban.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

/**
 * User: ban
 * Date: 9. 6. 2015
 * Time: 14:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class GoogleScholarCitationReaderTest {


	private static final String PAPER_NAME = "Advanced scale-space, invariant, low detailed feature recognition from images-car brand recognition";
	private static final String INVALID_PAPER_NAME = "dafdksdjfoidjflfdsfds";
	private static final String PAPER_WITH_MANY_CITATIONS = "Neural networks";

	@Autowired
	@Qualifier("citationReader")
	private GoogleScholarReader reader;

	@Test
	public void testSearchData() throws Exception {
		 CitationRecord record = (CitationRecord) reader.searchData(PAPER_NAME);
	}

	@Test
	public void invalidPaperSearchData() throws IOException, ExecutionException, InterruptedException {
		CitationRecord record = (CitationRecord) reader.searchData(INVALID_PAPER_NAME);
		assertNull(record);
	}

	@Test
	public void paggingTestPaperSearchData() throws IOException, ExecutionException, InterruptedException {
		CitationRecord record = (CitationRecord) reader.searchData(PAPER_WITH_MANY_CITATIONS);
	}


}