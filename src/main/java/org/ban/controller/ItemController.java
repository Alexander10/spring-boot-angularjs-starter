package org.ban.controller;

import org.ban.google_scholar.GoogleScholarReader;
import org.ban.model.Item;
import org.ban.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/items")
public class ItemController {
	@Autowired
	private ItemRepository repo;

	@Autowired
	@Qualifier("citationReader")
	private GoogleScholarReader reader;

	@RequestMapping(method = RequestMethod.GET)
	public List<Item> findItems() {
		return repo.findAll();
	}

//  @RequestMapping(method = RequestMethod.POST)
//  public Item addItem(@RequestBody Item item) {
//    item.setId(null);
//    return repo.saveAndFlush(item);
//  }
//
//  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//  public Item updateItem(@RequestBody Item updatedItem, @PathVariable Integer id) {
//    updatedItem.setId(id);
//    return repo.saveAndFlush(updatedItem);
//  }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteItem(@PathVariable Integer id) {
		repo.delete(id);
	}


	@RequestMapping(value = "/searchData/{queryString}", method = RequestMethod.GET)
	public List<Item> searchData(@PathVariable String queryString) throws InterruptedException, ExecutionException, IOException {
		System.out.println(queryString);
		// reader.searchData(queryString);
		return null;// repo.findAll();
	}
}
