package org.xenoid;

import org.xenoid.http.HttpClient;
import org.xenoid.http.HttpResponse;

import java.io.IOException;
import java.net.URISyntaxException;

public final class Main {
	
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		
		String url = "http://news.ycombinator.com";
		
		HttpResponse response = HttpClient.get(url);

		System.out.println(response.getResponseBodyString());
		
		
	}
	
}
