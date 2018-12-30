import java.sql.*;
import java.util.*;
import java.lang.*;
import java.text.*;

public class Group1Functions{
	
	/************************************************************************************
	 * Logs in the user by checking for the ID and password
	 * 
	 * @param user ID, password
	 * @returns true if logged in, false if failed to log in
	 ************************************************************************************/
	public static boolean logIn(Connection con, Statement st, String uID, String pas){
		Connection conn = con;
		Statement stmt = st;

		String userID = uID;
		String pass = pas;

		boolean empty = true;
		
		String sql = "SELECT userID FROM profile WHERE userID = ? AND password = ?";
		try{

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userID);
		pstmt.setString(2, pass);
	

			try{
				ResultSet rs = pstmt.executeQuery();
				//System.out.println("test0");
				//System.out.println(rs.getString("userID"));

				if(rs.next()){
					//System.out.println("test1");
					empty = false;
				}

			}catch(SQLException se){
				System.out.println(se.toString()); 
			}

		}catch(SQLException se){
			System.out.println(se.toString()); 
		}
		
		if(!empty){
			//System.out.println("test2");
			return true;

		}
		else{
			//System.out.println("test3");
			return false;
		}	

	}
	
	/************************************************************************************
	 * Creates a user, adding the profile to the database
	 * 
	 * @param user ID, name, email, password, date of birth
	 * @returns true if create, false if failed to create
	 ************************************************************************************/
	public static boolean createUser(Connection con, Statement st, String userID, String name, String email, String pass, String date_of_birth){
		Connection conn = con;
		Statement stmt = st;

		try{
			String sql = "INSERT INTO profile " + "VALUES ('" + userID + "', '" + name + "', '" + email + "', '" + pass + "', '" + date_of_birth + "', NULL)";
			stmt.executeUpdate(sql);
			conn.commit();
		}catch(SQLException se){
			System.out.println(se.toString()); 
			return false;
		}
		return true;
	}
	
	/************************************************************************************
	 * Adds a friend request to the pendingFriends table
	 * 
	 * @param current user's ID, user ID for requested, message
	 * @returns true if requested successfully, false if failed to request
	 ************************************************************************************/
	public static boolean initiateFriendship(Connection con, Statement st, String toUser, String message, String currentUserID){
		Connection conn = con;
		Statement stmt = st;

		try{

			String sql = "INSERT INTO pendingFriends " + "VALUES ('" + currentUserID + "', '" + toUser + "', '" + message + "')"; 
			stmt.executeUpdate(sql);
			conn.commit();
			//Print out success or failure for insert
			System.out.println("Message was sent successfully!");
		}
		catch(SQLException se){
			//Print out success or failure for insert
			System.out.println("Message was not sent successfully");
			System.out.println(se.toString()); 
			return false;
		}
		return true;
	}

	/************************************************************************************
	 * Adds a new group to the group table, adds the current user to groupMembership
	 * 
	 * @param user ID, group name, membership limit, description
	 * @returns true if created, false if not created
	 ************************************************************************************/
	public static boolean createGroup(Connection con, Statement st, String groupName, int limit, String description, String currentUserID){
		Connection conn = con;
		Statement stmt = st;
		
		String sql = "SELECT max(gID) as maximum FROM groups";
		try{
			ResultSet rs = stmt.executeQuery(sql);

			if(rs.next()){
				int oldGroupID = rs.getInt(1);
				int groupID = oldGroupID + 1;
				
				try{

					String sql1 = "INSERT INTO groups VALUES (" + groupID + ", '" + groupName + "', '" + description + "', " + limit + ")";
					stmt.executeUpdate(sql1);
					conn.commit();

					String sql2 = "INSERT INTO groupMembership VALUES (" + groupID + ", '" + currentUserID + "', 'admin')";
					stmt.executeUpdate(sql2);
					conn.commit();

				}catch(SQLException se){
					System.out.println(se.toString()); 
				}
			}
		} catch (SQLException e ) {
			System.out.println(e.toString());
		}
		return true;
	}
	
	/************************************************************************************
	 * Shows all the pending requests for a specific user
	 * Call before confirmFriendship to give a list of requests to confirm
	 * 
	 * @param user ID
	 ************************************************************************************/
	public static void displayFriendRequests(Connection con, Statement st, String currentUserID){
		Connection conn = con;
		Statement stmt = st;
		
		//Display numbered list of all friend requests and group requests
		String sql = "SELECT fromID, message FROM pendingFriends WHERE toID = '" + currentUserID + "'";
		
		try{
			ResultSet rs = stmt.executeQuery(sql);

			int i = 1;
			while(rs.next()){
				String otherUser = rs.getString("fromID");
				String message = rs.getString("message");

				//display values 
				System.out.print(i); //number it***
				System.out.println("Pending Friend: " + otherUser);
				System.out.println("Message: " + message + " \n");
				System.out.println("");
				i++;
			}
		}
		catch(SQLException e ){
        	System.out.println(e.toString());
    	}
	}
	
	/************************************************************************************
	 * Moves a pending request to the friends or groupMembership table
	 * 
	 * @param user ID, selected value from list (displayFriendRequests)
	 * @returns true if request accepted, false if failed to accept
	 ************************************************************************************/
	public static boolean confirmFriendship(Connection con, Statement st, int selected, String currentUserID){
		Connection conn = con;
		Statement stmt = st;
		int select = selected;
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
		String currentDate = dateFormat.format(cal.getTime());

		String sql = "SELECT fromID, message FROM pendingFriends WHERE toID = '" + currentUserID + "'";
		
		try{
			ResultSet rs = stmt.executeQuery(sql);
			
			//insert friendship into friends/groupMembership relations
			//seems inefficient???
		
				
				if (select == 0) {
					//loop through list
					//rs.first(); //repositions cursor
					while(rs.next()){
						System.out.println("1");
						String otherUser = rs.getString("fromID");
						System.out.println("2");
						String message = rs.getString("message");
						System.out.println("3");
						String sql1 = "INSERT INTO friends " + "VALUES ('" + otherUser + "', '" + currentUserID + "', '" + currentDate + "', '" + message + "')"; 
						
						try{
							stmt.executeUpdate(sql1);
						}catch(SQLException e){
							System.out.println(e.toString());
						}

					}

					//deletes all from list
					System.out.println("3");
					String sql2 = "DELETE FROM pendingFriends WHERE toID  = '" + currentUserID + "'";
					stmt.executeUpdate(sql2);
					System.out.println("4");
				}
				else{
					//get data from one number
					rs.absolute(select);

					String otherUser = rs.getString("fromID");
					String message = rs.getString("message");
					String sql3 = "INSERT INTO friends VALUES ('" + otherUser + "', '" + currentUserID + "', '" + currentDate + "', '" + message + "')"; 
					stmt.executeUpdate(sql3);

					//delete from list
					String sql4 = "DELETE FROM pendingFriends WHERE toID  = '" + currentUserID  + "' AND fromID = '" + otherUser + "'";
					stmt.executeUpdate(sql4);
				}
				conn.commit();

			}catch(SQLException se){
				System.out.println(se.toString()); 
				return false;
			
		}

		
		return true;
	}
	
	/************************************************************************************
	 * Removes all requests for the specified user from the pending tables
	 * Call after confirmFriendship to clear any requests not accepted
	 * 
	 * @param user ID
	 * @returns true if successfully cleared, false if failed to clear
	 ************************************************************************************/
	public static boolean removeRemainingRequests(Connection con, Statement st, String currentUserID){
		Connection conn = con;
		Statement stmt = st;
		
		//on exit......
		//remove all other pending requests from the table - declined
		try
		{
			String sql5 = "DELETE FROM pendingFriends WHERE toID  = '" + currentUserID + "'";
			stmt.executeUpdate(sql5);
			conn.commit();
		}catch(SQLException e){
			System.out.println(e.toString());
			return false;
		}
		return true;
	}
	
	/************************************************************************************
	 * Shows a list of all friends and friends-of-friends for a user
	 * 
	 * @param user ID
	 * @returns String[] array of user IDs for the friends displayed
	 ************************************************************************************/
	public static String[] displayFriends(Connection con, Statement st, String currentUserID){
		Connection conn = con;
		Statement stmt = st;
		//display names and userID of all friends and friends-of-friends

		ArrayList<String> friends = new ArrayList<String>();
		ArrayList<String> friends_names = new ArrayList<String>();	
		
		int i = 0;
		String sql = "SELECT userID1, userID2 FROM friends WHERE userID1 = '" + currentUserID + "' OR userID2 = '" + currentUserID + "'";
		try{
    		ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
			
				String user1 = rs.getString("userID1");
				String user2 = rs.getString("userID2");

				if(user1.equalsIgnoreCase(currentUserID)){
					friends.add(user2);
				} 
				else{
					friends.add(user1);
				}
			
				i++;
			
			}
			
		}catch(SQLException se){
			System.out.println(se.toString()); 
		}
		 
		try{
				
			for (int j = 0; j < friends.size(); j++)
			{
				String sql2 = "SELECT userID1, userID2 FROM friends WHERE userID1 = '" + friends.get(j) + "' OR userID2 = '" + friends.get(j) + "'";
				ResultSet rs2 = stmt.executeQuery(sql2);
					
				while(rs2.next()){
				
					String user1 = rs2.getString("userID1");
					String user2 = rs2.getString("userID2");

					if(user1.equals(friends.get(j))){
						if(!friends.contains(user2)){
							friends.add(user2);
						}
					} 
					else{
						if(!friends.contains(user1)){
							friends.add(user1);
						}
					}
					i++;
				}

			}
			
		}catch(Exception e){
			System.out.println(e.toString());
		}
		
		String[] userID_array = new String[friends.size()];
		friends.toArray(userID_array);
		/*for(int k = 0; k < userID_array.length; k++){
			userID_array[i] = friends.get(i);
		}*/
		
		try{
			
			for(int j = 0; j < userID_array.length; j++){
			
				String sql3 = "SELECT name FROM profile WHERE userID = '" + userID_array[j] + "'";
			
				ResultSet rs3 = stmt.executeQuery(sql3);
			
				while(rs3.next()){
				
					String name = rs3.getString("name");
				
					friends_names.add(name);
				
				}
		
			}
			
		}catch(Exception e){
			System.out.println(e.toString());
		}
		
		
		String[] names_array = new String[friends_names.size()];
		friends_names.toArray(names_array);
		/*for(int k = 0; k < names_array.length; k++){
			names_array[i] = friends_names.get(i);
		}*/
		
		System.out.println("");
		System.out.println("Displaying your friends and their friends:");
		int k = 1;
		for(int j = 0; j < userID_array.length; j++){
			System.out.println(k + ": " + userID_array[j] + ", " + names_array[j]);			
			k++;
		}
		System.out.println("");
		
		return userID_array;
	}
	
	/************************************************************************************
	 * Displays all public information for a specific user
	 * 
	 * @param user ID to display
	 ************************************************************************************/
	public static boolean displayProfile(Connection con, Statement st, String selectedUser){
		Connection conn = con;
		Statement stmt = st;		
		
		try{
			String sql = "SELECT userID, name, date_of_birth, email FROM profile where userID = '" + selectedUser + "'";
			ResultSet rs = stmt.executeQuery(sql);

			rs.first();

			String userID = rs.getString("userID");
			String name = rs.getString("name");
			String email = rs.getString("email");
			String date_of_birth = rs.getString("date_of_birth");
			
			System.out.println("");
			System.out.println("User ID:  " + userID);
			System.out.println("Name:     " + name);
			System.out.println("Email:    " + email);
			System.out.println("Birthday: " + date_of_birth);
			System.out.println("");	
			return true;
		}catch(SQLException se){
			System.out.println(se.toString()); 
			return false;
		}

	}
	
	/************************************************************************************
	 * Displays a list of all groups the user is not a part of already
	 * Call before initiateAddingGroup to get an appropriate number to input
	 * 
	 * @param user ID
	 ************************************************************************************/
	public static boolean displayAddingGroup(Connection con, Statement st, String currentUserID){
		Connection conn = con;
		Statement stmt = st;
		
		//show the different groups (numbered list)

		String sql = "SELECT gID, name, description FROM groups NATURAL JOIN groupMembership WHERE NOT groupMembership.userID = '" + currentUserID + "'";
		try{
			ResultSet rs = stmt.executeQuery(sql);

			int i = 1; 
			while(rs.next()){
				String groupName = rs.getString("name");
				String groupDescription = rs.getString("description");

				//display values 
				System.out.print(i + ". "); //number it***
				System.out.println("Group name: " + groupName);
				System.out.println("Group Description: " + groupDescription);

			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/************************************************************************************
	 * Creates a request in the pendingGroupMembers table for a specified group
	 * Call displayAddingGroup first to get a list of available groups
	 * 
	 * @param user ID, number for group in the list, message
	 * @returns true if request sent successfully, false if failed to send
	 ************************************************************************************/
	public static boolean initiateAddingGroup(Connection con, Statement st, int selected, String message, String currentUserID){
		Connection conn = con;
		Statement stmt = st;
		int select = selected;

		String sql = "SELECT gID, name, description FROM groups NATURAL JOIN groupMembership WHERE NOT groupMembership.userID = '" + currentUserID + "'";
		try{
			ResultSet rs = stmt.executeQuery(sql);
				
			//boolean finished = false;
				
			//do{
				rs.first();

				//insert into pendingGroupMembers
				try{
					if(select == 0){
						//loop through list
						while(rs.next()){
							int groupID = rs.getInt("gID");
							String sql1 = "INSERT INTO pendingGroupMembers VALUES (" + groupID + ", '" + currentUserID + "', '" + message + "')";
							stmt.executeUpdate(sql1);		
						}
					}
					else{
						rs.absolute(select);
						int groupID = rs.getInt("gID");
						String sql2 = "INSERT INTO pendingGroupMembers VALUES (" + groupID + ", '" + currentUserID + "', '" + message + "')";
						stmt.executeUpdate(sql2);	
					}

					conn.commit();

				}catch(SQLException se){
					System.out.println(se.toString());
					return false;
				}

			//}while(selected >= 0);
		
		}catch(Exception e){
			return false;
		}
		
		return true;
	}
	
	/************************************************************************************
	 * Displays groups available to send a message to (user ID must be a member of them)
	 * Call before sendMessageToGroup to get an appropriate group list number
	 * 
	 * @param user ID
	 ************************************************************************************/
	public static void displayMessageGroup(Connection con, Statement st, String currentUserID){
		Connection conn = con;
		Statement stmt = st;
		
		String sql = "SELECT gID, name, description FROM groups NATURAL JOIN groupMembership WHERE groupMembership.userID = '" + currentUserID + "'";
		try{
			ResultSet rs = stmt.executeQuery(sql);
			int i = 1;
			while(rs.next()){
				int groupID = rs.getInt("gID");
				String groupName = rs.getString("name");
				String groupDescription = rs.getString("description");

				System.out.println(i + ". Group ID: " + groupID);
				System.out.println("   Group Name: " + groupName);
				System.out.println("   Group Description: " + groupDescription);
				i++;
			}
		}catch(Exception e){
			
		}
	}
	
	/************************************************************************************
	 * Adds a message for each of the group's members
	 * Call displayMessageGroup before this one
	 *
	 * @param user ID, number for group from the list, message
	 * @returns true if successfully sent, false if failed to send
	 ************************************************************************************/
	public static boolean sendMessageToGroup(Connection con, Statement st, int selectedGroup, String message, String currentUserID){
		Connection conn = con;
		Statement stmt = st;

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
		String currentDate = dateFormat.format(cal.getTime());

		String sql = "SELECT gID, name, description FROM groups NATURAL JOIN groupMembership WHERE groupMembership.userID = '" + currentUserID + "'";
		try{
			ResultSet rs = stmt.executeQuery(sql);
		}catch(Exception e){
			return false;
		}
		
		//insert into messages and messageRecipient table (trigger should do the latter)
		//need to get last message ID 
		
		int messageID = 0;
		String sql2 = "SELECT max(msgID) FROM messages";
		try{
			ResultSet rs2 = stmt.executeQuery(sql2);

			rs2.last();
			messageID = rs2.getInt("msgID");
			messageID++;
		}
		catch(Exception e){
			
		}
		//need to get user ID of all other users in group 
		String sql3 = "SELECT userID FROM groups NATURAL JOIN groupMembership WHERE NOT groupMembership.userID = '" + currentUserID + "' AND gID = " + selectedGroup;
		
		try{
			ResultSet rs3 = stmt.executeQuery(sql3);

			while(rs3.next()){
				String toUser = rs3.getString("userID");
				String sql4 = "INSERT INTO messages " + "VALUES (" + messageID + ", '" + currentUserID + "', '" + message + "', '" + toUser + "', " + selectedGroup + ", " + currentDate + ")"; 
				messageID++;
				stmt.executeUpdate(sql4);
			}

			conn.commit();
			//Print out success or failure for insert
			System.out.println("Group message sent successfully");

		}catch(SQLException se){
			//Print out success or failure for insert
			System.out.println("Group message not sent successfully");
			System.out.println(se.toString());
			return false;
		}
		return true;
	}
	
	/************************************************************************************
	 * Shows all messages for a specific user
	 * 
	 * @param user ID
	 ************************************************************************************/
	public static void displayMessages(Connection con, Statement st, String currentUserID){
		Connection conn = con;
		Statement stmt = st;

		//select and display the contents of every message sent to the user

		String sql = "SELECT fromID, message FROM messages WHERE toUserID = '" + currentUserID + "'";
		try{
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("");
			System.out.println("Displaying all messages: ");
			System.out.println("");
			while(rs.next()){
				String from = rs.getString("fromID");
				String message = rs.getString("message");

				System.out.println("From: " + from);
				System.out.println("Message: " + message);
				System.out.println("");
			}
		}catch(SQLException se){
			System.out.println(se.toString()); 
		}
		
		System.out.println("displayMessages");

	}
	
	/************************************************************************************
	 * Displays all of the friends for a user in a numbered list
	 * Call before sendMessageToUser to get an appropriate number to input
	 * 
	 * @param user ID
	 ************************************************************************************/
	public static void displayImmediateFriends(Connection con, Statement st, String currentUserID){
		Connection conn = con;
		Statement stmt = st;
				
		String sql = "SELECT userID1, userID2 FROM friends WHERE userID1 = '" + currentUserID + "' OR userID2 = '" + currentUserID + "'";
		try{
			ResultSet rs = stmt.executeQuery(sql);

			int i = 1;

			while(!rs.isLast()){
				rs.next();
				
				String user1 = rs.getString("userID1");
				String user2 = rs.getString("userID2");

				if(user1.equals(currentUserID)){
					System.out.println(i + ": " + user2);
				} 
				else{
					System.out.println(i + ": " + user1);
				}

				i++;
			}
		}catch(SQLException se){
			//Print out success or failure for insert
			System.out.println(se.toString()); 
		}
		
	}
	
	/************************************************************************************
	 * Sends a message from the current user to one specified in the friends list
	 * Call after displayImmediateFriends to get the appropriate number
	 * 
	 * @param user ID, number for which friend to send to, message to send
	 * @returns true if sent successfully, false if failed to send
	 ************************************************************************************/
	public static boolean sendMessageToUser(Connection con, Statement st, int selected, String message, String currentUserID){
		Connection conn = con;
		Statement stmt = st;
		
		String sql = "SELECT userID1, userID2 FROM friends WHERE userID1 = '" + currentUserID + "' OR userID2 = '" + currentUserID + "'";
		try{
			ResultSet rs = stmt.executeQuery(sql);
		
			
			rs.absolute(selected);

			String toUser;
			String user1 = rs.getString("userID1");
			String user2 = rs.getString("userID2");

			if(user1.equals(currentUserID)){
				toUser = user2;
			} 
			else{
				toUser = user1;
			}
			//need last messageID
			String sql2 = "SELECT max(msgID) as maximum FROM messages";
			try{
				ResultSet rs2 = stmt.executeQuery(sql2);

				rs2.last();
				int messageID = rs2.getInt("maximum");
				messageID++;

				//need current date formatting
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
				String currentDate = dateFormat.format(cal.getTime());

				//insert into messages and trigger into messageRecipient
				//show success or failure

				try{

					String sql3 = "INSERT INTO messages(msgID, fromID, message, toUserID, dateSent) " + "VALUES (" + messageID + ", '" + currentUserID + "', '" + message + "', '" + toUser + "', '" + currentDate + "')"; 
					stmt.executeUpdate(sql3);
					conn.commit();
					//Print out success or failure for insert
					System.out.println("Message sent to friend");

				}catch(SQLException se){
					//Print out success or failure for insert
					System.out.println("Message failed to send");
					System.out.println(se.toString()); 
					return false;
				}
			}catch(SQLException se){
				//Print out success or failure for insert
				System.out.println(se.toString()); 
				return false;
			}
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	/************************************************************************************
	 * Shows all messages available for a user since their last log in time
	 * 
	 * @param user ID
	 ************************************************************************************/
	public static void displayNewMessages(Connection con, Statement st, String currentUserID){
		Connection conn = con;
		Statement stmt = st;

		System.out.println("displayNewMessages");
		
		//same as sendMessageToUser()
		//set a time limit as later than the user's last login time

		//get last login from user
		String sql = "SELECT lastlogin FROM profile WHERE userID = '" + currentUserID +"'";
		try{
			ResultSet rs = stmt.executeQuery(sql);
			rs.first();
			//******
			//data type for dates??????
			String lastLogin = rs.getString("lastlogin");

			lastLogin = lastLogin.substring(0, 9);

			//select and display the contents of every message sent to the user
			//need to check date compare

			try{
				String sql2 = "SELECT fromID, message FROM messages WHERE toUserID = '" + currentUserID + "' AND dateSent > TO_DATE('" + lastLogin + "','YYYY-MM-DD')";
				ResultSet rs2 = stmt.executeQuery(sql2);


				System.out.println("");
				System.out.println("Displaying new messages: ");
				System.out.println("");
				
				while(rs2.next()){
					String from = rs2.getString("fromID");
					String message = rs2.getString("message");

					System.out.println("From: " + from);
					System.out.println("Message: " + message);
					System.out.println("");
				}
			}catch(SQLException se){
			//Print out success or failure for insert

			System.out.println(se.toString()); 
		}
		}catch(SQLException se){
			//Print out success or failure for insert
			System.out.println(se.toString()); 
		}
		
	}
	
	/************************************************************************************
	 * Searches through the users public info for matches with the search terms
	 * 
	 * @param String of input terms separated by spaces
	 ************************************************************************************/
	public static void searchForUser(Connection con, Statement st, String input){
		Connection conn = con;
		Statement stmt = st;
		
		String[] terms = input.split(" ");
		
		try{
			for(int i = 0; i < terms.length; i++){
				String sql = "SELECT userID, name, email, date_of_birth FROM profile WHERE userID LIKE '%" + terms[i] + "%' OR name LIKE '%" + terms[i] + "%' OR email LIKE '%" + terms[i] + "%' OR date_of_birth LIKE '%" + terms[i] + "%'";
				ResultSet rs = stmt.executeQuery(sql);
				
				System.out.println("Showing search results for term: " + terms[i]);
				System.out.println("");
				while(!rs.isLast()){
					rs.next();
					
					String userID = rs.getString("userID");
					String name = rs.getString("name");
					String email = rs.getString("email");
					String date_of_birth = rs.getString("date_of_birth");
					
					System.out.println("User ID:    " + userID);
					System.out.println("Name:       " + name);
					System.out.println("Email:      " + email);
					System.out.println("Birth Date: " + date_of_birth);
					System.out.println("");
				}
				
				System.out.println("");
			}
		}
		catch(Exception e){
			System.out.println("No matches found.");
			System.out.println("Returning to menu...");
			return;
		}
	}
	
	/************************************************************************************
	 * Attempts to find a connection between two users with no more than two users
	 * between. Displays the path found
	 * 
	 * @param user ID for starting user, user ID for ending user
	 ************************************************************************************/
	public static void threeDegrees(Connection con, Statement st, String userA, String userB){
		Connection conn = con;
		Statement stmt = st;

		try{
			String sql0 = "SELECT userID1, userID2 FROM friends WHERE userID1 = '" + userA + "' OR userID2 = '" + userA + "'";
			ResultSet rs0 = stmt.executeQuery(sql0);
			
			ArrayList<String> first_level = new ArrayList<String>();
			ArrayList<String> second_level = new ArrayList<String>();
			ArrayList<String> third_level = new ArrayList<String>();
			
			while(rs0.next()){
				String user1 = rs0.getString("userID1");
				String user2 = rs0.getString("userID2");
				
				if(user1.equalsIgnoreCase(userA)){
					first_level.add(user2);
				}
				else{
					first_level.add(user1);
				}
			}

			for(int i = 0; i < first_level.size(); i++){
				if(first_level.get(i).equalsIgnoreCase(userB)){
					System.out.println("There is an immediate connection. These two users are friends!");
					System.out.println("Connection: " + userA + " -> " + userB);
					return;
				}
			}
			
			for(int i = 0; i < first_level.size(); i++){
				String userC = first_level.get(i);
				String sql1 = "SELECT userID1, userID2 FROM friends WHERE userID1 = '" + userC + "' OR userID2 = '" + userC + "'";
				ResultSet rs1 = stmt.executeQuery(sql1);
				
				while(rs1.next()){
					String user1 = rs1.getString("userID1");
					String user2 = rs1.getString("userID2");
					
					if(user1.equalsIgnoreCase(userC)){
						second_level.add(user2);
					}
					else{
						second_level.add(user1);
					}
				}
			
				for(int j = 0; j < second_level.size(); j++){
					if(second_level.get(j).equalsIgnoreCase(userB)){
						System.out.println("There is a connection with only two hops!");
						System.out.println(userA + " -> " + userC + " -> " + userB);
						return;
					}	
				}
				
				for(int j = 0; j < second_level.size(); j++){
					String userD = second_level.get(j);
					String sql2 = "SELECT userID1, userID2 FROM friends WHERE userID1 = '" + userD + "' OR userID2 = '" + userD + "'";
					ResultSet rs2 = stmt.executeQuery(sql2);
					
					while(rs2.next()){
						String user1 = rs2.getString("userID1");
						String user2 = rs2.getString("userID2");
						
						if(user1.equalsIgnoreCase(userD)){
							third_level.add(user2);
						}
						else{
							third_level.add(user1);
						}
					}
					
					for(int k = 0; k < third_level.size(); k++){
						if(third_level.get(k).equalsIgnoreCase(userB)){
							System.out.println("There is a connection with three hops!");
							System.out.println(userA + " -> " + userC + " -> " + userD + " -> " + userB);
							return;
						}
					}
				}
			}
		}catch(Exception e){}	
		System.out.println("There is no path with three hops or less between " + userA + " and " + userB);
		System.out.println("Returning to menu...");
		System.out.println("threeDegrees");
	}

	/************************************************************************************
	 * Shows the top k message senders or receivers in the last X months
	 * 
	 * @param number of people, number of months, "sent" or "received"
	 ************************************************************************************/
	public static void topMessages(Connection con, Statement st, int num_people, int months, String sendReceived){
		Connection conn = con;
		Statement stmt = st;
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
		cal.add(Calendar.MONTH,-months);
		String checkDate = dateFormat.format(cal.getTime());
		
		//search through messages and display the top K people with most messages in past x months


		//String sql = "SELECT fromID, toUserID FROM messages";
		try{
			//ResultSet rs = stmt.executeQuery(sql);


			//max sent 
			String sql2 = "SELECT count(fromID) as total, fromID FROM messages WHERE dateSent > '" + checkDate + "' GROUP BY fromID ORDER BY total DESC";
			//max recieved 

			String sql3 = "SELECT count(toUserID) as total, toUserID FROM messages WHERE dateSent > '" + checkDate + "' GROUP BY toUserID ORDER BY total DESC";
			//select if you want max sent or max recieved 
			int i = 0;
			if(sendReceived.equalsIgnoreCase("sent"))//max sent
			{
				System.out.println("Showing top sent...");
				try{
					ResultSet rs2 = stmt.executeQuery(sql2);

					while(rs2.next()){
						String from = rs2.getString("fromID");
						System.out.println(from);
						i++;

						if(i == num_people){
							break;
						}
					}

				}catch(SQLException se){
					System.out.println(se.toString());
				}


			}
			else{
				System.out.println("Showing top recieved...");
					try{
						ResultSet rs3 = stmt.executeQuery(sql3);

					while(rs3.next()){
						String to = rs3.getString("toUserID");
						System.out.println(to);
						i++;

						if(i == num_people){
							break;
						}
					}

				}catch(SQLException se){
					System.out.println(se.toString());
				}
			}
		}
		catch(Exception e){
			
		}
		System.out.println("topMessages");
	}
	
	/************************************************************************************
	 * Removes a user from the database entirely
	 * 
	 * @param user ID
	 * @returns true if successfully removed, false if failed to remove
	 ************************************************************************************/
	public static boolean dropUser(Connection con, Statement st, String currentUserID){
		Connection conn = con;
		Statement stmt = st;

		try{
			String sql = "DELETE profile WHERE userID = '" + currentUserID + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			System.out.println("Profile successfully deleted");
		}
		catch(Exception e){
			System.out.println("Error in removing profile");
			System.out.println(e.toString());
			return false;
		}
		
		return true;
	}

	/************************************************************************************
	 * Logs a user out, updates the log in time to the current time
	 * 
	 * @param user ID
	 * @returns true if successfully logged out, false if failed to log out
	 ************************************************************************************/
	public static boolean logOut(Connection con, Statement st, String currentUserID){
		Connection conn = con;
		Statement stmt = st;

		String currentUID = currentUserID;


		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yy hh.mm.ss a");
		String currentTime = formatDate.format(cal.getTime());
		
		try{
			String sql = "UPDATE profile SET lastlogin = '" + currentTime + "' WHERE userID = '" + currentUID + "'";
			stmt.executeUpdate(sql);
			conn.commit();
		}catch(SQLException e){
			System.out.println(e.toString());
			return false; //if it was not able to set the time for log out
		}
		
		System.out.println("Successful Log Out");
		return true;

	}

	/************************************************************************************
	 * Helper function to determine if a date was entered properly by a user
	 * 
	 * @param date
	 * @returns true if formatted properly, false if not formatted properly
	 ************************************************************************************/
	public static boolean checkDate(String date_of_birth){
		String[] array = date_of_birth.split("-");
		
		while(array.length != 3){
			System.out.println("Improper Date Format");
			System.out.println("Date must be formatted as dd-MMM-yy");
			System.out.println("ex. 31-OCT-91");
			System.out.println("");
			return false;
		}
		
		if(array[0].length() > 2 || array[0].length() < 0){
			System.out.println("Day format may only be between 1 and 31");
			System.out.println("Please try entering your date of birth again");
			return false;
		}
		
		if(array[2].length() != 2){
			System.out.println("Year format may only be between 00 and 99");
			System.out.println("Please try entering your date of birth again");
			return false;
		}
		
		int day = 0;
		int year = 0;
		
		try{
			day = Integer.parseInt(array[0]);
			year = Integer.parseInt(array[2]);
		}catch(Exception e){
			System.out.println("Only numbers may be entered for the day and year");
			System.out.println("Please try entering your date of birth again (ex. 31-OCT-91");
			return false;
		}
		
		if(array[1].equalsIgnoreCase("JAN")){
			if(day > 31 || day < 1){
				System.out.println("January only has 31 days! Please enter a valid date");
				return false;
			}
		}
		else if(array[1].equalsIgnoreCase("FEB")){
			if((year % 4) == 0){
				if(day > 29 || day < 1){
					System.out.println("February only has 29 days that year! Please enter a valid date");
					return false;
				}
			}
			if((year % 4) != 0){
				if(day > 28 || day < 1){
					System.out.println("February only has 28 days that year! Please enter a valid date");
					return false;
				}
			}
		}
		else if(array[1].equalsIgnoreCase("MAR")){
			if(day > 31 || day < 0){
				System.out.println("March only has 31 days! Please enter a valid date");
				return false;
			}
		}
		else if(array[1].equalsIgnoreCase("APR")){
			if(day > 30 || day < 0){
				System.out.println("April only has 30 days! Please enter a valid date");
				return false;
			}
		}
		else if(array[1].equalsIgnoreCase("MAY")){
			if(day > 31 || day < 0){
				System.out.println("May only has 31 days! Please enter a valid date");
				return false;
			}
		}
		else if(array[1].equalsIgnoreCase("JUN")){
			if(day > 30 || day < 0){
				System.out.println("June only has 30 days! Please enter a valid date");
				return false;
			}
		}
		else if(array[1].equalsIgnoreCase("JUL")){
			if(day > 31 || day < 0){
				System.out.println("July only has 31 days! Please enter a valid date");
				return false;
			}
		}
		else if(array[1].equalsIgnoreCase("AUG")){
			if(day > 31 || day < 0){
				System.out.println("August only has 31 days! Please enter a valid date");
				return false;
			}
		}
		else if(array[1].equalsIgnoreCase("SEP")){
			if(day > 30 || day < 0){
				System.out.println("September only has 30 days! Please enter a valid date");
				return false;
			}
		}
		else if(array[1].equalsIgnoreCase("OCT")){
			if(day > 31 || day < 0){
				System.out.println("October only has 31 days! Please enter a valid date");
				return false;
			}
		}
		else if(array[1].equalsIgnoreCase("NOV")){
			if(day > 30 || day < 0){
				System.out.println("November only has 30 days! Please enter a valid date");
				return false;
			}
		}
		else if(array[1].equalsIgnoreCase("DEC")){
			if(day > 31 || day < 0){
				System.out.println("December only has 31 days! Please enter a valid date");
				return false;
			}
		}
		else{
			System.out.println(array[1] + " is not a valid month.");
			System.out.println("Format should be the first three letters of the month");
			System.out.println("Please enter a valid date");
			return false;
		}
		
		return true;
	}



}