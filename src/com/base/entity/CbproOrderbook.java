package com.base.entity;

import java.awt.List;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonArray;

public class CbproOrderbook {
	
//	"sequence": "3",
//    "bids": [
//        [ price, size, num-orders ],
//        [ "295.96", "4.39088265", 2 ],
//        ...
//    ],
//    "asks": [
//        [ price, size, num-orders ],
//        [ "295.97", "25.23542881", 12 ],
//        ...
//    ]
    public String base;  //ÐéÄâ»õ±Ò
    public String currency;  //»õ±Ò
	public String sequence; 
	public String cbtime;  
	public JSONArray bids;  
	public JSONArray asks;
	
	
	
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
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public JSONArray getBids() {
		return bids;
	}
	public void setBids(JSONArray bids) {
		this.bids = bids;
	}
	public JSONArray getAsks() {
		return asks;
	}
	public void setAsks(JSONArray asks) {
		this.asks = asks;
	}
	public String getCbtime() {
		return cbtime;
	}
	public void setCbtime(String cbtime) {
		this.cbtime = cbtime;
	}

	
	  
	  
	  
	
	
	
	  
	  
	  
	  
	 
}
