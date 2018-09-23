package fck2068.example.loginpage.activities;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public Session(Context ctx){
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences("stud",Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setLoggedIn(boolean loggedIn){
        editor.putBoolean("loggedIn", loggedIn);
        editor.commit();
    }

    public boolean statusLoggedIn(){
        return prefs.getBoolean("loggedIn",false);
    }
}
