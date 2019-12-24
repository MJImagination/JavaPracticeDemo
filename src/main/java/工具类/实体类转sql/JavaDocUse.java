package 工具类.实体类转sql;

import com.sun.javadoc.*;
import com.sun.tools.javadoc.ClassDocImpl;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * JavaDoc的使用方法
 *
 * https://www.cnblogs.com/boring09/p/4274893.html
 */
public class JavaDocUse {
    private static RootDoc rootDoc;

    public static class Doclet {
        public static boolean start(RootDoc rootDoc) {
            JavaDocUse.rootDoc = rootDoc;
            return true;
        }
    }

    /**
     * 显示DocRoot中的基本信息
     */
    public static  Map<String,String> show()   {
        ClassDoc[] classes = rootDoc.classes();
        Map<String,String> map = new HashMap<>();
        for (ClassDoc classDoc : classes) {
            System.out.println(classDoc.name() +
                    "类的注释:" + classDoc.commentText());
            map.put("tableName",classDoc.commentText());

            Field[] fields = new Field[0];
            try {
                fields = Class.forName(((ClassDocImpl) classDoc).containingPackage().name()+"." + ((ClassDocImpl) classDoc).name()).getDeclaredFields();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            for (Field field : fields) {
                String fielCommentText = ((ClassDocImpl) classDoc).findField(field.getName()).commentText();
                map.put(field.getName(),fielCommentText);
            }



            MethodDoc[] methodDocs = classDoc.methods();
            for (MethodDoc methodDoc : methodDocs) {
                // 打印出方法上的注释
//                System.out.println("类"
//                        + classDoc.name() + ","
//                        + methodDoc.name() +
//                        "方法注释:"
//                        + methodDoc.commentText());
            }
        }
        return map;
    }


    /**
     * 获取类对象
     *
     * @param classPath class所处的位置(classes顾名思义就是编译后的.class文件所在位置)
     *                  比如: D:/work/base-server/target/classes/com/company/base/controller
     * @param className 需要获取到的类名称
     *                  比如: UserController
     * @return
     */
    public static Class<?> findClass(String classPath, String className) {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        //获取路径classes的路径
        String prefix = classPath.substring(0, classPath.indexOf("classes") + 8);
        //这里得到的就是com.company.base.controller包的全名
        String packageName = classPath.substring(classPath.indexOf("classes") + 8).replaceAll("/", ".");
        try {
            URL classes = new URL("file:///" + prefix);
            ClassLoader custom =
                    new URLClassLoader(new URL[]{classes}, systemClassLoader);
            Class<?> clazz = custom.loadClass(packageName + "." + className);
            return clazz;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        com.sun.tools.javadoc.Main.execute(new String[]{"-doclet",
                Doclet.class.getName(),
                "-encoding", "utf-8", "-classpath",
                "D:\\svn\\project\\zhejiang\\jinhuaTemp\\target\\classes",
                "D:\\svn\\project\\zhejiang\\jinhuaTemp\\src\\main\\java\\com\\hesc\\event\\pojo\\Apply2.java"});
        show();
    }


    public static Map<String,String> getFieldCommentText(String classPath,String name ){
        com.sun.tools.javadoc.Main.execute(new String[]{"-doclet",
                JavaDocUse.Doclet.class.getName(),
                "-encoding", "utf-8", "-classpath",
                classPath,
                name});
        return show();

    }

}
