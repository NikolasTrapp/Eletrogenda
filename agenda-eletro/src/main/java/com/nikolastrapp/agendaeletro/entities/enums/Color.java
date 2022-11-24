package com.nikolastrapp.agendaeletro.entities.enums;

public enum Color {
	
	BLACK("black"),
	RED("red"),
	YELLOW("yellow"),
	GRAY("gray"),
	GREEN("green"),
	BLUE("blue"),
	WHITE("white"),
	PURPLE("purple"),
	PINK("pink"),
	BROWN("brown"),
	ORANGE("orange");
	
	
	private String str;

	private Color(String str) {
		this.str = str;
	}

	public String getColor() {
		return str;
	}

}
