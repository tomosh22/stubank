package Classes;

public class User {
    public String username;
    //protected String password;
    public String firstName;
    public String secondName;
    public String email;

    public User(String username, String firstName, String secondName, String email){
        this.username = username;
        //this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
    }
}


