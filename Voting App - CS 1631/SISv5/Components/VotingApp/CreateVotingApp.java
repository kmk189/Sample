import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Calendar;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CreateVotingApp
{
    // socket for connection to SISServer
    static Socket universal;
    private static int port = 53217;
    // message writer
    static MsgEncoder encoder;
    // message reader
    static MsgDecoder decoder;

    // scope of this component
    private static final String SCOPE = "SIS.Scope1";
	// name of this component
    private static final String NAME = "VotingApp";
    // messages types that can be handled by this component
    private static final List<String> TYPES = new ArrayList<String>(
        Arrays.asList(new String[] { "Alert", "Emergency", "Confirm", "Setting" }));

	public static TallyTable tally;
	
    // variables for sending emails
    static final String SMTP_HOST_NAME = "smtp.ksiresearch.org.ipage.com";
    static final String SMTP_PORT = "587";
    static final String emailMsgTxt = "Test Message Contents";
    static final String emailSubjectTxt = "Personal Healthcare Data From SIS System."; // title
    static final String emailFromAddress = "chronobot@ksiresearch.org";
    static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    /*
     * Main program
     */
    public static void main(String[] args)
    {
        while (true)
        {
            try
            {
                // try to establish a connection to SISServer
                universal = connect();

                // bind the message reader to inputstream of the socket
                decoder = new MsgDecoder(universal.getInputStream());
                // bind the message writer to outputstream of the socket
                encoder = new MsgEncoder(universal.getOutputStream());

                /*
                 * construct a Connect message to establish the connection
                 */
                KeyValueList conn = new KeyValueList();
                conn.putPair("Scope", SCOPE);
                conn.putPair("MessageType", "Connect");
                conn.putPair("Role", "Advertiser");
                conn.putPair("Name", NAME);
                encoder.sendMsg(conn);

                // KeyValueList for inward messages, see KeyValueList for
                // details
                KeyValueList kvList;


                while (true)
                {
                    // attempt to read and decode a message, see MsgDecoder for
                    // details
                    kvList = decoder.getMsg();

                    // process that message
                    ProcessMsg(kvList);
                }

            }
            catch (Exception e)
            {
                // if anything goes wrong, try to re-establish the connection
                e.printStackTrace();
                try
                {
                    // wait for 1 second to retry
                    Thread.sleep(1000);
                }
                catch (InterruptedException e2)
                {
                }
                System.out.println("Try to reconnect");
                try
                {
                    universal = connect();
                }
                catch (IOException e1)
                {
                }
            }
        }
    }

    /*
     * used for connect(reconnect) to SISServer
     */
    static Socket connect() throws IOException
    {
        Socket socket = new Socket("127.0.0.1", port);
        return socket;
    }

    private static void ProcessMsg(KeyValueList kvList) throws Exception
    {

        String scope = kvList.getValue("Scope");
        if (!SCOPE.startsWith(scope))
        {
            return;
        }

        String messageType = kvList.getValue("MessageType");
        if (!TYPES.contains(messageType))
        {
            return;
        }

        String sender = kvList.getValue("Sender");

        String receiver = kvList.getValue("Receiver");

        String purpose = kvList.getValue("Purpose");

        switch (messageType)
        {
			case "Confirm":
				System.out.println("Connection to SISServer successful");
				break;
			case "Setting":
				switch(purpose)
				{
					case "Vote":
						System.out.println("Received a Vote");
						String email = kvList.getValue("Email");
						String vote = kvList.getValue("Vote");
						
						if(email != null && !email.equals("") && vote != null && !vote.equals("")){
							tally.castVote(email, vote);
						}
						break;
						
					case "StartPoll":
						System.out.println("Starting Poll");
						Integer numberOfCandidates = Integer.parseInt(kvList.getValue("NumberOfCandidates"));
						//move out to a more static location?
						int[] candidates = new int[numberOfCandidates];
						String[] topics = new String[numberOfCandidates];
						
						for(int i = 0; i < numberOfCandidates; i++){
							int candidate = Integer.parseInt(kvList.getValue("Candidate" + i));
							candidates[i] = candidate;
							topics[i] = kvList.getValue("Topic" + i);
						}
						tally = new TallyTable(candidates, topics);
						break;
						
					case "ClosePoll":
						System.out.println("Closing Poll");
						tally.displayAll();
						Candidate winner = tally.getWinner();
						System.out.println("The winner is...");
						System.out.println("Candidate: " + winner.getCandidateID());
						System.out.println("Votes: " + winner.getNumVotes());
						
						String[] topicsArr = tally.getTopicList();
						int[] votes = tally.getVoteList();
						
            KeyValueList conn = new KeyValueList();
            conn.putPair("Scope", SCOPE);
            conn.putPair("MessageType", "Setting");
            conn.putPair("Purpose", "Analyze");
            conn.putPair("Sender", "VotingApp");
            conn.putPair("Receiver", "TrendsAnalyzer");
            conn.putPair("NumberOfTopics", Integer.toString(topicsArr.length));
            for(int i = 0; i < topicsArr.length; i++){
              conn.putPair("Topic" + i, topicsArr[i]);
              conn.putPair("Vote" + i, Integer.toString(votes[i]));
            }
            encoder.sendMsg(conn);
						
						break;
					
					case "Kill":
						System.exit(0);
						break;
				}
        }
    }
}


