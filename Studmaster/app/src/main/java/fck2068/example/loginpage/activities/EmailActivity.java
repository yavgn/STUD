package fck2068.example.loginpage.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fck2068.example.loginpage.R;
import fck2068.example.loginpage.model.SendMail;

public class EmailActivity extends AppCompatActivity {

    EditText email, subject, message;
    String emailString, subjectString, messageString;
    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        context = this;

        Button sendButton  = (Button) findViewById(R.id.sendButton);
        email = (EditText) findViewById(R.id.recepientEmail);
        subject = (EditText) findViewById(R.id.subjectText);
        message = (EditText) findViewById(R.id.emailBody);

        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                emailString = email.getText().toString();
                subjectString = subject.getText().toString();
                messageString = message.getText().toString();

                SendMail sm = new SendMail(context, emailString, subjectString, messageString);
                sm.execute();

            }
        });
    }
}
