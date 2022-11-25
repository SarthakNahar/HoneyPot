import java.io.*;
import java.net.*;


public class HoneyPotClient
{
	public static String  checkuser(UserInfo u ) 
	{
		String response="";
try
{
		Socket esocket = new Socket("localhost", 2222);

		PrintWriter out = new PrintWriter(esocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(esocket
				.getInputStream()));

		System.out.println("Client is ready");
					out.println("CHECKLOGIN:" + u.getUsername() + ":" + u.getPassword() + ":" + u.getCategory());
					 response = in.readLine();
			System.out.println("From Server : " + response);
			
		out.close();
		in.close();

		esocket.close();
}
catch(IOException ie)
{
	ie.printStackTrace();
}
return response;
	}
	
	
	public static  void addactivity(String activity)
	{
		
		
		
		
		System.out.println("activity   " + activity);
		try
		{
				Socket esocket = new Socket("localhost", 2222);

				PrintWriter out = new PrintWriter(esocket.getOutputStream(), true);
				

				System.out.println("Client is ready");
							out.println("ACTIVITYLOG:" + activity);
							 
					
				out.close();
				

				esocket.close();
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
		}
	}
}