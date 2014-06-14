package com.formation.jee.domain;

import java.util.Date;

import com.formation.jee.common.MarketPlaceAction;


public class Log {
	private Date date;
	private MarketPlaceAction action;
	private String playerLogin;
	private int itemId;
	private int itemQuantity;
	private double playerAccountBalance;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public MarketPlaceAction getAction() {
		return action;
	}
	public void setAction(MarketPlaceAction action) {
		this.action = action;
	}
	public String getPlayerLogin() {
		return playerLogin;
	}
	public void setPlayerLogin(String playerLogin) {
		this.playerLogin = playerLogin;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public double getPlayerAccountBalance() {
		return playerAccountBalance;
	}
	public void setPlayerAccountBalance(double playerAccountBalance) {
		this.playerAccountBalance = playerAccountBalance;
	}
}
