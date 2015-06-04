package org.ban;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * User: ban
 * Date: 4. 6. 2015
 * Time: 16:29
 */
@SpringBootApplication
public class ServletApplication extends SpringBootServletInitializer {

	private static Class<Application> applicationClass = Application.class;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}
}
