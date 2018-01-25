package com.example.databaseaccess;

import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickFind(View view) throws RemoteException {
        EditText editText = (EditText) findViewById(R.id.search_text);
        TextView idText = (TextView) findViewById(R.id.id_text);
        TextView gradeText = (TextView) findViewById(R.id.grade_text);
        ContentClient CC = new ContentClient(this);

        Grades grades = CC.findGrades(editText.getText().toString());

        if (grades != null) {
            idText.setText(String.valueOf(grades.getID()));
            gradeText.setText(String.valueOf(grades.getGrades()));
        } else {
            idText.setText("No Match Found");
        }
    }
}
