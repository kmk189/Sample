public class Candidate{
	
	private String candidateID;
	private int numVotes;
	private String topic;
	
	public Candidate(){
		candidateID = null;
		numVotes = 0;
		topic = null;
	}
	
	public Candidate(String candID){
		candidateID = candID;
		numVotes = 0;
		topic = null;
	}
	
	public Candidate(String candID, int num){
		candidateID = candID;
		numVotes = num;
		topic = null;
	}
	
	public Candidate(String candID, int num, String top){
		candidateID = candID;
		numVotes = num;
		topic = top;
	}
	
	public void setCandidateID(String candID){
		candidateID = candID;
	}
	
	public void setNumVotes(int num){
		numVotes = num;
	}
	
	public String getCandidateID(){
		return candidateID;
	}
	
	public int getNumVotes(){
		return numVotes;
	}
	
	public void addVote(){
		numVotes++;
	}
	
	public String getTopic(){
		return topic;
	}
	
	public void setTopic(String top){
		topic = top;
	}
}