import java.io.*;
import java.net.*;
import java.util.StringTokenizer;


public class HoneyPotServer 
{

	ServerSocket ssocket;
	
	
	HoneyPotServer()
	{

	try
	{
		 ssocket=  new ServerSocket(2222);
		

		System.out.println("Server is ready");

		Socket csocket;

		boolean flag = true;

		while (flag) 
		{

			csocket = ssocket.accept();
		System.out.println("csocket.getPort()  " + csocket.getPort()); 

		PrintWriter out = new PrintWriter(csocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				csocket.getInputStream()));
		String s;
		s = in.readLine();
		
			System.out.println("From Client :  " + s);
			StringTokenizer st = new StringTokenizer(s,":");
			String clientactivity= st.nextToken();
			if(clientactivity.equalsIgnoreCase("CHECKLOGIN"))
			{
			UserInfo u = new UserInfo();
			
			u.setUsername(st.nextToken());
			u.setPassword(st.nextToken());
			u.setCategory(st.nextToken());
			
			
			boolean result = DataBase.check(u);
			if(result==false)
				addactivity(csocket.getInetAddress().getHostAddress(),"Intruder Login ");
			
			out.println(result + ":" +u.getRole() );	
			}
			else
				if(clientactivity.equalsIgnoreCase("ACTIVITYLOG"))
				{
				addactivity(csocket.getInetAddress().getHostAddress(),st.nextToken());
				
					
				}
				
			
		
		out.close();
		in.close();
		csocket.close();
	
		}
		System.out.println("Server Stopped");
		ssocket.close();

}
catch(IOException ie)
{
	ie.printStackTrace();
}

	}
	
	public void addactivity(String portno,String activity)
	{
		try
		{
			FileWriter fw = new FileWriter(portno+".log",true);
		PrintWriter out = new PrintWriter(fw);
		out.println(activity);
		out.close();
		fw.close();
		}
		catch(Exception e)
		{
			
		}
		
	}

	

}