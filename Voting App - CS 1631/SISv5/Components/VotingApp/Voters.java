public class Voters{

	private String voterID;
	private String candidateID;

	public Voters(){
		voterID = null;
		candidateID = null;
	}
	
	public Voters(String ID, String candID){
		voterID = ID;
		candidateID = candID;
	}
	
	public void setVoterID(String ID){
		voterID = ID;
	}
	
	public void setVote(String candID){
		candidateID = candID;
	}
	
	public String getVoterID(){
		return voterID;
	}
	
	public String getVote(){
		return candidateID;
	}
	
}