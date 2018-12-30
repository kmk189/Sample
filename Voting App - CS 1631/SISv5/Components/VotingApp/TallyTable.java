import java.util.*;

public class TallyTable{

	ArrayList<Voters> voters;
	ArrayList<Candidate> candidates;
	
	//Constructor
	public TallyTable(int[] candidateList, String[] topicList){
		
		voters = new ArrayList<Voters>();
		candidates = new ArrayList<Candidate>();
		
		for(int i = 0; i < candidateList.length; i++){
			candidates.add(new Candidate(Integer.toString(candidateList[i]), 0, topicList[i]));
		}
		
	}
	
	//returns 0 if invalid
	//returns 1 if valid
	public int castVote(String voterID, String candidateID){
		Candidate temp = new Candidate();
		
		for(int i = 0; i < candidates.size(); i++){
			if(candidates.get(i).getCandidateID().equals(candidateID)){
				temp = candidates.get(i);
				break; //valid candidate ID
			}
			else if(i == candidates.size() - 1){
				return 0;
			}
		}
		
		for(int i = 0; i < voters.size(); i++){
			if(voters.get(i).getVoterID().equals(voterID)){
				return 0;
			}
		}
		
		voters.add(new Voters(voterID, candidateID));
		temp.addVote();
		return 1;
		
	}
	
	public Candidate getWinner(){
		
		int currWinner = 0;
		Candidate winner = new Candidate();
		
		for(int i = 0; i < candidates.size(); i++){
			if(candidates.get(i).getNumVotes() > currWinner){
				currWinner = candidates.get(i).getNumVotes();
				winner = candidates.get(i);
			}
		}
		
		return winner;
		
	}
	
	public void displayAll(){
		System.out.println("");
		System.out.println("All candidate results: ");
		System.out.println("-------------------------");
		System.out.printf("CandidateID         Votes\n");
		for(int i = 0; i < candidates.size(); i++){
			System.out.printf("%-19s %-5d\n", candidates.get(i).getCandidateID(), candidates.get(i).getNumVotes());
		}
		System.out.println("");
	}
	
	public String[] getTopicList(){
		ArrayList<String> topic_list = new ArrayList<String>();
		
		for(int i = 0; i < candidates.size(); i++){
			topic_list.add(candidates.get(i).getTopic());
		}
		
    String[] arr = new String[topic_list.size()];
    for(int j = 0; j < topic_list.size(); j++){
      arr[j] = topic_list.get(j);
    }
		return arr;
	}
	
	public int[] getVoteList(){
		ArrayList<Integer> vote_list = new ArrayList<Integer>();
		
		for(int i = 0; i < candidates.size(); i++){
			vote_list.add(candidates.get(i).getNumVotes());
		}
		
    int[] arr = new int[vote_list.size()];
    for(int j = 0; j < vote_list.size(); j++){
      arr[j] = vote_list.get(j);
    }
		return arr;
	}

	
	public void clearTables(){
		voters.clear();
		candidates.clear();
	}

}