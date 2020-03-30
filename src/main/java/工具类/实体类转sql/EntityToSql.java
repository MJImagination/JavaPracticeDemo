package 工具类.实体类转sql;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import org.hibernate.hql.internal.ast.SqlGenerator;

public class EntityToSql {
    private static final Logger logger = LoggerFactory.getLogger(EntityToSql.class);
    private static Map<String, String> fileNameMap = null;

    public static void main(String[] args) {
        String classTargetPath = "D:\\svn项目\\JavaPracticeDemo\\target\\classes";
        String classPath = "D:\\svn项目\\JavaPracticeDemo\\src\\main\\java\\工具类\\实体类转sql\\Apply2.java";
        fileNameMap = JavaDocUse.getFieldCommentText(classTargetPath, classPath);
        //实体类所在的package在磁盘上的绝对路径
        String packageName = "D:\\svn项目\\JavaPracticeDemo\\src\\main\\java\\工具类\\实体类转sql\\";
        //项目中实体类的路径
        String prefix = "工具类.实体类转sql";
        //要生成的类名，不写全部
        List<String> targetCalssName = new ArrayList<>();
        targetCalssName.add("Apply2");
        //生成sql的文件夹
        String filePath = "D:/create/";

        String className = "";


        StringBuffer sqls = new StringBuffer();
        //获取包下的所有类名称
        List<String> list = getAllClasses(packageName);
        for (String str : list) {
            if (targetCalssName.size() > 0 && targetCalssName.contains(str.substring(0, str.indexOf(".")))) {
                className = prefix + "." + str.substring(0, str.lastIndexOf("."));

                String sql = generateSql(className, filePath);
                sqls.append(sql);
            }
            if (targetCalssName.size() == 0) {
                className = prefix + "." + str.substring(0, str.lastIndexOf("."));
                String sql = generateSql(className, filePath);
                sqls.append(sql);
            }

        }
        System.out.println(sqls.toString());
        StringToSql(sqls.toString(), filePath + "report.sql");

    }

    /**
     * 将字段名大写转换成  "_小写"
     *
     * @param className
     * @return
     */
    public static String convertName(String className) {
        char[] chars = className.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isUpperCase(className.charAt(i))) {
                sb.append("_").append(Character.toLowerCase(chars[i]));
            } else {
                sb.append(chars[i]);
            }
        }
        className = sb.toString();
        return sb.toString();
    }

    /**
     * 根据实体类生成建表语句
     *
     * @param className 全类名
     * @param filePath  磁盘路径  如 : d:/workspace/
     * @author
     * @date 2019年1月14日
     */
    public static String generateSql(String className, String filePath) {
        try {
            Class<?> clz = Class.forName(className);
            className = clz.getSimpleName();
            Field[] fields = clz.getDeclaredFields();
            StringBuffer column = new StringBuffer();
            String varchar = " varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL";
            for (Field f : fields) {
                column.append(" \n `" + convertName(f.getName()) + "`").append(varchar);
                if (StringUtils.isNotEmpty(fileNameMap.get(f.getName()))) {
                    column.append(" COMMENT" + " '" + fileNameMap.get(f.getName())).append("'");
                }
                column.append(" ,");
            }
            StringBuffer sql = new StringBuffer();

            String tableEnd = " \n ) ENGINE=InnoDB DEFAULT CHARSET=utf8";
            if (StringUtils.isNotEmpty(fileNameMap.get("tableName"))) {
                tableEnd += " COMMENT='" + fileNameMap.get("tableName") + "'";
            }
            tableEnd +=";";

            sql.append("\n DROP TABLE IF EXISTS `" + className + "`; ")
                    .append(" \n CREATE TABLE `" + className + "`  (")
//                    .append(" \n `id` int(11) NOT NULL AUTO_INCREMENT,")
                    .append(" \n " + column)
                    .append(" \n PRIMARY KEY (`id`) USING BTREE,")
                    .append("\n INDEX `id`(`id`) USING BTREE")
//                    .append(" \n ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci;");
                    .append(tableEnd);
            return sql.toString();
        } catch (ClassNotFoundException e) {
            logger.debug("该类未找到！");
            return null;
        }

    }

    /**
     * 获取包下的所有类名称,获取的结果类似于 XXX.java
     *
     * @param packageName
     * @return
     * @author
     * @date 2019年1月14日
     */
    public static List<String> getAllClasses(String packageName) {
        List<String> classList = new ArrayList<String>();
        String className = "";
        File f = new File(packageName);
        if (f.exists() && f.isDirectory()) {
            File[] files = f.listFiles();
            for (File file : files) {
                className = file.getName();
                classList.add(className);
            }
            return classList;
        } else {
            logger.debug("包路径未找到！");
            return null;
        }
    }

    /**
     * 将string 写入sql文件
     *
     * @param str
     * @param path
     * @author
     * @date 2019年1月14日
     */
    public static void StringToSql(String str, String path) {
        byte[] sourceByte = str.getBytes();
        if (null != sourceByte) {
            try {
                File file = new File(path);     //文件路径（路径+文件名）
                if (!file.exists()) {   //文件不存在则创建文件，先创建目录
                    File dir = new File(file.getParent());
                    dir.mkdirs();
                    file.createNewFile();
                }
                FileOutputStream outStream = new FileOutputStream(file);    //文件输出流用于将数据写入文件
                outStream.write(sourceByte);
                outStream.flush();
                outStream.close();  //关闭文件输出流
                System.out.println("生成成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
