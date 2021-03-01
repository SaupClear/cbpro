package com.base.util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtil {
	
	//Í¬²½
	public static String getPostInfo(String url,String json) {
		String s = null;
		MediaType mediaType = MediaType.parse("application/json");
		OkHttpClient client = new OkHttpClient();
		client = new OkHttpClient.Builder()
         .connectTimeout(1000, TimeUnit.SECONDS)
         .readTimeout(1000, TimeUnit.SECONDS)
         .build();
		RequestBody body = RequestBody.create(mediaType,json);
		Request request = new Request.Builder().url(url).post(body).build();
		try {
			Response response = client.newCall(request).execute();
			s = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return s;
	}
	public static String getInfo(String url){
		String s = null;
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(url).build();

		Response response;
		try {
			response = client.newCall(request).execute();
			s = response.body().string();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return  s;
	}


}
