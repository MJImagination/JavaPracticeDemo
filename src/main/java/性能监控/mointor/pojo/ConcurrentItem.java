package 性能监控.mointor.pojo;

public class ConcurrentItem {
	// 并发有效次数
	private int num = 0;
	// 并发总次数
	private int total = 0;
	// 并发超时次数
    private int overtimemun = 0;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getOvertimemun() {
		return overtimemun;
	}

	public void setOvertimemun(int overtimemun) {
		this.overtimemun = overtimemun;
	}
}
