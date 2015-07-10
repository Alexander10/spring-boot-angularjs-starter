package org.ban.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * User: ban
 * Date: 11. 6. 2015
 * Time: 15:08
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LookupAgorithmParams {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	private String userAgentDefinition;

	private long lowerBound;

	private long upperBound;
}
