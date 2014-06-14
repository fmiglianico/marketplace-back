package com.formation.jee.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="consumable")
public class Consumable extends Item {
	private String effect;

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}
}
