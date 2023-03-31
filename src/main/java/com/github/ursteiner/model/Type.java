package com.github.ursteiner.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Type {

	BLUE(100, 30, "attacks blue enemies"), BROWN(100, 60, "attacks brown enemies"), MIXED(150, 60, "attacks blue and brown enemies"), DETECTOR(150, 60, "detects invisible enemies");

	private final int cost;
	private final int upgradeTime;
	private final String description;
}
