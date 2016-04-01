package xyz.iiemyewrs.www.technica.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by iiemyewrs on 20/1/16.
 */
public class SQLiteHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "android_database";

    // Notification table name
    private static final String TABLE_NOTIFICATION = "notification";
    private static final String TABLE_EVENTS = "events";
    private static final String TABLE_SPONSOR = "sponsors";

    private static final String KEY_TEXT = "text";
    private static final String KEY_DATE = "date";

    private static final String KEY_NAME_SPONSOR = "name";
    private static final String KEY_DESIG_SPONSOR = "design";
    private static final String KEY_URL_SPONSOR = "url";

    private static final String KEY_ID = "_ID";
    private static final String KEY_NAME = "event_name";
    private static final String KEY_DAY = "day";
    private static final String KEY_VENUE ="venue";
    private static final String KEY_TIME = "time";
    private static final String KEY_DISC = "disc";


    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE "+ TABLE_NOTIFICATION +"("
                + KEY_TEXT +" TEXT,"
                + KEY_DATE+ " TEXT)";
        db.execSQL(CREATE_TABLE);

        CREATE_TABLE = "CREATE TABLE "+ TABLE_SPONSOR +"("
                + KEY_NAME_SPONSOR +" TEXT,"
                + KEY_DESIG_SPONSOR+ " TEXT,"
                + KEY_URL_SPONSOR +" TEXT)";
        db.execSQL(CREATE_TABLE);

        CREATE_TABLE = "CREATE TABLE "+ TABLE_EVENTS +"("
                + KEY_ID +" INTEGER IDENTITY(1,1) PRIMARY KEY,"
                + KEY_NAME +" TEXT,"
                + KEY_DAY +" TEXT,"
                + KEY_VENUE+ " TEXT,"
                +KEY_TIME+" TEXT,"
                +KEY_DISC +" TEXT)";
        db.execSQL(CREATE_TABLE);
        Log.d("SQLiteHandler", "Database Created!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPONSOR);
        Log.d("SQLiteHandler", "Database dropped and is ready to be created again.");

        // Create tables again
        onCreate(db);
    }

    public void addNotificationsToDatabase(String[] text,String[] date){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(TABLE_NOTIFICATION, null, null);
        for (int i=0;i<text.length;i++){
            ContentValues values = new ContentValues();
            values.put(KEY_TEXT,text[i]);
            values.put(KEY_DATE,date[i]);
            db.insert(TABLE_NOTIFICATION,null,values);
        }

        db.close();
    }


    public void addSponsorsToDatabase(String[] name,String[] desig,String[] url){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(TABLE_SPONSOR, null, null);
        for (int i=0;i<name.length;i++){
            ContentValues values = new ContentValues();
            values.put(KEY_NAME_SPONSOR,name[i]);
            values.put(KEY_DESIG_SPONSOR,desig[i]);
            values.put(KEY_URL_SPONSOR,url[i]);
            db.insert(TABLE_SPONSOR,null,values);
        }

        db.close();
    }

    public void addEventsToDatabase(String[] id,String[] eventName,String[] eventDay,String[] eventVenue,String[] eventTime,String[] eventDisc){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(TABLE_EVENTS, null, null);
        for (int i=0;i<id.length;i++){
            ContentValues values = new ContentValues();
            values.put(KEY_NAME,eventName[i]);
            values.put(KEY_DAY,eventDay[i]);
            values.put(KEY_VENUE,eventVenue[i]);
            values.put(KEY_TIME,eventTime[i]);
            values.put(KEY_DISC,eventDisc[i]);
            db.insert(TABLE_EVENTS,null,values);
        }

        db.close();
    }

    public Object[] getNotificationDataFromDatabase(){
        String[] data,date;

        String selectQuery = "SELECT  * FROM " + TABLE_NOTIFICATION;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        data=new String[cursor.getCount()];
        date=new String[cursor.getCount()];
        int i=0;
        while(cursor.moveToNext()){
            data[i]=cursor.getString(0);
            date[i]=cursor.getString(1);
            i++;
        }
        cursor.close();
        db.close();
        return new Object[]{data,date};
    }

    public Object[] getSponsorDataFromDatabase(){
        String[] name,desig,url;

        String selectQuery = "SELECT  * FROM " + TABLE_SPONSOR;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        name=new String[cursor.getCount()];
        desig=new String[cursor.getCount()];
        url=new String[cursor.getCount()];
        int i=0;
        while(cursor.moveToNext()){
            name[i]=cursor.getString(0);
            desig[i]=cursor.getString(1);
            url[i]=cursor.getString(2);
            i++;
        }
        cursor.close();
        db.close();
        return new Object[]{name,desig,url};
    }

    public Object[] getEventsDataFromDatabase(){
        ArrayList<String> name,venue,time,disc;
        ArrayList<String> _name,_venue,_time,_disc;
        ArrayList<String> _name1,_venue1,_time1,_disc1;
        ArrayList<String> _name2,_venue2,_time2,_disc2;
        ArrayList<String> _name3,_venue3,_time3,_disc3;
        ArrayList<String> _name4,_venue4,_time4,_disc4;

        String selectQuery = "SELECT  * FROM " + TABLE_EVENTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        name=new ArrayList<String>();
        venue=new ArrayList<String>();
        time=new ArrayList<String>();
        disc=new ArrayList<String>();

        _name=new ArrayList<String>();
        _venue=new ArrayList<String>();
        _time=new ArrayList<String>();
        _disc=new ArrayList<String>();

        _name1=new ArrayList<String>();
        _venue1=new ArrayList<String>();
        _time1=new ArrayList<String>();
        _disc1=new ArrayList<String>();

        _name2=new ArrayList<String>();
        _venue2=new ArrayList<String>();
        _time2=new ArrayList<String>();
        _disc2=new ArrayList<String>();

        _name3=new ArrayList<String>();
        _venue3=new ArrayList<String>();
        _time3=new ArrayList<String>();
        _disc3=new ArrayList<String>();

        _name4=new ArrayList<String>();
        _venue4=new ArrayList<String>();
        _time4=new ArrayList<String>();
        _disc4=new ArrayList<String>();

        while(cursor.moveToNext()){
            if(cursor.getString(2).equals("1")){
                name.add(cursor.getString(1));
                venue.add(cursor.getString(3));
                time.add(cursor.getString(4));
                disc.add(cursor.getString(5));
            }else if(cursor.getString(2).equals("2")){
                _name.add(cursor.getString(1));
                _venue.add(cursor.getString(3));
                _time.add(cursor.getString(4));
                _disc.add(cursor.getString(5));
            }else if(cursor.getString(2).equals("3")){
                _name1.add(cursor.getString(1));
                _venue1.add(cursor.getString(3));
                _time1.add(cursor.getString(4));
                _disc1.add(cursor.getString(5));
            }else if(cursor.getString(2).equals("4")){
                _name2.add(cursor.getString(1));
                _venue2.add(cursor.getString(3));
                _time2.add(cursor.getString(4));
                _disc2.add(cursor.getString(5));
            }else if(cursor.getString(2).equals("5")){
                _name3.add(cursor.getString(1));
                _venue3.add(cursor.getString(3));
                _time3.add(cursor.getString(4));
                _disc3.add(cursor.getString(5));
            }else if(cursor.getString(2).equals("6")){
                _name4.add(cursor.getString(1));
                _venue4.add(cursor.getString(3));
                _time4.add(cursor.getString(4));
                _disc4.add(cursor.getString(5));
            }
        }
        cursor.close();
        db.close();
        return new Object[]{
                new Object[]{name.toArray(new String[name.size()]),venue.toArray(new String[name.size()]),time.toArray(new String[name.size()]),disc.toArray(new String[name.size()])},
                new Object[]{_name.toArray(new String[_name.size()]),_venue.toArray(new String[_name.size()]),_time.toArray(new String[_name.size()]),_disc.toArray(new String[_name.size()])},
                new Object[]{_name1.toArray(new String[_name1.size()]),_venue1.toArray(new String[_name1.size()]),_time1.toArray(new String[_name1.size()]),_disc1.toArray(new String[_name1.size()])},
                new Object[]{_name2.toArray(new String[_name2.size()]),_venue2.toArray(new String[_name2.size()]),_time2.toArray(new String[_name2.size()]),_disc2.toArray(new String[_name2.size()])},
                new Object[]{_name3.toArray(new String[_name3.size()]),_venue3.toArray(new String[_name3.size()]),_time3.toArray(new String[_name3.size()]),_disc3.toArray(new String[_name3.size()])},
                new Object[]{_name4.toArray(new String[_name4.size()]),_venue4.toArray(new String[_name4.size()]),_time4.toArray(new String[_name4.size()]),_disc4.toArray(new String[_name4.size()])}
        };
    }
}
