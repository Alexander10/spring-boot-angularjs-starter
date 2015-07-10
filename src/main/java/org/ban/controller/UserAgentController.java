package org.ban.controller;

import org.ban.model.UserAgent;
import org.ban.repository.UserAgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User: ban
 * Date: 3. 7. 2015
 * Time: 14:49
 */
@RestController
@RequestMapping("/user-agent")
public class UserAgentController {

	@Autowired
	private UserAgentRepository repository;

	@RequestMapping(method = RequestMethod.GET)
	public List<UserAgent> findItems() {
		return repository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public UserAgent addItem(@RequestBody UserAgent userAgent) {
		return repository.saveAndFlush(userAgent);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public UserAgent updateItem(@RequestBody UserAgent updatedItem, @PathVariable Integer id) {
		updatedItem.setId(id);
		return repository.saveAndFlush(updatedItem);
	}

	@RequestMapping(value= "/{id}", method = RequestMethod.DELETE)
	public void removeItem(@PathVariable Integer id){
		repository.delete(id);
	}


}
