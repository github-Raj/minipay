package com.paypal.minipay.commons;

public enum CURRENCY {
	INR(1, "Indian rupee"),
	USD(64, "United states Dollar"),
	AUD(66, "Australian Dollar"),
	EUR(72, "Euro");
	double value;
	String desc;
	CURRENCY(int value,String desc){
		this.value=value;
		this.desc = desc;
	}
	
	public static CURRENCY getByName(String str) {
		for(CURRENCY curr: values()) {
			if(curr.toString().equalsIgnoreCase(str)) {
				return curr;
			}
		}
		throw new RuntimeException("Invalid urrency");
	}

	public double getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}
	

}
