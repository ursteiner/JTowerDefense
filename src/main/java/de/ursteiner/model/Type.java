package de.ursteiner.model;

public enum Type {
	// TODO change prize for detector towers
	BLUE(100, 30, "attacks blue attackers"), BROWN(100, 60, "attacks brown attackers"), MIXED(150, 60, "attacks blue and brown attackers"), DETECTOR(150, 60, "detects invisible attackers");

	private int cost;
	private int upgradeTime;
	private String description;

	private Type(int cost, int upgradeTime, String description) {
		this.cost = cost;
		this.upgradeTime = upgradeTime;
		this.description = description;
	}

	public int getCost() {
		return cost;
	}

	public int getUpgradeTime() {
		return upgradeTime;
	}

	public String getDescription() {
		return description;
	}

}
