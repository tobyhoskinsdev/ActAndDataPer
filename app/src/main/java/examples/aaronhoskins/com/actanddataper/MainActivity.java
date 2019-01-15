package examples.aaronhoskins.com.actanddataper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import examples.aaronhoskins.com.actanddataper.utils.StringUtil;

public class MainActivity extends AppCompatActivity {
    private static final int READ_BLOCK_SIZE = 100;
    TextView tvDisplay;

    public static final String TAG = "tag_act_one";
    public static final int RESULT_CODE = 666;
    SharedPreferences sharedPreferences;
    double d = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key", "sharedPrefValue");
        editor.commit();

        tvDisplay = findViewById(R.id.tvDisplay);
        if(savedInstanceState != null) {
            d = savedInstanceState.getDouble("d");
        }
        Log.d(TAG, "CREATE");

        MySqlDatabaseHelper mySqlDatabaseHelper = new MySqlDatabaseHelper(this);
        Person person = new Person("Crazy Eyes", "Unknown", 500);
        mySqlDatabaseHelper.insertPerson(person);
        Person crazyEyes = mySqlDatabaseHelper.getPerson("Crazy Eyes");
        Log.d(TAG, "onCreate: " + crazyEyes.getName());
        crazyEyes.setGender("Male");
        mySqlDatabaseHelper.updatePerson(crazyEyes);
        crazyEyes = mySqlDatabaseHelper.getPerson("Crazy Eyes");
        Log.d(TAG, "onCreate: " + crazyEyes.getGender());

        WriteBtn();
        readBtn();
    }

    // write text to file
    public void WriteBtn() {
        // add-write text into file
        try {
            FileOutputStream fileout=openFileOutput("mytextfile.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write("Some info");
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read text from file
    public void readBtn() {
        //reading text from file
        try {
            FileInputStream fileIn=openFileInput("mytextfile.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();

            Toast.makeText(getBaseContext(), s,Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivityForResult(intent,RESULT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null) {
            Intent passedIntent = data;
            String passedString = passedIntent.getStringExtra("value");
            tvDisplay.setText(passedString);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putDouble("d", d);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.equals(Configuration.ORIENTATION_LANDSCAPE)){
            d = 2.0;
        } else {

        }
    }


}
