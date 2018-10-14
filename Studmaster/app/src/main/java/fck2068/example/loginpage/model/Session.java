package fck2068.example.loginpage.model;

import android.content.Context;
import android.content.SharedPreferences;

//Session uses shared preferences to stay from logging out
public class Session {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;
    //session takes in context
    public Session(Context ctx){
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences("stud",Context.MODE_PRIVATE);
        editor = prefs.edit();
    }
    //set state to logged in
    public void setLoggedIn(boolean loggedIn){
        editor.putBoolean("loggedIn", loggedIn);
        editor.commit();
    }
    
    public boolean statusLoggedIn(){
        return prefs.getBoolean("loggedIn",false);
    }
}
