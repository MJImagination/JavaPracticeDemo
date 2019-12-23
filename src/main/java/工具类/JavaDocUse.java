package 工具类;

/**
 * JavaDoc的使用方法
 */
public class JavaDocUse {
    private static RootDoc rootDoc;
    public static  class Doclet {
        public static boolean start(RootDoc rootDoc) {
            JavaDocUse.rootDoc = rootDoc;
            return true;
        }
    }
 
    /**
     * 显示DocRoot中的基本信息
      */
    public static void show(){
        ClassDoc[] classes = rootDoc.classes();
        for(ClassDoc classDoc : classes){
            System.out.println(classDoc.name()+
                    "类的注释:"+classDoc.commentText());
            FieldDoc[] memberDocs =classDoc.fields();
            for (FieldDoc memberDoc : memberDocs) {
                // 打印出方法上的注释
                System.out.println("类"
                        +classDoc.name()+","
                        +memberDoc.name()+
                        "属性注释:"
                        +memberDoc.commentText());
            }

            MethodDoc[] methodDocs = classDoc.methods();
            for(MethodDoc methodDoc : methodDocs){
                // 打印出方法上的注释
                System.out.println("类"
                        +classDoc.name()+","
                        +methodDoc.name()+
                        "方法注释:"
                        +methodDoc.commentText());
            }
        }
    }
 
    public static void main(String[] args) {
        com.sun.tools.javadoc.Main.execute(new String[] {"-doclet",
                Doclet.class.getName(),
                "-encoding","utf-8","-classpath",
                "D:\\work\\base-Server\\target\\classes",
                "D:\\svn\\project\\zhejiang\\jinhuaTemp\\src\\main\\java\\com\\hesc\\event\\pojo\\Apply.java"});
        show();
    }
}
