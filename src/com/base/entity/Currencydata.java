package com.base.entity;

public class Currencydata {
	
	
	public String Website;    // ��վ
    public String Operation;  //��������
    public String Virtualcurrency;  //�������
    public String Exchangecurrency; //�һ�����
    public String Time; //ʱ��
    
    
	
    public Currencydata(String website, String operation, String virtualcurrency, String exchangecurrency,
			String time) {
		super();
		Website = website;
		Operation = operation;
		Virtualcurrency = virtualcurrency;
		Exchangecurrency = exchangecurrency;
		Time = time;
	}
    
    
	public String getWebsite() {
		return Website;
	}
	public void setWebsite(String website) {
		Website = website;
	}
	public String getOperation() {
		return Operation;
	}
	public void setOperation(String operation) {
		Operation = operation;
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
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
  
}
