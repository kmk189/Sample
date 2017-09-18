import java.util.Scanner;
import java.io.*;
import java.util.Iterator;

public class NetworkAnalysis{
	
	private static Scanner sc = new Scanner(System.in);
	private static int V;
	private static NetworkGraph netGraph;
	
	public static void main(String[] args)throws IOException{
		
		//Check for the command line argument
		String startup = null;
		
		try{
			startup = args[0];
		}catch(ArrayIndexOutOfBoundsException e){
			System.err.println("Please specify a file to read from.");
			System.exit(0);
		}
		
		//load the file one line at a time
		BufferedReader load = null;
		
		try{
			load = new BufferedReader(new FileReader(startup));
		}catch(FileNotFoundException e){
			System.out.println("The specified file does not exist");
			System.exit(0);
		}
		
		//first line is the number of vertices
		String loaded = load.readLine();
		int vertices = Integer.parseInt(loaded);
		V = vertices - 1;
		netGraph = new NetworkGraph(vertices);
		
		//build the graph using each line of the file
		while((loaded = load.readLine()) != null){
			String[] tokens = loaded.split(" ");
			int vertexA = Integer.parseInt(tokens[0]);
			int vertexB = Integer.parseInt(tokens[1]);
			String type = tokens[2];
			int b_width = Integer.parseInt(tokens[3]);
			int length = Integer.parseInt(tokens[4]);
			
			EdgeInfo e_info = new EdgeInfo(vertexA, vertexB, type, b_width, length);
			EdgeInfo e_info2 = new EdgeInfo(vertexB, vertexA, type, b_width, length);
			netGraph.addEdge(e_info);
			netGraph.addEdge(e_info2);
			//create graph with above values
		}
		
		load.close();
		//try-catch to load the file
		//first line is number of lines
		//load all the lines into objects??
		//build graph using the loaded lines
		
		int choice = 0;
		
		while(choice != 6){
			System.out.println("");
			System.out.println("Please choose an option from the following menu:");
			System.out.println("1. Find lowest latency path between 2 points");
			System.out.println("2. Determine if the graph is copper-only connected");
			System.out.println("3. Find the maximum amount of data that can be transmitted");
			System.out.println("4. Find the lowest average latency spanning tree");
			System.out.println("5. Determine if the graph will fail without 2 vertices");
			System.out.println("6. Exit program");
			
			choice = sc.nextInt();
			
			switch(choice){
				case 1:
					new NetworkAnalysis().lowestLatency();
					break;
				case 2:
					new NetworkAnalysis().copperOnly();
					break;
				case 3:
					new NetworkAnalysis().maximumData();
					break;
				case 4:
					new NetworkAnalysis().spanningTree();
					break;
				case 5:
					new NetworkAnalysis().determineFail();
					break;
				case 6:
					System.out.println("Exiting program");
					break;
				default:
					System.out.println("Not a valid option. Please choose 1-6");
			}
			
		}
		
		System.exit(0);
	
	}
	
	public void lowestLatency(){
		System.out.println("");
		System.out.println("Finding the lowest latency path between two points in the graph!");
		System.out.println("");
		System.out.println("Please enter the first vertex you would like to connect:");
		int vertex1 = sc.nextInt();
		sc.nextLine();
		
		while(vertex1 < 0 || vertex1 > V){
			System.out.println("Vertex must be between 0 and " + V);
			System.out.println("Please enter the first vertex you would like to connect:");
			vertex1 = sc.nextInt();
			sc.nextLine();
		}
		
		System.out.println("Please enter the second vertex you would like to connect:");
		int vertex2 = sc.nextInt();
		sc.nextLine();
		
		while(vertex2 < 0 || vertex2 > V || vertex1 == vertex2){
			System.out.println("Vertex must be between 0 and " + V + "and not equal the first vertex.");
			System.out.println("Please enter the second vertex you would like to connect:");
			vertex1 = sc.nextInt();
			sc.nextLine();
		}
		System.out.println("");
		
		DijkstraSP dijk_path = new DijkstraSP(netGraph, vertex1);
		
		if(dijk_path.hasPathTo(vertex2) == false){
			System.out.println("There is no path to this vertex.");
			return;
		}
		
		Stack<EdgeInfo> edge_stack = dijk_path.pathTo(vertex2);
		
		int[] band_arr = new int[V];
		int band_pos = 0;
		
		int size = edge_stack.size();
		
		for(int i = 0; i < size; i++){
			EdgeInfo e = edge_stack.pop();
			System.out.println(e.getVertex1() + " " + e.getVertex2() + " " + e.getType() + " " + e.getBandwidth() + " " + e.getLength());
			band_arr[i] = e.getBandwidth();
			band_pos++;
		}
		System.out.println("");
		
		int min = band_arr[0];
		
		for(int i = 1; i < band_pos; i++){
			if(band_arr[i] < min){
				min = band_arr[i];
			}
		}
		
		System.out.println("The bandwidth available along this path is " + min + " megabits per second.");
	}
	
	public void copperOnly(){
		System.out.println("");
		System.out.println("Determining if the graph is copper-only connected!");
		System.out.println("");
		
		int[] vert_fail = new int[V];
		int fail_pos = 0;
		EdgeInfo[] edge_arr = new EdgeInfo[V];
		int pos = 0;
		
		for(int i = 0; i < V; i++){
			Bag<EdgeInfo> vert_adj = netGraph.adj1(i);
			Iterator<EdgeInfo> bag_iter = vert_adj.iterator();
			
			while(bag_iter.hasNext()){
				EdgeInfo e = bag_iter.next();
				if(e.getType().equalsIgnoreCase("copper")){
					edge_arr[pos] = e;
					pos++;
					break;
				}
				if(e.getType().equalsIgnoreCase("optical") && bag_iter.hasNext() == false){
					vert_fail[fail_pos] = i;
					fail_pos++;
				}
			}
		}
		
		if(fail_pos > 0){
			System.out.println("The following vertices are not copper-connected:");
			for(int i = 0; i < fail_pos; i++){
				System.out.println("Vertex " + vert_fail[i]);
			}
		}else{
			System.out.println("The graph is copper-connected using the following edges:");
			for(int i = 0; i < V; i++){
				EdgeInfo e = edge_arr[i];
				System.out.println(e.getVertex1() + " " + e.getVertex2() + " " + e.getType() + " " + e.getBandwidth() + " " + e.getLength());
			}
		}
	}
	
	public void maximumData(){
		System.out.println("");
		System.out.println("Finding the max amount of data that can be transmitted between two vertices!");
		System.out.println("");
		System.out.println("Please enter the first vertex you would like to connect:");
		int vertex1 = sc.nextInt();
		sc.nextLine();
		
		while(vertex1 < 0 || vertex1 > V){
			System.out.println("Vertex must be between 0 and " + V);
			System.out.println("Please enter the first vertex you would like to connect:");
			vertex1 = sc.nextInt();
			sc.nextLine();
		}
		
		System.out.println("Please enter the second vertex you would like to connect:");
		int vertex2 = sc.nextInt();
		sc.nextLine();
		
		while(vertex2 < 0 || vertex2 > V || vertex1 == vertex2){
			System.out.println("Vertex must be between 0 and " + V + ", and not equal to the first vertex.");
			System.out.println("Please enter the second vertex you would like to connect:");
			vertex1 = sc.nextInt();
			sc.nextLine();
		}
		System.out.println("");
		
		FordFulkerson ff_path = new FordFulkerson(netGraph, vertex1, vertex2);
		
		double max_flow = ff_path.value();
		
		System.out.println("The maximum bandwidth from vertex " + vertex1 + " and vertex "
								+ vertex2 + " is " + max_flow + " megabits per second.");
	}
	
	public void spanningTree(){
		System.out.println("");
		System.out.println("Finding the lowest average latency spanning tree for the graph!");
		System.out.println("");
		
		KruskalMST krusk_tree = new KruskalMST(netGraph);
		
		Queue<EdgeInfo> krusk_edges = krusk_tree.edges();
		
		double krusk_weight = krusk_tree.weight();
		
		for(int i = 0; i < krusk_edges.size(); i++){
			EdgeInfo e = krusk_edges.dequeue();
			System.out.println(e.getVertex1() + " " + e.getVertex2() + " " + e.getType() + " " + e.getBandwidth() + " " + e.getLength());
		}
		
	}
	
	public void determineFail(){
		System.out.println("");
		System.out.println("Determining if the graph would be connected without any two vertices");
		System.out.println("");
		
		
		int[] vert1_fail = new int[V*V];
		int[] vert2_fail = new int[V*V];
		int pos = 0;
		
		for(int i = 0; i <= V; i++){
			
			int degree = netGraph.outdegree(i);
			
			System.out.println(i + " " + degree);
			
			if(degree == 2){
			
				Bag<EdgeInfo> bag_adj = netGraph.adj1(i);
				Iterator<EdgeInfo> bag_iter = bag_adj.iterator();
				
				EdgeInfo e = bag_iter.next();
				vert1_fail[pos] = e.getVertex2();
				
				e = bag_iter.next();
				vert2_fail[pos] = e.getVertex2();
				pos += 1;
				
			}else if(degree == 1){
				
				Bag<EdgeInfo> bag_adj1 = netGraph.adj1(i);
				Iterator<EdgeInfo> bag_iter1 = bag_adj1.iterator();
				
				EdgeInfo e1 = bag_iter1.next();
				
				for(int j = 0; j <= V; j++){
					if(j != i && j != e1.getVertex2()){
						vert1_fail[pos] = e1.getVertex2();
						vert2_fail[pos] = j;
						pos++;
					}
				}
			}else{
				System.out.println("meow");
			}
		}
		for(int i = 0; i < pos; i++){
			System.out.print(vert1_fail[i]);
		}
		System.out.println("");
		for(int i = 0; i < pos; i++){
			System.out.print(vert2_fail[i]);
		}
		
		System.out.println("");
		if(pos > 0){
			System.out.println("The graph would fail without the following pair(s) of vertices:");
			for(int i = 0; i < pos; i++){
				System.out.println(vert1_fail[i] + " " + vert2_fail[i]);
			}
		}else{
			System.out.println("The graph will not fail with any pair of vertices removed");
		}
	}
	
}