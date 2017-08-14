package com.jmc.foaas;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FOClient {

	public static void main(String [] args) {
		try {
			System.out.println("Hello World!");
			callFOAAS();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void callFOAAS() throws Exception {
		
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://foaas.com/cool/hank");
		httpGet.addHeader("Accept", "application/json");
		
		CloseableHttpResponse resp = client.execute(httpGet);
		String json = EntityUtils.toString(resp.getEntity());
		
		ObjectMapper mapper = new ObjectMapper();
		FOPayload payload = mapper.readValue(json, FOPayload.class);
		System.out.println("Message=" + payload.getMessage());
		System.out.println("Subtitle=" + payload.getSubtitle());
	}
	
	private static class FOPayload {
		private String message;
		private String subtitle;
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getSubtitle() {
			return subtitle;
		}
		public void setSubtitle(String subtitle) {
			this.subtitle = subtitle;
		}
	}
}
