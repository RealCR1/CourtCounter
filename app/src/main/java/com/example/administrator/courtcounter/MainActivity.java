package com.example.administrator.courtcounter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.courtcounter.R;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    int scoreTeamA = 0;
    int scoreTeamB = 0;
    public EditText editTeamA;
    public EditText editTeamB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTeamA = (EditText) findViewById(R.id.edit_team_a);
        editTeamB = (EditText) findViewById(R.id.edit_team_b);
        String inputText = load();  //load() to read the data
        if (!TextUtils.isEmpty(inputText)) {    //if the data is not null,then set the data to th EditText.
            editTeamA.setText(inputText);
            editTeamA.setSelection(inputText.length());  //Move the typing signal to the end of the EditText.
            editTeamB.setText(inputText);
            editTeamB.setSelection(inputText.length());

            Toast.makeText(this, "Restoring succeeded", Toast.LENGTH_SHORT).show();  //Make a toast to remind the user.
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        String inputTextA = editTeamA.getText().toString();
        String inputTextB = editTeamB.getText().toString();
        save(inputTextA, inputTextB);

    }

    /*Try to save data of inputTextA and inputTextB*/

    public void save(String inputTextA, String inputTextB) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputTextA);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /*Try to load the data we saved
            That we can get them when we restart the App
             */
    public String load() {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }


    /**
     * Increase the score for Team A by 1 point.
     */
    public void addOneForTeamA(View v) {
        scoreTeamA = scoreTeamA + 1;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Increase the score for Team A by 2 points.
     */
    public void addTwoForTeamA(View v) {
        scoreTeamA = scoreTeamA + 2;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Increase the score for Team A by 3 points.
     */
    public void addThreeForTeamA(View v) {
        scoreTeamA = scoreTeamA + 3;
        displayForTeamA(scoreTeamA);
    }


    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }
    /**
     * In the real game,a foul happened,you will get a chance to free throw.
     * Increase the score for Team B by 1 points.
     */
    public void addOneForTeamB(View v) {
        scoreTeamB = scoreTeamB + 1;
        displayForTeamB(scoreTeamB);
    }


    /**
     * Increase the score for Team B by 2 points.
     */
    public void addTwoForTeamB(View v) {
        scoreTeamB = scoreTeamB + 2;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Increase the score for Team B by 3 points.
     */
    public void addThreeForTeamB(View v) {
        scoreTeamB = scoreTeamB + 3;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }
    /* Try to reset score.
    And make them to be zero at the same time.
     */
    public void resetForScore(View v) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        displayNullTextForTeam(editTeamA,editTeamB);

    }
    /*When we reset the APP
    we should get the null EditText and the null socre for team A and team B.

     */
    public void displayNullTextForTeam(EditText editTeamA ,EditText editTeamB){
        editTeamA = (EditText) findViewById(R.id.edit_team_a);
        editTeamB = (EditText) findViewById(R.id.edit_team_b);
        editTeamA.setText(null);
        editTeamB.setText(null);
    }


}


