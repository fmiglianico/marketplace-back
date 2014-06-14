package com.formation.jee.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.formation.jee.dao.ArmorDao;
import com.formation.jee.dao.ConsumableDao;
import com.formation.jee.dao.DaoManager;
import com.formation.jee.dao.WeaponDao;
import com.formation.jee.domain.Armor;
import com.formation.jee.domain.Consumable;
import com.formation.jee.domain.Item;
import com.formation.jee.domain.Weapon;
import com.formation.jee.service.ItemService;

public class ItemServiceImpl implements ItemService {
		
	private ArmorDao armorDao;
	
	private WeaponDao weaponDao;
	
	private ConsumableDao consumableDao;
	
	public ItemServiceImpl() {
		armorDao = DaoManager.INSTANCE.getArmorDao();
		weaponDao = DaoManager.INSTANCE.getWeaponDao();
		consumableDao = DaoManager.INSTANCE.getConsumableDao();
	}

	@Override
	public Item get(int id) {
		Item item = null;
		if((item = weaponDao.get(id)) != null)
			return item;
		if((item = armorDao.get(id)) != null)
			return item;
		if((item = consumableDao.get(id)) != null)
			return item;
		return null;
	}

	@Override
	public Set<Item> getAll() {
		Set<Item> items = new HashSet<>();
		items.addAll(armorDao.getAll());
		items.addAll(weaponDao.getAll());
		items.addAll(consumableDao.getAll());
		return items;
		
	}

	@Override
	public List<Item> getInventoryOfPlayer(String login) {
		List<Item> inventory = new ArrayList<Item>();
		inventory.addAll(armorDao.getArmorsOfPlayer(login));
		inventory.addAll(weaponDao.getWeaponsOfPlayer(login));
		inventory.addAll(consumableDao.getConsumablesOfPlayer(login));
		return inventory;
	}

	@Override
	public Set<Weapon> getAllWeapons() {
		return weaponDao.getAll();
	}

	@Override
	public Set<Armor> getAllArmors() {
		return armorDao.getAll();
	}

	@Override
	public Set<Consumable> getAllConsumables() {
		return consumableDao.getAll();
	}
	

}
