package com.formation.jee.domain;

import java.util.List;

public class Player {
	private PlayerLogin playerLogin;
	
	private List<Item> inventory;
	private double accountBalance;
	
	public Player() {
		playerLogin = new PlayerLogin();
	}
	
	public String getLogin() {
		return playerLogin.getLogin();
	}
	public void setLogin(String login) {
		this.playerLogin.setLogin(login);
	}
	public String getPassword() {
		return playerLogin.getPassword();
	}
	public void setPassword(String password) {
		this.playerLogin.setPassword(password);
	}
	
	public List<Item> getInventory() {
		return inventory;
	}
	public void setInventory(List<Item> inventory) {
		this.inventory = inventory;
	}

	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}


	public static class Builder {
		private Player player;
		
		public Builder() {
			player = new Player();
		}
		
		public Builder login(String login) {
			player.setLogin(login);
			return this;
		}
		public Builder password(String password) {
			player.setPassword(password);
			return this;
		}
		public Builder inventory(List<Item> inventory) {
			player.setInventory(inventory);
			return this;
		}
		public Builder accountBalance(double accountBalance) {
			player.setAccountBalance(accountBalance);
			return this;
		}
		
		public Player build() {
			return player;
		}
	}
	
}
