# GreenDao
GreenDao使用和简单封装

使用步骤
一、新建工程，添加GreenDao依赖
  implementation 'org.greenrobot:greendao:3.2.2'
  implementation 'org.greenrobot:greendao-generator:3.2.2'

二、在main目录下创建java-gen目录，与java目录同级，并在gradle文件android{}中添加源文件应用

    sourceSets{
        main{
            java.srcDirs = ['src/main/java','src/main/java-gen']
        }
    }
    
三、创建一个新的module作为Library，配置gradle文件，添加依赖

  implementation 'org.greenrobot:greendao-generator:3.2.2'
  
  在module中创建文件DaoMaker,让GreenDao自动生成DaoMaster,DaoSession,表所对应的数据库操作文件，如Studeng表对应的StudengDao,还有表对应的实体类，如Student表的实体类Student
 
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
  
  完成后，文件中右键点击运行java代码，会在设置的目录中生成上述文件。
  
 四、以上步骤完成后即可在主工程中调用GreenDao相关方法
 
 五、简单封装（具体看项目文件）
 
    1、DaoManager:管理数据库
    
    2、CommonUtils:操作数据表
    
    官方API文档：http://greenrobot.org/files/greendao/javadoc/current/
