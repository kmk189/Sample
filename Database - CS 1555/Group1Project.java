import java.sql.*;
import java.util.*;
import java.lang.*;
import java.text.*;

public class Group1Project{
	
	private static Scanner sc = new Scanner(System.in).useDelimiter("[^0-9]+");
	private static Scanner sc2 = new Scanner(System.in);
	static final String myDriver = "";
	static final String myDatatbase = "jdbc:oracle:thin:@class3.cs.pitt.edu:1521:dbclass";
	static final String username = "sal162";		
	static final String password = "4002531";
	static String currentUser;
	static Group1Functions functions = new Group1Functions();
	
	public static void main(String[] args){
	
		Connection con = null;
		Statement st = null;
    
		try{

			con = DriverManager.getConnection(myDatatbase, username, password);
			st = con.createStatement();
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			con.setAutoCommit(false);
		
			run(con, st);

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

		sc.close();
		sc2.close();
		System.exit(0);	
		
	}
	

	public static void run(Connection conn, Statement stmt){
		Connection con = conn;
		Statement st = stmt;
		
		
		int choice = 0;
		boolean loggedIn = false;
		System.out.printf("\nWelcome to Social Panther by Group 1\n");
		do{
			do{
				
				System.out.printf("\nPlease choose an option: \n1. Log In\n2. Create User\n3. Exit\n");
				choice = sc.nextInt();
				sc.nextLine();
				
			}while(choice != 1 && choice != 2 && choice != 3);
			
			switch(choice){
				
				case 1:
				
					System.out.println("");
					System.out.print("User ID: ");
					String userID = sc2.nextLine();
					
					boolean empty  = true;
					
					while(userID.length() < 1 || userID.length() > 20){
						System.out.println("Entered ID is not a valid length. Please try again");
						System.out.print("User ID: ");
						userID = sc2.nextLine();
					}
					
					System.out.print("Password: ");
					String pass = sc2.nextLine();
					
					while(pass.length() < 1 || pass.length() > 50){
						System.out.println("Entered password is not a valid length. Please try again");
						System.out.print("Password: ");
						pass = sc2.nextLine();
					}
					
					loggedIn = functions.logIn(con, st, userID, pass);
					
					if(loggedIn){
						currentUser = userID;
					}
					
					break;
				
				case 2:
				
					System.out.println("createUser"); 
					System.out.println("");
					System.out.println("Please enter the following information:");
					
					System.out.print("User ID: ");
					String userIDa = sc2.nextLine();
					
					while(userIDa.length() > 20){
						System.out.println("User ID is too long. Must be 20 characters or less");
						System.out.print("User ID: ");
						userIDa = sc2.nextLine();
					}
					
					System.out.print("Name: ");
					String name = sc2.nextLine();
					
					while(name.length() > 50){
						System.out.println("Name entered is too long. Must be 50 characters or less");
						System.out.print("Name: ");
						name = sc2.nextLine();
					}
					
					String email = "aaaaaaaaaaaaaaaaaaaa";
					boolean valid_email = false;
					
					while(valid_email == false){
						
						System.out.print("Email (ex. aaa111@pitt.edu): ");
						email = sc2.nextLine();
						
						if(!email.endsWith("@pitt.edu")){
							System.out.println("Email must be a Pitt email ending with '@pitt.edu'");
							System.out.print("Please try entering email again");
							valid_email = false;
						}
						else{
							if(valid_email = true){
								if(email.length() <= 15){
									valid_email = true;
								}
								else{
									System.out.println("Email may not be more than 15 characters long");
									System.out.println("Please try entering email again");
									valid_email = false;
								}
							}
						}
					}

					System.out.print("Password: ");
					String passa = sc2.nextLine();
					
					while(passa.length() > 50){
						System.out.println("Password length is too long. Must be less than 50 characters");
						System.out.print("Password: ");
						passa = sc2.nextLine();
					}
					
					System.out.print("Date of Birth (ex. 31-OCT-91): ");
					String date_of_birth = sc2.nextLine();

					while(!functions.checkDate(date_of_birth)){
						System.out.print("Date of Birth: ");
						date_of_birth = sc2.nextLine();
					}
					
					functions.createUser(con, st, userIDa, name, email, passa, date_of_birth);
					
					break;
				
				case 3:
					System.out.printf("Thank you for visiting Social Panther by Group 1\n\n");
					System.exit(0);
				default:
					System.out.printf("How did it reach this option? It should be impossible!");
					System.exit(0);
			}
			
			if(!loggedIn){
				System.out.println("Please log in, create user, or exit!");
			}
		
		}while(!loggedIn);
		
		boolean loggedOut = false;
		
		do{
			System.out.println("");
			System.out.println("Which thing do you want to deal with?");
			System.out.println("Please choose an option from the menu by typing the corresponding number:");
			System.out.println("1. Personal");
			System.out.println("2. Friends");
			System.out.println("3. Groups");
			System.out.println("4. Miscellaneous");
			System.out.println("5. Log Out");
			choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice){
				
				case 1:
					
					do{
						System.out.println("");
						System.out.println("Please choose an option:");
						System.out.println("1. Display Messages");
						System.out.println("2. Display Newest Messages");
						System.out.println("3. Delete Account");
						System.out.println("4. Return to Main Menu");
						choice = sc.nextInt();
						sc.nextLine();
						
						switch(choice){
							case 1:
								//no user input necessary
								functions.displayMessages(con, st, currentUser);
								break;
							case 2:
								//no user input necessary
								functions.displayNewMessages(con, st, currentUser);
								break;
							case 3:
								System.out.print("Are you sure you want to delete your account? (y/n) ");
								String check = sc2.nextLine();
								
								while(!check.equalsIgnoreCase("y") && !check.equalsIgnoreCase("n")){
									System.out.println("Please enter 'y' for yes or 'n' for no");
									System.out.print("Are you sure? ");
									check = sc2.nextLine();
								}
								
								if(check.equalsIgnoreCase("n")){
									System.out.println("Thanks for not leaving Social Panther!");
									System.out.println("Returning to menu...");
									break;
								}
								
								functions.dropUser(con, st, currentUser);
								
								System.out.println("Thank you for using Social Panther by Group 1");
								
								break;
							
							case 4:
								
								System.out.println("Returning to Main Menu...");
								break;
							
							default:
								
								System.out.println(choice + " is not a valid option");
						
						}
						
					}while(choice != 4);
					
					choice = 0;
					
					break;
					
				case 2: 
					
					do{
						System.out.println("");
						System.out.println("Please choose an option: ");
						System.out.println("1. Display Friends");
						System.out.println("2. Confirm Friendship(s)");
						System.out.println("3. Initiate Friendship");
						System.out.println("4. Search for a User");
						System.out.println("5. Send a Message to a User");
						System.out.println("6. Return to Main Menu");
						choice = sc.nextInt();
						sc.nextLine();
						
						switch(choice){
							case 1:
							
								String[] friends_list = functions.displayFriends(con, st, currentUser);
								
								System.out.println("Select a profile to view with the corresponding number or press 0 to exit");
								int selected = sc.nextInt();
								
								while(selected != 0){
									functions.displayProfile(con, st, friends_list[selected - 1]);
									
									System.out.println("Choose another profile or press 0 to return to menu");
									selected = sc.nextInt();
								}
								
								break;
								
							case 2:
							
								functions.displayFriendRequests(con, st, currentUser);
								
								System.out.println("Please choose the number corresponding to the friend request you would like to accept");
								System.out.println("If you would like to accept all requests, enter 0");
								System.out.println("Enter 'exit' to finish accepting requests and decline all remaining");
								System.out.print("Friend to request: ");
								String selected_str = sc2.next();

								while(!selected_str.equalsIgnoreCase("exit")){
									selected_str = selected_str.replaceAll("\\D+","");
									int selecteda = Integer.parseInt(selected_str);

									functions.confirmFriendship(con, st, selecteda, currentUser);
									
									System.out.print("Enter another number to add or 0 to add all or 'exit' to quit: ");
									selected_str = sc2.next();
								}
								if(selected_str.equalsIgnoreCase("exit")){
									functions.removeRemainingRequests(con, st, currentUser);
								}
								
								break;
								
							case 3:
							
								System.out.println("initiateFriendship");
								System.out.println("User ID: ");
								String toUser = sc2.nextLine();
								
								while(toUser.length() > 20){
									System.out.println("The user ID is too long to be valid");
									System.out.print("Please enter the user ID: ");
									toUser = sc2.nextLine();
								}
								
								System.out.println("Friend Request Message (Press enter twice to finish): ");
								
								StringBuilder messageb = new StringBuilder();
								String temp;
								boolean loop = true;
								
								while(loop){
									
									temp = sc2.nextLine();
									
									if(temp.equals("")){
										loop = false;
									}
									
									else{
										messageb.append(temp);
										
										if(messageb.length() > 200){
											System.out.println("Message may only be 200 characters total");
											System.out.println("Message has been cleared. Please try entering it again:");
											messageb.setLength(0);
											loop = true;
										}
										
									}
									
							}
								String message = messageb.toString();
								
								System.out.println("Are you sure that you would like to add " + toUser + "? (y/n)");
								String confirm = sc2.nextLine();
								
								while(!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n")){
									System.out.println("");
									System.out.println("Please enter either 'y' for yes or 'n' for no");
									System.out.println("Are you sure that you would like to add " + toUser + "? (y/n)");
									confirm = sc2.nextLine();
								}
								
								if(confirm.equalsIgnoreCase("n")){
									System.out.println("That's okay! Maybe later!");
									System.out.println("Returning to Menu");
									break;
								}

								functions.initiateFriendship(con, st, toUser, message, currentUser);
								
								break;
								
							case 4:
							
								System.out.println("");
								System.out.println("Searching for user");
								System.out.println("Please enter any search terms separated by spaces");
								System.out.println("Search terms: ");
								String input = sc2.nextLine();
							
								functions.searchForUser(con, st, input);

								break;

							case 5:
								
								functions.displayImmediateFriends(con, st, currentUser);

								//user entry of number for which to send message to 
								System.out.println("Please choose the number corresponding to the friend you would like to message");
								System.out.println("If you would like to exit, enter 0");
								System.out.print("Friend to message: ");
								int selectedb = sc.nextInt();
								sc.nextLine();
								
								if(selectedb == 0){
									System.out.println("Returning to Menu...");
									break;
								}

								//prompt for text to enter (200 chars max, can be multi-line[set terminating char?])
								System.out.println("Message (Press enter twice to finish): ");
									
								StringBuilder messagebu = new StringBuilder();
								String tempe;
								boolean loope = true;
									
								while(loope){
									
									tempe = sc2.nextLine();
									
									if(tempe.equals("")){
										loope = false;
									}
									
									else{
										
										messagebu.append(tempe);
										
										if(messagebu.length() > 200){
											System.out.println("Message may only be 200 characters total");
											System.out.println("Message has been cleared. Please try entering it again:");
											messagebu.setLength(0);
											loope = true;
										}
										
									}
									
								}
									
								String messagec = messagebu.toString();
								
								functions.sendMessageToUser(con, st, selectedb, messagec, currentUser);
								
								break;
								
							case 6:
							
								System.out.println("Returning to Main Menu...");
							
								break;
							
							default:
								
								System.out.println(choice + " is not a valid option");
						
						}
						
					}while(choice != 6);
					
					choice = 0;
					
					break;
				
				case 3: 
				
					do{
						System.out.println("");
						System.out.println("Please choose an option:");
						System.out.println("1. Create Group");
						System.out.println("2. Join a Group");
						System.out.println("3. Send a Message to a Group");
						System.out.println("4. Return to the Main Menu");
						choice = sc.nextInt();
						sc.nextLine();
						
						switch(choice){
							case 1:
															
								System.out.println("");
								System.out.println("Please provide the following information: ");
								System.out.print("Group Name: ");
								String groupName = sc2.nextLine();

								//currentUser = "s_lambert";
								
								while(groupName.length() > 50 || groupName.length() < 1){
									System.out.println("Group name may not be more than 50 characters long");
									System.out.print("Group Name: ");
									groupName = sc2.nextLine();
								}
								
								System.out.println("Member Limit: ");
								int limit = sc.nextInt();
								sc.nextLine();
								
								while(limit < 1){
									System.out.println("Member Limit must be at least 1");
									System.out.print("Member Limit: ");
									limit = sc.nextInt();
									sc.nextLine();
								}
								
								System.out.println("Group Description (Press enter twice to finish): ");
								
								StringBuilder description = new StringBuilder();
								String temp;
								boolean loop = true;
								int groupID;
								
								while(loop){
									
									temp = sc2.nextLine();
									
									if(temp.equals("")){
										loop = false;
									}
									
									else{
										
										description.append(temp);
										
										if(description.length() > 200){
											System.out.println("Message may only be 200 characters total");
											System.out.println("Message has been cleared. Please try entering it again:");
											loop = true;
										}
										
									}
								}

								String descriptionFinal = description.toString();
								
								System.out.println("Are you sure that you would like to create the group, " + groupName + "? (y/n)");
								String confirm = sc2.nextLine();
								
								while(!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n")){
									System.out.println("");
									System.out.println("Please enter either 'y' for yes or 'n' for no");
									System.out.println("Are you sure that you would like to create the group, " + groupName + "? (y/n)");
									confirm = sc2.nextLine();
								}
								if(confirm.equalsIgnoreCase("n")){
									System.out.println("That's okay! Maybe later!");
									System.out.println("Returning to Menu");
									break;
								}
								
								functions.createGroup(con, st, groupName, limit, descriptionFinal, currentUser);
								
								break;
							
							case 2:
							
								functions.displayAddingGroup(con, st, currentUser);
								
								String selected_str = "a";
								
								do{
									System.out.println("");
									System.out.println("Please choose the number corresponding to the group you would like to join");
									System.out.println("If you would like to join all groups, enter 0");
									System.out.println("To exit the groups list, enter 'exit'");
									System.out.print("Group to request: ");
									selected_str = sc2.next();
									int selected = -1;
									boolean valid_input = false;
									boolean finished = false;
									while(!valid_input){
										if(selected_str.equalsIgnoreCase("exit")){
											System.out.println("Not joining anymore groups");
											System.out.println("Returning to menu");
											finished = true;
											break;
										}
										else{
											try{
												selected = Integer.parseInt(selected_str);
											}
											catch(Exception exc){
												System.out.println("Input may only be numbers or 'exit'");
												System.out.println("Group to request: ");
												selected_str = sc2.next();
											}
										}
									}
									if(!finished){
										System.out.println("Group Request Message (Press enter twice to finish): ");
											
										StringBuilder messageb = new StringBuilder();
										String tempa;
										boolean loopa = true;
											
										while(loopa){
												
											tempa = sc2.nextLine();
												
											if(tempa.equals("")){
												loopa = false;
											}
												
											else{
													
												messageb.append(tempa);
													
												if(messageb.length() > 200){
													System.out.println("Message may only be 200 characters total");
													System.out.println("Message has been cleared. Please try entering it again:");
													messageb.setLength(0);
													loopa = true;
												}
													
											}
												
										}
											
										String messaged = messageb.toString();
											
										if(selected == 0){
											System.out.println("Are you sure that you would like to join all groups? (y/n)");
										}
										else{		
											System.out.println("Are you sure that you would like to add that group? (y/n)");
										}
										String confirma = sc2.nextLine();
										while(!confirma.equalsIgnoreCase("y") && !confirma.equalsIgnoreCase("n")){
											System.out.println("");
											System.out.println("Please enter either 'y' for yes or 'n' for no");
											confirma = sc2.nextLine();
										}
										if(confirma.equalsIgnoreCase("n")){
											System.out.println("That's okay! Maybe later!");
											System.out.println("Returning to Menu");
											break;
										}

										functions.initiateAddingGroup(con, st, selected, messaged, currentUser);
									}
								}while(!selected_str.equalsIgnoreCase("exit"));
								
								break;
							
							case 3:
							
								functions.displayMessageGroup(con, st, currentUser);
							
								//promt user for group ID to send to 
								System.out.println("Please choose the number corresponding to the group you would like to message");
								System.out.println("If you would like to exit, enter 0");
								System.out.print("Group to message: ");
								int selectedGroup = sc.nextInt();
								sc.nextLine();

								if(selectedGroup == 0){
									System.out.println("Returning to Menu...");
									break;
								}
								
								//prompt for text of message 
								System.out.println("Group Message (Press enter twice to finish): ");
									
								StringBuilder messageb = new StringBuilder();
								String tempd;
								boolean loopd = true;
									
								while(loopd){
									
									tempd = sc2.nextLine();
									
									if(tempd.equals("")){
										loopd = false;
									}
									
									else{
										
										messageb.append(tempd);
										
										if(messageb.length() > 200){
											System.out.println("Message may only be 200 characters total");
											System.out.println("Message has been cleared. Please try entering it again:");
											messageb.setLength(0);
											loopd = true;
										}
										
									}
									
								}
									
								String message = messageb.toString();

								functions.sendMessageToGroup(con, st, selectedGroup, message, currentUser);
							
								break;
							
							case 4:
								
								System.out.println("Returning to Main Menu");
								break;
							
							default:
								
								System.out.println(choice + " is not a valid option");
						
						}
					
					}while(choice != 4);
					
					choice = 0;
					
					break;
				
				case 4: 
					
					do{
						
						System.out.println("");
						System.out.println("Please choose an option: ");
						System.out.println("1. Find Connection Between 2 Users");
						System.out.println("2. Find Top Message Senders");
						System.out.println("3. Return to the Main Menu");
						choice = sc.nextInt();
						sc.nextLine();
						
						switch(choice){
							case 1:
							
								//prompt for two different users 
								System.out.println("");
								System.out.print("Please enter the first user's ID: ");
								String userA = sc2.nextLine();
								
								while(userA.length() < 1 || userA.length() > 20){
									System.out.println("User ID is not of a valid length. Please try again");
									System.out.print("First User ID: ");
									userA = sc2.nextLine();
								}
								
								System.out.print("Please enter the second user's ID: ");
								String userB = sc2.nextLine();
								
								while(userB.length() < 1 || userB.length() > 20){
									System.out.println("User ID is not of a valid length. Please try again");
									System.out.print("Second User ID: ");
									userB = sc2.nextLine();
								}
							
								functions.threeDegrees(con, st, userA, userB);
								
								break;
							
							case 2:
								
								System.out.println("");
								System.out.print("Enter number of people to query: ");
								int num_people = sc.nextInt();
								sc.nextLine();
								
								if(num_people == 0){
									System.out.println("Querying 0 people.");
									System.out.println("Done!");
									System.out.println("It's 0 people, obviously");
									System.out.println("Returning to Menu...");
									break;
								}
								
								System.out.println("Enter how many months to check for: ");
								int months = sc.nextInt();
								sc.nextLine();
								
								if(months == 0){
									System.out.println("0 months, huh? That's not enough!");
									System.out.println("Returning to Menu...");
									break;
								}
								
								System.out.println("Would you like to check for top (sent) or (received)?");
								String sendReceived = sc2.nextLine();
								
								while(!sendReceived.equalsIgnoreCase("sent") && !sendReceived.equalsIgnoreCase("received")){
									System.out.println("Please enter 'sent' or received to view the top sender or receiver");
									sendReceived = sc2.nextLine();
								}

								functions.topMessages(con, st, num_people, months, sendReceived);
								
								break;
							
							case 3:
								
								System.out.println("Returning to Main Menu...");
								break;
							
							default:
								
								System.out.println(choice + " is not a valid option");
						
						}
						
					}while(choice != 3);
					
					choice = 0;
				
					break;
				
				case 0:
					//this is for looping purposes and will just go to beginning and display menu again
					break;
				
				case 5:

					functions.logOut(con, st, currentUser);
					loggedOut = true;

					break;
				
				default:
					System.out.println(choice + " is not a valid option!");
			}
			
		}while(!loggedOut);

	}

}
