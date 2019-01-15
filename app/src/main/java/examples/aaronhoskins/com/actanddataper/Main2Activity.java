package examples.aaronhoskins.com.actanddataper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView tvDisplay;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        sharedPreferences = getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        String savedFromPrefString = sharedPreferences.getString("key", "NO VALUE HAS BEEN SAVED");

        tvDisplay = findViewById(R.id.tvDisplay2);
        tvDisplay.setText(savedFromPrefString);



    }

    public void onClick(View view) {
        String passToMain = "I was in activity 2";
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("value", passToMain);
        setResult(666, intent);
        finish();
    }
}
