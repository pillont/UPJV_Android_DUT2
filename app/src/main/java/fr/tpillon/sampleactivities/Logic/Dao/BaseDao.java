package fr.tpillon.sampleactivities.Logic.Dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

import fr.tpillon.sampleactivities.Models.Entities.BaseEntity;
import fr.tpillon.sampleactivities.Models.Entities.UserEntity;


/**
 * SOURCE : https://developer.android.com/training/data-storage/sqlite
 */
public abstract class BaseDao<T extends BaseEntity>  implements BaseColumns {
    private final DataBaseHelper dbHelper;

    public BaseDao(DataBaseHelper helper){
        this.dbHelper = helper;
    }

    protected abstract String getTableName();
    protected abstract void putValues(ContentValues values, T entity);
    protected abstract T getEntity(Cursor cursor);

    /**
     * @param entity : element a ajouter dans la base
     * @return : l element créait avec son ID
     */
    public T create(T entity){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {

            ContentValues values = new ContentValues();
            putValues(values, entity);

            long newRowId = db.insert(getTableName(), null, values);
            entity.id = newRowId;
            return entity;

        }finally {
            db.close();
        }
    }

    protected List<T> query(String selection, String[] selectionArgs, String sortOrder){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.query(
                    getTableName(),
                    null,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
            );

            List items = new ArrayList<T>();
            while (cursor.moveToNext()) {
                items.add(getEntity(cursor));

            }

            cursor.close();

            return items;
        }
        finally {
            db.close();
        }
    }


    public T lastOrNull() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.query(
                    getTableName(),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null);

            int count = cursor.getCount();
            if (count < 1) {
                return null;
            }
            cursor.moveToLast();
            T last = this.getEntity(cursor);

            int idIndex = cursor.getColumnIndex(BaseColumns._ID);
            long id = cursor.getLong(idIndex);
            last.id = id;

            cursor.close();

            return last;
        } finally {
            db.close();
        }
    }


    public long count() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        try {

            Cursor cursor = db.rawQuery("select count(*) from " + getTableName(), null);
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            cursor.close();
            return count;
        }finally {
            db.close();
        }
    }

    public void close() {
        dbHelper.close();
    }

    public void update(T entity) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            putValues(values, entity);

            String selection = BaseDao._ID + " LIKE ?";
            String[] selectionArgs = {entity.id.toString()};

            db.update(
                    getTableName(),
                    values,
                    selection,
                    selectionArgs);
        }finally {
            db.close();
        }
    }

    public void remove(Long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            String selection = BaseColumns._ID + " LIKE ?";
            String[] selectionArgs = {Long.toString(id)};
            db.delete(
                    getTableName(),
                    selection,
                    selectionArgs);
        }
        finally {
            db.close();
        }
    }
}
