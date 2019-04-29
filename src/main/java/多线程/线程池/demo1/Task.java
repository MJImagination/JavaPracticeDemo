package 多线程.线程池.demo1;

public class Task implements Runnable{

    private String taskId;
    private String taskName;

    public Task(String taskId,String taskName){
        this.taskId = taskId;
        this.taskName = taskName;
    }
    public String getTaskId() {
        return taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        try {
            System.out.println("taskName:"+taskName+"，开始执行");
            Thread.sleep(3000);
            System.out.println("taskName:"+taskName+"，完成操作");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return taskName.toString();
    }
}



