package weightlossdata.businesslogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONObject;

public class Jconnector {

	//--------------------------This code checks if a username is available----------

	public static int checkUsernameAvailability(String uname) {
//		System.out.println("In checkUsernameAvailability method now");

		String url = "jdbc:mysql://localhost:3306/aml";
		String user = "hsr";
		String password = "root";

		int count = 0;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stSetLimit = conn.createStatement();

			String sql = "select count(userid) from wld.useraccount where userid='"+uname+"'";
			ResultSet rs = stSetLimit.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			} 

//			System.out.println("Count in checkUsernameAvailability() method: " + count);
			conn.close();
			stSetLimit.close();
			return count;

		} 

		catch (Exception ex) {
			ex.printStackTrace();
			return count;
		}

	}
	
	
	// Code to check userid--------------------------------------------------------------------------------------------  
	
		public static String checkSimpleUserId(String useridNoPassword) {

			String url = "jdbc:mysql://localhost:3306/aml";
			String user = "hsr";
			String password = "root";

			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection(url, user, password);
				Statement stSetLimit = conn.createStatement();

				JSONObject jo = new JSONObject(useridNoPassword);

				String userid = jo.getString("uname").trim();

				String sql = "SELECT COUNT(*) FROM wld.useraccount WHERE userid IN ('" + userid + "')";
				//SELECT COUNT(*) FROM aml.useraccount WHERE userid IN ('" + userid + "');
				
				ResultSet rs = stSetLimit.executeQuery(sql);
				while (rs.next()) {
					String rsToString = rs.getString(1);
//					System.out.println("This is the rsToString value you're looking for: " + rsToString);
					return rsToString;
				}

				conn.close();
				stSetLimit.close();
				return "Connection Closed";

			} // end try
			catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("______________Stack trace in Jconnector.checkSimpleUserId________________");
				return "Stack Trace";
			} // end catch

		}
	//-------------------------------------------------------------------------------------------------------

}
