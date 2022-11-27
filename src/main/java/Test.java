import java.util.Scanner;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/7/18
 */
public class Test {




    Scanner scanner = new Scanner(System.in);

//    public static void main(String[] args){
//        Scanner scanner = new Scanner(System.in);
//        String a = scanner.next().replaceAll("\"","");
//        String b = scanner.next().replaceAll("\"","");
//        System.out.println("\""+(Integer.parseInt(a) + Integer.parseInt(b))+"\"");
//
//    }



//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
//            String s = sc.nextLine();
//            int res = 0;
//            for (int i = s.length(); i > 0; i--) {
//                for (int j = 0; j < s.length()-i+1; j++) {
//                    String temp = s.substring(j,i+j);
//                    if (judge(temp))res++;
//                }
//            }
//            System.out.println(res);
//        }
//    }
//    public static boolean judge(String s){
//        char[] chs = s.toCharArray();
//        int len = s.length();
//        for (int i = 0; i < chs.length; i++) {
//            if (chs[i]!=chs[len-i-1])return false;
//        }
//        return true;
//    }

//    ababa
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        char[] a = s.toCharArray();
        int num = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length ; j++) {
//                String temp = s.substring(i,j+1);
//                char[] c = temp.toCharArray();
//                boolean flag = true;
//                for(int k = 0;k<c.length;k++){
//                    if(c[k] != c[c.length-1-k]){
//                        flag = false;
//                        break;
//                    }
//                }
//                if(flag){
//                    num++;
//                }


                boolean flag = true;
                if(j!=i){
                    for (int k = i; k < j; k++) {
                        if(a[k] !=a[j-1-k]){
                            flag = false;
                            break;
                        }
                    }
                }

                if(flag){
                    num ++;
                }
            }
        }
        System.out.println(num);
    }

    private String name;


}
