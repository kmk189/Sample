public class EdgeInfo implements Comparable<EdgeInfo>{
	private static final double FLOATING_POINT_EPSILON = 1E-10;

	private int vertex1;
	private int vertex2;
	private String type;
	private int bandwidth;
	private int length;
	private double time;
	private double capacity;
	private double flow;

	public EdgeInfo(int vert1_in, int vert2_in, String type_in, int band_in, int length_in){
		if(vert1_in < 0 || vert2_in < 0) throw new IllegalArgumentException("Vertices must be non-negative integers");
		
		this.vertex1 = vert1_in;
		this.vertex2 = vert2_in;
		this.type = type_in;
		this.bandwidth = band_in;
		this.length = length_in;
		this.capacity = band_in;
		this.flow = 0;
		
		if(type_in.equalsIgnoreCase("copper")){
			this.time = length_in / 230000000;
		}else if(type_in.equalsIgnoreCase("optical")){
			this.time = length_in / 200000000;
		}
	}
	
	public int other(int vertex) {
		if(vertex == vertex1){
			return vertex2;
		}else if(vertex == vertex2){
			return vertex1;
		}else{
			throw new IllegalArgumentException("Illegal endpoint");
		}
	}
	
	public String toString() {
		return String.format("%d-%d %.5f", vertex1, vertex2, time);
	}
	
	public void setVertex1(int vert1_in){
		this.vertex1 = vert1_in;
	}
	
	public int getVertex1(){
		return vertex1;
	}
	
	public void setVertex2(int vert2_in){
		this.vertex2 = vert2_in;
	}
	
	public int getVertex2(){
		return vertex2;
	}
	
	public void setType(String type_in){
		this.type = type_in;
	}
	
	public String getType(){
		return type;
	}
	
	public void setBandwidth(int band_in){
		this.bandwidth = band_in;
	}
	
	public int getBandwidth(){
		return bandwidth;
	}
	
	public void setLength(int length_in){
		this.length = length_in;
	}
	
	public int getLength(){
		return length;
	}
	
	public void setTime(double time_in){
		this.time = time_in;
	}
	
	public double getTime(){
		return time;
	}
	
	public double capacity(){
		return capacity;
	}
	
	public double flow(){
		return flow;
	}
	
	public double residualCapacityTo(int vertex) {
		if(vertex == vertex1){
			return flow;		// backward edge
		}else if(vertex == vertex2){ 
			return capacity - flow;   // forward edge
		}else{
			throw new IllegalArgumentException("invalid endpoint");
		}
	}

	public void addResidualFlowTo(int vertex, double delta) {
		if(!(delta >= 0.0)){
			throw new IllegalArgumentException("Delta must be nonnegative");
		}
        
		if(vertex == vertex1){
			flow -= delta;           // backward edge
		}else if(vertex == vertex2){
			flow += delta;           // forward edge
		}else{
			throw new IllegalArgumentException("invalid endpoint");
		}
        // round flow to 0 or capacity if within floating-point precision
		if (Math.abs(flow) <= FLOATING_POINT_EPSILON)
			flow = 0;
		if (Math.abs(flow - capacity) <= FLOATING_POINT_EPSILON)
			flow = capacity;

		if (!(flow >= 0.0))      throw new IllegalArgumentException("Flow is negative");
		if (!(flow <= capacity)) throw new IllegalArgumentException("Flow exceeds capacity");
	}
	
	public int compareTo(EdgeInfo that) {
		return Double.compare(this.time, that.time);
	}

}