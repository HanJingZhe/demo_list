package com.qtgm.peng.t0805_save.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.qtgm.peng.t0805_save.bean.UserBean;

/**
 * @author peng_wang
 * @date 2019/8/5
 */
public class UserService {

    private SQlPersonHelp dbHelper;

    public UserService(Context context) {
        dbHelper = new SQlPersonHelp(context);
    }

    public boolean login(String uname, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select * from user where username=? and password=?";
        Cursor cursor = db.rawQuery(sql, new String[]{uname, password});
        if (cursor.moveToNext()) {
            cursor.close();
            return true;
        }
        return false;
    }

    public boolean register(UserBean userBean) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "insert into user(username,password,age,sex) values(?,?,?,?)";
        db.execSQL(sql, new Object[]{userBean.getUsername(), userBean.getPassword(), userBean.getAge(), userBean.getSex()});
        return true;
    }

}
