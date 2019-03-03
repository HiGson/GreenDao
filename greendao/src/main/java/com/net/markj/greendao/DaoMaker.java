package com.net.markj.greendao;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

/**
 * 让GreenDao自动生成DaoMaster,DaoSession,表所对应的数据库操作文件，如Studeng表对应的StudengDao,还有表对应的实体类，如Student表的实体类Student
 */
public class DaoMaker {

    public static void main(String []args){
        // 生成数据库的实体类XXEntity,对应的是数据库的表,version：数据库版本号，defaultJavaPackage：实体类所在的包
        Schema schema = new Schema(1,"com.student.entity");
        addStudent(schema);
        // 生成数据库操作等相应的文件 defaultJavaPackage：DaoMaster,DaoSession和StudentDao所在的目录
        schema.setDefaultJavaPackageDao("com.student.dao");
        try {
            // 生成对应的文件，选择生成文件的目录，文件在上面设置的两个包中，两个包在下面设置的路径里面
            new DaoGenerator().generateAll(schema,"C:\\Users\\MarkJ\\Desktop\\MyApplication\\app\\src\\main\\java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addStudent(Schema schema) {
        Entity entity = schema.addEntity("Student");// 创建数据库的表
        entity.addIdProperty();//添加主键，是int类型的
        entity.addIntProperty("age");// 添加其他属性
        entity.addStringProperty("address");
        entity.addStringProperty("number");
    }
}
