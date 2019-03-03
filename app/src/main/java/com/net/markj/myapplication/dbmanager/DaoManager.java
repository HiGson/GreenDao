package com.net.markj.myapplication.dbmanager;

import android.content.Context;

import com.student.dao.DaoMaster;
import com.student.dao.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * Created by Kron Xu on 2019/3/2 23:26
 * Description: DaoGenerator v3.1 API文档地址：http://greenrobot.org/files/greendao-generator/javadoc/3.1/
 */
public class DaoManager {
    private static DaoManager daoManager;
    private DaoMaster daoMaster;
    private static final String DB_NAME = "greenDao_test";
    private DaoSession daoSession;
    private Context context;
    private DaoMaster.DevOpenHelper helper;

    private DaoManager(){}

    /**
     * 使用单例模式创建DaoManager对象
     * @return
     */
    public static DaoManager getInstance(){
        if (daoManager == null){
            synchronized (DaoManager.class){
                if(daoManager == null){
                    daoManager = new DaoManager();
                }
            }
        }
        return daoManager;
    }

    /**
     * 获取DaoMaster,创建数据库
     * @return
     */
    public DaoMaster getDaoMaster(){
        if (daoMaster == null){
            helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 获取DaoSession
     * @return
     */
    public DaoSession getDaoSession(){
        if (daoSession == null){
            if (daoMaster == null){
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    public void init(Context context){
        this.context = context;
    }

    public void closeHelper(){
        if (helper != null){
            helper.close();;
            helper = null;
        }
    }

    public void closeSession(){
        if (daoSession != null){
            daoSession.clear();
            daoSession= null;
        }
    }

    public void closeConnection(){
        closeHelper();
        closeSession();
    }

    public void setDebug(){
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }
}
