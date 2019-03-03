package com.net.markj.myapplication.dbmanager;

import android.content.Context;

import com.student.dao.StudentDao;
import com.student.entity.Student;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kron Xu on 2019/3/3 0:07
 * Description:
 */
public class CommonUtil <T>{
    private DaoManager daoManager;
    public CommonUtil(Context context){
        daoManager = DaoManager.getInstance();
        daoManager.init(context);
    }

    public Boolean insertSingleData(T t){
        boolean flag = false;
        flag = daoManager.getDaoSession().insert(t) != -1 ? true : false;
        return flag;
    }


    public Boolean insertMultData(final ArrayList<T> ts){
        boolean flag = false;
        try{
            daoManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (T t : ts){
                        daoManager.getDaoSession().insertOrReplace(t);
                    }
                }
            });

            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    public Boolean updateSingleData(T t){
        boolean flag = false;
        try{
            daoManager.getDaoSession().update(t);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }

    /**
     * 直接使用键来查询数据
     * @param t
     * @return
     */
    public T querySingleData(Class<T> t, long key){
        T t1 = daoManager.getDaoSession().load(t,key);
        return t1;
    }

    /**
     * 查询所有数据
     * @return
     */
    public List<T> queryMultData(Class<T> tClass){
        List<T> t = daoManager.getDaoSession().loadAll(tClass);
        return t;
    }

    /**
     * 使用SQL语句查询
     */
    public List<T> queryDataBySql(Class<T> t,String sql,String[] contions){
        List<T> ts = daoManager.getDaoSession().queryRaw(t, sql, contions);
        return ts;
    }

    /**
     * 使用QueryBuilder构建查询条件
     */
    public List<Student> queryDataByBuilder(long key,String address){

        List<Student> students =daoManager.getDaoSession().queryBuilder(Student.class) // 获取QueryBuilder
                .where(StudentDao.Properties.Id.ge(key)) // 添加查询条件，具体条件方法查看官方文档
                .where(StudentDao.Properties.Address.notEq(address)) // 添加查询条件，具体条件方法查看官方文档
                .list();//返回一个List
        return students;
    }
}
