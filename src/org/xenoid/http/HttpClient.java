package org.xenoid.http;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.logging.Logger;

public final class HttpClient {

	private static final Logger logger = Logger.getLogger(HttpClient.class.getName());

	public static HttpResponse get(String url) throws IOException, URISyntaxException {

		HttpRequest request = new HttpRequest(url);

		Socket socket = null;
		InputStream in = null;
		OutputStream out = null;

		try {

			socket = new Socket(request.getHost(), request.getPort());

			in = socket.getInputStream();

			in = new BufferedInputStream(in);

			out = socket.getOutputStream();

			out.write(request.getHeader());

			return HttpResponse.read(in);

		} finally {
			close(out);
			close(in);
			close(socket);
		}


	}


	private static void close(Socket socket) {
		try {
			if (socket != null)
				socket.close();
		} catch (IOException e) {
			logger.warning("IOException thrown while closing socket: " + e);
		}


	}

	private static void close(InputStream in) {
		try {
			if (in != null)
				in.close();

		} catch (IOException e) {
			logger.warning("IOException thrown while closing InputStream: " + e);
		}
	}


	private static void close(OutputStream out) {
		try {
			if (out != null)
				out.close();
		} catch (IOException e) {
			logger.warning("IOExeption thrown while closing OutputStream: " + e);
		}
	}


}
