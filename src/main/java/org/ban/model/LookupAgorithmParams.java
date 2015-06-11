package org.ban.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

/**
 * User: ban
 * Date: 11. 6. 2015
 * Time: 15:08
 */
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LookupAgorithmParams {
	@Id
	private String id;

	private String userAgentDefinition;

	private long lowerBound;

	private long upperBound;
}
