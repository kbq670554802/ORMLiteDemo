package com.kbq.ormlitedemo;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by KBQ on 16/7/18.
 */
public class UserDao extends BaseDao<User>{

    private static UserDao instance;

    private UserDao(Context context) {
        super(context);
    }

    /**
     * 单例获取该UserDao
     *
     * @param context
     * @return
     */
    public static synchronized UserDao getInstance(Context context) {
        if (instance == null) {
            synchronized (UserDao.class) {
                if (instance == null)
                    instance = new UserDao(context);
            }
        }
        return instance;
    }
//    public void add(User user){
//        try {
//            getDao().create(user);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<User> queryAll(){
//        try {
//            return getDao().queryForAll();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
