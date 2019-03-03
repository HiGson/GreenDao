package com.net.markj.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.net.markj.myapplication.dbmanager.CommonUtil;
import com.net.markj.myapplication.dbmanager.DaoManager;
import com.student.dao.StudentDao;
import com.student.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CommonUtil commonUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaoManager.getInstance().setDebug();
        commonUtil = new CommonUtil(this);
    }

    public void insertSingleData(View view) {
        Student student = new Student();
//        student.setId(1L); //如果插入的数据设置了id(主键)，并且这个主键在表中已经存在会抛出异常
        student.setAddress("北京市昌平区");
        student.setAge(27);
        student.setNumber("10241010");
        commonUtil.insertSingleData(student);
    }

    public void insertMultData(View view) {
        ArrayList<Student> arrayList = new ArrayList();
        for (int i = 0;i < 10;i++){
            Student student = new Student();
            student.setAddress("北京市昌平区" + i);
            student.setAge(27 + i);
            student.setNumber(String.valueOf(Long.parseLong("10241010") + 1));
            arrayList.add(student);
        }

        commonUtil.insertMultData(arrayList);
    }

    public void updateSingleData(View view) {
        Student student = new Student();
        student.setId(3l);
        student.setAddress("湖北省宜昌市");
        student.setNumber("110101199204193523");
        student.setAge(26);
        commonUtil.updateSingleData(student);
    }

    public void querySingleDataByKey(View view) {
        Student student = new Student();
        student.setId(2L);
        Student student1 = (Student) commonUtil.querySingleData(Student.class,student.getId());
        Log.v("--------->",student1.toString());
    }

    public void queryAllData(View view) {
        List<Student> students = commonUtil.queryMultData(Student.class);
        Log.v("--------->",students.toString());
    }

    public void qureyDataBySql(View view) {
        List<Student> students = commonUtil.queryDataBySql(Student.class,"where _id > ? and address != ?",new String[]{"9","北京市昌平区9"});
        Log.v("--------->",students.toString());

    }

    public void queryDataByBuilder(View view) {
//        List<Student> students = commonUtil.queryDataByBuilder(9l, "北京市昌平区9");
//        Log.v("--------->",students.toString());

//        List<Student> students = commonUtil.queryDataByBuilder();
//        Log.v("--------->",students.toString());

        List<Student> students = commonUtil.queryDataByBuilder(Student.class, StudentDao.Properties.Id.gt(9),StudentDao.Properties.Address.notEq("北京市昌平区9"));
        Log.v("--------->",students.toString());
    }

    public void queryOneOrMoreData(View view) {
        List list = commonUtil.queryOneOrMoreData(Student.class,StudentDao.Properties.Age.ge(36),StudentDao.Properties.Id.lt(3));
        Log.v("--------->",list.toString());
    }

    public void delSingleData(View view) {
        Student student = new Student();
        student.setId(7L);
        commonUtil.deleteData(student);
    }

    public void delAllData(View view) {
        commonUtil.deleteAllData(Student.class);
    }

}
