package com.kbq.ormlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * Created by KBQ on 16/7/18.
 *  数据库操作基类
 */
public class BaseDao<T> extends OrmLiteSqliteOpenHelper {
    private Class<T> tClass;
    /**
     * tDao ，每张表对应一个
     * T 表示对应实体
     */
    private Dao<T, Integer> tDao;

    public BaseDao(Context context) {
        super(context, Constant.DATABASE_NAME, null, Constant.DATABASE_VERSION);
        tClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void onCreate(SQLiteDatabase database,
                         ConnectionSource connectionSource) {
        try {

            TableUtils.createTable(connectionSource, tClass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, tClass, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得userDao
     *
     * @return
     * @throws SQLException
     */
    public Dao<T, Integer> getDao(){
        if (tDao == null) {
            try {
                tDao = getDao(tClass);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tDao;
    }
    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();
        tDao = null;
    }

    /**
     * 查询所有
     * @return
     */
    public List<T> queryAll(){
        try {
            return getDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加
     * @param t 要添加的对象
     * @return 影响行数，-1为添加失败
     */
    public int add(T t){
        try {
            return getDao().create(t);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 删除，根据ID
     * @param id
     * @return 影响行数，-1为删除失败
     */
    public int deleteById(int id){
        try {
            return getDao().deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    /**
     * 删除，根据多个ID
     * @param ids
     * @return 影响行数，-1为删除失败
     */
    public int deleteIds(Collection<Integer> ids){
        try {
            return getDao().deleteIds(ids);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    /**
     * 删除，根据集合
     * @param collection
     * @return 影响行数，-1为删除失败
     */
    public int delete(Collection<T> collection){
        try {
            return getDao().delete(collection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 删除，根据Prepared
     * @param preparedDelete
     * @return 影响行数，-1为删除失败
     */
    public int delete(PreparedDelete<T> preparedDelete){
        try {
            return getDao().delete(preparedDelete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 删除，根据对象
     * @param t
     * @return 影响行数，-1为删除失败
     */
    public int delete(T t){
        try {
            return getDao().delete(t);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
