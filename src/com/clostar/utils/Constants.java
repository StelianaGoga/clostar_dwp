package com.clostar.utils;

public class Constants {
	public final static String USER_ID = "userId";
	public final static String USER = "user";
	public final static String TIME = "timestamp";
	public final static String TARGET = "targetAction";
	public final static String CART = "cart";
	public final static String CART_SIZE = "cart_size";
	public final static String CART_PRICE = "cart_price";
	public final static String FAVS_SIZE = "favs_size";

	public final static String[] productTypesNameEng = {
		"Dresses", "Jackets", "Jeans", "Tops", "Skirts", "Shirts",
		"T-shirts", "Trousers", "Coats", "Shoes", "All", "Bottoms",
		"Full Costumes", "Shoes", "Sandals", "Moccasins", "Sport Shoes",
		"Boots"
	};
	
	public final static String[] productTypesNameRo = {
        "Rochii", "Jachete", "Blugi", "Topuri", "Fuste", "Camasi",
        "Tricouri", "Pantaloni", "Geci", "Incaltaminte", "Toate", "Bottoms",
        "Costume intregi", "Pantofi", "Sandale", "Mocasini", "Pantofi Sport", 
        "Ghete"
	};
	
	public enum Rates{
		EUR(1),
		GBP(1.36),
		RON(0.23),
		USD(0.88);

        private double value;

        Rates(double value) {
            this.value = value;
        }

        public double doubleValue() {
            return value;
        }
	}
	
	public enum Genders{
		FEMALE(1),
		MALE(2),
		CHILDREN(3),
		ALL(4);

        private int value;

        Genders(int value) {
            this.value = value;
        }

        public int intValue() {
            return value;
        }
	}
	
	public enum ProductsTypes{
		DRESSES(0),
		JACKETS(1),
		JEANS(2),
		TOPS(3),
		SKIRTS(4),
		SHIRTS(5),
		T_SHIRTS(6),
		TROUSERS(7),
		COATS(8),
		ALL_SHOES(9),
		ALL(10),
		BOTTOMS(11),
		FULL(12),
		SHOES(13),
		SANDALS(14),
		MOCCASINS(15),
		SPORT_SHOES(16),
		BOOTS(17);

        private int value;

        ProductsTypes(int value) {
            this.value = value;
        }

        public int intValue() {
            return value;
        }
	}
	
	public enum Currency{
		EURO(0),
		GBP(1),
		RON(2),
		USD(3);

        private int value;

        Currency(int value) {
            this.value = value;
        }

        public int intValue() {
            return value;
        }
	}
}
