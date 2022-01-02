package fr.tpillon.sampleactivities.Logic.Dao;

import android.content.Context;
import android.provider.BaseColumns;

public class SampleDbHelper extends DataBaseHelper{
    public SampleDbHelper(Context context) {
        super(context, "SampleDB", 1);
    }

    @Override
    protected String getCreationSql() {
        return "CREATE TABLE IF NOT EXISTS " + UserDao.TABLE_NAME + " (" +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                UserDao.FIRST_NAME_KEY + " TEXT NOT NULL," +
                UserDao.LAST_NAME_KEY + " TEXT NOT NULL," +
                UserDao.AGE_KEY + " INTEGER NOT NULL" +
                ")";
    }

    @Override
    protected String getDeleteSql() {
        return "DROP TABLE IF EXISTS " + UserDao.TABLE_NAME;
    }
}
