package fr.tpillon.sampleactivities.Logic.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import fr.tpillon.sampleactivities.Models.Entities.UserEntity;

public class UserDao extends  BaseDao<UserEntity> {
    public static final String FIRST_NAME_KEY = "first_name";
    public static final String LAST_NAME_KEY = "last_name";
    public static final String AGE_KEY = "age";
    public static final String TABLE_NAME = "user";

    public UserDao(Context context) {
        super(new SampleDbHelper(context));
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected void putValues(ContentValues values, UserEntity entity) {
        values.put(FIRST_NAME_KEY, entity.firstName);
        values.put(LAST_NAME_KEY, entity.lastName);
        values.put(AGE_KEY, entity.age);
    }

    @Override
    protected UserEntity getEntity(Cursor cursor) {
        UserEntity operation = new UserEntity();

        int firstNameIndex = cursor.getColumnIndex(FIRST_NAME_KEY);
        operation.firstName = cursor.getString(firstNameIndex);

        int lastNameIndex = cursor.getColumnIndex(LAST_NAME_KEY);
        operation.lastName = cursor.getString(lastNameIndex);

        int ageIndex = cursor.getColumnIndex(AGE_KEY);
        operation.age = cursor.getInt(ageIndex);

        return operation;
    }
}
