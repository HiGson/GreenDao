package com.net.markj.myapplication.dbmanager;

import android.content.Context;

import com.student.dao.StudentDao;
import com.student.entity.Student;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kron Xu on 2019/3/3 0:07
 * Description:  GreenDao v3.1 API文档地址：http://greenrobot.org/files/greendao/javadoc/3.1/
 */
public class CommonUtil<T> {
    private DaoManager daoManager;

    public CommonUtil(Context context) {
        daoManager = DaoManager.getInstance();
        daoManager.init(context);
    }

    public Boolean insertSingleData(T t) {
        boolean flag = false;
        flag = daoManager.getDaoSession().insert(t) != -1 ? true : false;
        return flag;
    }


    public Boolean insertMultData(final ArrayList<T> ts) {
        boolean flag = false;
        try {
            daoManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (T t : ts) {
                        daoManager.getDaoSession().insertOrReplace(t);
                    }
                }
            });

            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public Boolean updateSingleData(T t) {
        boolean flag = false;
        try {
            daoManager.getDaoSession().update(t);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    /**
     * 直接使用主键来查询数据
     *
     * @param t
     * @return
     */
    public T querySingleData(Class<T> t, long key) {
        T t1 = daoManager.getDaoSession().load(t, key);
        return t1;
    }

    /**
     * 查询所有数据--loadAll
     *
     * @return
     */
    public List<T> queryMultData(Class<T> tClass) {
        List<T> t = daoManager.getDaoSession().loadAll(tClass);
        return t;
    }

    /**
     * 使用SQL语句查询
     */
    public List<T> queryDataBySql(Class<T> t, String sql, String[] contions) {
        List<T> ts = daoManager.getDaoSession().queryRaw(t, sql, contions);
        return ts;
    }

    /**
     * 使用QueryBuilder构建查询条件--where
     */
    public List<T> queryDataByBuilder(/*long key, String address*/Class<T> tClass,WhereCondition... whereConditions) {

//        List<Student> students = daoManager.getDaoSession().queryBuilder(Student.class) // 获取QueryBuilder
//                .where(StudentDao.Properties.Id.ge(key)) // 添加查询条件，具体条件方法查看官方文档
//                .where(StudentDao.Properties.Address.notEq(address)) // 添加查询条件，具体条件方法查看官方文档
//                .list();//返回一个List
//        return students;

        // 查询条件分开写可以叠加，效果通链式调用
//        QueryBuilder<Student> builder = daoManager.getDaoSession().queryBuilder(Student.class);
//        builder.where(StudentDao.Properties.Id.gt(9l));
//        builder.where(StudentDao.Properties.Address.notEq("北京市昌平区9"));
//        List<Student> students = builder.list();
//        return students;

        QueryBuilder<T> builder = daoManager.getDaoSession().queryBuilder(tClass);
        for (WhereCondition whereCondition : whereConditions){
            builder.where(whereCondition);
        }
        List<T> list = builder.list();
        return list;

    }

    /**
     * 使用QueryBuilder构建查询条件--whereOr
     * @return
     */
    public List<Student> queryOneOrMoreData(Class<T> tClass,WhereCondition whereCondition1,WhereCondition whereCondition2){
        QueryBuilder<Student> builder = daoManager.getDaoSession().queryBuilder(Student.class);
//        builder.whereOr(StudentDao.Properties.Age.ge(36),StudentDao.Properties.Id.lt(3));
        builder.whereOr(whereCondition1,whereCondition2);
        List<Student> list = builder.list();
        return list;
    }

    public Boolean deleteData(T t) {
        boolean flag = false;
        try {
            daoManager.getDaoSession().delete(t);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public Boolean deleteAllData(Class<T> tClass){
        boolean flag = false;
        try{
            daoManager.getDaoSession().deleteAll(tClass);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();;
        }
        return flag;
    }

    public long getRecordCount(Class<T> tClass){
        return daoManager.getDaoSession().queryBuilder(tClass).count();
    }

}
