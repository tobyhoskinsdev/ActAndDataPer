package examples.aaronhoskins.com.actanddataper.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class StringUtil {
    private static StringUtil stringUtil;
    SharedPreferences sharedPreferences;


    public static synchronized StringUtil getInstance(Context context){
        if(stringUtil == null) {
            stringUtil = new StringUtil(context);
        }
        return stringUtil;
    }

    private StringUtil(Context context) {
        sharedPreferences = context.getSharedPreferences("key", Context.MODE_PRIVATE);
    }

    public String getFirstName() {
        return sharedPreferences.getString("key", "");
    }
}
