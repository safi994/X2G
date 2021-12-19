public class Item {
    private String title;
    private String story;
    private String place_id;
    private String place_title;
    private String person_id;
    private String person_name;
    private String person_gender;
    private String person_role;
    private String person_profession;

    public String gettitle() {
        return title;
    }
    public void settitle(String title) {
        this.title = title;
    }
    
    
    public String getstory() {
        return story;
    }
    public void setstory(String story) {
        this.story = story;
    }
    
    
    public String getplace_id() {
        return place_id;
    }
    public void setplace_id(String place_id) {
        this.place_id = place_id;
    }
    
    
    public String getplace_title() {
        return place_title;
    }
    public void setplace_title(String place_title) {
        this.place_title = place_title;
    }
    
    
    public String getperson_id() {
        return person_id;
    }
    public void setperson_id(String person_id) {
        this.person_id = person_id;
    }

    
    public String getperson_name() {
        return person_name;
    }
    public void setperson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getperson_gender() {
        return person_gender;
    }
    public void setperson_gender(String person_gender) {
        this.person_gender = person_gender;
    }

    
    public String getperson_role() {
        return person_role;
    }
    public void setperson_role(String person_role) {
        this.person_role = person_role;
    }
 
    
    public String getperson_profession() {
        return person_profession;
    }
    public void setperson_profession(String person_profession) {
        this.person_profession = person_profession;
    }
    
    @Override
    public String toString() {
        return "Item [Title" + title + ", Story=" + story + ", Place_ID="
                + place_id + ", Place_Title=" + place_title + ", Person_ID=" + person_id + ", "
                + "Person_Name=" + person_name +", Person_Gender=" + person_gender + ", Person_Role=" + person_role + 
                ", Person_Profession=" + person_profession + "]";
    }
}