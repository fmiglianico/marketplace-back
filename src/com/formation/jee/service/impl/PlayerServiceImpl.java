package com.formation.jee.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.formation.jee.common.MarketPlaceAction;
import com.formation.jee.common.MarketPlaceError;
import com.formation.jee.dao.DaoManager;
import com.formation.jee.dao.LogDao;
import com.formation.jee.dao.PlayerDao;
import com.formation.jee.domain.Item;
import com.formation.jee.domain.Log;
import com.formation.jee.domain.Player;
import com.formation.jee.domain.PlayerLogin;
import com.formation.jee.service.ItemService;
import com.formation.jee.service.PlayerService;
import com.formation.jee.service.ServiceManager;

public class PlayerServiceImpl implements PlayerService {
		
	private PlayerDao playerDao;
	
	private LogDao logDao;
	
	private ItemService itemService;
	
	public PlayerServiceImpl() {
		playerDao = DaoManager.INSTANCE.getPlayerDao();
		logDao = DaoManager.INSTANCE.getLogDao();
		itemService = ServiceManager.INSTANCE.getItemService();
	}
	
	/* (non-Javadoc)
	 * @see com.formation.jee.service.PlayerService#getPlayers()
	 */
	@Override
	public List<Player> getPlayers() {
		return playerDao.getAll();
	}

	@Override
	public void addPlayer(PlayerLogin playerLogin) throws SQLException {
		playerDao.add(playerLogin);
		Log log = new Log();
		log.setAction(MarketPlaceAction.ACCOUNT_CREATION);
		log.setDate(new Date());
		log.setPlayerLogin(playerLogin.getLogin());
		log.setPlayerAccountBalance(500.0);
		logDao.persist(log);
		
	}

	@Override
	public String login(PlayerLogin playerLogin) throws SQLException {
		String token = playerDao.login(playerLogin);
		Log log = new Log();
		log.setAction(MarketPlaceAction.PLAYER_LOG_IN);
		log.setDate(new Date());
		log.setPlayerLogin(playerLogin.getLogin());
		logDao.persist(log);
		return token;
	}

	@Override
	public Player getPlayer(String token) {
		Player player = playerDao.get(token);
		player.setInventory(itemService.getInventoryOfPlayer(player.getLogin()));
		return player;
	}

	@Override
	public MarketPlaceError buy(String token, Item item) throws SQLException {
		Player player = getPlayer(token);
		
		if(player.getAccountBalance() >= item.getPrice()*item.getQuantity()) {
			playerDao.buy(player, item);
			double newBalance = player.getAccountBalance() - item.getPrice()*item.getQuantity();
			player.setAccountBalance(newBalance);
			playerDao.updateBalance(player);
			
			Log log = new Log();
			log.setAction(MarketPlaceAction.PLAYER_BUY_ITEM);
			log.setDate(new Date());
			log.setPlayerLogin(player.getLogin());
			log.setItemId(item.getId());
			log.setItemQuantity(item.getQuantity());
			log.setPlayerAccountBalance(newBalance);
			logDao.persist(log);
			
		} else {
			return MarketPlaceError.ITEM_TOO_EXPENSIVE;
		}
		return null;
	}

	@Override
	public MarketPlaceError sell(String token, Item item) throws SQLException {
		Player player = getPlayer(token);
		List<Item> inventory = player.getInventory();
		
		if(inventory != null && inventory.contains(item) && inventory.get(inventory.indexOf(item)).getQuantity() >= item.getQuantity()) {
			playerDao.sell(player, item);
			double newBalance = player.getAccountBalance() + item.getPrice()*item.getQuantity();
			player.setAccountBalance(newBalance);
			playerDao.updateBalance(player);
			
			Log log = new Log();
			log.setAction(MarketPlaceAction.PLAYER_SELL_ITEM);
			log.setDate(new Date());
			log.setPlayerLogin(player.getLogin());
			log.setItemId(item.getId());
			log.setItemQuantity(item.getQuantity());
			log.setPlayerAccountBalance(newBalance);
			logDao.persist(log);
		} else {
			return MarketPlaceError.ITEM_NOT_IN_INVENTORY;
		}
		return null;
	}

}
