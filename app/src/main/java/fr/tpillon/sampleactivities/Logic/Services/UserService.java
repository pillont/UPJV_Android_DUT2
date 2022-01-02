package fr.tpillon.sampleactivities.Logic.Services;

import android.content.Context;

import fr.tpillon.sampleactivities.Logic.Dao.UserDao;
import fr.tpillon.sampleactivities.Models.Entities.UserEntity;

public class UserService {
    private final UserDao userDao;

    public UserService(Context context) {
        userDao = new UserDao((context));
    }

    public UserEntity lastOrNull() {
        return userDao.lastOrNull();
    }

    public void close() {
        userDao.close();
    }

    public UserEntity create(UserEntity user) {
        return userDao.create(user);
    }

    public void update(UserEntity user) {
        userDao.update(user);
    }

    public void remove(UserEntity user) {
        if(user == null){
            return;
        }

        userDao.remove(user.id);
    }
}
