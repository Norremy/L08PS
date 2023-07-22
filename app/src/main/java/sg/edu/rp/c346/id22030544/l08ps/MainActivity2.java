package sg.edu.rp.c346.id22030544.l08ps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;


import java.util.ArrayList;


public class MainActivity2 extends AppCompatActivity {
ListView lv;
Button btn5Stars, btnRetrieve;
private int myIntVariable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        lv = findViewById(R.id.ListView);
        btn5Stars = findViewById(R.id.btn5Star);
        btnRetrieve = findViewById(R.id.buttonRetrive);
        myIntVariable = 0;

        DBHelper db = new DBHelper(MainActivity2.this);
        ArrayList<Song> songs = db.getSongs();
        db.close();

        CustomAdapter adapter = new CustomAdapter(this,
                R.layout.row, songs);
        lv.setAdapter(adapter);
        registerForContextMenu(lv);


        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity2.this);
                adapter.clear();

                adapter.addAll(dbh.getSongs());


                adapter.notifyDataSetChanged();
                myIntVariable =0;


                lv.setAdapter(adapter);
                registerForContextMenu(lv);

                }});

        btn5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity2.this);
                ArrayList<Song> songs = db.getSongsfiltered();
                db.close();
                CustomAdapter adapter = new CustomAdapter(MainActivity2.this,
                        R.layout.row, songs);
                lv.setAdapter(adapter);
                registerForContextMenu(lv);
                myIntVariable =1;

            }
        });

    }
    // Implement onCreateOptionsMenu()
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,0,0,"Insert Song");
        return super.onCreateOptionsMenu(menu);
    }

    // Implement onOptionsItemSelected()
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemID = item.getItemId();
        if (itemID == 0) {
            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    // Implement onCreateContextMenu()
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,0,0,"Edit");
        menu.add(0,1,1,"Delete");
    }

    // Implement onContextItemSelected()
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemID = item.getItemId();

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        int a = myIntVariable;
        Log.i("OnClick", "position = " + position);
        Log.i("OnClick", "position = " + a);

        if (itemID == 0) {
            if(myIntVariable == 0){
            DBHelper dbh = new DBHelper(MainActivity2.this);

            ArrayList<Song> songs = dbh.getSongs();
            Song target = songs.get(position);


            Intent i = new Intent(MainActivity2.this,
                    MainActivityEdit.class);
            i.putExtra("data", target);
            startActivity(i);}
            else if (myIntVariable ==1) {
                DBHelper dbh = new DBHelper(MainActivity2.this);

                ArrayList<Song> songs = dbh.getSongsfiltered();
                Song target = songs.get(position);

                Intent i = new Intent(MainActivity2.this,
                        MainActivityEdit.class);
                i.putExtra("data", target);
                startActivity(i);

            }




        } else if (itemID == 1) {

            if(myIntVariable == 0){
            DBHelper dbh = new DBHelper(MainActivity2.this);

            ArrayList<Song> songs = dbh.getSongs();
            int id = songs.get(position).getId();
            dbh.deleteSong(id);
            btnRetrieve.performClick();}
        else{DBHelper dbh = new DBHelper(MainActivity2.this);

            ArrayList<Song> songs = dbh.getSongsfiltered();
            Song target = songs.get(position);
            int id = target.getId();
            dbh.deleteSong(id);
            btnRetrieve.performClick();}

        }
        return super.onContextItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        btnRetrieve.performClick();



    }

}


