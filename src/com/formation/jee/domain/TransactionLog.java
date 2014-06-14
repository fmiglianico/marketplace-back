package com.formation.jee.domain;

public class TransactionLog extends Log {
	private Item item;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
