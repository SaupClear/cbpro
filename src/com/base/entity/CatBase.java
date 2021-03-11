package com.base.entity;

public class CatBase {

	//机组
	public int bid; //机组负责买入边界
	
	
	public String working;
	public int workingtype;//0：正常卖买，1：只卖不买

	public double spaceProfitMoving; //机组浮动区买入 利润是否固定2%，还是2%+浮空区空间
	

	public String productid;
	public String tradetype;
	public String threadid;
	
	
	public double buyPrice; //机组负责买入边界
	public double sellPrice; //机组负责卖出边界
	
	public double tradefee;//机组交易费率
	public double profitMargins; //利润空间
	
	public double slopecount;//斜率数量设置
	public double slopevalue;//斜率边界设置
	
	public double earlyBuyspace;//买入浮动区(空间)
	public double earlyTradespace; //提早下单买入(空间)
	
	public int unitcount;//多少个单元
	public double unitavgcurrency;//每个单元多少钱
	
	
	public int fundsSmallest;
	public int sizeSmallest;
	
	
	public double trendbuy; //动态机组负责买入边界
	public double trendsell; //动态机组负责卖出边界
	public int trendtype; //动态机组负责的0：正常卖买，1：只卖不买
	
	public double portfoliolimit; //持仓组合上限限制
	public int portfoliotype; //持仓组合上限负责0：正常卖买，1：只卖不买

	
	
	
	
	
	
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
