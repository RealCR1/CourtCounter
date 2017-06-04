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
import java.io.Writer;

public class MainActivity extends AppCompatActivity {
    public int scoreTeamA = 0;
    public int scoreTeamB = 0;
    public EditText editTeamA;
    public EditText editTeamB;
    public TextView teamA;
    public TextView teamB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTeamA = (EditText) findViewById(R.id.edit_team_a);
        editTeamB = (EditText) findViewById(R.id.edit_team_b);
        teamA = (TextView) findViewById(R.id.team_a_score);
        teamB = (TextView) findViewById(R.id.team_b_score);
        String inputTextA = loadTeamAName();  //load() method is to read the data of team A's name.
        if (!TextUtils.isEmpty(inputTextA)) {    //if the data is not null,then set the data to th EditText.
            editTeamA.setText(inputTextA);
            editTeamA.setSelection(inputTextA.length());  //Move the typing signal to the end of the EditText.
            Toast.makeText(this, "Restoring Name of Team A succeeded", Toast.LENGTH_SHORT).show();  //Make a toast to remind the user.
        }

        String inputTextB = loadTeamBName();  //load() method is to read the data of team B'S name.
        if (!TextUtils.isEmpty(inputTextB)) {
            editTeamB.setText(inputTextB);
            editTeamB.setSelection(inputTextB.length());
            Toast.makeText(this, "Restoring Name of Team B succeeded", Toast.LENGTH_SHORT).show();  //Make a toast to remind the user.
        }


        // Try to load the score data of team a and team b.

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        String inputTextA = editTeamA.getText().toString();
        String inputTextB = editTeamB.getText().toString();
        String scoreSavedTeamA = teamA.getText().toString();
        String scoreSavedTeamB = teamB.getText().toString();


    }

    /*Try to save data of inputTextA and inputTextB*/

    public void saveTeamName(String inputTextA, String inputTextB) {
        FileOutputStream outAName = null;
        BufferedWriter writerAName = null;
        FileOutputStream outBName = null;
        BufferedWriter writerBName = null;
        try {
            outAName = openFileOutput("dataOfAName", Context.MODE_PRIVATE);
            writerAName = new BufferedWriter(new OutputStreamWriter(outAName));
            writerAName.write(inputTextA);
            outBName = openFileOutput("dataOfBName", Context.MODE_PRIVATE);
            writerBName = new BufferedWriter(new OutputStreamWriter(outBName));
            writerBName.write(inputTextB);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writerAName != null && writerBName != null) {
                    writerAName.close();
                    writerBName.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



    /*Try to load the data we saved
            That we can get them when we restart the App
             */
    public String loadTeamAName() {
        FileInputStream inAName = null;
        BufferedReader readerAName = null;
        StringBuilder contentAName = new StringBuilder();

        try {
            inAName = openFileInput("dataOfAName");
            readerAName = new BufferedReader(new InputStreamReader(inAName));
            String line = "";
            while ((line = readerAName.readLine()) != null) {
                contentAName.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (readerAName != null) {
                try {
                    readerAName.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return contentAName.toString();
    }

    //*Try to load the name of team b.

    public String loadTeamBName() {
        FileInputStream inBName = null;
        BufferedReader readerBName = null;
        StringBuilder contentBName = new StringBuilder();
        try {
            inBName = openFileInput("dataOfBName");
            readerBName = new BufferedReader(new InputStreamReader(inBName));
            String line = "";
            while ((line = readerBName.readLine()) != null) {
                contentBName.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (readerBName != null) {
                try {
                    readerBName.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return contentBName.toString();
    }
/*Try to save the score of two teams.
It is TextView
 */
    public void saveTeamScore(String scoreSavedTeamA, String scoreSavedTeamB) {
        FileOutputStream outAScore = null;
        BufferedWriter writerAScore = null;
        FileOutputStream outBScore = null;
        BufferedWriter writerBScore = null;

        try {
            outAScore = openFileOutput("dataOfAScore", Context.MODE_PRIVATE);
            writerAScore = new BufferedWriter(new OutputStreamWriter(outAScore));
            writerAScore.write(scoreSavedTeamA);
            outBScore = openFileOutput("dataOfBScore", Context.MODE_PRIVATE);
            writerBScore = new BufferedWriter(new OutputStreamWriter(outBScore));
            writerBScore.write(scoreSavedTeamB);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writerAScore != null && writerBScore != null) {
                    writerAScore.close();
                    writerBScore.close();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
        displayNullTextForTeam(editTeamA, editTeamB);

    }

    /*When we reset the APP
    we should get the null EditText and the null socre for team A and team B.

     */
    public void displayNullTextForTeam(EditText editTeamA, EditText editTeamB) {
        editTeamA = (EditText) findViewById(R.id.edit_team_a);
        editTeamB = (EditText) findViewById(R.id.edit_team_b);
        editTeamA.setText(null);
        editTeamB.setText(null);
    }


}


