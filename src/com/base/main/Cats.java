package com.base.main;


import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdk.nashorn.internal.ir.Block;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.db.DBUtils;
import com.base.entity.CatBank;
import com.base.entity.CatBase;
import com.base.entity.CatUnit;
import com.base.entity.CbproHistoricrates;
import com.base.util.JsonUtil;

public class Cats {
	
	public CatBase base;
	public List<CbproHistoricrates> pointList = new ArrayList<CbproHistoricrates>();
	public List<CatUnit> catUnitList = new ArrayList<CatUnit>();
	
	public void baseUnitCirculationLoopOpen(CatBase catBase,CbproHistoricrates amout){
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				if (pointList.size()>=10) {
					pointList.remove(0);
				}
				
				pointList.add(amout);
				
				
				JSONArray array = new JSONArray();
				if (catBase.getSlopecount()==0&&catBase.getSlopevalue()==0) {
					//取消斜率
					for (int i = 0; i < pointList.size(); i++) {
						JSONObject data = new JSONObject();
						data.put("price", pointList.get(i).getOpen());
						data.put("slope", 0);
						data.put("bid", pointList.get(i).getLow());
						data.put("ask", pointList.get(i).getHigh());

						array.add(data);
					}
				}else {
					//生成斜率 
					List<Double> slope = getSlopeArray(pointList, (int)catBase.getSlopecount());
					for (int i = 0; i < pointList.size(); i++) {
						JSONObject data = new JSONObject();
						data.put("price", pointList.get(i));
						data.put("slope", slope.get(i));
						data.put("bid", pointList.get(i).getLow());
						data.put("ask", pointList.get(i).getHigh());
						
						array.add(data);
					}
				}
				
				executionCatUnits(catBase, catUnitList, array.getJSONObject(array.size()-1));
				
			}
		}).start();
		
	}
	


	
	public void executionCatUnits(CatBase catBase,List<CatUnit> catUnitsManage,JSONObject point){
		//3.获取最新价格，调动指定单元执行运行
		
		Double slopevalue = point.getDouble("slope");
		Double pricevalue = point.getDouble("price");
		Double bid = point.getDouble("bid");
		Double ask = point.getDouble("ask");

		for (int k = 0; k < catUnitsManage.size(); k++) {
			CatUnit unit = catUnitsManage.get(k);

			//大于卖出区间
			if (pricevalue>unit.getMarkRangeSellPrice()) {
				//不看怀孕标签，只看是否买入标签
				if (unit.getAlreadybought()==1) {
					//浮动区间判断
					if (catBase.getTrendbuy()!=0&&catBase.getTrendsell()!=0) {
						if (pricevalue>=catBase.getTrendbuy()&&pricevalue<=getBase().getTrendsell()) {
							if (catBase.getTrendtype()==1) {
								catBase.setTrendtype(0);
								String[] coulmn = new String[]{String.valueOf(catBase.getTrendtype()),String.valueOf(catBase.getBid())};
								int[] type = new int[]{Types.INTEGER, Types.INTEGER};
								DBUtils.updateData("update catbases set trendtype = ? where bid = ?",coulmn,type);
							}
						}else {
							if (catBase.getTrendtype()==0) {
								catBase.setTrendtype(1);
								String[] coulmn = new String[]{String.valueOf(catBase.getTrendtype()),String.valueOf(catBase.getBid())};
								int[] type = new int[]{Types.INTEGER, Types.INTEGER};
								DBUtils.updateData("update catbases set trendtype = ? where bid = ?",coulmn,type);
							}
						}
					}else {
						if (catBase.getTrendtype()==1) {
							catBase.setTrendtype(0);
							String[] coulmn = new String[]{String.valueOf(catBase.getTrendtype()),String.valueOf(catBase.getBid())};
							int[] type = new int[]{Types.INTEGER, Types.INTEGER};
							DBUtils.updateData("update catbases set trendtype = ? where bid = ?",coulmn,type);
						}
					}
					unit.unitAnalysisData(catBase,unit,slopevalue,pricevalue,bid,ask);
				}
				
			}else if(((pricevalue)>=unit.getMarkRangeBuyPrice()&&pricevalue<=unit.getMarkRangeSellPrice())||(unit.getMarkRangeTags()==1)) {
				//浮动区间判断
				if (catBase.getTrendbuy()!=0&&catBase.getTrendsell()!=0) {
					if (pricevalue>=catBase.getTrendbuy()&&pricevalue<=getBase().getTrendsell()) {
						if (catBase.getTrendtype()==1) {
							catBase.setTrendtype(0);
							String[] coulmn = new String[]{String.valueOf(catBase.getTrendtype()),String.valueOf(catBase.getBid())};
							int[] type = new int[]{Types.INTEGER, Types.INTEGER};
							DBUtils.updateData("update catbases set trendtype = ? where bid = ?",coulmn,type);
						}
					}else {
						if (catBase.getTrendtype()==0) {
							catBase.setTrendtype(1);
							String[] coulmn = new String[]{String.valueOf(catBase.getTrendtype()),String.valueOf(catBase.getBid())};
							int[] type = new int[]{Types.INTEGER, Types.INTEGER};
							DBUtils.updateData("update catbases set trendtype = ? where bid = ?",coulmn,type);
						}
					}
				}else {
					if (catBase.getTrendtype()==1) {
						catBase.setTrendtype(0);
						String[] coulmn = new String[]{String.valueOf(catBase.getTrendtype()),String.valueOf(catBase.getBid())};
						int[] type = new int[]{Types.INTEGER, Types.INTEGER};
						DBUtils.updateData("update catbases set trendtype = ? where bid = ?",coulmn,type);
					}
				}

				unit.unitAnalysisData(catBase,unit,slopevalue,pricevalue,bid,ask);
			}
		}
	}
	
	
	
	
	//根据机主生成单元
	public List<CatUnit> generatingCatUnits(CatBase catBase){
		
		
		if (this.catUnitList.size()==0) {
			
			JSONArray dataArray = DBUtils.searchData("select * from catunits where bid ="+catBase.getBid());
			if (dataArray.size()!=0) {
				//执行单元
				this.catUnitList = JsonUtil.toList(dataArray, CatUnit.class);
				for (int i = 0; i < this.catUnitList.size(); i++) {
					CatUnit u = this.catUnitList.get(i);
					u.setTradestatus("done");
				}
			}else {
				//创建单元
				//1.根据机组生成单元，按价格分段
				List<Double> unitslog = new ArrayList<Double>();
				double startPrice = catBase.getBuyPrice();
				unitslog.add(startPrice);
				
				while (startPrice<catBase.getSellPrice()) {
					startPrice = startPrice*(1+catBase.getProfitMargins());
					unitslog.add(startPrice);
				}
				
				//2.找妈妈分钱
				//妈妈去找银行要钱 每个孩子1000元
//				CatBank bank = new CatBank();
//				bank.setTotalassets(unitslog.size()*catBase.getUnitavgcurrency());
				
				
				catBase.setUnitcount(unitslog.size());
				//3.修改机主分钱信息
				String[] ucoulmn = new String[]{String.valueOf(catBase.getUnitcount()),String.valueOf(catBase.getUnitavgcurrency()),String.valueOf(catBase.getBid())};
				int[] utype = new int[]{Types.INTEGER, Types.DOUBLE, Types.INTEGER};
				DBUtils.updateData("update catbases set unitcount = ?,unitavgcurrency = ? where bid = ?",ucoulmn,utype);
				
				//4.生成单元
				//添进单元数组集管理
				for (int j = 0; j < unitslog.size(); j++) {
					double pricelog = unitslog.get(j);
					CatUnit unCatUnit = new CatUnit();
					unCatUnit.setBid(catBase.getBid());
					unCatUnit.setUnitbuyPrice(pricelog);
					unCatUnit.setMarkRangeBuyPrice(pricelog*(1-catBase.getEarlyBuyspace()));
					double markRangeSellPrice = unCatUnit.getUnitbuyPrice()*(1+catBase.getProfitMargins());
					unCatUnit.setMarkRangeSellPrice(markRangeSellPrice);
					unCatUnit.setMarkRangeTags(0);

					String uid = String.valueOf(pricelog)+String.valueOf(new Date().getTime());
					unCatUnit.setUid(uid);
					unCatUnit.setCurrencycount(catBase.getUnitavgcurrency());
					
					unCatUnit.setPreviousvalue(0.0);
					unCatUnit.setAlreadybought(0);
					unCatUnit.setTradestatus("done");
					
			        String[] coulmn = new String[]{String.valueOf(catBase.getBid()),uid,String.valueOf(pricelog),String.valueOf(unCatUnit.getBasecount()),
			        		String.valueOf(unCatUnit.getCurrencycount()),"0",String.valueOf(unCatUnit.getMarkRangeBuyPrice()),
			        		String.valueOf(unCatUnit.getMarkRangeSellPrice()),String.valueOf(unCatUnit.getMarkRangeTags()),
			        		String.valueOf(unCatUnit.getPreviousvalue()),String.valueOf(unCatUnit.getAlreadybought()),"0","0","0","0","0","0","0","0","","",unCatUnit.getTradestatus()};
			        int[] type = new int[]{Types.INTEGER,Types.CHAR, Types.DOUBLE,Types.DOUBLE,Types.DOUBLE,Types.INTEGER,Types.DOUBLE,Types.DOUBLE,Types.INTEGER,Types.DOUBLE,Types.INTEGER,Types.DOUBLE,Types.DOUBLE,Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.DOUBLE,Types.DOUBLE,Types.CHAR,Types.CHAR,Types.CHAR};
					DBUtils.intsertData("insert into catunits(bid,uid,unitbuyPrice,basecount,currencycount,currencyAccountBool,markRangeBuyPrice,markRangeSellPrice,markRangeTags,previousvalue,alreadybought,unitsellPrice,basebuyPrice,belowline,aboveline,safebelowline,safeaboveline,unitbuytotal,unitselltotal,buyOrderid,sellOrderid,tradestatus)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", coulmn, type);
					this.catUnitList.add(unCatUnit);
				}
			}
		}
		return this.catUnitList;
	}




	public List<CbproHistoricrates> getPointList() {
		return pointList;
	}




	public void setPointList(List<CbproHistoricrates> pointList) {
		this.pointList = pointList;
	}
	
	public List<Double> getSlopeArray(List<CbproHistoricrates> data,int slopeconfig){
		//计算斜率(直接算斜率)
		
		List<Double> slope = new ArrayList<Double>();
		
		for (int i = 0; i < data.size(); i++) {
			
			if (i==0) {
				//第一个点没有斜率
				slope.add(0, 0.0);
			}else{
				double previousAvgY=0;
				double previousAvgX=0;
				
				if (slopeconfig>i) {
					//2 , 1
					previousAvgY = Double.valueOf(data.get(0).getOpen());
					previousAvgX = 1;
					
				}else {
					previousAvgY = Double.valueOf(data.get(i-slopeconfig).getOpen());;
					previousAvgX = (i-slopeconfig)+1;
				}
				
				
				double nowAvgY = Double.valueOf(data.get(i).getOpen());
				double nowAvgX = i+1;    
				
				double slopvalue = (nowAvgY-previousAvgY)/(nowAvgX-previousAvgX);
				slope.add(slopvalue);
			}
			
		}
		return slope;
	}




	public List<CatUnit> getCatUnitList() {
		return catUnitList;
	}




	public void setCatUnitList(List<CatUnit> catUnitList) {
		this.catUnitList = catUnitList;
	}




	public CatBase getBase() {
		return base;
	}




	public void setBase(CatBase base) {
		this.base = base;
	}
	
	
	
	
	
	
}
