package 分页;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description: list分页工具
 * @Author: MJ
 * @Date: Created in 2018/9/28
 */
public class ListPageUtil<T> {
    //原集合
    private List<T> data;

    //上一页
    private int lastPage;

    //当前页
    private int nowPage;

    //下一页
    private int nextPage;

    //每页条数
    private int pageSize;

    //总页数
    private int totalPage;

    //总数据条数
    private int totalCount;

    public ListPageUtil(List<T> data, int nowPage, int pageSize) {
        if (data != null && !data.isEmpty()) {
            this.data = data;
            this.pageSize = pageSize;
            this.nowPage = nowPage;
            this.totalCount = data.size();
            this.totalPage = (totalCount + pageSize - 1) / pageSize;
            this.lastPage = nowPage - 1 > 1 ? nowPage - 1 : 1;
            this.nextPage = nowPage >= totalPage ? totalPage : nowPage + 1;
        }
    }

    /**
     * 得到分页后的数据
     *
     * @return 分页后结果
     */
    public List<T> getPagedList() {
        if (data != null && !data.isEmpty()) {
            int fromIndex = (nowPage - 1) * pageSize;
            if (fromIndex >= data.size()) {
                return Collections.emptyList();//空数组
            }
            if (fromIndex < 0) {
                return Collections.emptyList();//空数组
            }
            int toIndex = nowPage * pageSize;
            if (toIndex >= data.size()) {
                toIndex = data.size();
            }
            return data.subList(fromIndex, toIndex);
        } else {
            return null;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public List<T> getData() {
        return data;
    }

    public int getLastPage() {
        return lastPage;
    }

    public int getNowPage() {
        return nowPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        List<Integer> list = Arrays.asList(array);

        ListPageUtil<Integer> listPageUtil = new ListPageUtil<Integer>(list, 2, 3);
        List<Integer> pagedList = listPageUtil.getPagedList();
        System.out.println(pagedList);
    }
}
