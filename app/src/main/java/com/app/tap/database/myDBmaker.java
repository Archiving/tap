package com.app.tap.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class myDBmaker {

    myDbHelper myhelper;
    public myDBmaker(Context context){

        myhelper = new myDbHelper(context);
    }

    public long insertData(String f_name, String l_name, String email,
                           Integer phone_n, String gender, String race,
                           String age, String acc_needs, String diet_res,
                           String extra_n){

        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.F_NAME, f_name);
        contentValues.put(myDbHelper.L_NAME, l_name);
        contentValues.put(myDbHelper.EMAIL, email);
        contentValues.put(myDbHelper.PHONE_N, phone_n);
        contentValues.put(myDbHelper.GENDER, gender);
        contentValues.put(myDbHelper.RACE, race);
        contentValues.put(myDbHelper.AGE, age);
        contentValues.put(myDbHelper.ACCESSI, acc_needs);
        contentValues.put(myDbHelper.DIET, diet_res);
        contentValues.put(myDbHelper.EXTRA_NOTES, extra_n);

        long id = dbb.insert(myDbHelper.TABLE_NAME, null, contentValues);
        return id;

    }

    public String getData(){

        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.F_NAME,myDbHelper.L_NAME,
                            myDbHelper.EMAIL, myDbHelper.PHONE_N,myDbHelper.GENDER,myDbHelper.RACE,
                            myDbHelper.AGE,myDbHelper.ACCESSI,myDbHelper.DIET,myDbHelper.EXTRA_NOTES};

        Cursor cursor = db.query(myDbHelper.TABLE_NAME,columns,null,null
                                ,null   ,null,null);
        StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext()){

            int civ = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String f_name = cursor.getString(cursor.getColumnIndex(myDbHelper.F_NAME));
            String l_name = cursor.getString(cursor.getColumnIndex(myDbHelper.L_NAME));
            String email = cursor.getString(cursor.getColumnIndex(myDbHelper.EMAIL));
            int phone_N= cursor.getInt(cursor.getColumnIndex(myDbHelper.PHONE_N));
            String gender = cursor.getString(cursor.getColumnIndex(myDbHelper.GENDER));
            String race = cursor.getString(cursor.getColumnIndex(myDbHelper.RACE));
            String age = cursor.getString(cursor.getColumnIndex(myDbHelper.AGE));
            String acc = cursor.getString(cursor.getColumnIndex(myDbHelper.ACCESSI));
            String diet_r= cursor.getString(cursor.getColumnIndex(myDbHelper.DIET));
            String xtra_n = cursor.getString(cursor.getColumnIndex(myDbHelper.EXTRA_NOTES));

            buffer.append(civ + "  " + f_name + "," + l_name + "  " +
                            email + "  " + phone_N + "  " + gender + "  " + race+ "  " +
                                age + "  " +  acc + "  " + diet_r+ "  " + xtra_n + " \n" );
        }
        return buffer.toString();

    }

    static class myDbHelper extends SQLiteOpenHelper{

        private static final String DATABASE_NAME = "Tapp";
        private static final String TABLE_NAME = "myTable";
        private static final int DATABASE_Version = 1;
        private static final String UID="_id";
        private static final String F_NAME = "F_Name";
        private static final String L_NAME = "L_Name";
        private static final String EMAIL = "EMail";
        private static final String PHONE_N = "Phone Number";
        private static final String GENDER = "Gender";
        private static final String RACE = "Race";
        private static final String AGE = "Age";
        private static final String ACCESSI = "Acc_Needs";
        private static final String DIET = "Diet Rest";
        private static final String EXTRA_NOTES = "Extras";


        private static final String CT = "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + F_NAME +
        " VARCHAR(255), " + L_NAME + " VARCHAR(255), " + EMAIL + " VARCHAR(255), " + PHONE_N + " VARCHAR(10), "
        + GENDER + " VARCHAR(50), " + RACE + " VARCHAR(100), " + AGE + " VARCHAR(255), " + ACCESSI + " VARCHAR(255), "
        + DIET + " VARCHAR(255), " + EXTRA_NOTES + " VARCHAR(255);";


        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;

        public myDbHelper(Context context){

            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        public void onCreate(SQLiteDatabase db){
            try{
                db.execSQL(CT);
            }catch (Exception e){
                Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            try{
                Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch(Exception e){
                Message.message(context,""+e);
            }
        }








    }




}
