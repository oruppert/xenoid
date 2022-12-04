package org.xenoid.http;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

public final class HttpRequest {


	private static final Charset US_ASCII = Charset.forName("US-ASCII");




	private final URI uri;
	
	public HttpRequest(URI uri) {
		this.uri = uri;
	}
	
	public HttpRequest(String uri) throws URISyntaxException {
		this.uri = new URI(uri);
	}
	
	public int getPort() {
		if (uri.getPort() == -1)
			return 80;
		else
			return uri.getPort();
	}
	
	public String getPath() {
		if (uri.getRawPath() == null || uri.getRawPath().isEmpty())
			return "/";
		else
			return uri.getRawPath();
	}
	
	public String getHost() {
		return uri.getHost();
	}
	
	public String getURI() {
		// todo: add query
		return getPath();
		
	}
	
	public byte[] getHeader() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("GET %s HTTP/1.0\r\n", getURI()));
		sb.append("Connection: close\r\n");
		sb.append("Content-Length: 0\r\n");
		sb.append("\r\n");
		
		System.out.println(sb.toString());

		return sb.toString().getBytes(US_ASCII);
	}


	
	
	
}
