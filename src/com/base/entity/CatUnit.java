package com.base.entity;

import java.math.BigDecimal;
import java.security.PublicKey;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import okhttp3.OkHttpClient;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.db.DBUtils;
import com.base.util.JsonUtil;
import com.base.util.OkHttpUtil;



public class CatUnit {
	
	public static String host = "http://5.148.183.68:8191";
	//public static String host = "http://localhost:8191";
	


	//单元id
	public String uid; //单元标识码
	
	public int bid; //机组标识码

	public double unitbuyPrice; //机组自己设定的单元买入价格
	//markRange属性
	public double markRangeBuyPrice;
	public double markRangeSellPrice;
	public int   markRangeTags;//是否怀孕0,1
	
	public double basecount;//虚拟货币余额
	public double currencycount; //现金余额
	
	//资金是否到位
	public int currencyAccountBool;
	
	public double previousvalue;//上一个价格
	public int alreadybought;//已经买入,0未买,1买入
	
	public double unitsellPrice;//单元自己设定的卖出价格
	public double basebuyPrice;//虚拟货币的买入价格
	
	
	public int belowline;//下穿买入线
	public int aboveline;//上穿买入线
	
	public int safebelowline;//下穿安全买入线
	public int safeaboveline;//上穿安全买入线
	
	public double unitbuytotal; //买入花了多少钱
	public double unitselltotal;//卖出花了多少钱
	
	public String buyOrderid;
	public String sellOrderid;
	
	
	public String tradestatus; //交易状态，不允许别人打扰
	
	
	public static String getHost() {
		return host;
	}


	public static void setHost(String host) {
		CatUnit.host = host;
	}


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public int getBid() {
		return bid;
	}


	public void setBid(int bid) {
		this.bid = bid;
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


	public int getMarkRangeTags() {
		return markRangeTags;
	}


	public void setMarkRangeTags(int markRangeTags) {
		this.markRangeTags = markRangeTags;
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


	public double getBasebuyPrice() {
		return basebuyPrice;
	}


	public void setBasebuyPrice(double basebuyPrice) {
		this.basebuyPrice = basebuyPrice;
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


	public double getUnitbuytotal() {
		return unitbuytotal;
	}


	public void setUnitbuytotal(double unitbuytotal) {
		this.unitbuytotal = unitbuytotal;
	}


	public double getUnitselltotal() {
		return unitselltotal;
	}


	public void setUnitselltotal(double unitselltotal) {
		this.unitselltotal = unitselltotal;
	}


	public String getBuyOrderid() {
		return buyOrderid;
	}


	public void setBuyOrderid(String buyOrderid) {
		this.buyOrderid = buyOrderid;
	}


	public String getSellOrderid() {
		return sellOrderid;
	}


	public void setSellOrderid(String sellOrderid) {
		this.sellOrderid = sellOrderid;
	}


	public String getTradestatus() {
		return tradestatus;
	}


	public void setTradestatus(String tradestatus) {
		this.tradestatus = tradestatus;
	}


	public void unitAnalysisData(CatBase cBase,CatUnit cUnit,Double slope,Double pricevalue,Double bid,Double ask){

		//1:卖出设置取消怀孕   2:价格低于区间取消怀孕   3：价格进入区间设置怀孕  0:未操作
		CatLog log = new CatLog();
		Date startdate = new Date();
		
		
		double profitMargins = cBase.getProfitMargins();
		double buyprice =  cUnit.getUnitbuyPrice(); 
		double sellprice = buyprice*(1+profitMargins);
		double earlyTradespace = cBase.getEarlyTradespace(); //提早千分之5买入

		double tradefee = cBase.getTradefee();
		double earlyBuyspace =  cBase.getEarlyBuyspace(); //安全价百分之10 


		double buySpaceprice =  buyprice*(1-earlyBuyspace);
		double point = pricevalue;
		if (cUnit.getAlreadybought()==1) {
			point = ask;
		}else {
			point = bid;
		}
		
		if (cUnit.getPreviousvalue()==0) {
			cUnit.setPreviousvalue(point);
		}

		
		//0、设置怀孕标签
		if (point>=buySpaceprice) {
			cUnit.setMarkRangeTags(1);
			String[] ucoulmn = new String[]{String.valueOf(cUnit.getMarkRangeTags()),String.valueOf(cUnit.getUid())};
			int[] utype = new int[]{Types.INTEGER,Types.CHAR};
			DBUtils.updateData("update catunits set markRangeTags = ? where uid = ?",ucoulmn,utype);
			log.setActTag(3);
		}
		
		if (point<buySpaceprice) {
			cUnit.setMarkRangeTags(0);
			String[] ucoulmn = new String[]{String.valueOf(cUnit.getMarkRangeTags()),String.valueOf(cUnit.getUid())};
			int[] utype = new int[]{Types.INTEGER,Types.CHAR};
			DBUtils.updateData("update catunits set markRangeTags = ? where uid = ?",ucoulmn,utype);
			log.setActTag(2);
		}
		
		
		//1、打上上穿，下穿标记
		if (point<buyprice) {
			cUnit.setBelowline(1);
			cUnit.setAboveline(0);
			String[] linecoulmn = new String[]{String.valueOf(cUnit.getBelowline()),String.valueOf(cUnit.getAboveline()),
					String.valueOf(cUnit.getSafebelowline()),String.valueOf(cUnit.getSafeaboveline()),String.valueOf(cUnit.getUid())};
			int[] linetype = new int[]{Types.INTEGER, Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.CHAR};
			DBUtils.updateData("update catunits set belowline = ?,aboveline = ?,safebelowline = ?,safeaboveline = ? where uid = ?",linecoulmn,linetype);
		}
		if (point<buySpaceprice) {
			cUnit.setSafebelowline(1);
			cUnit.setSafeaboveline(0);
			
			String[] linecoulmn = new String[]{String.valueOf(cUnit.getBelowline()),String.valueOf(cUnit.getAboveline()),
					String.valueOf(cUnit.getSafebelowline()),String.valueOf(cUnit.getSafeaboveline()),String.valueOf(cUnit.getUid())};
			int[] linetype = new int[]{Types.INTEGER, Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.CHAR};
			DBUtils.updateData("update catunits set belowline = ?,aboveline = ?,safebelowline = ?,safeaboveline = ? where uid = ?",linecoulmn,linetype);
		}
		
		if (cUnit.getBelowline()==1&&point>=buyprice) {
			cUnit.setAboveline(1);
			cUnit.setBelowline(0);
			
			String[] linecoulmn = new String[]{String.valueOf(cUnit.getBelowline()),String.valueOf(cUnit.getAboveline()),
					String.valueOf(cUnit.getSafebelowline()),String.valueOf(cUnit.getSafeaboveline()),String.valueOf(cUnit.getUid())};
			int[] linetype = new int[]{Types.INTEGER, Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.CHAR};
			DBUtils.updateData("update catunits set belowline = ?,aboveline = ?,safebelowline = ?,safeaboveline = ? where uid = ?",linecoulmn,linetype);
		}
		if (cUnit.getSafebelowline()==1&&point>=buySpaceprice) {
			cUnit.setSafeaboveline(1);
			cUnit.setSafebelowline(0);
			String[] linecoulmn = new String[]{String.valueOf(cUnit.getBelowline()),String.valueOf(cUnit.getAboveline()),
					String.valueOf(cUnit.getSafebelowline()),String.valueOf(cUnit.getSafeaboveline()),String.valueOf(cUnit.getUid())};
			int[] linetype = new int[]{Types.INTEGER, Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.CHAR};
			DBUtils.updateData("update catunits set belowline = ?,aboveline = ?,safebelowline = ?,safeaboveline = ? where uid = ?",linecoulmn,linetype);
		}
		
		
		//停机休眠 再启动时  检查买入条件，不符合-->复位上穿买入条件
		if (point>=sellprice) {
			cUnit.setAboveline(0);
			cUnit.setSafeaboveline(0);
			cUnit.setBelowline(0);
			cUnit.setSafebelowline(0);
			
			String[] linecoulmn = new String[]{String.valueOf(cUnit.getBelowline()),String.valueOf(cUnit.getAboveline()),
					String.valueOf(cUnit.getSafebelowline()),String.valueOf(cUnit.getSafeaboveline()),String.valueOf(cUnit.getUid())};
			int[] linetype = new int[]{Types.INTEGER, Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.CHAR};
			DBUtils.updateData("update catunits set belowline = ?,aboveline = ?,safebelowline = ?,safeaboveline = ? where uid = ?",linecoulmn,linetype);
		}
		

		if(cBase.getWorkingtype()==0&&cBase.getTrendtype()==0&&cBase.getPortfoliotype()==0
				&&point>=buyprice*(1-earlyTradespace)&&
				point>=cBase.getTrendbuy()&&point>=cBase.getTrendsell()&&
				cUnit.getAboveline()==1&&cUnit.getAlreadybought()==0&&cUnit.getTradestatus().equals("done")){
			//执行买入操作
			//1、去钱包里取钱			
			double eurAccount = cBase.getUnitavgcurrency();
			if (eurAccount>0) {
				//2、插入交易记录
				cUnit.setTradestatus("pending");
				String[] ucoulmn = new String[]{String.valueOf(cUnit.getTradestatus()),String.valueOf(cUnit.getUid())};
				int[] utype = new int[]{Types.CHAR,Types.CHAR};
				DBUtils.updateData("update catunits set tradestatus = ? where uid = ?",ucoulmn,utype);
				
				TradingOrders(point, "buy", cUnit, cBase, log, eurAccount,0.0);
				
			}
		}else if (cBase.getWorkingtype()==0&&cBase.getTrendtype()==0&&cBase.getPortfoliotype()==0
				&&point>=buySpaceprice*(1-earlyTradespace)&&cUnit.getSafeaboveline()==1&&cUnit.getAlreadybought()==0&&cUnit.getTradestatus().equals("done")) {
			//执行买入操作
			//1、去钱包里取钱

			double eurAccount = cBase.getUnitavgcurrency();
			if (eurAccount>0) {
				cUnit.setTradestatus("pending");
				String[] ucoulmn = new String[]{String.valueOf(cUnit.getTradestatus()),String.valueOf(cUnit.getUid())};
				int[] utype = new int[]{Types.CHAR,Types.CHAR};
				DBUtils.updateData("update catunits set tradestatus = ? where uid = ?",ucoulmn,utype);
				
				TradingOrders(point, "safebuy", cUnit, cBase, log, eurAccount,0.0);
			}
		}

		
		//判断单元是否有自己的卖出价格
		double fixingprice = 0;
		if(cUnit.getUnitsellPrice()!=0){
			fixingprice = cUnit.getUnitsellPrice();
		}else {
			fixingprice = sellprice;
		}
		
		if (point>=fixingprice&&cUnit.getAlreadybought()==1&&slope<=cBase.getSlopevalue()&&cUnit.getTradestatus().equals("done")) {
			//1、去钱包里取BTC
			double basecount = cUnit.getBasecount();
			if (basecount>0) {
				cUnit.setTradestatus("pending");
				String[] ucoulmn = new String[]{String.valueOf(cUnit.getTradestatus()),String.valueOf(cUnit.getUid())};
				int[] utype = new int[]{Types.CHAR,Types.CHAR};
				DBUtils.updateData("update catunits set tradestatus = ? where uid = ?",ucoulmn,utype);
				
				TradingOrders(point, "sell", cUnit, cBase, log, 0.0,basecount);
			}
		}
	


		
		
		log.setBid(cUnit.getBid());
		log.setUid(cUnit.getUid());
		log.setLogprice(point);
		log.setMarkRangeTags(cUnit.getMarkRangeTags());
		log.setLogtime(getnewstdate());
		
		
		
		log.setBuyPrice(cBase.getBuyPrice());
		log.setSellPrice(cBase.getSellPrice());
		log.setTradefee(cBase.getTradefee());
		log.setProfitMargins(cBase.getProfitMargins());
		log.setSlopecount(cBase.getSlopecount());
		log.setSlopevalue(cBase.getSlopevalue());
		log.setEarlyBuyspace(cBase.getEarlyBuyspace());
		log.setEarlyTradespace(cBase.getEarlyTradespace());
		log.setUnitcount(cBase.getUnitcount());
		log.setUnitavgcurrency(cBase.getUnitavgcurrency());
		log.setUnitbuyPrice(cUnit.getUnitbuyPrice());
		log.setUnitsellPrice(cUnit.getUnitsellPrice());
		log.setBasecount(cUnit.getBasecount());
		log.setCurrencycount(cUnit.getCurrencycount());
		log.setCurrencyAccountBool(cUnit.getCurrencyAccountBool());
		log.setMarkRangeBuyPrice(cUnit.getMarkRangeBuyPrice());
		log.setMarkRangeSellPrice(cUnit.getMarkRangeSellPrice());
		log.setPreviousvalue(cUnit.getPreviousvalue());
		log.setAlreadybought(cUnit.getAlreadybought());
		log.setBelowline(cUnit.getBelowline());
		log.setAboveline(cUnit.getAboveline());
		log.setSafeaboveline(cUnit.getSafeaboveline());
		log.setSafebelowline(cUnit.getSafebelowline());
		cUnit.setPreviousvalue(point);
		
		String[] logcoulmn = new String[]{String.valueOf(log.getBid()),log.getUid(),
				String.valueOf(log.getTradetype()),String.valueOf(log.getMarkRangeTags()),String.valueOf(log.getActTag()),
				String.valueOf(log.getLogprice()),log.getLogtime(),String.valueOf(log.getBuyPrice()),String.valueOf(log.getSellPrice()),
				String.valueOf(log.getTradefee()),String.valueOf(log.getProfitMargins()),String.valueOf(log.getSlopecount()),
				String.valueOf(log.getSlopevalue()),String.valueOf(log.getEarlyBuyspace()),String.valueOf(log.getEarlyTradespace()),
				String.valueOf(log.getUnitcount()),String.valueOf(log.getUnitavgcurrency()),String.valueOf(log.getUnitbuyPrice()),String.valueOf(log.getUnitsellPrice()),
				String.valueOf(log.getBasecount()),String.valueOf(log.getCurrencycount()),String.valueOf(log.getCurrencyAccountBool()),
				String.valueOf(log.getMarkRangeBuyPrice()),String.valueOf(log.getMarkRangeSellPrice()),String.valueOf(log.getPreviousvalue()),
				String.valueOf(log.getAlreadybought()),String.valueOf(log.getBelowline()),String.valueOf(log.getAboveline()),
				String.valueOf(log.getSafebelowline()),String.valueOf(log.getSafeaboveline())};
		int[] logtype = new int[]{Types.INTEGER, Types.CHAR, Types.CHAR,Types.INTEGER,Types.INTEGER,Types.DOUBLE,Types.CHAR,Types.DOUBLE,Types.DOUBLE
				,Types.DOUBLE,Types.DOUBLE,Types.DOUBLE,Types.DOUBLE,Types.DOUBLE,Types.DOUBLE,Types.INTEGER,Types.DOUBLE,Types.DOUBLE,Types.DOUBLE
				,Types.DOUBLE,Types.DOUBLE,Types.INTEGER,Types.DOUBLE,Types.DOUBLE,Types.DOUBLE,Types.INTEGER,Types.INTEGER,Types.INTEGER
				,Types.INTEGER,Types.INTEGER};
		
		DBUtils.intsertData("insert into catlogs(bid,uid,tradetype,markRangeTags,actTag,logprice,logtime,buyPrice,sellPrice,tradefee,profitMargins,slopecount,slopevalue,earlyBuyspace,earlyTradespace,unitcount,unitavgcurrency,unitbuyPrice,unitsellPrice,basecount,currencycount,currencyAccountBool,markRangeBuyPrice,markRangeSellPrice,previousvalue,alreadybought,belowline,aboveline,safebelowline,safeaboveline)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",logcoulmn,logtype);

		
//		Date endDate = new Date();
		
//		System.out.print(endDate.getTime()-startdate.getTime()+"\n");
//		System.out.print(point+"\n");


	}
	
	
	public static String getnewstdate() {
		Calendar cd = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(cd.getTime());
	}
	
	
	
	public void insertTradeData(Double point,String logtrade,CatUnit cUnit, CatBase cBase,CatLog log,String orderid,String filled_size,String fill_fees,String executed_value,Double price){
		
		
		if (logtrade.equals("buy")) {
			
			CatTradeRecord record = new CatTradeRecord();
			record.setBid(cUnit.getBid());
			record.setUid(cUnit.getUid());
			record.setOrderid(orderid);
			record.setTcount(filled_size);
			record.setTradetype(logtrade);
			record.setTprice(String.valueOf(point));
			record.setTbase(cBase.getProductid());
			record.setUnitbuyPrice(price);
			Double filled_total =  Double.valueOf(executed_value)+Double.valueOf(fill_fees);//总花费
			record.setTvalue(executed_value);
			record.setTradefee(fill_fees);
			Double rate = Double.valueOf(fill_fees)/Double.valueOf(executed_value);
			record.setTraderate(String.valueOf(rate));
			record.setCbtime(getnewstdate());
			String[] coulmn = new String[]{String.valueOf(record.getBid()),record.getUid(),record.getTbase(),
					record.getTcount(),record.getTradefee(),record.getTraderate(),record.getTradetype(),record.getTvalue(),record.getTprice(),String.valueOf(record.getUnitbuyPrice()),record.getCbtime(),record.getOrderid()};
			int[] type = new int[]{Types.INTEGER,Types.CHAR,Types.CHAR, Types.CHAR,Types.CHAR,Types.CHAR,Types.CHAR,Types.CHAR,Types.CHAR,Types.DOUBLE,Types.CHAR,Types.CHAR};
			log.setTradetype("buy");

			DBUtils.intsertData("insert into cattraderecord(bid,uid, tbase, tcount,tradefee,traderate,tradetype,tvalue,tprice,unitbuyPrice,cbtime,orderid)values(?,?,?,?,?,?,?,?,?,?,?,?)",coulmn,type);;


			//3、更新机主钱包状态
			cUnit.setBasecount(cUnit.getBasecount()+Double.valueOf(filled_size));
			cUnit.setCurrencycount(cUnit.getCurrencycount()-filled_total);
			cUnit.setAlreadybought(1);
			
			if (cBase.getSpaceProfitMoving()==0) {
				cUnit.setUnitsellPrice(price*(1+cBase.getProfitMargins()));
			}else {
				cUnit.setUnitsellPrice(0);
			}
			
			cUnit.setBasebuyPrice(price);
	
			
			String[] bcoulmn = new String[]{String.valueOf(cUnit.getBasecount()),String.valueOf(cUnit.getCurrencycount()),
					String.valueOf(cUnit.getAlreadybought()),String.valueOf(cUnit.getBelowline()),String.valueOf(cUnit.getAboveline()),
					String.valueOf(cUnit.getSafebelowline()),String.valueOf(cUnit.getSafeaboveline()),String.valueOf(cUnit.getUnitsellPrice()),String.valueOf(cUnit.getBasebuyPrice()),String.valueOf(cUnit.getUnitbuytotal()),String.valueOf(cUnit.getUid())};
			int[] btype = new int[]{Types.DOUBLE, Types.DOUBLE,Types.INTEGER,Types.INTEGER, Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.DOUBLE,Types.DOUBLE,Types.DOUBLE,Types.CHAR};

			
			DBUtils.updateData("update catunits set basecount = ?,currencycount = ?,alreadybought = ?,belowline = ?,aboveline = ?,safebelowline = ?,safeaboveline = ?,unitsellPrice = ?,basebuyPrice = ?,unitbuytotal = ? where uid = ?",bcoulmn,btype);
			//4、关闭上传买入操作等待卖出
			
			cUnit.setTradestatus("done");
			
			String[] ucoulmn = new String[]{String.valueOf(cUnit.getTradestatus()),String.valueOf(cUnit.getUid())};
			int[] utype = new int[]{Types.CHAR,Types.CHAR};
			DBUtils.updateData("update catunits set tradestatus = ? where uid = ?",ucoulmn,utype);
			
			
		}else if (logtrade.equals("safebuy")) {
			
			//2、插入交易记录
			CatTradeRecord record = new CatTradeRecord();
			record.setBid(cUnit.getBid());
			record.setUid(cUnit.getUid());
			record.setOrderid(orderid);
			record.setTcount(filled_size);
			record.setTradetype(logtrade);
			record.setTprice(String.valueOf(point));
			record.setTbase(cBase.getProductid());
			record.setUnitbuyPrice(price);
			Double filled_total =  Double.valueOf(executed_value)+Double.valueOf(fill_fees);//总花费
			Double rate = Double.valueOf(fill_fees)/Double.valueOf(executed_value);
			record.setTraderate(String.valueOf(rate));
			record.setTvalue(executed_value);
			record.setTradefee(fill_fees);
			record.setCbtime(getnewstdate());
			
			
			String[] coulmn = new String[]{String.valueOf(record.getBid()),record.getUid(),record.getTbase(),
					record.getTcount(),record.getTradefee(),record.getTraderate(),record.getTradetype(),record.getTvalue(),record.getTprice(),String.valueOf(record.getUnitbuyPrice()),record.getCbtime(),record.getOrderid()};
			int[] type = new int[]{Types.INTEGER,Types.CHAR,Types.CHAR, Types.CHAR,Types.CHAR,Types.CHAR,Types.CHAR,Types.CHAR,Types.CHAR,Types.DOUBLE,Types.CHAR,Types.CHAR};
			
			log.setTradetype("safebuy");
			
			DBUtils.intsertData("insert into cattraderecord(bid,uid, tbase, tcount,tradefee,traderate,tradetype,tvalue,tprice,unitbuyPrice,cbtime,orderid)values(?,?,?,?,?,?,?,?,?,?,?,?)",coulmn,type);;

			


			//3、更新钱包状态
			cUnit.setBasecount(cUnit.getBasecount()+Double.valueOf(filled_size));
			cUnit.setCurrencycount(cUnit.getCurrencycount()-filled_total);
			cUnit.setAlreadybought(1);
			
			if (cBase.getSpaceProfitMoving()==0) {
				cUnit.setUnitsellPrice(price*(1+cBase.getProfitMargins()));
			}else {
				cUnit.setUnitsellPrice(0);
			}
			

			cUnit.setBasebuyPrice(price);
		
			String[] bcoulmn = new String[]{String.valueOf(cUnit.getBasecount()),String.valueOf(cUnit.getCurrencycount()),
					String.valueOf(cUnit.getAlreadybought()),String.valueOf(cUnit.getBelowline()),String.valueOf(cUnit.getAboveline()),
					String.valueOf(cUnit.getSafebelowline()),String.valueOf(cUnit.getSafeaboveline()),String.valueOf(cUnit.getBasebuyPrice())
					,String.valueOf(cUnit.getUnitbuytotal()),String.valueOf(cUnit.getUid())};
			int[] btype = new int[]{Types.DOUBLE, Types.DOUBLE,Types.INTEGER,Types.INTEGER, Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.DOUBLE,Types.DOUBLE,Types.CHAR};

			DBUtils.updateData("update catunits set basecount = ?,currencycount = ?,alreadybought = ?,belowline = ?,aboveline = ?,safebelowline = ?,safeaboveline = ?,basebuyPrice=?,unitbuytotal = ? where uid = ?",bcoulmn,btype);
			
			cUnit.setTradestatus("done");
			String[] ucoulmn = new String[]{String.valueOf(cUnit.getTradestatus()),String.valueOf(cUnit.getUid())};
			int[] utype = new int[]{Types.CHAR,Types.CHAR};
			DBUtils.updateData("update catunits set tradestatus = ? where uid = ?",ucoulmn,utype);

			
		}else if (logtrade.equals("sell")) {
			
			//2、插入交易记录
			CatTradeRecord record = new CatTradeRecord();
			Double filled_total =  Double.valueOf(executed_value)-Double.valueOf(fill_fees);//总盈利
			record.setBid(cUnit.getBid());
			record.setUid(cUnit.getUid());
			record.setOrderid(orderid);

			record.setTcount(filled_size);
			record.setTradetype(logtrade);
			record.setTbase(cBase.getProductid());
			record.setTprice(String.valueOf(point));
			record.setTvalue(executed_value);
			record.setUnitsellPirce(price);
			record.setTradefee(fill_fees);
			Double rate = Double.valueOf(fill_fees)/Double.valueOf(executed_value);
			record.setTraderate(String.valueOf(rate));
			
			
			String[] strArr= cBase.getProductid().split("-");

			//3、利润转账指令
			double profitDouble = cUnit.getUnitselltotal()-cUnit.getUnitbuytotal();
			double profit = new BigDecimal(profitDouble).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			if (strArr[1].equals("EUR")) {
				profit = new BigDecimal(profitDouble).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			}else if (strArr[1].equals("BTC")) {
				profit = new BigDecimal(profitDouble).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
			}
			
			record.setCbtime(getnewstdate());
			String[] coulmn = new String[]{String.valueOf(record.getBid()),record.getUid(),record.getTbase(),
					record.getTcount(),record.getTradefee(),record.getTraderate(),record.getTradetype(),record.getTvalue(),record.getTprice(),String.valueOf(record.getUnitsellPirce()),record.getCbtime(),record.getOrderid(),String.valueOf(profit)};
			int[] type = new int[]{Types.INTEGER,Types.CHAR,Types.CHAR, Types.CHAR,Types.CHAR,Types.CHAR,Types.CHAR,Types.CHAR,Types.CHAR,Types.DOUBLE,Types.CHAR,Types.CHAR,Types.DOUBLE};
			DBUtils.intsertData("insert into cattraderecord(bid,uid,tbase,tcount,tradefee,traderate,tradetype,tvalue,tprice,unitsellPrice,cbtime,orderid,tradeprofit)values(?,?,?,?,?,?,?,?,?,?,?,?,?)",coulmn,type);

			//3、更新钱包状态
			log.setTradetype("sell");
			cUnit.setBasecount(cUnit.getBasecount()-Double.valueOf(filled_size));
			cUnit.setCurrencycount(cUnit.getCurrencycount()+filled_total);
			cUnit.setAlreadybought(0);
			cUnit.setUnitbuytotal(0.0f);
			
			//复位
			cUnit.setAboveline(0);
			cUnit.setSafeaboveline(0);
			cUnit.setBelowline(0);
			cUnit.setSafebelowline(0);
			cUnit.setUnitsellPrice(0);
			
			//4/取消怀孕标签
			cUnit.setMarkRangeTags(0);
			
			
			log.setActTag(1);

			String[] bcoulmn = new String[]{String.valueOf(cUnit.getBasecount()),String.valueOf(cUnit.getCurrencycount()),
					String.valueOf(cUnit.getAlreadybought()),String.valueOf(cUnit.getMarkRangeTags()),String.valueOf(cUnit.getBelowline()),String.valueOf(cUnit.getAboveline()),
					String.valueOf(cUnit.getSafebelowline()),String.valueOf(cUnit.getSafeaboveline()),String.valueOf(cUnit.getUnitsellPrice()),String.valueOf(cUnit.getUnitbuytotal()),String.valueOf(cUnit.getUnitselltotal()),String.valueOf(cUnit.getUid())};
			int[] btype = new int[]{Types.DOUBLE, Types.DOUBLE,Types.INTEGER,Types.INTEGER,Types.INTEGER, Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.DOUBLE,Types.DOUBLE,Types.DOUBLE,Types.CHAR};
			
			DBUtils.updateData("update catunits set basecount = ?,currencycount = ?,alreadybought = ?,markRangeTags = ?,belowline = ?,aboveline = ?,safebelowline = ?,safeaboveline = ?,unitsellPrice = ?,unitbuytotal = ?,unitselltotal = ? where uid = ?",bcoulmn,btype);
			
			cUnit.setTradestatus("done");
			String[] ucoulmn = new String[]{String.valueOf(cUnit.getTradestatus()),String.valueOf(cUnit.getUid())};
			int[] utype = new int[]{Types.CHAR,Types.CHAR};
			DBUtils.updateData("update catunits set tradestatus = ? where uid = ?",ucoulmn,utype);
			
		}
		

		
	}
	
	
	
	
	public void portfolioDataRefresh(CatBase b){
		
		if(b.getPortfoliolimit()>10000){
			
			JSONArray units = DBUtils.searchData("select * from catunits where bid = "+bid);
			List<CatUnit> ulist =  JsonUtil.toList(units, CatUnit.class);
			
			double value = 0;
			for (int i = 0; i < ulist.size(); i++) {
				CatUnit u = ulist.get(i);
				if (u.getAlreadybought()==1) {
					value = value + b.getUnitavgcurrency();
				}
			}
			
			if (value>=b.getPortfoliolimit()) {
				if (b.getPortfoliolimit()==0) {
					b.setPortfoliotype(1);
					String[] coulmn = new String[]{String.valueOf(b.getPortfoliotype()),String.valueOf(b.getBid())};
					int[] type = new int[]{Types.DOUBLE,Types.INTEGER};
					DBUtils.updateData("update catbases set portfoliotype = ? where bid = ?",coulmn,type);
				}
			}else{
				if (b.getPortfoliolimit()==1) {
					b.setPortfoliotype(0);
					String[] coulmn = new String[]{String.valueOf(b.getPortfoliotype()),String.valueOf(b.getBid())};
					int[] type = new int[]{Types.DOUBLE,Types.INTEGER};
					DBUtils.updateData("update catbases set portfoliotype = ? where bid = ?",coulmn,type);
				}
			}
		}
		
	}
	
	
	
	
	public void TradingOrders(Double point,String logtrade,CatUnit cUnit, CatBase cBase,CatLog log,Double eurAccount,Double baseCount) {


		new Thread(new Runnable() {
			@Override
			public void run() {

				System.out.print("1.Start Trading-point:"+point+" Thread_id:"+Thread.currentThread().getId()+"\n");

				JSONObject json = new JSONObject();
				json.put("product_id", cBase.getProductid());
				json.put("tradetype", cBase.getTradetype());

				if (logtrade.equals("buy")||logtrade.equals("safebuy")) {
					json.put("side", "buy");
					
			
					double avgcurrency = new BigDecimal(cBase.getUnitavgcurrency()).setScale(cBase.getFundsSmallest(), BigDecimal.ROUND_DOWN).doubleValue();
					System.out.print("avgcurrency:"+avgcurrency+"\n count:"+cBase.getFundsSmallest()+"\n");

					json.put("funds",avgcurrency);
					

				}else if (logtrade.equals("sell")) {
					json.put("side", "sell");
					double basecount = new BigDecimal(cUnit.getBasecount()).setScale(cBase.getSizeSmallest(), BigDecimal.ROUND_DOWN).doubleValue();
					
					System.out.print("basecount:"+basecount+"\n count:"+cBase.getSizeSmallest()+"\n");

					json.put("size", basecount);
				}

				//1、交易指令
				String answer = OkHttpUtil.getPostInfo(host,
						json.toString());
				JSONObject anObject = null;
				if (answer!=null&&answer.equals("")==false&&answer.length()>5) {
					anObject = JsonUtil.toJSONObject(answer);
				}
				

				//2、订单状态，记录信息
				if (anObject != null&&anObject.getString("id")!=null) {
					String oid = anObject.getString("id");
					System.out.print("2.Traded Orderid:"+oid+"\n");

					if (logtrade.equals("buy")||logtrade.equals("safebuy")) {
						cUnit.setBuyOrderid(oid);
						cUnit.setAlreadybought(1);

						CatLogistic logistic = new CatLogistic();
						logistic.setBuyOrderid(oid);
						logistic.setTansferflag(0);
						logistic.setGetOrderflag(0);
						logistic.setTime(getnewstdate());
						
						
						String[] coulmn = new String[]{oid,cUnit.getUid()};
						int[] type = new int[]{Types.CHAR, Types.CHAR};
						DBUtils.updateData("update catunits set buyOrderid = ? where uid = ?",coulmn,type);

						String[] lc = new String[]{oid,"","0","0",getnewstdate()};
						int[] lt = new int[]{Types.CHAR,Types.CHAR,Types.INTEGER,Types.INTEGER,Types.CHAR};
						DBUtils.intsertData("insert into catlogistic(buyOrderid,sellOrderid,getOrderflag,tansferflag,time)values(?,?,?,?,?)",lc,lt);

					}else if (logtrade.equals("sell")) {

						cUnit.setSellOrderid(oid);
						cUnit.setAlreadybought(0);
						cUnit.setMarkRangeTags(0);
						//复位
						cUnit.setAboveline(0);
						cUnit.setSafeaboveline(0);
						cUnit.setBelowline(0);
						cUnit.setSafebelowline(0);
						cUnit.setUnitsellPrice(0);

						if (cUnit.getBuyOrderid()!=null&&cUnit.getBuyOrderid().length()>5) {
							
							String[] coulmn = new String[]{oid,cUnit.getUid()};
							int[] type = new int[]{Types.CHAR, Types.CHAR};
							DBUtils.updateData("update catunits set sellOrderid = ? where uid = ?",coulmn,type);
							
							String[] lc = new String[]{cUnit.getBuyOrderid(),cUnit.getSellOrderid(),"0","0",getnewstdate()};
							int[] lt = new int[]{Types.CHAR,Types.CHAR,Types.INTEGER,Types.INTEGER,Types.CHAR};
							DBUtils.intsertData("insert into catlogistic(buyOrderid,sellOrderid,getOrderflag,tansferflag,time)values(?,?,?,?,?)",lc,lt);
						}
					}


					if (oid != null) {
						// 查询订单状态
						try {
							Random r = new Random(1);
							int ran1 = r.nextInt(5000);
							Thread.sleep(15000+ran1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						JSONObject idJsonObject = new JSONObject();
						idJsonObject.put("id", oid);
						idJsonObject.put("tradetype", "get_order");
						String tanswer = OkHttpUtil.getPostInfo(
								host,
								idJsonObject.toString());

						JSONObject tObject = null;
						String status = null;
						if (tanswer!=null&&tanswer.equals("")==false&&tanswer.length()>5) {
							tObject = JsonUtil.toJSONObject(tanswer);
							if (tObject!=null) {
								status = tObject.getString("status");
							}
						}

						int wi = 0;
						while (wi<10&&(status == null || status.equals("done") == false || tObject.getString("price")==null)) {
							wi++;
							String reString = OkHttpUtil.getPostInfo(
									host,
									idJsonObject.toJSONString());

							if (reString==null||reString.equals("")||reString.length()<4) {
								try {
									Random r = new Random(1);
									int ran1 = r.nextInt(5000);
									Thread.sleep(5000+ran1);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								continue;
							}

							tObject = JsonUtil
									.toJSONObject(reString);
							
							status = tObject.getString("status");
							

							System.out.print("3.Get Traded Order Status:"+reString+"\n");
							System.out.print("3.1.status:"+status+"\n");
							try {
								Random r = new Random(1);
								int ran1 = r.nextInt(5000);
								Thread.sleep(5000+ran1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						String orderid = tObject.getString("id");
						String filled_size = tObject.getString("filled_size");
						String fill_fees = tObject.getString("fill_fees");
						String executed_value = tObject.getString("executed_value");
						String price = tObject.getString("price");

						Double executed_total = 0.0;
					

						System.out.print("4.Profit transfer\n");

						if (logtrade.equals("buy")||logtrade.equals("safebuy")) {
							executed_total = Double.valueOf(executed_value)+Double.valueOf(fill_fees);//总花费
							cUnit.setUnitbuytotal(executed_total);
							
							if (cUnit.getBuyOrderid()!=null&&cUnit.getBuyOrderid().length()>5) {
								String[] coulmn = new String[]{"1",cUnit.getBuyOrderid()};
								int[] type = new int[]{Types.INTEGER, Types.CHAR};
								DBUtils.updateData("update catlogistic set getOrderflag = ? where buyOrderid = ?",coulmn,type);
							}
							
						
						}else if (logtrade.equals("sell")) {
							executed_total = Double.valueOf(executed_value)-Double.valueOf(fill_fees);//总利润
							cUnit.setUnitselltotal(executed_total);
							
							if (cUnit.getSellOrderid()!=null&&cUnit.getSellOrderid().length()>5) {
								String[] coulmn = new String[]{"1",cUnit.getSellOrderid()};
								int[] type = new int[]{Types.INTEGER, Types.CHAR};
								DBUtils.updateData("update catlogistic set getOrderflag = ? where sellOrderid = ?",coulmn,type);
							}

						}



						System.out.print("4.cUnit.getUnitbuytotal()="+cUnit.getUnitbuytotal()+"\n");
						System.out.print("4.cUnit.getUnitselltotal()="+cUnit.getUnitselltotal()+"\n");



						if (logtrade.equals("buy")||logtrade.equals("safebuy")) {
							//不做操作	
							System.out.print("4.1.Buy no Profit transfer\n");
							insertTradeData(point,logtrade,cUnit,cBase,log,orderid,filled_size,fill_fees,executed_value,Double.valueOf(price));
							portfolioDataRefresh(cBase);

						}else if (logtrade.equals("sell")) {


							String[] strArr= cBase.getProductid().split("-");

							//3、利润转账指令
							double transfDouble = cUnit.getUnitselltotal()-cUnit.getUnitbuytotal();
							double transvalue = new BigDecimal(transfDouble).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
							if (strArr[1].equals("EUR")) {
								transvalue = new BigDecimal(transfDouble).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
							}else if (strArr[1].equals("BTC")) {
								transvalue = new BigDecimal(transfDouble).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
							}
							
							
							
							if (transvalue<=0) {
								transvalue = 0.01;
							}
							
							insertTradeData(point,logtrade,cUnit,cBase,log,orderid,filled_size,fill_fees,executed_value,Double.valueOf(price));
							portfolioDataRefresh(cBase);

							JSONObject transjson = new JSONObject();
							transjson.put("currency", strArr[1]);
							transjson.put("tradetype", "transfer");
							
							//最后十次交易求和 为负数 自动停止机组
				          	JSONArray tradearray = DBUtils.searchData("select * from cattraderecord where tradetype ='sell' and bid = "+cUnit.getBid()+" order by cbtime desc limit 10");
				          	List<CatTradeRecord> tradelist =  JsonUtil.toList(tradearray, CatTradeRecord.class);
				          	
				          	double tprofit = 0;
				          	for (int i = 0; i < tradelist.size(); i++) {
				          		CatTradeRecord ctr = tradelist.get(i);
				          		tprofit = tprofit + ctr.getTradeprofit();
				          	}
							
	
							if (tprofit<0) {
								String[] bb = new String[]{"no",String.valueOf(cBase.getBid())};
								int[] bbt = new int[]{Types.CHAR, Types.INTEGER};
								DBUtils.updateData("update catbases set working = ? where bid = ?",bb,bbt);
								System.out.print("working set no 10 tradeprofit sum = :"+tprofit+"\n");
							}
							

							transjson.put("amount", transvalue);

							String transString = OkHttpUtil.getPostInfo(host,
									transjson.toString());

							JSONObject transObject = null;
							
							if (transString!=null&&transString.equals("")==false&&transString.length()>5) {
								transObject = JsonUtil.toJSONObject(transString);
							}
							
							System.out.print("4.Get Profit transfer Status:"+transString+"\n");
							System.out.print("4.1.status:"+transString+"\n");
							
							int wti = 0;
							while (wti<=3&&(transObject == null || transObject.getString("id") == null)) {
								wti++;
								
								transString = OkHttpUtil.getPostInfo(host,
										transjson.toString());

								if (transString==null||transString.equals("")||transString.length()<4) {
									try {
										Random r = new Random(1);
										int ran1 = r.nextInt(5000);
										Thread.sleep(3000+ran1);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									continue;
								}

								transObject = JsonUtil.toJSONObject(transString);

								System.out.print("4.Get Profit transfer Status:"+transString+"\n");
								System.out.print("4.1.status:"+transString+"\n");

								try {
									Random r = new Random(1);
									int ran1 = r.nextInt(5000);
									Thread.sleep(3000+ran1);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

							if (transObject!=null&&transObject.getString("id")!=null) {
								System.out.print("4.2.Profit transfer succeed\n");
								
								if (cUnit.getSellOrderid()!=null&&cUnit.getSellOrderid().length()>5) {
									String[] tcoulmn = new String[]{"1",cUnit.getSellOrderid()};
									int[] ttype = new int[]{Types.INTEGER, Types.CHAR};
									DBUtils.updateData("update catlogistic set tansferflag = ? where sellOrderid = ?",tcoulmn,ttype);
								}

								
							}else {
								System.out.print("Profit transfer Failed：transvalue:"+transvalue+"\n");
							}
							System.out.print("Transfer time:"+getnewstdate()+"\n");
						}
						System.out.print("5.Trading succeed end. Thread_id:"+Thread.currentThread().getId()+"\n");
						System.out.print("---------------------\n");

					}
				}else {
					System.out.print("2.Trading failed bid:"+cBase.getBid()+"currentThread:+"+Thread.currentThread().getId()+"\n");
					System.out.print("Trading send json:"+host+"//"+json.toString()+"\n");
					System.out.print("Trading response:"+answer+"\n");

					if (answer!=null&&answer.length()>5&&answer.contains("size is too accurate")) {
						int shu = answer.indexOf("0.");
						String shuString = answer.substring(shu);
						int index_id = shuString.indexOf("1");
						index_id = index_id-1;
						
						String[] fcoulmn = new String[]{String.valueOf(index_id),String.valueOf(cBase.getBid())};
						int[] ftype = new int[]{Types.INTEGER, Types.INTEGER};
						DBUtils.updateData("update catbases set sizeSmallest = ? where bid = ?",fcoulmn,ftype);
					}
					if (answer!=null&&answer.length()>5&&answer.contains("funds is too accurate")) {
						int shu = answer.indexOf("0.");
						String shuString = answer.substring(shu);
						int index_id = shuString.indexOf("1");
						index_id = index_id-1;
						
						String[] fcoulmn = new String[]{String.valueOf(index_id),String.valueOf(cBase.getBid())};
						int[] ftype = new int[]{Types.INTEGER, Types.INTEGER};
						DBUtils.updateData("update catbases set fundsSmallest = ? where bid = ?",fcoulmn,ftype);
					}
					
					if (cBase.getProductid().equals("BTC-EUR")==false&&answer!=null&&answer.length()>5&&answer.contains("Insufficient funds")) {
						JSONArray units = DBUtils.searchData("select * from catunits where uid = '"+cUnit.getUid()+"'");
			          	List<CatUnit> ulist =  JsonUtil.toList(units, CatUnit.class);
			          	if (ulist!=null&&ulist.size()>0) {
							CatUnit u = ulist.get(0);
							System.out.print("MySQL basecount before:"+u.getBasecount()+"\n");
							System.out.print("MySQL basecount after:"+cUnit.getBasecount()+"\n");
						}
					}
					
					System.out.print("Trading Productid:"+cBase.getProductid()+"\n");
					System.out.print("Trading UnitbuyPrice:"+cUnit.getUnitbuyPrice()+"\n");
					System.out.print("Trading time:"+getnewstdate()+"\n");
					System.out.print("---------------------\n");
					cUnit.setTradestatus("done");
					String[] ucoulmn = new String[]{String.valueOf(cUnit.getTradestatus()),String.valueOf(cUnit.getUid())};
					int[] utype = new int[]{Types.CHAR,Types.CHAR};
					DBUtils.updateData("update catunits set tradestatus = ? where uid = ?",ucoulmn,utype);
				}

			}
		}).start();
	}
}
