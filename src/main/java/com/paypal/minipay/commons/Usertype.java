package com.paypal.minipay.commons;

public enum Usertype {

	BUYER(1, "buyer"), SELLER(2, "seller");

	int id;
	String desc;

	Usertype(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public static Usertype getById(int id) {
		for (Usertype type : values()) {
			if (type.id == id) {
				return type;
			}
		}
		throw new RuntimeException("Invalid User type");
	}
	
	public static Usertype getByDesc(String uType) {
		for (Usertype type : values()) {
			if (type.toString().equalsIgnoreCase(uType)) {
				return type;
			}
		}
		throw new RuntimeException("Invalid User type");
	}

}
