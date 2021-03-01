package com.base.entity;

public class BitstampOhlc {
	
	public String base;  //ÐéÄâ»õ±Ò
	public String currency;  //»õ±Ò
	public String timestamp;
    public String timeperiod;  
	public String volume;
	public String low;
	public String high;
	public String open;
	public String close;
	
	
	
	
	public String getTimeperiod() {
		return timeperiod;
	}
	public void setTimeperiod(String timeperiod) {
		this.timeperiod = timeperiod;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
	}
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getClose() {
		return close;
	}
	public void setClose(String close) {
		this.close = close;
	}
	
	
	
}
