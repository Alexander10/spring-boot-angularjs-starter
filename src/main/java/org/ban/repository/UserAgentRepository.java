package org.ban.repository;

import org.ban.model.UserAgent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: ban
 * Date: 3. 7. 2015
 * Time: 15:23
 */

public interface UserAgentRepository extends JpaRepository<UserAgent, Integer> {
}
