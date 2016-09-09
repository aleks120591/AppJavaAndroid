package com.bignerdranch.android.appjavaandroid.conect;

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;

import java.net.HttpURLConnection;

public class FoneService extends Service{

    String SERVER_NAME="http://aleksandrkrasilov.xyz";

    SQLiteDatabase mSQLiteDatabase;
    HttpURLConnection mHttpURLConnection;
    Cursor mCursor;
    Thread mThread;
    ContentValues mContentValues;
    Long mLong;

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    public void onStart(Intent intent, int startId){
        Log.i("chat", "+ FoneService - запуск сервиса");
        mSQLiteDatabase=openOrCreateDatabase("mSQLiteDatabase.db", Context.MODE_PRIVATE,null);
        mSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS chat (_id integer primary key autoincrement, author, client, data, text)");

        startLoop();
    }

    private void startLoop() {
        mThread=new Thread(new Runnable() {

            String ansver;
            String lnk;

            @Override
            public void run() {
                while (true){
                    mCursor=mSQLiteDatabase.rawQuery("SELECT * FROM chat ORDER BY data", null);
                    if (mCursor.moveToLast()){

                    }else{

                    }
                    mCursor.close();
                }
            }
        });
    }
}
