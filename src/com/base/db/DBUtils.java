package com.base.db;

import java.awt.List;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.util.JsonUtil;

public class DBUtils {

	
	
	
	public static boolean updateData(String sql,String[] coulmn,int[] type)
	{    
		DBManager dm = new DBManager();
		try {
			boolean flag = dm.updateOrAdd(coulmn, type, sql);
			if(flag){
				//System.out.println("修改成功");
			}else {
				System.out.println("update sql 0："+sql);
			}
			return flag;
		} catch (SQLException e) {
			System.out.println("update failed sql："+sql+"\n"+e.toString()+"\n");
			e.printStackTrace();
			return false;
		}
	}
	

	public static boolean intsertData(String sql,String[] coulmn,int[] type)
	{    
		DBManager dm = new DBManager();
		try {
			boolean flag = dm.updateOrAdd(coulmn, type, sql);
			if(flag){
				///System.out.println("插入成功");
			}else {
				System.out.println("插入失败");
			}
			return flag;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static JSONArray searchData(String sql)
	{    
		DBManager dm = new DBManager();
		String[] coulmn = null;
		int[] type = null;
		JSONArray array = new JSONArray();
		try {
			DataTable dt = dm.getResultData(coulmn, type, sql);
			if(dt != null && dt.getRowCount()> 0){            
				for(int i = 0; i<dt.getRowCount(); i++)
				{
					JSONObject jObject = new JSONObject();
					for(int j = 0; j<dt.getColCoun(); j++){
						//System.out.printf(dt.getRow()[i][j]+"\t");
						//System.out.printf(dt.getColumn()[j]+"\t");
						jObject.put(dt.getColumn()[j], dt.getRow()[i][j]);
					}
					array.add(jObject);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查询失败"+e+sql);
		}
		return array;
	}
	
	public static void deleteData(String sql)
	    {    
	        DBManager dm = new DBManager();
	        try {
	            boolean flag = dm.updateOrAdd(null, null, sql);
	            if(flag)
	                System.out.println("删除成功");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	
	
}
