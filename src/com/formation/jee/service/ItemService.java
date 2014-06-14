package com.formation.jee.service;

import java.util.List;
import java.util.Set;

import com.formation.jee.domain.Armor;
import com.formation.jee.domain.Consumable;
import com.formation.jee.domain.Item;
import com.formation.jee.domain.Weapon;

public interface ItemService {

	Item get(int id);
	
	Set<Item> getAll();

	Set<Weapon> getAllWeapons();

	Set<Armor> getAllArmors();

	Set<Consumable> getAllConsumables();
	
	List<Item> getInventoryOfPlayer(String login);

}