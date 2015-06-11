package org.ban.google_scholar;

import org.ban.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * User: ban
 * Date: 9. 6. 2015
 * Time: 14:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class GoogleScholarAuthorReaderTest {

	@Test
	public void testSearchData() throws Exception {

	}

	@Test
	public void testGetUniqueAuthors() throws Exception {

	}
}