package com.base.entity;

public class BitstampCurrencyData {
	
	public String Website;    // ��վ
    public String Cbtype;  //��������
    public String Virtualcurrency;  //�������
    public String Exchangecurrency; //�һ�����
    
    
    
    
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
