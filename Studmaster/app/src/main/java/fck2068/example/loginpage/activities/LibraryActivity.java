package fck2068.example.loginpage.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import fck2068.example.loginpage.R;
import fck2068.example.loginpage.helper.LibraryAdapter;
import fck2068.example.loginpage.helper.SingleRow;

public class LibraryActivity extends AppCompatActivity implements TextWatcher{

    EditText searchEditText;
    ListView bookList;

    String[] title = {"Human Resources Management", "Java: The Good Parts", "PHP: For Absolute Beginners", "Project Management: Twelfth Edition"};
    int[] images = {R.drawable.hr_book, R.drawable.java_book, R.drawable.php_book, R.drawable.pm_book};
    String[] descriptions = {"A guide to managing hoomans", "Only the essentials, no demoing", "Recommended as a base for little to zero experience", "12th edition, keep em coming"};
    ArrayList<SingleRow> books;
    LibraryAdapter libraryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_activity);

        searchEditText = (EditText) findViewById(R.id.searchEditText);
        bookList = (ListView) findViewById(R.id.bookList);
        searchEditText.addTextChangedListener(this);
        books = new ArrayList<>();
        SingleRow singleRow;

        //loop to initialize ArrayList books
        for(int i = 0;i < title.length; i++){
            singleRow = new SingleRow(title[i], images[i], descriptions[i]);
            books.add(singleRow);
        }
        //send ArrayList to LibraryAdapter class
        libraryAdapter = new LibraryAdapter(this, books);
        //set ListView bookList to use LibraryAdapter
        bookList.setAdapter(libraryAdapter);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        this.libraryAdapter.getFilter().filter(s);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
