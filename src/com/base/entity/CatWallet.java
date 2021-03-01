package com.base.entity;

public class CatWallet {
	
	//交易钱包
	public int tid;
	public int catid;//钱包ID
	public String btcaccount;//BTC余额
	public String otheraccount;//其它货币余额
	public String euraccount;//现金欧元余额
	public String cattime;//现金欧元余额

	
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getCattime() {
		return cattime;
	}
	public void setCattime(String cattime) {
		this.cattime = cattime;
	}
	public int getCatid() {
		return catid;
	}
	public void setCatid(int catid) {
		this.catid = catid;
	}
	public String getBtcaccount() {
		return btcaccount;
	}
	public void setBtcaccount(String btcaccount) {
		this.btcaccount = btcaccount;
	}
	public String getOtheraccount() {
		return otheraccount;
	}
	public void setOtheraccount(String otheraccount) {
		this.otheraccount = otheraccount;
	}
	public String getEuraccount() {
		return euraccount;
	}
	public void setEuraccount(String euraccount) {
		this.euraccount = euraccount;
	}
	
}
