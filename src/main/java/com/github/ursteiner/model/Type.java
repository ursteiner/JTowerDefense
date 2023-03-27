package com.github.ursteiner.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Type {
	// TODO change prize for detector towers
	BLUE(100, 30, "attacks blue attackers"), BROWN(100, 60, "attacks brown attackers"), MIXED(150, 60, "attacks blue and brown attackers"), DETECTOR(150, 60, "detects invisible attackers");

	private int cost;
	private int upgradeTime;
	private String description;
}
