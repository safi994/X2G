public class Node{

    private String id = null;
    private String value = null;
    private String type = null;
    
    
    Node(String id, String val, String type){
        this.id = id;
        this.value = val;
        this.type = type;
    }

    public String get_id(){
        return this.id;
    }

    public String get_value(){
        return this.value;
    }

    public String get_type(){
        return this.type;
    }

    public void set_id(String id){
        this.id = id;
    }

    public void set_value(String value){
        this.value = value;
    }

    public void set_type(String type){
        this.type = type;
    }
    

}