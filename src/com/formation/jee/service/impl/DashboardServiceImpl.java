package com.formation.jee.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.formation.jee.common.MarketPlaceAction;
import com.formation.jee.dao.DaoManager;
import com.formation.jee.dao.LogDao;
import com.formation.jee.domain.Item;
import com.formation.jee.domain.Log;
import com.formation.jee.domain.TransactionLog;
import com.formation.jee.service.DashboardService;
import com.formation.jee.service.ItemService;
import com.formation.jee.service.ServiceManager;

public class DashboardServiceImpl implements DashboardService {
	
	private LogDao logDao;
	
	private ItemService itemService;
	
	public DashboardServiceImpl() {
		logDao = DaoManager.INSTANCE.getLogDao();
		itemService = ServiceManager.INSTANCE.getItemService();
	}

	@Override
	public List<Log> getLogs() throws SQLException {
		List<Log> logs = logDao.getAll();
		List<Log> ret = new ArrayList<Log>();
		for(Log log : logs) {
			MarketPlaceAction mke = log.getAction();
			if(mke.equals(MarketPlaceAction.PLAYER_BUY_ITEM) ||
					mke.equals(MarketPlaceAction.PLAYER_SELL_ITEM)) {
				Item item = itemService.get(log.getItemId());
				TransactionLog transactionLog = new TransactionLog();
				transactionLog.setItem(item);
				transactionLog.setAction(mke);
				transactionLog.setDate(log.getDate());
				transactionLog.setItemQuantity(log.getItemQuantity());
				transactionLog.setPlayerAccountBalance(log.getPlayerAccountBalance());
				transactionLog.setPlayerLogin(log.getPlayerLogin());
				
				ret.add(transactionLog);
			} else {
				ret.add(log);
			}
		}
		return ret;
	}
		
}
