package com.base.entity;

public class CatBase {

	//����
	public int bid; //���鸺������߽�
	
	
	public String working;
	public int workingtype;//0����������1��ֻ������

	public double spaceProfitMoving; //���鸡�������� �����Ƿ�̶�2%������2%+�������ռ�
	

	public String productid;
	public String tradetype;
	public String threadid;
	
	
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
	
	
	public int fundsSmallest;
	public int sizeSmallest;
	
	
	public double trendbuy; //��̬���鸺������߽�
	public double trendsell; //��̬���鸺�������߽�
	public int trendtype; //��̬���鸺���0����������1��ֻ������
	
	public double portfoliolimit; //�ֲ������������
	public int portfoliotype; //�ֲ�������޸���0����������1��ֻ������

	
	
	
	
	
	
	public int getTrendtype() {
		return trendtype;
	}
	public void setTrendtype(int trendtype) {
		this.trendtype = trendtype;
	}
	public int getPortfoliotype() {
		return portfoliotype;
	}
	public void setPortfoliotype(int portfoliotype) {
		this.portfoliotype = portfoliotype;
	}
	public double getPortfoliolimit() {
		return portfoliolimit;
	}
	public void setPortfoliolimit(double portfoliolimit) {
		this.portfoliolimit = portfoliolimit;
	}
	public double getTrendbuy() {
		return trendbuy;
	}
	public void setTrendbuy(double trendbuy) {
		this.trendbuy = trendbuy;
	}
	public double getTrendsell() {
		return trendsell;
	}
	public void setTrendsell(double trendsell) {
		this.trendsell = trendsell;
	}
	public int getBid() {
		return bid;
	}
	public int getFundsSmallest() {
		return fundsSmallest;
	}
	public void setFundsSmallest(int fundsSmallest) {
		this.fundsSmallest = fundsSmallest;
	}
	public int getSizeSmallest() {
		return sizeSmallest;
	}
	public void setSizeSmallest(int sizeSmallest) {
		this.sizeSmallest = sizeSmallest;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getWorking() {
		return working;
	}
	public void setWorking(String working) {
		this.working = working;
	}
	public int getWorkingtype() {
		return workingtype;
	}
	public void setWorkingtype(int workingtype) {
		this.workingtype = workingtype;
	}
	public double getSpaceProfitMoving() {
		return spaceProfitMoving;
	}
	public void setSpaceProfitMoving(double spaceProfitMoving) {
		this.spaceProfitMoving = spaceProfitMoving;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getTradetype() {
		return tradetype;
	}
	public void setTradetype(String tradetype) {
		this.tradetype = tradetype;
	}
	public String getThreadid() {
		return threadid;
	}
	public void setThreadid(String threadid) {
		this.threadid = threadid;
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
	

	
	
	
	
	
	
	
	
}
