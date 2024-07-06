package com.example.serviceB.Util;

import com.google.gson.Gson;

public class ServiceBUtility {
	
	private static final Gson gson = new Gson();
	
	public static String fromObjectToJson(Object object) {
		String response = null;
		try {
			response = gson.toJson(object);
		} catch (Exception e) {
		}
		return response;
	}
	
	public static <T> T fromJsonToObjec(String request,Class<T> object) {
		T response = null;
		try {
			response = gson.fromJson(request,object);
		} catch (Exception e) {
		}
		return response;
	}

}
