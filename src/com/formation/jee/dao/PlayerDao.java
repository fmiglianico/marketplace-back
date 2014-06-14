package com.formation.jee.dao;

import java.sql.SQLException;
import java.util.List;

import com.formation.jee.domain.Item;
import com.formation.jee.domain.Player;
import com.formation.jee.domain.PlayerLogin;

public interface PlayerDao {

	List<Player> getAll();
	
	void add(PlayerLogin playerLogin) throws SQLException;
	
	String login(PlayerLogin playerLogin);
	
	Player get(String token);
	
	void buy(Player player, Item item);
	
	void sell(Player player, Item item);
	
	void updateBalance(Player player);

}