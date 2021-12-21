public class Edge {
	
	private Node from;
	private Node to;
	private String type; // Type is the Label of this Edge 

	 Edge (Node from, Node to){
		this.from = from;
		this.to = to;
		
	}
	
	public Node getFrom(){
		return from;
	}
	
	public Node getTo(){
		return to;
	}
	

}
