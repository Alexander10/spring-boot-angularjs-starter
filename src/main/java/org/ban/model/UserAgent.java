package org.ban.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * User: ban
 * Date: 3. 7. 2015
 * Time: 14:49
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAgent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String version;

	private String operatingSystem;


}
