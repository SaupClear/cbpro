package com.base.entity;

public class CatWallet {
	
	//����Ǯ��
	public int tid;
	public int catid;//Ǯ��ID
	public String btcaccount;//BTC���
	public String otheraccount;//�����������
	public String euraccount;//�ֽ�ŷԪ���
	public String cattime;//�ֽ�ŷԪ���

	
	
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
