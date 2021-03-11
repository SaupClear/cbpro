package com.base.main;

import java.io.DataInputStream;//导入DataInputStream类


import java.io.DataOutputStream;//导入DataOutputStream
import java.io.IOException;//导入IOException类
import java.net.Socket;//导入Socket类
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.db.DBManager;
import com.base.util.Get50CurrenciesUtil;
import com.base.util.JsonUtil;
import com.base.db.DBUtils;
import com.base.entity.Amount;
import com.base.entity.BitstampCurrencyData;
import com.base.entity.BitstampOhlc;
import com.base.entity.CatBase;
import com.base.entity.CatUnit;
import com.base.entity.CbproHistoricrates;
import com.base.entity.CBproCurrencydata;
import com.base.entity.CbproOrderbook;
import com.base.entity.Currencydata;
import com.google.gson.JsonArray;
//1101 coinbase 
//1102 cbpro
//1103 bitstamp 

public class CoinbaseClient  {


	private String host = "5.148.183.68";// 默认连接到本机
	private int port = 8189;// 默认连接到端口8189
    private Thread thread;//循环发送心跳包的线程\
    
    private Socket socket;
    
    private static HashMap<String, String> lastprice_cbproMap;
    

    
    public CoinbaseClient() {
    }

    // 连接到指定的主机和端口
    public CoinbaseClient(String host, int port) {//构造方法
        this.host = "5.148.183.68";//将构造方法的参数host传递给类变量host
        //this.host = "localhost";//将构造方法的参数host传递给类变量host
        this.port = 8189;//将构造方法的参数port传递给类变量port
    }
    
    
    public static List<Date> findDates(Date dBegin, Date dEnd) {
        List<Date> lDate = new ArrayList<Date>();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
    // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
    // 使用给定的 Date 设置此 Calendar 的时间
          calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime()))  {
         // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
             calBegin.add(Calendar.DAY_OF_MONTH, 1);
             lDate.add(calBegin.getTime());
        }
        return lDate;
   }

    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stu
		
		
		JSONArray units = DBUtils.searchData("select * from catunits where tradestatus != 'done'");
		List<CatUnit> ulist =  JsonUtil.toList(units, CatUnit.class);
		
		for (int i = 0; i < ulist.size(); i++) {
			CatUnit unit = ulist.get(i);
			System.out.print("Start for the first time:"+unit.getBid()+"-"+unit.getTradestatus()+"\n");
			unit.setTradestatus("done");
			String[] ucoulmn = new String[]{String.valueOf(unit.getTradestatus()),String.valueOf(unit.getUid())};
			int[] utype = new int[]{Types.CHAR,Types.CHAR};
			DBUtils.updateData("update catunits set tradestatus = ? where uid = ?",ucoulmn,utype);
		}
		
		
//		JSONArray bases = DBUtils.searchData("select * from catbases");
//		List<CatBase> list_bases =  JsonUtil.toList(bases, CatBase.class);
//
//		for (int i = 0; i < list_bases.size(); i++) {
//			CatBase b = list_bases.get(i);
//			if (b.getTrendbuy()==0&&b.getTrendsell()==0) {
//				b.setTrendbuy(b.getBuyPrice());
//				b.setTrendsell(b.getSellPrice());
//				String[] ucoulmn = new String[]{String.valueOf(b.getTrendbuy()),String.valueOf(b.getTrendsell()),String.valueOf(b.getBid())};
//				int[] utype = new int[]{Types.DOUBLE,Types.DOUBLE,Types.INTEGER};
//				DBUtils.updateData("update catbases set trendbuy = ?,trendsell = ? where bid = ?",ucoulmn,utype);
//			}
//		}
		
		lastprice_cbproMap = new HashMap<String, String>();
		Get50CurrenciesUtil.startTimerGetCurrenciesPrices();
		
	}
    
    
//	public String startHeartbeatThreadSocket() {
//    	String jsonString = "";
//    	
//        try {
//        	Socket socket = new Socket(host, port);
//        	socket.setSoTimeout(15000);
//
//            // 连接到服务器
//            if(!socket.getKeepAlive()) socket.setKeepAlive(true);   
//            if(!socket.getOOBInline())socket.setOOBInline(true);
//                
//            try {
//                DataInputStream in = new DataInputStream(socket
//                        .getInputStream());// 读取服务器端传过来信息的DataInputStream
//
//                DataOutputStream out = new DataOutputStream(socket
//                        .getOutputStream());// 向服务器端发送信息的DataOutputStrea
//                
//                String send = "Heartbeat bag";
//                byte q[] = send.getBytes();
//                out.write(q);//将客户端的信息传递给服务器             
//                 
//
//                byte[] b = new byte[1024];
//                in.read(b);
//				String str = new String(b);
//				String strdata = str.substring(0,str.indexOf("\n"));
//
//        	
//        		char s =strdata.trim().charAt(0); 
//        		if(s==65279){ 
//        			  if(str.length()>1){ 
//        			     str=str.substring(1); 
//        			  }	 
//        		}
//        		
//        		jsonString = str;//输出来自服务器的信息
//
//          
//            } finally {
//                socket.close();
//            }
//        } catch (IOException e) {//捕获异常
//        	System.out.print("Socket IOException");
//            e.printStackTrace();
//        }
//		return jsonString;
//	}
    
    
	public int getValidLength(byte[] bytes){
	    int i = 0;
	    if (null == bytes || 0 == bytes.length)
	        return i ;
	    for (; i < bytes.length; i++) {
	        if (bytes[i] == '\0')
	            break;
	    }
	    return i + 1;
	}
	
	
    public String chat(Object cdata) {//chat方法
    	
    	String jsonString = "";
    	
        try {
        	Socket socket = new Socket(host, port);
        	socket.setSoTimeout(0);
        	
            // 连接到服务器
            if(!socket.getKeepAlive())socket.setKeepAlive(true);   
            if(!socket.getOOBInline())socket.setOOBInline(true);
                
            DataInputStream in = new DataInputStream(socket
                    .getInputStream());// 读取服务器端传过来信息的DataInputStream
            DataOutputStream out = new DataOutputStream(socket
                    .getOutputStream());// 向服务器端发送信息的DataOutputStrea
            
            try {

                
                String send = JsonUtil.toJSON(cdata);//读取控制台输入的内容
                
                
                byte q[] = send.getBytes();
                out.write(q);//将客户端的信息传递给服务器             
                 

                byte[] b = new byte[1024*1024];
                in.read(b);
        		String str = new String(b);
        		
				String strdata = "";
				
				if (str!=null&&str.length()>1) {
					try {
						int location = str.indexOf("\n");
						if (location!=-1) {
		    				strdata = str.substring(0,str.indexOf("\n"));
						}
					} catch (Exception e) {
						// TODO: handle exception
				        System.out.print("strdata = str.substring(0,str.indexOf\n):\n"+e);
				        return "";
					}
				}
				

        		try {
        			if (strdata.trim().length()>1) {
        				char s =strdata.trim().charAt(0); 
                		if(s==65279){ 
                			  if(strdata.length()>1){ 
                				  strdata=strdata.substring(1); 
                			  }	 
                		}
					}
            		jsonString = strdata;//输出来自服务器的信息
        		} catch (Exception e) {
					// TODO: handle exception
			        System.out.print("strdata=strdata.substring(1);\n "+e);
			        return "";
				}
				
        		

          
            } finally {
            	in.close();
            	out.close();
                socket.close();
            }
        } catch (IOException e) {//捕获异常
	        System.out.print("Socket IOException:");
            e.printStackTrace();
        }
		return jsonString;
    
    }

	private static void intsertCoinbaseData(Amount amount)
	{    
	    String[] coulmn = new String[]{"1101",  amount.getOperation(), amount.getBase(),amount.getCurrency(),amount.getAmount(),getnewstdate()};
	    int[] type = new int[]{Types.INTEGER, Types.CHAR, Types.CHAR,Types.CHAR,Types.CHAR,Types.CHAR};
	 
	    boolean flag = DBUtils.intsertData("insert into coinbasedata(wid,operation,cbbase,currency,amount,cbtime)values(?,?,?,?,?,?)",coulmn, type);
	    if(flag){};
	
	}
    

	private static void insertCBprotHistoricratesData(CbproHistoricrates amount)
	{    


		String[] coulmn = new String[]{"1102",amount.getTimeperiod(),amount.getBase(),amount.getCurrency(),amount.getTime(), amount.getLow(),amount.getHigh(),amount.getOpen(),amount.getClose(),amount.getVolume()};
		int[] type = new int[]{Types.INTEGER, Types.CHAR,Types.CHAR,Types.CHAR,Types.CHAR,Types.DOUBLE,Types.DOUBLE,Types.DOUBLE,Types.DOUBLE,Types.DOUBLE};


		boolean flag = DBUtils.intsertData("insert into cbpro_historicrates(wid,cbtimeperiod,cbbase,cbcurrency,cbtime,cblow,cbhigh,cbopen,cbclose,cbvolume)values(?,?,?,?,?,?,?,?,?,?)",coulmn, type);
		if(flag==false){
			return;
		};



		String productid = amount.getBase().toUpperCase()+"-"+amount.getCurrency().toUpperCase();

		JSONArray bases = DBUtils.searchData("select * from catbases where working ='yes' and productid = '"+productid+"'");
		List<CatBase> list_bases =  JsonUtil.toList(bases, CatBase.class);

		for (int i = 0; i < list_bases.size(); i++) {
			CatBase b = list_bases.get(i);
			searchBasesgo(amount, b.getThreadid(), b.getBid());
		}

		//        	Cats cats = catsMap.get("1001097");
		//        	searchBasesgo(cats, amount, "1001097", 3);
		//  
		//          	Cats cats2 = catsMap.get("1001098");
		//        	searchBasesgo(cats2, amount, "1001098",4);



	}
	
	private static void inserCbproOrderbooktData(CbproOrderbook orderbook)
    {    
        String[] coulmn = new String[]{"1102",getnewstdate(),orderbook.getBase(),orderbook.getCurrency(),orderbook.getSequence(),orderbook.getBids().toString(),orderbook.getAsks().toString()};
        int[] type = new int[]{Types.INTEGER,Types.CHAR,Types.CHAR,Types.CHAR,Types.CHAR, Types.CHAR, Types.CHAR};
        boolean flag = DBUtils.intsertData("insert into cbpro_orderbook(wid,cbtime,cbbase,cbcurrency,cbsequence,cbbids,cbasks)values(?,?,?,?,?,?,?)",coulmn, type);
        if(flag){};
        
    }
	
	private static void insertBitstampOhlcData(BitstampOhlc bOhlc)
    {    
        String[] coulmn = new String[]{"1103","60",bOhlc.getBase(),bOhlc.getCurrency(),bOhlc.getTimestamp(), bOhlc.getLow(),bOhlc.getHigh(),bOhlc.getOpen(),bOhlc.getClose(),bOhlc.getVolume()};
        int[] type = new int[]{Types.INTEGER, Types.CHAR,Types.CHAR,Types.CHAR,Types.CHAR,Types.DOUBLE,Types.DOUBLE,Types.DOUBLE,Types.DOUBLE,Types.DOUBLE};
        boolean flag = DBUtils.intsertData("insert into cbpro_historicrates(wid,cbtimeperiod,cbbase,cbcurrency,cbtime,cblow,cbhigh,cbopen,cbclose,cbvolume)values(?,?,?,?,?,?,?,?,?,?)",coulmn, type);
        if(flag){};
       
    }
	


	public static void GetDataFrompythonserver(String website,String granularity, String base, String currency, String cbtype) {
		try {

			
			if(website.equals("bitstamp")){
				
				if (cbtype.equals("ohlc_price")) {
					BitstampCurrencyData bdata = new BitstampCurrencyData(website,cbtype, base, currency);
					String dataString = new CoinbaseClient("", 0).chat(bdata);
					
					JSONArray array = JsonUtil.tojsonArray(dataString);
					if (array==null||array.size()==0) {
						return;
					}
					
					JSONObject btdata = array.getJSONObject(0);
					String time = formatdate(btdata.getLongValue("timestamp"));
					String low = btdata.getString("low");
					String high = btdata.getString("high");
					String open = btdata.getString("open");
					String close = btdata.getString("close");
					String volume = btdata.getString("volume");

					BitstampOhlc biOhlc = new BitstampOhlc();
					biOhlc.setBase(base);
					biOhlc.setTimeperiod("60");
					biOhlc.setCurrency(currency);
					biOhlc.setTimestamp(time);
					biOhlc.setLow(low);
					biOhlc.setHigh(high);
					biOhlc.setOpen(open);
					biOhlc.setClose(close);
					biOhlc.setVolume(volume);
					
					insertBitstampOhlcData(biOhlc);

				}
				
				
			} else if (website.equals("cbpro")) {

				
				if (cbtype.equals("ticker")) {

					CBproCurrencydata cdata = new CBproCurrencydata(website,
							granularity, cbtype, base, currency);

					String dataString = new CoinbaseClient("", 0).chat(cdata);
					JSONObject obj = JsonUtil.toJSONObject(dataString);
					if (obj==null) {
						
						String openString = lastprice_cbproMap.get(base);
						
						if(openString==null){
							return;	
						}
						
						String low = openString;
						String high = openString;
						String open = openString;
						String close = openString;
						String volume = "0";

	 					CbproHistoricrates amount = new CbproHistoricrates();
						amount.setBase(base);
						amount.setCurrency(currency);
						amount.setTimeperiod("60");
						amount.setTime(getnewstdateMinute());
						amount.setLow("0");
						amount.setHigh("0");
						amount.setOpen("0");
						amount.setClose("0");
						amount.setVolume("0");

	 					insertCBprotHistoricratesData(amount);
						
						return;
					}
					
					String open = obj.getString("price");
					lastprice_cbproMap.put(base, open);
					
 					CbproHistoricrates amount = new CbproHistoricrates();
					amount.setBase(base);
					amount.setCurrency(currency);
					amount.setOpen(open);
					amount.setLow("0");
					amount.setHigh("0");
					amount.setClose("0");
					amount.setVolume("0");
					amount.setTimeperiod("60");
					amount.setTime(getnewstdateMinute());
				
 					insertCBprotHistoricratesData(amount);
				}
				
				
				
				if (cbtype.equals("historic_rates")) {

					CBproCurrencydata cdata = new CBproCurrencydata(website,
							granularity, cbtype, base, currency);

					String dataString = new CoinbaseClient("", 0).chat(cdata);
					JSONArray array = JsonUtil.tojsonArray(dataString);
					if (array.size()==0) {
						
						String openString = lastprice_cbproMap.get(base);
						
						if(openString==null){
							return;	
						}
						
						String low = openString;
						String high = openString;
						String open = openString;
						String close = openString;
						String volume = "0";

	 					CbproHistoricrates amount = new CbproHistoricrates();
						amount.setBase(base);
						amount.setCurrency(currency);
						amount.setTimeperiod(granularity);
						amount.setTime(getnewstdateMinute());
						amount.setLow(low);
						amount.setHigh(high);
						amount.setOpen(open);
						amount.setClose(close);
						amount.setVolume(volume);

	 					insertCBprotHistoricratesData(amount);
						
						return;
					}
					
					JSONArray cbdata = array.getJSONArray(0);
					Integer time = cbdata.getInteger(0);
					String low = cbdata.getString(1);
					String high = cbdata.getString(2);
					String open = cbdata.getString(3);
					String close = cbdata.getString(4);
					String volume = cbdata.getString(5);

					lastprice_cbproMap.put(base, open);
					
 					CbproHistoricrates amount = new CbproHistoricrates();
					amount.setBase(base);
					amount.setCurrency(currency);
					amount.setTimeperiod(granularity);
					amount.setTime(formatdate(time));
					amount.setLow(low);
					amount.setHigh(high);
					amount.setOpen(open);
					amount.setClose(close);
					amount.setVolume(volume);

 					insertCBprotHistoricratesData(amount);
				}

				if (cbtype.equals("order_book")) {

					CBproCurrencydata cdata = new CBproCurrencydata(website,
							granularity, cbtype, base, currency);
					String dataString = new CoinbaseClient("", 0).chat(cdata);
					
					CbproOrderbook book = JsonUtil.toBean(dataString,
							CbproOrderbook.class);
					if (book==null) {
						return;
					}
					book.setBase(base);
					book.setCurrency(currency);
					book.setCbtime(getnewstdate());
					inserCbproOrderbooktData(book);

				}
				
				if (cbtype.equals("book_level1")) {

					CBproCurrencydata cdata = new CBproCurrencydata(website,
							granularity, cbtype, base, currency);
					String dataString = new CoinbaseClient("", 0).chat(cdata);
					
					CbproOrderbook book = JsonUtil.toBean(dataString,
							CbproOrderbook.class);
					if (book==null) {
						return;
					}
					
					
					JSONArray bids = book.getBids();
					JSONArray asks = book.getAsks();
					
 					CbproHistoricrates amount = new CbproHistoricrates();
 					
					if (bids!=null&&asks!=null&&bids.size()!=0&&asks.size()!=0) {
						
						JSONArray bid = bids.getJSONArray(0);
						JSONArray ask = asks.getJSONArray(0);

						
						if (bid!=null&&ask!=null&&bid.size()!=0&&ask.size()!=0) {
							
							String bprice  = bid.getString(0);
							String aprice = ask.getString(0);
							
							amount.setBase(base);
							amount.setCurrency(currency);
							

							Double priceDouble =  (Double.valueOf(bprice)+Double.valueOf(aprice))/2;
							
							amount.setOpen(String.valueOf(priceDouble));
							amount.setLow(bprice);
							amount.setHigh(aprice);
							amount.setClose("0");
							amount.setVolume("0");
							amount.setTimeperiod("60");
							amount.setTime(getnewstdateMinute());

		 					insertCBprotHistoricratesData(amount);
							
						}

					}

				}
				

			} else if (website.equals("coinbase")) {

				Currencydata cdata = new Currencydata("coinbase", "SPOT", base,
						currency, "");
				if (cdata.getWebsite().equals("coinbase")) {
					String dataString = new CoinbaseClient("", 0).chat(cdata);
					// System.out.println(dataString);// 输出来自服务器的信息
					Amount amount = JsonUtil.toBean(dataString, Amount.class);
					if (amount==null) {
						return;
					}
					amount.setOperation("SPOT");
					intsertCoinbaseData(amount);

				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	public static String getnewstdate() {
		Calendar cd = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		return sdf.format(cd.getTime());
	}

	public static String formatdate(long longtime) {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.US);
         String date = sdf.format(new Date(longtime * 1000L));
		 return date;
	}
	public static String getnewstdateMinute() {
		Calendar cd = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:00", Locale.US);
		return sdf.format(cd.getTime());
	}
	
	
	public static void searchBasesgo(CbproHistoricrates amount,String mapid,int bid){


		Cats cats = new Cats();
		JSONArray bases = DBUtils.searchData("select * from catbases where bid = "+bid);
		List<CatBase> list =  JsonUtil.toList(bases, CatBase.class);

		JSONArray units = DBUtils.searchData("select * from catunits where bid = "+bid);
		List<CatUnit> ulist =  JsonUtil.toList(units, CatUnit.class);
		

		if (list.size()>0&&ulist.size()==0) {
			CatBase catBase = list.get(0);
			cats.setBase(catBase);
			cats.generatingCatUnits(catBase);
			cats.baseUnitCirculationLoopOpen(catBase, amount);
			
		}else if (list.size()>0&&ulist.size()>0) {
			CatBase catBase = list.get(0);
			cats.setBase(catBase);
			cats.setCatUnitList(ulist);
			cats.baseUnitCirculationLoopOpen(cats.getBase(), amount);
		}
		
	}

	

	
	
}





