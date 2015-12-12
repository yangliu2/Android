package com.panzoto.android;

public class Card {

	// enum for suits
	public enum Suit {
		SPADES, HEARTS, DIAMONDS, CLUBS
	}

	// enum for rank
	public enum Rank {
		TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
	}

	Suit suit;
	Rank rank;
	int value;

	public Card(Suit suit, Rank rank, int value) {
		this.suit = suit;
		this.rank = rank;
		this.value = value;
	}

}
