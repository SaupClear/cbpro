package com.base.entity;

public class CatTradeRecord {
	
	//交易记录
	
	public int tid;
	public int bid;//机组ID
	public String uid;//单元ID
	public String orderid;//order ID
	public String tbase;//交易币种
	public String tcount;//交易数量
	public String tvalue;//交易金额
	public String tprice;//交易价格
	public String tradefee;//手续费
	public String traderate;//手续税率
	public String tradetype;//交易类型
	public double tradeprofit;//交易利润

	
	public double  unitbuyPrice;
	public double  unitsellPirce;
	public String cbtime;
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getTbase() {
		return tbase;
	}
	public void setTbase(String tbase) {
		this.tbase = tbase;
	}
	public String getTcount() {
		return tcount;
	}
	public void setTcount(String tcount) {
		this.tcount = tcount;
	}
	public String getTvalue() {
		return tvalue;
	}
	public void setTvalue(String tvalue) {
		this.tvalue = tvalue;
	}
	public String getTprice() {
		return tprice;
	}
	public void setTprice(String tprice) {
		this.tprice = tprice;
	}
	public String getTradefee() {
		return tradefee;
	}
	public void setTradefee(String tradefee) {
		this.tradefee = tradefee;
	}
	public String getTraderate() {
		return traderate;
	}
	public void setTraderate(String traderate) {
		this.traderate = traderate;
	}
	public String getTradetype() {
		return tradetype;
	}
	public void setTradetype(String tradetype) {
		this.tradetype = tradetype;
	}
	public double getTradeprofit() {
		return tradeprofit;
	}
	public void setTradeprofit(double tradeprofit) {
		this.tradeprofit = tradeprofit;
	}
	public double getUnitbuyPrice() {
		return unitbuyPrice;
	}
	public void setUnitbuyPrice(double unitbuyPrice) {
		this.unitbuyPrice = unitbuyPrice;
	}
	public double getUnitsellPirce() {
		return unitsellPirce;
	}
	public void setUnitsellPirce(double unitsellPirce) {
		this.unitsellPirce = unitsellPirce;
	}
	public String getCbtime() {
		return cbtime;
	}
	public void setCbtime(String cbtime) {
		this.cbtime = cbtime;
	}
	

	
	
	
	
	
}
