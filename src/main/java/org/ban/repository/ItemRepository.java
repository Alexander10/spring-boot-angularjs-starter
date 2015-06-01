package org.ban.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.ban.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
