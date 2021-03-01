package com.base.entity;

public class BitstampCurrencyData {
	
	public String Website;    // 网站
    public String Cbtype;  //操作类型
    public String Virtualcurrency;  //虚拟货比
    public String Exchangecurrency; //兑换币种
    
    
    
    
	public BitstampCurrencyData(String website, String cbtype,
			String virtualcurrency, String exchangecurrency) {
		super();
		Website = website;
		Cbtype = cbtype;
		Virtualcurrency = virtualcurrency;
		Exchangecurrency = exchangecurrency;
	}
	
	
	public String getWebsite() {
		return Website;
	}
	public void setWebsite(String website) {
		Website = website;
	}
	public String getCbtype() {
		return Cbtype;
	}
	public void setCbtype(String cbtype) {
		Cbtype = cbtype;
	}
	public String getVirtualcurrency() {
		return Virtualcurrency;
	}
	public void setVirtualcurrency(String virtualcurrency) {
		Virtualcurrency = virtualcurrency;
	}
	public String getExchangecurrency() {
		return Exchangecurrency;
	}
	public void setExchangecurrency(String exchangecurrency) {
		Exchangecurrency = exchangecurrency;
	}
    
    
    

}
