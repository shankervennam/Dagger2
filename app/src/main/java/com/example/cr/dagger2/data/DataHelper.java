package com.example.cr.dagger2.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;

import com.example.cr.dagger2.model.User;

import javax.inject.Inject;

public class DataHelper extends SQLiteOpenHelper
{
    public static final String USER_TABLE_NAME = "users";
    public static final String USER_COLUMN_USER_ID = "id";
    public static final String USER_COLUMN_USER_NAME = "user_name";
    public static final String USER_COLUMN_USER_ADDRESS = "user_address";
    public static final String USER_COLUMN_USER_CREATED_AT = "created_at";
    public static final String USER_COLUMN_USER_UPDATED_AT = "updated_at";

    @Inject
    public DataHelper(Context context, String name, int version)
    {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        tableCreateStatements(db);
    }

    private void tableCreateStatements(SQLiteDatabase db)
    {
        try
        {
            db.execSQL("CREATE IF NOT EXISTS" + USER_TABLE_NAME + "("
                                                + USER_COLUMN_USER_ID + "INTEGER PRIMARY KEY AUTO INCREMENT,"
                                                + USER_COLUMN_USER_NAME + "VARCHAR(20),"
                                                + USER_COLUMN_USER_ADDRESS + "VARCHAR(50),"
                                                + USER_COLUMN_USER_CREATED_AT + " VARCHAR(10) DEFAULT" + getCurrentTimeStamp() + ","
                                                + USER_COLUMN_USER_UPDATED_AT + "VARCHAR(10) DEFAULT" + getCurrentTimeStamp() + ")");
        }
        catch (SQLException s)
        {
            s.printStackTrace();
        }
    }

    protected User getUser(Long userId) throws Resources.NotFoundException, NullPointerException
    {
        Cursor cursor = null;

        try
        {
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(" SELECT * FROM " +USER_TABLE_NAME + "WHERE " + USER_COLUMN_USER_ID + "= ?" , new String[]{userId + ""});

            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                User user = new User();
                user.setId(cursor.getLong(cursor.getColumnIndex(USER_COLUMN_USER_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_NAME)));
                user.setAddress(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_ADDRESS)));
                user.setCreatedAt(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_CREATED_AT)));
                user.setUpdatedAt(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_UPDATED_AT)));
                return user;
            }
            else
            {
                throw new Resources.NotFoundException("USer with Id" + userId + " doesn't exists");
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
            throw e;
        }
        finally {
            if(cursor != null)
            {
                cursor.close();
            }
        }
    }

    protected Long insertUser(User user) throws Exception
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(USER_COLUMN_USER_NAME, user.getName());
            contentValues.put(USER_COLUMN_USER_ADDRESS, user.getAddress());
            return db.insert(USER_TABLE_NAME, null, contentValues);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXITS" + USER_TABLE_NAME);
        onCreate(db);
    }

    public String getCurrentTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }
}
