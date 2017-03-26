package com.hashbird.learningvolley;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Show_timetable extends AppCompatActivity {
TextView textView;
    Button button;
    SqliteDatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_timetable);

        textView = (TextView)findViewById(R.id.textView);
        button = (Button)findViewById(R.id.button);
        mydb = new SqliteDatabaseHelper(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = mydb.gettt();
                if(res.getCount()==0){

                   textView.setText("no data found");
                }
                else
                {
                    StringBuffer buffer = new StringBuffer();
                    while(res.moveToNext()){
                        buffer.append("DAY- "+res.getString(1)+"\n");
                        buffer.append("HOUR 1 "+res.getString(2)+"\n");
                        buffer.append("HOUR 2 "+res.getString(3)+"\n");
                        buffer.append("HOUR 3 "+res.getString(4)+"\n");
                        buffer.append("HOUR 4 "+res.getString(5)+"\n");
                        buffer.append("HOUR 5 "+res.getString(6)+"\n");
                        buffer.append("HOUR 6 "+res.getString(7)+"\n");
                        buffer.append("HOUR 7 "+res.getString(8)+"\n\n");

                    }

                    textView.setText(buffer.toString());

                }


            }
        });


    }
}
