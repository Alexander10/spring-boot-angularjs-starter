package org.ban.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * User: ban
 * Date: 4. 6. 2015
 * Time: 16:31
 */

@Configuration
@EnableMongoRepositories(basePackages = "org.ban.repository")
@EnableMongoAuditing
public class MongoDBConfiguration {
}
