package sg.edu.rp.c346.id22030544.l08ps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
EditText etTitle, etSinger, etYear;
Button btnInsert, btnShow;
RadioButton rb1, rb2, rb3, rb4, rb5;
RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etSinger = findViewById(R.id.editTextSinger);
        etTitle = findViewById(R.id.editTextTitle);
        etYear = findViewById(R.id.editTextYear);
        btnInsert = findViewById(R.id.buttonInsert);
        btnShow = findViewById(R.id.buttonShow);
        rg = findViewById(R.id.RadioGroup);



        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
//                Log.i("MyActivity", year );

                DBHelper db = new DBHelper(MainActivity.this);


                String title = etTitle.getText().toString();
                String singer = etSinger.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());

                if(rg.getCheckedRadioButtonId()==R.id.radioButton1){
                    int stars=1;
                    db.insertSong(title, singer, year, stars);
                    Log.i("MyActivity", "year is" + stars );

                } else if (rg.getCheckedRadioButtonId()==R.id.radioButton2) {
                    int stars=2;
                    db.insertSong(title, singer, year, stars);
                }
                else if (rg.getCheckedRadioButtonId()==R.id.radioButton3) {
                    int stars=3;
                    db.insertSong(title, singer, year, stars);
                }
                else if (rg.getCheckedRadioButtonId()==R.id.radioButton4) {
                    int stars=4;
                    db.insertSong(title, singer, year, stars);
                }
                else if (rg.getCheckedRadioButtonId()==R.id.radioButton5) {
                    int stars=5;
                    db.insertSong(title, singer, year, stars);
                }
//                 Insert a task
//                db.insertTask("Submit RJ", "25 Apr 2021");
                db.close();
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);


            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}