package org.xenoid.http;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.TreeMap;

public final class HttpResponse {
	
	private final String responseLine;
	
	private final Map<String, String> responseHeaders;
	
	private final byte[] responseBody;
	
	public HttpResponse(String responseLine, Map<String, String> responseHeaders, byte[] responseBody) {
		this.responseLine = responseLine;
		this.responseHeaders = responseHeaders;
		this.responseBody = responseBody;
	}
	
	public int getStatusCode() {
		return Integer.parseInt(responseLine.split("\\s+")[1]);
	}

	public byte[] getResponseBody() {
		return this.responseBody;
	}
	
	public String getResponseBodyString() {
		return new String(getResponseBody(), getCharset());
	}
	
	public Charset getCharset() {
		return Charset.forName("UTF-8");
	}

	public static HttpResponse read(InputStream in) throws IOException {
		String responseLine = readLine(in);

		Map<String, String> headers = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);

		while (true) {
			String line = readLine(in);
			if (line.isEmpty()) {
				break;
			}
			String parts[] = line.split(":\\s*", 2);
			if (parts.length != 2) {
				continue;
			}
			headers.put(parts[0], parts[1]);

		}
		
		byte[] responseBody = readBody(in, headers.get("content-length"));
		return new HttpResponse(responseLine, headers, responseBody);
	}
	
	private static byte[] readBody(InputStream in, String contentLength) throws IOException {
		if (contentLength != null) {
			byte[] out = new byte[Integer.parseInt(contentLength)];
			int offset = 0;
			while (offset < out.length) {
				int n = in.read(out, offset, out.length - offset);
				if (n == -1)
					throw new EOFException();
				offset += n;
			}
			return out;
		} else {
			// assumes Connection: close
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buffer = new byte[0x1000];
			for (int n = in.read(buffer); n != -1; n = in.read(buffer))
				out.write(buffer, 0, n);
			return out.toByteArray();
			
			
		}
	}

	private static String readLine(InputStream in) throws IOException {

		StringBuilder sb = new StringBuilder();

		int p = -1;

		while (true) {

			int c = in.read();

			if (c == -1)
				throw new EOFException();

			if (p == '\r' && c == '\n')
				break;

			if (p != -1)
				sb.append((char) p);

			p = c;

		}


		return sb.toString();

	}

	
}
