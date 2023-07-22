package sg.edu.rp.c346.id22030544.l08ps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Song> versionList;
    public CustomAdapter(Context context, int resource,
                         ArrayList<Song> objects){
        super(context, resource, objects);
        this.parent_context = context;
        this.layout_id = resource;
        this.versionList = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvName = rowView.findViewById(R.id.textViewItem);
        TextView tvVersion = rowView.findViewById(R.id.textViewDate);
        TextView tv2 = rowView.findViewById(R.id.textViewDate2);
        // Obtain the Android Version information based on the position
        Song currentVersion = versionList.get(position);
        // Set values to the TextView to display the corresponding information
        tvName.setText(currentVersion.getTitle());
        tvVersion.setText(currentVersion.toString());
        tv2.setText(currentVersion.getSinger());

        return rowView;
    }

}
