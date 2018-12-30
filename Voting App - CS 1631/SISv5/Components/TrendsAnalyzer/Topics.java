import java.util.*;
import java.lang.*;

public class Topics{
	
	private String topic;
	private int vote_count;
	
  public Topics(){
    topic = null;
    vote_count = 0;
  }
  
	public Topics(String top, int vot){
		topic = top;
		vote_count = vot;
	}
	
	public void setTopic(String top){
		topic = top;
	}
	
	public String getTopic(){
		return topic;
	}
	
	public void setVoteCount(int vot){
		vote_count = vot;
	}
	
	public int getVoteCount(){
		return vote_count;
	}
  
  public void addVotes(int vot){
    vote_count += vot;
  }
	
}