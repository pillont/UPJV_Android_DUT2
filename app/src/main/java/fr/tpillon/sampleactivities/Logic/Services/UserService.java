package fr.tpillon.sampleactivities.Logic.Services;

import android.content.Context;

import fr.tpillon.sampleactivities.Logic.Dao.UserDao;
import fr.tpillon.sampleactivities.Models.Entities.UserEntity;
import fr.tpillon.sampleactivities.Models.Exceptions.AgeException;
import fr.tpillon.sampleactivities.Models.Exceptions.NameMissingException;

public class UserService {
    private final UserDao userDao;

    public UserService(Context context) {
        userDao = new UserDao((context));
    }

    public void close() {
        userDao.close();
    }

    public UserEntity lastOrNull() {
        return userDao.lastOrNull();
    }

    public void remove(UserEntity user) {
        if(user == null){
            return;
        }

        userDao.remove(user.id);
    }

    public UserEntity create(UserEntity user)
        throws AgeException, NameMissingException {

        checkUserOrThrows(user);
        return userDao.create(user);
    }

    public void update(UserEntity user)
        throws AgeException , NameMissingException {

        checkUserOrThrows(user);
        userDao.update(user);
    }

    private void checkUserOrThrows(UserEntity user)
            throws AgeException, NameMissingException {

        if (hasNameMissing(user)) {
            throw new NameMissingException();
        }

        if (hasInvalidAge(user)) {
            throw new AgeException();
        }
    }

    private boolean hasNameMissing(UserEntity user) {
        return user.firstName == null
        || user.lastName == null
        || "".equals(user.firstName)
        || "".equals(user.lastName);
    }

    private boolean hasInvalidAge(UserEntity user) {
        return user.age < 0
        || user.age > 100;
    }
}


