package examples.aaronhoskins.com.actanddataper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import static examples.aaronhoskins.com.actanddataper.DatabaseConstants.DATABASE_NAME;
import static examples.aaronhoskins.com.actanddataper.DatabaseConstants.DATABASE_VERSION;
import static examples.aaronhoskins.com.actanddataper.DatabaseConstants.FIELD_AGE;
import static examples.aaronhoskins.com.actanddataper.DatabaseConstants.FIELD_GENDER;
import static examples.aaronhoskins.com.actanddataper.DatabaseConstants.FIELD_NAME;
import static examples.aaronhoskins.com.actanddataper.DatabaseConstants.TABLE_NAME;

public class MySqlDatabaseHelper extends SQLiteOpenHelper {
    public MySqlDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + TABLE_NAME + "("
                + FIELD_NAME + " TEXT PRIMARY KEY, "
                + FIELD_GENDER + " TEXT, "
                + FIELD_AGE + " TEXT)";
        db.rawQuery(createQuery,null);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void insertPerson(Person person) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        if(person != null) {
            String name = person.getName();
            String gender = person.getGender();
            String age = String.valueOf(person.getAge());

            contentValues.put(FIELD_NAME, name);
            contentValues.put(FIELD_GENDER, gender);
            contentValues.put(FIELD_AGE, age);

            database.insert(TABLE_NAME, null, contentValues);
        }
    }
    public ArrayList<Person> getAllPeople() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            ArrayList<Person> arrayList = new ArrayList<>();
            do {
                String name = cursor.getString(cursor.getColumnIndex(FIELD_NAME));
                String gender = cursor.getString(cursor.getColumnIndex(FIELD_GENDER));
                int age = Integer.parseInt(cursor.getString(cursor.getColumnIndex(FIELD_AGE)));
                arrayList.add(new Person(name, gender, age));
            } while(cursor.moveToNext());
            return arrayList;
        } else {
            return null;
        }
    }

}
