package com.example.lui.sdpapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class TermsConditions extends FragmentActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_conditions);

        final Button mButton = (Button) findViewById(R.id.submitButton);
        mButton.setEnabled(false);
        CheckBox mCheckBox = (CheckBox)findViewById(R.id.checkBox);
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    mButton.setEnabled(true);
                }else{
                    mButton.setEnabled(false);
                }
            }
        });

        Details detailFragment = new Details();
        getSupportFragmentManager().beginTransaction().add(R.id.container,detailFragment).commit();


        TextView textView =(TextView)findViewById(R.id.termsView);
        textView.setClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='http://www.google.com'> Terms and Conditions </a>";
        textView.setText(Html.fromHtml(text));
    }
}
