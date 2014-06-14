package com.formation.jee.service;

import java.sql.SQLException;
import java.util.List;

import com.formation.jee.common.MarketPlaceError;
import com.formation.jee.domain.Item;
import com.formation.jee.domain.Player;
import com.formation.jee.domain.PlayerLogin;

public interface PlayerService {

	List<Player> getPlayers();
	
	void addPlayer(PlayerLogin playerLogin) throws SQLException;
	
	String login(PlayerLogin playerLogin) throws SQLException;
	
	Player getPlayer(String token);
	
	MarketPlaceError buy(String token, Item item) throws SQLException;
	
	MarketPlaceError sell(String token, Item item) throws SQLException;

}