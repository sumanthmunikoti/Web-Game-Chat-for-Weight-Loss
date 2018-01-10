package weightlossdata.views;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;

//import aml.data.ValidateAndPair;
import weightlossdata.data.User;

/**
 * Servlet implementation class WebGameServlet
 */
@WebServlet(name="WebGameServlet", loadOnStartup=1, description = "This servlet loads the UI for the game. It takes care of the interface.", urlPatterns = { "/WebGameServlet" })
public class WebGameServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("In doPost() of WebGameServlet");
		ServletContext ctx = request.getServletContext();
		// Fetch the map containing the pair ID maps to the users.		
		
		StringWriter writer = new StringWriter();  
		IOUtils.copy(request.getInputStream(), writer, "UTF-8");
		String userID = writer.toString();
		System.out.println("This is the string unamePasswordStr which has been passed from loginform.html': "+ userID); 

//		String userID = request.getParameter("uname");
		//'uname' is got from SimpleLoginForm.html
		System.out.println("userID: "+userID);
		
		HttpSession session = request.getSession();
		session.setAttribute("user", userID);
		Cookie userName = new Cookie("user", userID);
		Cookie startTime = new Cookie("begin_time", String.valueOf(System.currentTimeMillis()));
		
		response.addCookie(userName);
		response.addCookie(startTime);
		
		User user = new User(userID, "", "");
		
		ArrayList<User> usersTillNow = (ArrayList<User>)ctx.getAttribute("users_connected_till_now");
		if (usersTillNow == null) {
			usersTillNow = new ArrayList<>();			
		}
		usersTillNow.add(user);
		ctx.setAttribute("users_connected_till_now", usersTillNow);
		}
}		
