package 多线程;

public class Test {
    
    /**
     * 主线程打印20次，然后子线程打印10次，如此交替打印30次   https://www.jianshu.com/p/81563a6b7806
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        
        final Service service=new Service();
        
        new Thread(){
            
            public void run() {
                for (int i = 0; i < 30; i++) {
                    try {
                        service.subPrint(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            
        }.start();
        
        for (int i = 0; i < 30; i++) {
            service.mainPrint(i);
        }
    }
    
    public static class Service{
        private boolean shouldBeMain=true;
    
        /**
         * 主线程打印，注意同步
         */
        public synchronized void mainPrint(int loopth) throws Exception{
            
            while(!shouldBeMain){//不该主线程
                System.out.println("主线程等待："+loopth);
                this.wait();
            }
            
            Thread.sleep(1000);
            for (int i = 0; i < 2; i++) {
                System.out.println("主线程打印："+i+",第"+loopth+"次循环");
            }
            
            shouldBeMain=false;
            
            this.notify();
            
        }
        
        
       public synchronized void subPrint(int loopth) throws Exception{
           while(shouldBeMain){
               System.out.println("子线程等待："+loopth);
               this.wait();
           }
           
           Thread.sleep(1000);
           
            for (int i = 0; i < 2; i++) {
                System.out.println("子线程打印："+i+",第"+loopth+"次循环");
            }
            
            shouldBeMain=true;
            
            this.notify();
            
        }
        
    }

}

