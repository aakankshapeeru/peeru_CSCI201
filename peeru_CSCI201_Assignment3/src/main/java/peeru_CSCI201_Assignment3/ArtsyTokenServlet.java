package peeru_CSCI201_Assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getToken")
public class ArtsyTokenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String clientID="516dc4ef1494287532e9";
	private static final String clientSecret="6df32eae7ac19ba0f6fa7d8cafeb7b02";
	private static final String clientInfo="client_id="+clientID+"&client_secret="+clientSecret;
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {
			URI uri=new URI("https", "api.artsy.net", "/api/tokens/xapp_token", null);
			URL url=uri.toURL();
			HttpURLConnection connection= (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			try(OutputStream output=connection.getOutputStream()) {
				output.write(clientInfo.getBytes());
			}
			BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder data=new StringBuilder();
			String line=reader.readLine();
			while(line!=null) {
				data.append(line);
				line=reader.readLine();
			}
			response.setContentType("application/json");
			PrintWriter out=response.getWriter();
			out.print(data);
			out.flush();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
