package com.base.util;

import java.sql.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.base.main.CoinbaseClient;


public class Get50CurrenciesUtil {
	 private static Get50CurrenciesUtil config;
	 private static Integer cacheTime;

	 
	    static {
	        config = new Get50CurrenciesUtil();
	    }
	    
	    
		/** Object TO Json String 字符串输出 */
		public static void getBitstampPrices(final String base, final String currency, int delay, int cacheTime) {
			Timer timerspot = new Timer();
			// (TimerTask task, long delay, long period)任务，延迟时间，多久执行
			timerspot.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					try {
						
						CoinbaseClient.GetDataFrompythonserver("bitstamp",
								"", base, currency, "ohlc_price");
						Thread.sleep(5000);
							
						CoinbaseClient.GetDataFrompythonserver("bitstamp",
								"", "ltc", "eur", "ohlc_price");
						Thread.sleep(5000);
						
						CoinbaseClient.GetDataFrompythonserver("bitstamp",
								"", "eth", "eur", "ohlc_price");
						Thread.sleep(5000);
						
						CoinbaseClient.GetDataFrompythonserver("bitstamp",
								"", "bch", "eur", "ohlc_price");
						Thread.sleep(5000);
						
						CoinbaseClient.GetDataFrompythonserver("bitstamp",
								"", "xlm", "eur", "ohlc_price");
						Thread.sleep(5000);


					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}, delay, cacheTime);

		}    
	    
		
		public static void getTradePrices(final String granularity,
				final String base, final String currency, int delay, int cacheTime) {
			Timer timerspot = new Timer();
			// (TimerTask task, long delay, long period)任务，延迟时间，多久执行
			timerspot.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					try {
						int sleeptime = 1500;
						CoinbaseClient.GetDataFrompythonserver("cbpro",
								granularity, base, currency, "book_level1");
						Thread.sleep(sleeptime);
						CoinbaseClient.GetDataFrompythonserver("cbpro",
								granularity, "LTC", "BTC", "book_level1");
						Thread.sleep(sleeptime);
						CoinbaseClient.GetDataFrompythonserver("cbpro",
								granularity, "OMG", "EUR", "book_level1");
						Thread.sleep(sleeptime);
						CoinbaseClient.GetDataFrompythonserver("cbpro",
								granularity, "EOS", "EUR", "book_level1");
						Thread.sleep(sleeptime);
//						CoinbaseClient.GetDataFrompythonserver("cbpro",
//								granularity, "NMR", "EUR", "book_level1");
						Thread.sleep(sleeptime);
						CoinbaseClient.GetDataFrompythonserver("cbpro",
								granularity, "ZRX", "EUR", "book_level1");
						Thread.sleep(sleeptime);
						CoinbaseClient.GetDataFrompythonserver("cbpro",
								granularity, "BCH", "BTC", "book_level1");
						Thread.sleep(sleeptime);
						CoinbaseClient.GetDataFrompythonserver("cbpro",
								granularity, "ETC", "EUR", "book_level1");
						Thread.sleep(sleeptime);
						CoinbaseClient.GetDataFrompythonserver("cbpro",
								granularity, "XTZ", "EUR", "book_level1");
						Thread.sleep(sleeptime);
						CoinbaseClient.GetDataFrompythonserver("cbpro",
								granularity, "UMA", "EUR", "book_level1");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}, delay, cacheTime);

		}	
		
		
	    
	/** Object TO Json String 字符串输出 */
	public static void getCBproPrices(final String granularity,
			final String base, final String currency, int delay, int cacheTime) {
		Timer timerspot = new Timer();
		// (TimerTask task, long delay, long period)任务，延迟时间，多久执行
		timerspot.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				try {
					int sleeptime = 3000;
//					CoinbaseClient.GetDataFrompythonserver("cbpro",
//							granularity, base, currency, "ticker");
					if (granularity.equals("60")) {
 						Thread.sleep(1000);
 						CoinbaseClient.GetDataFrompythonserver("cbpro", "",
 									base, currency, "order_book");
					}
					Thread.sleep(sleeptime);
					CoinbaseClient.GetDataFrompythonserver("cbpro",
							granularity, "ETH", "EUR", "ticker");
					Thread.sleep(sleeptime);
//					CoinbaseClient.GetDataFrompythonserver("cbpro",
//							granularity, "LTC", "EUR", "ticker");
					Thread.sleep(sleeptime);
//					CoinbaseClient.GetDataFrompythonserver("cbpro",
//							granularity, "BCH", "EUR", "ticker");
					Thread.sleep(sleeptime);
					CoinbaseClient.GetDataFrompythonserver("cbpro",
							granularity, "XLM", "EUR", "ticker");
					Thread.sleep(sleeptime);
					CoinbaseClient.GetDataFrompythonserver("cbpro",
							granularity, "LINK", "EUR", "ticker");
					Thread.sleep(sleeptime);
					CoinbaseClient.GetDataFrompythonserver("cbpro",
							granularity, "ALGO", "EUR", "ticker");
					Thread.sleep(sleeptime);
					CoinbaseClient.GetDataFrompythonserver("cbpro",
							granularity, "BAND", "EUR", "ticker");
					Thread.sleep(sleeptime);
					CoinbaseClient.GetDataFrompythonserver("cbpro",
							granularity, "CGLD", "EUR", "ticker");
					Thread.sleep(sleeptime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, delay, cacheTime);

	}
	    
	    
	    /** Object TO Json String 字符串输出 */
	    public static void getCurrenciesPrices(final String base,final String currency) {
			Timer timerspot = new Timer();
			// (TimerTask task, long delay, long period)任务，延迟时间，多久执行
			timerspot.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					CoinbaseClient.GetDataFrompythonserver("coinbase", "", base, currency, "");
				}
			}, 0, 1000 * 60);
			
	    }
	    
	    
	    
		public static void startTimerGetCurrenciesPrices() {
			// TODO Auto-generated method stub
			
			//Trade
			getTradePrices("60","BTC", "EUR",0,1000*30);
			
			//Bitstamp
			getBitstampPrices("btc", "eur", 0, 1000*60);

			//Cbpro
			getCBproPrices("60","BTC", "EUR",0,1000*60);


			
			
			//Coinbase
			//1、BTC
//	    	getCurrenciesPrices("BTC", "EUR");
//	    	//2、ETH
//	    	getCurrenciesPrices("ETH", "EUR");
////	    	//3、XRP
//	    	getCurrenciesPrices("XRP", "EUR");
////	    	//4、BCH
//	    	getCurrenciesPrices("BCH", "EUR");
////	    	//5、LTC
//	    	getCurrenciesPrices("LTC", "EUR");
////	    	//6、EOS
//	    	getCurrenciesPrices("EOS", "EUR");
////	    	//7、XTZ
//	    	getCurrenciesPrices("XTZ", "EUR");
////	    	//8、LINK
//	    	getCurrenciesPrices("LINK", "EUR");
////	    	//9、XLM
//	    	getCurrenciesPrices("XLM", "EUR");
////	    	//10、ETC
//	    	getCurrenciesPrices("ETC", "EUR");
////	    	//11、DASH
//	    	getCurrenciesPrices("DASH", "EUR");

			
	    	

		}
	    
	    
	    
}
