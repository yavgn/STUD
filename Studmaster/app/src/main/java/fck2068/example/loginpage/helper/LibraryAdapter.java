package fck2068.example.loginpage.helper;

import android.content.Context;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import fck2068.example.loginpage.R;

public class LibraryAdapter extends BaseAdapter implements Filterable{

    Context c;
    ArrayList<SingleRow> originalArray, tempArray;
    CustomFilter cs;

    public LibraryAdapter(Context c, ArrayList<SingleRow> originalArray){
        this.c = c;
        this.originalArray = originalArray;
        this.tempArray = originalArray;
    }

    @Override
    public int getCount() {
        return originalArray.size();
    }

    @Override
    public Object getItem(int position) {
        return originalArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.library_row, null);

        TextView titleTV = (TextView) row.findViewById(R.id.bookTitle);
        ImageView imageIV = (ImageView) row.findViewById(R.id.bookImage);
        TextView descTV = (TextView) row.findViewById(R.id.bookDescription);

        titleTV.setText(originalArray.get(position).getTitle());
        imageIV.setImageResource(originalArray.get(position).getImage());
        descTV.setText(originalArray.get(position).getDescription());

        return row;
    }

    @Override
    public Filter getFilter() {

        if(cs == null){
            cs = new CustomFilter();
        }

        return cs;
    }
    //create a new class - custom filter
    class CustomFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();

            if(constraint!=null && constraint.length()>0) {
                constraint = constraint.toString().toUpperCase();
                ArrayList<SingleRow> filters = new ArrayList<>();

                //use a for loop to go to all the indexes to check if the constraint is present or not
                //if constraint matches it will set the results to equal the match
                for (int i = 0; i < tempArray.size(); i++) {
                    if (tempArray.get(i).getTitle().toUpperCase().contains(constraint)) {
                        SingleRow singleRow = new SingleRow(tempArray.get(i).getTitle(), tempArray.get(i).getImage(), tempArray.get(i).getDescription());
                        filters.add(singleRow);
                    }
                }

                filterResults.count = filters.size();
                filterResults.values = filters;
                //else if the results don't match it will just list the array
            }else{
                filterResults.count = tempArray.size();
                filterResults.values = tempArray;
            }

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            originalArray = (ArrayList<SingleRow>) results.values;
            notifyDataSetChanged();
        }
    }
}
