package org.ban.google_scholar;

import org.ban.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.UnknownHostException;

import static org.junit.Assert.assertFalse;



/**
 * User: ban
 * Date: 9. 6. 2015
 * Time: 13:46
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class GoogleScholarConnectorTest {

	private static final String VALID_URL = "http://www.google.com";

	private static final String INVALID_URL = "http://invalidlink.c";

	@Autowired
	private GoogleScholarConnector connector;

	@Test
	public void testConnect() throws Exception {
		InputStream stream = connector.connect(new URL(VALID_URL));
		System.out.println(stream.available());
		assertFalse(0 == stream.available());

	}

	@Test(expected = UnknownHostException.class)
	public void testUnsucseccfulConnect() throws IOException {
		InputStream stream = connector.connect(new URL(INVALID_URL));

	}
}