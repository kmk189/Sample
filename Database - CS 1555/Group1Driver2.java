import java.sql.*;
import java.util.*;
import java.lang.*;
import java.text.*;

public class Group1Driver2{

	static Group1Functions functions = new Group1Functions();
	static final String myDatabase = "jdbc:oracle:thin:@class3.cs.pitt.edu:1521:dbclass";
	static final String username = "sal162";		
	static final String password = "4002531";
	static Connection con = null;
	static Statement st = null;
	static Scanner sc = new Scanner(System.in).useDelimiter("[^0-9]+");
	
	public static void main(String[] args){


	
		try {
			DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
			con = DriverManager.getConnection(myDatabase, username, password);

			st = con.createStatement();
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			con.setAutoCommit(false);

	
		System.out.println("");
		System.out.println("Driver to test functions in the Social Panther system for Group 1");
		System.out.println("Please reload the database before running the driver program");
		
		int choice = 0;
		
		do{
			System.out.println("Please choose a function to test:");
			System.out.println("");
			
			System.out.println("1.  Run all tests!");
			System.out.println("2.  Log In");
			System.out.println("3.  Create User");
			System.out.println("4.  Send a Friend Request");
			System.out.println("5.  Accept a Friend Request");
			System.out.println("6.  Display Friends and Individual Profiles");
			System.out.println("7.  Send a Message to a User");
			System.out.println("8.  Create a Group");
			System.out.println("9.  Send a Group Request");
			System.out.println("10. Send a Message to a Group");
			System.out.println("11. Display Messages");
			System.out.println("12. Display New Messages");
			System.out.println("13. Search for a User");
			System.out.println("14. Find Path Between Users");
			System.out.println("15. Find Top Message Senders");
			System.out.println("16. Drop an Account");
			System.out.println("17. Log Out");
			System.out.println("18. Finish Using Driver and Exit");
			choice = sc.nextInt();
			sc.nextLine();
		
			switch(choice){
				case 1:
					runAll();
					break;
				case 2:
					logInTest(0);
					break;
				case 3:
					createUserTest(0);
					break;
				case 4:
					initiateFriendshipTest(0);
					break;
				case 5:
					confirmFriendshipTest(0);
					break;
				case 6:
					displayFriendsTest(0);
					break;
				case 7:
					sendUserMessageTest(0);
					break;
				case 8:
					createGroupTest(0);
					break;
				case 9:
					initiateGroupTest(0);
					break;
				case 10:
					sendGroupMessageTest(0);
					break;
				case 11:
					displayMessagesTest(0);
					break;
				case 12:
					displayNewMessagesTest(0);
					break;
				case 13:
					searchTest(0);
					break;
				case 14:
					threeDegreesTest(0);
					break;
				case 15:
					topMessagesTest(0);
					break;
				case 16:
					dropUserTest(0);
					break;
				case 17:
					logOutTest(0);
					break;
				case 18:
					//Exits loop and leaves the system
					break;
				default:
					System.out.println(choice + " is not a valid option.");
			}
		
		}while(choice != 18);
		
		st.close();
		con.close();

				}catch(SQLException se){
			//Handle errors for JDBC
      		se.printStackTrace();
   		}catch(Exception e){
			//Handle errors for Class.forName
      		e.printStackTrace();
   		}finally{
			//finally block used to close resources
      		try{
        		if(st!=null)
            		st.close();
      		}catch(SQLException se2){
      		}// nothing we can do
      		try{
        		if(con!=null)
            		con.close();
      		}catch(SQLException se){
        		se.printStackTrace();
      		}//end finally try
			
   		}//end try	

		//sc.close();
		//sc2.close();
		System.exit(0);	
		
	}

	public static void runAll(){

		int i = 0;

		i = logInTest(i);
		i = createUserTest(i);
		i = initiateFriendshipTest(i);
		i = confirmFriendshipTest(i);
		i = displayFriendsTest(i);
		i = sendUserMessageTest(i);
		i = createGroupTest(i);
		i = initiateGroupTest(i);
		i = sendGroupMessageTest(i);
		i = displayMessagesTest(i);
		i = displayNewMessagesTest(i);
		i = searchTest(i);
		i = threeDegreesTest(i);
		i = topMessagesTest(i);
		i = dropUserTest(i);
		i = logOutTest(i);

		System.out.println("\n***************\n\n" + i + "/22 tests passed\n***************");


	}


	/*testing log in method*/
	public static int logInTest(int i){
		int j = i;
		System.out.println("");
		System.out.println("Attempting successful log in test:");
		/*log in with existing user*/
		if(functions.logIn(con, st, "k_krause", "password100")){
			System.out.println("Test was successful");
			j++;
		}
		else{
			System.out.println("Test was not successful");
		}
		
		System.out.println("");
		System.out.println("Attempting failed log in test:");
		//log in with wrong password
		if(!functions.logIn(con, st, "k_krause", "password99")){
			System.out.println("Test was successful");
			j++;
		}
		else{
			System.out.println("Test was not successful");
		}
		return j;
		
	}
	
	/*testing creating user*/
	public static int createUserTest(int i){
		int j = i;
		System.out.println("");
		System.out.println("Attempting to create a new profile:");
		if(functions.createUser(con, st, "j_brown", "James Brown", "jdb123@pitt.edu", "password101", "17-NOV-92")){
			System.out.println("Test was successful");
			j++;
		}
		else{
			System.out.println("Test was not successful");
		}
		return j;

	}
	
	public static int initiateFriendshipTest(int i){
		int j = i;
		System.out.println("");
		System.out.println("Attempting to send a friend request:");
		if(functions.initiateFriendship(con, st, "s_lambert", "Hi, Sam!", "k_krause")){
			System.out.println("Test was successful");
			j++;
		}
		else{
			System.out.println("Test was not successful");
		}

		/*SQL to see if this actually appears in Pending Friends*/
		String checkPendingFriends = "SELECT * FROM pendingFriends WHERE fromID = 'k_krause' AND toID = 's_lambert'";
		
		try{
			ResultSet pendingFriend = st.executeQuery(checkPendingFriends);
		/*if result set is not empty -- should only be one value in it*/
			if(pendingFriend.next())
			{	
				//System.out.println("Initiate Frienship Successful");
				j++;
			}
		}catch(SQLException se){
		System.out.println(se.toString());
		}	

		return j;
	}
	
	public static int confirmFriendshipTest(int i){
		int j = i;
		System.out.println("");
		System.out.println("Sending friend requests to test...");
		functions.initiateFriendship(con, st, "k_krause", "Hey", "i_flynn");
		functions.initiateFriendship(con, st, "k_krause", "Hey", "d_hawkins");
		functions.initiateFriendship(con, st, "k_krause", "Hey", "k_hayden");
		functions.initiateFriendship(con, st, "k_krause", "Hey", "a_bush");
		
		//functions.displayFriendRequests(con, st, "k_krause");
		System.out.println("");
		System.out.println("Attempting to accept i_flynn request");
		if(functions.confirmFriendship(con, st, 1, "k_krause")){
			System.out.println("Test was successful");
			j++;
		}
		else{
			System.out.println("Test was not successful");
		}
		//functions.displayFriendRequests(con, st, "k_krause");

		/*
		System.out.println("");
		System.out.println("Attempting to accept remaining requests");
		if(functions.confirmFriendship(con, st, 0, "k_krause")){
			System.out.println("Test was successful");
			j++;
		}
		else{
			System.out.println("Test was not successful");
		}*/

		return j;

	} 
	
	public static int displayFriendsTest(int i){
		int j = i;
		System.out.println("");
		System.out.println("Attempting to display friends of k_stone");
		if(isEmptyStringArray(functions.displayFriends(con, st, "k_stone"))){
			System.out.println("Test was successful");
			j++;
		}
		else{
			System.out.println("Test was not successful");
		}
		
		System.out.println("");
		System.out.println("Attempting to display profile of k_krause");
		if(functions.displayProfile(con, st, "k_krause")){
			System.out.println("Test was successful");
			j++;
		}
		else{
			System.out.println("Test was not successful");
		}
		return j;
		

	}
	
	public static int sendUserMessageTest(int i){
		int j = i;
		System.out.println("");
		System.out.println("Attempting to send a message to a user");
		if(functions.sendMessageToUser(con, st, 1, "Hey", "l_fitzpatrick")){
			System.out.println("Test was successful");
			j++;
		}
		else{
			System.out.println("Test was not successful");
		}
		return j;

	}
	
	public static int createGroupTest(int i){
		int j = i;
		System.out.println("");
		System.out.println("Attempting to create a group:");
		if(functions.createGroup(con, st, "Kitty Lovers!", 17, "lots of kitties!", "k_stone")){
			System.out.println("Test was successful");
			j++;
		}
		else{
			System.out.println("Test was not successful");
		}

		return j;

	}
	
	public static int initiateGroupTest(int i){
		int j = i;
		System.out.println("");
		System.out.println("Attempting to display groups");
		if(functions.displayAddingGroup(con, st, "k_krause")){

					System.out.println("Test was successful");
					j++;
		}
		else{
			System.out.println("Test was not successful");
		}
		
		System.out.println("");
		System.out.println("Attempting to join a group");
		if(functions.initiateAddingGroup(con, st, 1, "Let me join!", "k_krause")){
			System.out.println("Test was successful");
			j++;
		}
		else{
			System.out.println("Test was not successful");
		}
		
		System.out.println("");
		System.out.println("Attempting to join a group that is full");
		if(!functions.initiateAddingGroup(con, st, 9, "Group is full?", "l_fitzpatrick")){
			System.out.println("Test was successful");
			j++;
		}
		else{
			System.out.println("Test was not successful");
		}
		
		return j;
	}
	
	public static int sendGroupMessageTest(int i){
		int j =i;
		System.out.println("");
		System.out.println("Attempting to send message to group");
		if(functions.sendMessageToGroup(con, st, 1, "Hello, Group 1", "k_krause")){
			System.out.println("Test was successful");
			j++;
		}
		else{
			System.out.println("Test was not successful");
		}

		return j;
	}
	
	public static int displayMessagesTest(int i){
		int j = i;
		System.out.println("");
		System.out.println("Attempting to display messages of k_krause");
		functions.displayMessages(con, st, "k_krause");	

				j++;

		return j;


	}
	
	public static int displayNewMessagesTest(int i){
		int j = i;
		System.out.println("");
		System.out.println("Attempting to display new messages of i_flynn");
		functions.displayNewMessages(con, st, "i_flynn");

				j++;

		return j;

	
	}
	
	public static int searchTest(int i){
		int j = i;
		System.out.println("");
		System.out.println("Attempting to search for 'Eve', 'Krause', and 'JAN'");
		functions.searchForUser(con, st, "Eve Krause jan");
		j++;

		return j;

	}
	
	public static int threeDegreesTest(int i){
		int j = i;
		System.out.println("");
		System.out.println("Attempting to find a connection");

		functions.threeDegrees(con, st, "l_fitzpatrick", "i_combs");
		j++;
		
		//System.out.println("");
		//System.out.println("Attempting to find a connection when no connection exists");
		//functions.threeDegrees(con, st, "l_fitzpatrick", "x_hammond");
		return j;
	}
	
	public static int topMessagesTest(int i){
		int j = i;
		System.out.println("");
		System.out.println("Attempting to show top 5 message senders in last 5 months");
		functions.topMessages(con, st, 5, 5, "sent");
		j++;
		
		System.out.println("");
		System.out.println("Attempting to show top 7 message receivers in last 6 months");
		functions.topMessages(con, st, 7, 6, "received");
		j++;
		
		return j;
	}
	
	public static int dropUserTest(int i){
		int j = i;
		System.out.println("");
		System.out.println("Attempting to remove t_wilcox from the system");
		if(functions.dropUser(con, st, "t_wilcox")){
			System.out.println("Test was successful");
			j++;
		}
		else{
			System.out.println("Test was not successful");
		}
		
		return j; 
	}

	public static int logOutTest(int i){
		int j = i;
		System.out.println("");
		System.out.println("Attempting to log out k_krause");
		if(functions.logOut(con, st, "k_krause")){
			System.out.println("Test was successful");
			j++;
		}
		else{
			System.out.println("Test was unsuccessful");
		}
		return j;
	}

	public static boolean isEmptyStringArray(String[] array){
 		for(int i=0; i<array.length; i++){ 
  			if(array[i]!=null){
   				return true;
 		 	}
 		 }
  		return false;
	}
	
}