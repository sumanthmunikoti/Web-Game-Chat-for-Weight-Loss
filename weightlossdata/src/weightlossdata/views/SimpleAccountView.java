package weightlossdata.views;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import weightlossdata.businesslogic.Jconnector;

@WebServlet("/SimpleAccountView")
public class SimpleAccountView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String uid;
       
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("In doPost() of SimpleAccountView");
		StringWriter writer = new StringWriter();  
		IOUtils.copy(request.getInputStream(), writer, "UTF-8");
		String theString = writer.toString();
		System.out.println(theString); 

		try {
			JSONObject ca = new JSONObject(theString);
			uid = ca.getString("userid");
		} catch (JSONException e1) {
			//e1.printStackTrace();
		}   
		
		try {
			JSONObject jo = new JSONObject(theString);
			
			Jconnector.insertSimpleUserInformation(jo.getString("userid"));
		} catch (JSONException e) {
			System.out.println("******************Stack trace in AccountView servlet***************");
			//e.printStackTrace();
		}
		
		}
	}

