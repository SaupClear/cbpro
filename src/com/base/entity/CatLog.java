package com.base.entity;

public class CatLog {

	public int lid;

	//log״̬��Ϣ
	public int bid;
	public String uid;
	
	public String tradetype;
	public int markRangeTags;//�Ƿ���0,1
	public int actTag;//1:��������ȡ������   2:�۸��������ȡ������   3���۸�����������û���  0:δ����

	public double logprice;
	public String logtime;
	
	
	//log������Ϣ
	public double buyPrice; //���鸺������߽�
	public double sellPrice; //���鸺�������߽�
	
	public double tradefee;//���齻�׷���
	public double profitMargins; //����ռ�
	
	public double slopecount;//б����������
	public double slopevalue;//б�ʱ߽�����
	
	public double earlyBuyspace;//���븡����(�ռ�)
	public double earlyTradespace; //�����µ�����(�ռ�)
	
	public int unitcount;//���ٸ���Ԫ
	public double unitavgcurrency;//ÿ����Ԫ����Ǯ
	
	
	
	//log��Ԫ��Ϣ
	public double unitbuyPrice; //��¼�Լ��趨�ĵ�Ԫ����۸�

	public double markRangeBuyPrice;
	public double markRangeSellPrice;
	
	public double basecount;//����������
	public double currencycount; //�ֽ����
	

	public int currencyAccountBool;
	
	public double previousvalue;//��һ���۸�
	public int alreadybought;//�Ѿ�����,0δ��,1����
	
	public double unitsellPrice;//��Ԫ�Լ��趨�������۸�
	
	public int belowline;//�´�������
	public int aboveline;//�ϴ�������
	
	public int safebelowline;//�´���ȫ������
	public int safeaboveline;//�ϴ���ȫ������
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
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
	public String getTradetype() {
		return tradetype;
	}
	public void setTradetype(String tradetype) {
		this.tradetype = tradetype;
	}
	public int getMarkRangeTags() {
		return markRangeTags;
	}
	public void setMarkRangeTags(int markRangeTags) {
		this.markRangeTags = markRangeTags;
	}
	public int getActTag() {
		return actTag;
	}
	public void setActTag(int actTag) {
		this.actTag = actTag;
	}
	public double getLogprice() {
		return logprice;
	}
	public void setLogprice(double logprice) {
		this.logprice = logprice;
	}
	public String getLogtime() {
		return logtime;
	}
	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}
	public double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public double getTradefee() {
		return tradefee;
	}
	public void setTradefee(double tradefee) {
		this.tradefee = tradefee;
	}
	public double getProfitMargins() {
		return profitMargins;
	}
	public void setProfitMargins(double profitMargins) {
		this.profitMargins = profitMargins;
	}
	public double getSlopecount() {
		return slopecount;
	}
	public void setSlopecount(double slopecount) {
		this.slopecount = slopecount;
	}
	public double getSlopevalue() {
		return slopevalue;
	}
	public void setSlopevalue(double slopevalue) {
		this.slopevalue = slopevalue;
	}
	public double getEarlyBuyspace() {
		return earlyBuyspace;
	}
	public void setEarlyBuyspace(double earlyBuyspace) {
		this.earlyBuyspace = earlyBuyspace;
	}
	public double getEarlyTradespace() {
		return earlyTradespace;
	}
	public void setEarlyTradespace(double earlyTradespace) {
		this.earlyTradespace = earlyTradespace;
	}
	public int getUnitcount() {
		return unitcount;
	}
	public void setUnitcount(int unitcount) {
		this.unitcount = unitcount;
	}
	public double getUnitavgcurrency() {
		return unitavgcurrency;
	}
	public void setUnitavgcurrency(double unitavgcurrency) {
		this.unitavgcurrency = unitavgcurrency;
	}
	public double getUnitbuyPrice() {
		return unitbuyPrice;
	}
	public void setUnitbuyPrice(double unitbuyPrice) {
		this.unitbuyPrice = unitbuyPrice;
	}
	public double getMarkRangeBuyPrice() {
		return markRangeBuyPrice;
	}
	public void setMarkRangeBuyPrice(double markRangeBuyPrice) {
		this.markRangeBuyPrice = markRangeBuyPrice;
	}
	public double getMarkRangeSellPrice() {
		return markRangeSellPrice;
	}
	public void setMarkRangeSellPrice(double markRangeSellPrice) {
		this.markRangeSellPrice = markRangeSellPrice;
	}
	public double getBasecount() {
		return basecount;
	}
	public void setBasecount(double basecount) {
		this.basecount = basecount;
	}
	public double getCurrencycount() {
		return currencycount;
	}
	public void setCurrencycount(double currencycount) {
		this.currencycount = currencycount;
	}
	public int getCurrencyAccountBool() {
		return currencyAccountBool;
	}
	public void setCurrencyAccountBool(int currencyAccountBool) {
		this.currencyAccountBool = currencyAccountBool;
	}
	public double getPreviousvalue() {
		return previousvalue;
	}
	public void setPreviousvalue(double previousvalue) {
		this.previousvalue = previousvalue;
	}
	public int getAlreadybought() {
		return alreadybought;
	}
	public void setAlreadybought(int alreadybought) {
		this.alreadybought = alreadybought;
	}
	public double getUnitsellPrice() {
		return unitsellPrice;
	}
	public void setUnitsellPrice(double unitsellPrice) {
		this.unitsellPrice = unitsellPrice;
	}
	public int getBelowline() {
		return belowline;
	}
	public void setBelowline(int belowline) {
		this.belowline = belowline;
	}
	public int getAboveline() {
		return aboveline;
	}
	public void setAboveline(int aboveline) {
		this.aboveline = aboveline;
	}
	public int getSafebelowline() {
		return safebelowline;
	}
	public void setSafebelowline(int safebelowline) {
		this.safebelowline = safebelowline;
	}
	public int getSafeaboveline() {
		return safeaboveline;
	}
	public void setSafeaboveline(int safeaboveline) {
		this.safeaboveline = safeaboveline;
	}
	
	
	
	
	
	
	




	
}
