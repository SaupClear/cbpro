package com.base.entity;

public class CatLog {

	public int lid;

	//log状态信息
	public int bid;
	public String uid;
	
	public String tradetype;
	public int markRangeTags;//是否怀孕0,1
	public int actTag;//1:卖出设置取消怀孕   2:价格低于区间取消怀孕   3：价格进入区间设置怀孕  0:未操作

	public double logprice;
	public String logtime;
	
	
	//log机组信息
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
	
	
	
	//log单元信息
	public double unitbuyPrice; //记录自己设定的单元买入价格

	public double markRangeBuyPrice;
	public double markRangeSellPrice;
	
	public double basecount;//虚拟货币余额
	public double currencycount; //现金余额
	

	public int currencyAccountBool;
	
	public double previousvalue;//上一个价格
	public int alreadybought;//已经买入,0未买,1买入
	
	public double unitsellPrice;//单元自己设定的卖出价格
	
	public int belowline;//下穿买入线
	public int aboveline;//上穿买入线
	
	public int safebelowline;//下穿安全买入线
	public int safeaboveline;//上穿安全买入线
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
