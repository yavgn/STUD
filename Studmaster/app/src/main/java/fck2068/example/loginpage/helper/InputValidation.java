package fck2068.example.loginpage.helper;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/*
    CLASS to validate input from EditTexts
 */
public class InputValidation {
    //TextInputEditText and TextInputLayout, can only be used because "implementation 'com.android.support:design:28.0.0-rc02'" was added to the Gradle Scripts|builld.gradle(Modeule: app) in dependencies
    private Context context;
    //default constructor, takes in a context
    public InputValidation(Context context){
        this.context = context;
    }
    //method to check if EditText is empty, returns true if editText is filled
    public boolean isInputEditTextFilled(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message){
        String value = textInputEditText.getText().toString().trim();
        if(value.isEmpty()){
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        } else{
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }
    //method to check email format, returns true if email field is not empty and correct format
    public boolean isInputEditTextEmail(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message){
        String value = textInputEditText.getText().toString().trim();
        if(value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            textInputEditText.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        }else{
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }
    //method to if two fields (EditTexts) are the same, returns true if input1 matches input2
    public boolean isInputEditTextMatches(TextInputEditText textInputEditText1, TextInputEditText textInputEditText2, TextInputLayout textInputLayout, String message){
        String value1 = textInputEditText1.getText().toString().trim();
        String value2 = textInputEditText2.getText().toString().trim();
        if(!value1.contentEquals(value2)){
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText2);
            return false;
        }else{
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }
    /*Hides the keyboard when displaying errors and takes in a View object
        method to detect if there is an empty field or if the email is not well structured or if some text matches*/
    private void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
