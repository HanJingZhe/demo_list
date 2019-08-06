package com.qtgm.peng.t0805_save.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

/**
 * @author peng_wang
 * @date 2019/8/5
 */
public class SQlPersonHelp extends SQLiteOpenHelper {

    static String dbName = "person.db";
    static int dbVersion = 1;

    public SQlPersonHelp(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table user(" +
                "id integer primary key autoincrement," +
                "uname varchar(20)," +
                "passworld varchar(20)," +
                "age integer," +
                "sex varchar(2)" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
