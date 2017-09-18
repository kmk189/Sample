import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;


public class voting {
	
	public static int userPlace;
	
	static ArrayList<Voter> voters = new ArrayList<Voter>();
	
	static ArrayList<Ballot> ballots = new ArrayList<Ballot>();

	//the main area which will start all of the gui and loading
	public static void main(String[] args) {
		
		File file = null;
		File votersinfo = null;
		Scanner load = null;
		Scanner loadVoters = null;
		
		//reading the file from the command line
		try {
			file = new File(args[0]);
			votersinfo = new File("voters.txt");
		} 
		catch (Exception FileNotFoundException) {}; 
		
		//checks if the file exists, if not then says so and exits
		if (!file.exists()) {
			System.out.println("This file does not exist.");
			System.exit(0);
		}
		
		//file now exists, loads the first line to find number of voters
		try {
			load = new Scanner(file);
			loadVoters = new Scanner(votersinfo);
		}
		catch (Exception e) {};
		
		//created the main window without any frames or panels here. 
		JFrame f = new JFrame("Voting Booth");
		//setting the size for the big window. seems like a fair size
		f.setSize(1000, 500);
		//setting so that clicking the x does not close it
		f.setDefaultCloseOperation(f.DO_NOTHING_ON_CLOSE);
		//don't know what the (1,0,10,10) means. I was told that was good...
		f.setLayout(new GridLayout(1, 0, 10, 10)); 
		
		
		int numcategories = load.nextInt();
		load.nextLine(); // 
		
		String[] options1 = null;
		String[] _Stuff = null;
		
		//loading and splitting the ballot file lines
		for (int i = 0; i < numcategories; i++) {
			
			String split1 = load.nextLine();
			
			//splits into the 3 parts
			_Stuff = split1.split(":"); 
			
			//assigns each part to a specific thing, splits the array of options
			int ballotID = Integer.parseInt(_Stuff[0]);
			String category = _Stuff[1];
			String _Options = _Stuff[2];
			options1 = _Options.split(",");
			
			//sends the things above to the ballot class to create the ballots. 
			ballots.add(new Ballot(ballotID, category, options1));
			
			for (int j = 0; j < ballots.get(i).getpanel().size(); j++) {
				ballots.get(i).add(ballots.get(i).getpanel().get(j));
			}
			
			f.add(ballots.get(i)); 
		}
		
		
		
		String[] _voterinfo = null;
		
		//loop to load the voter file
		for (int i = 0; loadVoters.hasNext(); i++) {
			
			voters.add(new Voter());
			
			//splitting the lines into their 3 parts (2 more lines down)
			String split1 = loadVoters.nextLine();
			
			_voterinfo = split1.split(":");
			
			//setting the 3 things loaded to their own spots in the voter class
			voters.get(i).addvoterID(Integer.parseInt(_voterinfo[0]));
			voters.get(i).addvotername(_voterinfo[1]);
			voters.get(i).addvoted(Boolean.parseBoolean(_voterinfo[2]));
			
		}
		
		//setting uf the cast vote button
		JPanel submitP = new JPanel();
		JButton submitB = new JButton("Cast Vote");
		
		//setting up the login button
		JPanel LogInP = new JPanel();
		LogInP.setLayout(new GridBagLayout());
		
		JButton loginButton = new JButton("Login to Vote");
		
		LogInP.add(loginButton);
		
		//the following are action listeners for the buttons and direct to those methods
		ActionListener LogInListen = new LogInListen(voters, ballots, loginButton);
		loginButton.addActionListener(LogInListen);
		
		ActionListener FinalAnswerListen = new FinalAnswerListen(voters, ballots, loginButton, submitB);
		submitB.addActionListener(FinalAnswerListen);
		submitB.setEnabled(false);
		
		submitP.add(submitB);
		submitP.setLayout(new GridBagLayout());
		
		//more action listeners for all the other buttons
		ActionListener OptionsListen = new OptionsListen(ballots, submitB);
		
		for (int i = 0; i < ballots.size(); i++) {
		
			for (int j = 0; j < ballots.get(i).getbutton().size(); j++) {
			
				ballots.get(i).getbutton().get(j).addActionListener(OptionsListen);
			
			}
			
		}
		
		//adding all the panels to the frame
		f.add(LogInP);
		
		f.add(submitP);
	
		f.setVisible(true);
		
	}
	
}

class Ballot extends JPanel{
	
	String[] options = null;
	ArrayList<JPanel> spacing = new ArrayList<JPanel>();
	ArrayList<JButton> buttons = new ArrayList<JButton>();
	
	boolean chosen = false;
	int choice;
	String ballotcategory;
	int ballotID;
	
	//this is the main ballot constructor which will set up the primary window view
	public Ballot(int ballotID, String ballotcategory, String[] options) {

		//still not sure what the 0,1 does, but I was told this was good? 
		setLayout(new GridLayout(0, 1));
		
		//specific variables: string, array, and id code
		this.ballotcategory = ballotcategory; 
		this.options = options; 
		this.ballotID = ballotID;
		
		//the following stuff puts the panels and buttons together to make the jframe
		//also sets them so they can't be clicked
		spacing.add(new JPanel(new GridBagLayout())); 

		JLabel categoryLabel = new JLabel(ballotcategory); 

		spacing.get(0).add(categoryLabel); 

		for (int i = 0; i < options.length; i++) {

			spacing.add(new JPanel(new GridBagLayout())); 
			
			buttons.add(new JButton(options[i])); 
			
			buttons.get(i).setEnabled(false); 
			
			spacing.get(i + 1).add(buttons.get(i));
			
		}
		
	}
	
	//getting the option for what to vote for
	public int getchoice() {

		return choice;
	
	}
	
		
	//getting the button
	public ArrayList<JButton> getbutton() {
	
		return buttons;
	
	}
	
	//getting the ballot's id number as a string
	public String getballotID() {
	
		return Integer.toString(ballotID);
	
	}
	
		
	//getting a panel
	public ArrayList<JPanel> getpanel() {
	
		return spacing;
	
	}
	
	//sets the option that was chosen as red
	public void setchoice(int choice) {
	
		this.choice = choice;
		
		for (int i = 0; i < buttons.size(); i++) {
		
			buttons.get(i).setForeground(Color.BLACK);
		
		}
		
		buttons.get(choice).setForeground(Color.RED);
		chosen = true;
	
	}
	
	//this clears and option and turns it back from red
	public void clear() {
	
		chosen = false;
		
		for (int i = 0; i < buttons.size(); i++) {
		
			buttons.get(i).setEnabled(false);
			
			buttons.get(i).setForeground(Color.GRAY);
		
		}
		
	}
	
	//before options are chosen it looks like this
	public void beforechoose() {
		
		for (int i = 0; i < buttons.size(); i++) {
		
			buttons.get(i).setForeground(Color.BLACK);
		
		}
	
	}

}

//this class does everything for the voter info: creating and referencing them.
class Voter {
	
	int id;
	boolean voted;
	String name;	
	
	public Voter() {
		
		//no constructor for this since there's the add methods
		
	}
	
	//adding the voter id code
	public void addvoterID(int id) {
	
		this.id = id;
	
	}
		
	//adding the voter's name
	public void addvotername(String name) {
	
		this.name = name;
	
	}
	
	//adding whether the voter already voted or not (true = voted, false = not voted)
	public void addvoted(boolean voted) {
	
		this.voted = voted;
	
	}
	
	//getting the voter id code
	public int getvoterID() {
	
		return id;
	
	}
	
	//getting the voter's name
	public String getvotername() {
	
		return name;
	
	}
	
	//checking whether the voter has already voted
	public boolean getvoted() {
	
		return voted;
	
	}
	
}

//action listener for the choice buttons for each category
class OptionsListen implements ActionListener {
	
	ArrayList<Ballot> ballots;
	JButton submitB;
	
	//setting the specifics
	public OptionsListen(ArrayList<Ballot> ballots, JButton submitB) {
		
		this.ballots = ballots;
		
		this.submitB = submitB;
	
	}
	
	//lots of loops to set the option choice (loops in loops in loops in loops, yo)
	public void actionPerformed(ActionEvent actionEvent) {
	
		for (int i = 0; i < ballots.size(); i++) {
		
			for (int j = 0; j < ballots.get(i).getbutton().size(); j++) {
			
				if (actionEvent.getActionCommand().equals(ballots.get(i).getbutton().get(j).getText())) {
				
					ballots.get(i).setchoice(j);
				
				}
				
			}
			
		}
		
		for (int i = 0; i < ballots.size(); i++) {
				
			if (!ballots.get(i).chosen) {
			
				break;
			} 
			
			if (i == ballots.size() - 1) {
			
				submitB.setEnabled(true);
			
			}
			
		}
		
	}
	
}

//the action listener for logging in
class LogInListen implements ActionListener {
	
	JButton loginButton;
	
	ArrayList<Voter> voters;
	ArrayList<Ballot> ballots;
	
	boolean successfulLogin = false;
	int userId;
	int userPlace;
	
	//setting the specifics again
	public LogInListen(ArrayList<Voter> voters, ArrayList<Ballot> ballots, JButton loginButton) {
		
		this.voters = voters;
		
		this.ballots = ballots;
		
		this.loginButton = loginButton;
	
	}
	
	//creating the frames for the prompt, multiple votes, and welcome
	//thought it would have these as the frame titles, but it doesn't so now I'm disappointed
	JFrame IDprompt = new JFrame("Put the ID in the box and no one gets hurt.");
	JFrame VotedAlready = new JFrame("How dare you try to vote again!!");
	JFrame SuccessfulLogIn = new JFrame("Welcome, kind sir or madam!");
	
	//checking the id here
	//set generically to -1 since that won't be a user id
	public int checkingID(int id) {
	
		int placement = -1; 
		
		for (int i = 0; i < voters.size(); i++) {
		
			if (id == voters.get(i).getvoterID()) {
			
				placement = i;
			
			}
		
		}
		
		return placement;
	
	}
	
	public void actionPerformed(ActionEvent e) {
		
		String userIdString;
		
		//prompting for a user id to be entered
		//if there isn't an id found, it will loop back and ask again
		do {
			userIdString = (String)JOptionPane.showInputDialog(IDprompt, "Please enter your user ID: ", JOptionPane.PLAIN_MESSAGE);
			
			if (userIdString == null) {
			
				break;
			
			}
			
			userId = Integer.parseInt(userIdString);
			userPlace = checkingID(userId);
		
		} 
		while (userPlace == -1);
		
		//once the user id has been entered, either second vote or welcome
		if (userPlace != -1 && userIdString != null) {
			
			//if the user has already voted before
			if (voters.get(userPlace).getvoted()) {
			
				JOptionPane.showMessageDialog(VotedAlready, "Sorry, you may not vote again, " + voters.get(userPlace).getvotername());
			
			}

			//if the user has not voted before
			else {
			
				JOptionPane.showMessageDialog(SuccessfulLogIn, "Please make your selection, " + voters.get(userPlace).getvotername());
				
				for (int i = 0; i < ballots.size(); i++) {
				
					for (int j = 0; j < ballots.get(i).getbutton().size(); j++) {
					
						ballots.get(i).getbutton().get(j).setEnabled(true);
					
					}
					
				}
				
				//changes it so the login button is gray instead
				loginButton.setEnabled(false);
				
				Assig4.userPlace = userPlace;
				
				for (int i = 0; i < ballots.size(); i++) {
				
					ballots.get(i).beforechoose();
				
				}
				
			}
			
		}
		
	}
	
}

//action listener for submissions
class FinalAnswerListen implements ActionListener {
	
	JButton loginButton;
	JButton submitB;
	
	ArrayList<Ballot> ballots;
	ArrayList<Voter> voters;
	
	int userPlace;
	
	//setting the new jframe for confirmation question
	JFrame submitFrame = new JFrame("Is that your final answer?");
	
	//setting the general things
	public FinalAnswerListen(ArrayList<Voter> voters, ArrayList<Ballot> ballots, JButton loginButton, JButton submitB) {
		
		this.ballots = ballots;
		
		this.voters = voters;
		
		this.loginButton = loginButton;
		
		this.submitB = submitB;		
	
	}
	
	//asking for confirmation and then submitting
	public void actionPerformed(ActionEvent e) {
		
		int choice = JOptionPane.showConfirmDialog(submitFrame, "Do you want to submit your vote?", "Submit Vote?", JOptionPane.YES_NO_OPTION);
		
		if (choice == 0) {
			
			userPlace = Assig4.userPlace;
			submitB.setEnabled(false);
			
			for (int i = 0; i < ballots.size(); i++) {
			
				ballots.get(i).clear();
				
				for (int j = 0; j < ballots.get(i).getbutton().size(); j++) {
					
					ballots.get(i).getbutton().get(j).setEnabled(false);
					ballots.get(i).chosen = false;
				
				}
				
			}
			
			loginButton.setEnabled(true);
			
			voters.get(userPlace).addvoted(true);
			
			//jumping over to the save method
			saveChange();
		
		}
	
	}
	
	public void saveChange() {
		
		//setting up the various printwriters and scanners to not mix them up. I believe multiple is necessary
		PrintWriter ballot = null;
		PrintWriter tempBallot = null;
		PrintWriter votersPrinter = null;
		PrintWriter tempVotersPrinter = null;
		
		Scanner loadBallot = null;
		Scanner loadtempBallot = null;
		
		File ballotfile = null;
		File tempBallotfile = null;
		
		int votes;
		
		String[] splitsplit = null;
		String split1;
		String tempString;
		
		//saving ballots
		for (int i = 0; i < ballots.size(); i++) {
			
			ballotfile = new File((ballots.get(i).getballotID() + ".txt"));
			tempBallotfile = new File((ballots.get(i).getballotID() + "temp.txt"));
			
			if (ballotfile.exists()) {
			
				//creating a temporary save
				try {
				
					tempBallot = new PrintWriter(ballots.get(i).getballotID() + "temp.txt");
				
				}
				catch (Exception e) {e.printStackTrace();}
				
				try {
				
					loadBallot = new Scanner(ballotfile);
				
				}
				catch (Exception e) {};

				//splitting and checking the ballot file and adding the vote and saving
				for (int j = 0; j < ballots.get(i).getbutton().size(); j++) {
					
					split1 = loadBallot.nextLine();

					splitsplit = split1.split(":");

					votes = Integer.parseInt(splitsplit[1]);

					if (ballots.get(i).getchoice() == j) {

						votes++;
					
					}
					
					tempBallot.println(ballots.get(i).getbutton().get(j).getText() + ":" + votes);
				
				}
			
			} 
			
			else {
				
				//checking for ballot and saving the temp file
				try {
					
					tempBallot = new PrintWriter(ballots.get(i).getballotID() + "temp.txt");
				
				}
				catch (Exception e) {e.printStackTrace();}

				for (int j = 0; j < ballots.get(i).getbutton().size(); j++) {

					votes = 0;
					
					if (ballots.get(i).getchoice() == j) {
						
						votes = 1;
					
					}
					
					tempBallot.println(ballots.get(i).getbutton().get(j).getText() + ":" + votes);

				}

			}

			tempBallot.close();
			
			//saving the ballots until they run out
			try {

				loadtempBallot = new Scanner(tempBallotfile);
				
				ballot = new PrintWriter(ballots.get(i).getballotID() + ".txt");
			
			}
			catch (Exception e) {};
			
			while (loadtempBallot.hasNext()) {

				tempString = loadtempBallot.nextLine();
				
				ballot.println(tempString);
			
			}
			
			ballot.close();
		
		}
		
		//checking and saving temp voters
		try {
		
			tempVotersPrinter = new PrintWriter("tempvoters.txt");
		
		}
		catch (Exception e) {};
		
		for (int j = 0; j < voters.size(); j++) {
		
			tempVotersPrinter.println(voters.get(j).getvoterID() + ":" + voters.get(j).getvotername() + ":" + voters.get(j).getvoted());
		
		}
		
		tempVotersPrinter.close();
		
		//checking and saving actual voters
		try {
		
			votersPrinter = new PrintWriter("voters.txt");
		
		}
		catch (Exception e) {};
		
		for (int j = 0; j < voters.size(); j++) {

			votersPrinter.println(voters.get(j).getvoterID() + ":" + voters.get(j).getvotername() + ":" + voters.get(j).getvoted());
		
		}
		
		votersPrinter.close();
	
	}
	
}
