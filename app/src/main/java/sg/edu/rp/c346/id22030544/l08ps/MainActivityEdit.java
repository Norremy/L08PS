package sg.edu.rp.c346.id22030544.l08ps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivityEdit extends AppCompatActivity {
    EditText etTitle, etSinger, etYear;
    Button btnUpdate, btnCancel;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    RadioGroup rg;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_edit);
        etSinger = findViewById(R.id.editTextSinger);
        etTitle = findViewById(R.id.editTextTitle);
        etYear = findViewById(R.id.editTextYear);
        btnUpdate = findViewById(R.id.buttonInsert);
        btnCancel = findViewById(R.id.buttonCancel);
        rg = findViewById(R.id.RadioGroup);
        Intent i = getIntent();
//        int receivedValue = i.getIntExtra("pos", 1);
        data = (Song) i.getSerializableExtra("data");

        DBHelper db = new DBHelper(MainActivityEdit.this);
        ArrayList<Song> songs = db.getSongs();
        db.close();

        etTitle.setText(data.getTitle());
        etSinger.setText(data.getSinger());
        etYear.setText(Integer.toString(data.getYear()));
//        etTitle.setText(songs.get(receivedValue).getTitle());
//        etSinger.setText(songs.get(receivedValue).getSinger());
//        etYear.setText(Integer.toString(songs.get(receivedValue).getYear()));

        int rating = (data.getStars());
        switch (rating) {
            case 1:
                rg.check(R.id.radioButton1);
                break;
            case 2:
                rg.check(R.id.radioButton2);
                break;
            case 3:
                rg.check(R.id.radioButton3);
                break;
            case 4:
                rg.check(R.id.radioButton4);
                break;
            case 5:
                rg.check(R.id.radioButton5);
                break;
            default:
                break;
        }




        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivityEdit.this);
                data.setTitle(etTitle.getText().toString());
                data.setSinger(etSinger.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));
                if(rg.getCheckedRadioButtonId()==R.id.radioButton1){
                    int stars=1;
                    data.setStars(stars);

                } else if (rg.getCheckedRadioButtonId()==R.id.radioButton2) {
                    int stars=2;
                    data.setStars(stars);
                }
                else if (rg.getCheckedRadioButtonId()==R.id.radioButton3) {
                    int stars=3;
                    data.setStars(stars);
                }
                else if (rg.getCheckedRadioButtonId()==R.id.radioButton4) {
                    int stars=4;
                    data.setStars(stars);
                }
                else if (rg.getCheckedRadioButtonId()==R.id.radioButton5) {
                    int stars=5;
                    data.setStars(stars);
                }
                dbh.updateSong(data);
                dbh.close();
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }

}