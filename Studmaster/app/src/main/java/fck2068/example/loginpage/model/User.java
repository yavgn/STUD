package fck2068.example.loginpage.model;
/*
    CLASS to create a USER with get and set methods to be used to enter a USER into the DB from the DatabaseHelper CLASS
 */
public class User {
    private int id;
    private String username;
    private String email;
    private String password;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getUserName(){
        return username;
    }
    public void setUserName(String username){
        this.username = username;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
}
