package com.github.ursteiner.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.awt.Point;

@Data
@AllArgsConstructor
public class Hint {

	private String text;
	private Point p;

}
