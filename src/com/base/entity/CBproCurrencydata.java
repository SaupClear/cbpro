package com.base.entity;

public class CBproCurrencydata {
	
	
	public String Website;    // ��վ
    public String Granularity;  //��������
    public String Cbtype;  //��������
    public String Virtualcurrency;  //�������
    public String Exchangecurrency; //�һ�����
    
   
    public CBproCurrencydata(String website, String granularity,String cbtype,String virtualcurrency, String exchangecurrency) {
		super();
		Website = website;
		Granularity = granularity;
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

	public String getGranularity() {
		return Granularity;
	}

	public void setGranularity(String granularity) {
		Granularity = granularity;
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


	public String getCbtype() {
		return Cbtype;
	}

	public void setCbtype(String cbtype) {
		Cbtype = cbtype;
	}
	

}
