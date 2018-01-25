package com.example.databaseaccess;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by jerald on 1/24/18.
 */

public class ContentClient extends AppCompatActivity{
    public Context thisContext;
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SUBJECTNAME = "subjectname";
    public static final String COLUMN_GRADE  = "marks";

    public ContentClient(Context context){
        thisContext = context;
    }

    public Grades findGrades(String subjectname) throws RemoteException {
        String URL = "content://com.example.database.MyContentProvider/grades";
        Uri uri = Uri.parse(URL);

        if(thisContext != null) {
            ContentResolver cr = thisContext.getContentResolver(); //This line is new
            String[] projection = {COLUMN_ID, COLUMN_SUBJECTNAME, COLUMN_GRADE};
            String selection = "subjectname = \"" + subjectname+ "\"";
            Cursor cursor = cr.query(uri, projection, selection, null, null);

            Grades grades = new Grades();

            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                grades.setID(Integer.parseInt(cursor.getString(0)));
                grades.setSubjectName(cursor.getString(1));
                grades.setGrades(Integer.parseInt(cursor.getString(2)));
                cursor.close();
            } else {
                grades = null;
            }
            return grades;
        }
        else{
            return null;
        }
    }
}
