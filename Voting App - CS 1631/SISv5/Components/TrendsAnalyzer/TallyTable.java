import java.util.*;
import java.lang.*;

public class TallyTable{

	ArrayList<Topics> topics;
	
	public TallyTable(String[] topic_list, int[] votes){
		
		topics = new ArrayList<Topics>();
		
		for(int i = 0; i < topic_list.length; i++){
			topics.add(new Topics(topic_list[i], votes[i]));
		}
    
	}
  
  public void shrink(){
    ArrayList<Topics> shrink_topics = new ArrayList<Topics>();
    ArrayList<String> fin_topics = new ArrayList<String>();
    
    for(int i = 0; i < topics.size(); i++){
			String c = topics.get(i).getTopic();
			int v = topics.get(i).getVoteCount();
			if(fin_topics.isEmpty()){
				shrink_topics.add(new Topics(c, v));
				fin_topics.add(c);
			}
			else{
				if(fin_topics.contains(c)){
					int index = fin_topics.indexOf(c);
          shrink_topics.get(index).addVotes(v);
        }
				else{
					fin_topics.add(c);
					shrink_topics.add(new Topics(c, v));
				}
			}
		}
    
    topics = shrink_topics;
  }
	
	public Topics getTopicWinner(){
		
		int currWinner = 0;
		Topics winner = new Topics();
		
		for(int i = 0; i < topics.size(); i++){
			if(topics.get(i).getVoteCount() > currWinner){
				currWinner = topics.get(i).getVoteCount();
				winner = topics.get(i);
			}
		}
		
		return winner;
		
	}
	
  public void displayAllTopics(){
		System.out.println("");
		System.out.println("All topic results: ");
		System.out.println("-------------------------");
		System.out.printf("CandidateID                   Votes\n");
		for(int i = 0; i < topics.size(); i++){
			System.out.printf("%-29s %-5d\n", topics.get(i).getTopic(), topics.get(i).getVoteCount());
		}
		System.out.println("");
	}
}