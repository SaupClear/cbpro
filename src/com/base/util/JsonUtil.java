package com.base.util;

import java.sql.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.google.gson.JsonArray;

public class JsonUtil {
	
	 private static SerializeConfig config;

	    static {
	        config = new SerializeConfig();
	        config.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
	    }
	    
	    
	    /** Object TO Json String �ַ������ */
	    public static String toJSON(Object object) {
	        try {
	            return JSON.toJSONString(object, config);
	        } catch (Exception e) {
	        	System.out.print(e+"(to toJSON��"+object.toString()+"\n");
	        }
	        return null;
	    }
	    

	    /** List TO JsonArray �ַ������ 
	     * @param <T>*/
	    public static <T> List<T> toList(JSONArray array,Class<T> clazz) {
	        try {
	        	List<T> list = JSONObject.parseArray(array.toJSONString(),clazz);
	            return list;
	        } catch (Exception e) {
	            System.out.print(e+"(to toList��"+array.toJSONString()+"\n");
	        }
	        return null;
	    }
	    
	    
	    
	    /** Object TO Json String �ַ������ */
	    public static JSONObject toJSONObject(String object) {
	        try {
	            return (JSONObject) JSON.parseObject(object);
	        } catch (Exception e) {
	        	System.out.print(e+"(to toJSONObject��"+object+"\n");
	        }
	        return null;
	    }
	    
	    /** Json תΪ Jave Bean */
	    public static <T> T toBean(String text, Class<T> clazz) {
	        try {
	            return JSON.parseObject(text, clazz);
	        } catch (Exception e) {
	            System.out.print(e+"(to toBean��"+text+"\n");
	        }
	        return null;
	    }
	    
	    
	    /** string תΪ Jave jsonArray */
	    public static JSONArray tojsonArray(String text) {
	        try {
	            return JSON.parseArray(text);
	        } catch (Exception e) {
	            System.out.print(e+"(to tojsonArray��"+text+"\n");
	        }
	        return null;
	    }

  

}
