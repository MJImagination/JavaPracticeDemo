package 集合.链表;

import org.springframework.util.ObjectUtils;

import java.util.LinkedList;

/**
 * @Description:  自己实现简单双向链表
 * @Author: MJ
 * @Date: Created in 2021/4/17
 */
public class Link<T> {
    public static void main(String[] args) {
        Link link = new Link();
        link.add("1");
        link.add("2");
        link.add("3");
        link.list();
        LinkedList linkedList = new LinkedList();;
        linkedList.add("1");


    }

    private Node<T> firstNode;
    private Node<T> lastNode;
    private int length =0;

    public Link() {
        super();
    }

    public T add(T data) {
        if (ObjectUtils.isEmpty(firstNode)) {
            Node<T> node = new Node<>(null, null, data);
            firstNode = node;
            lastNode = node;
            length ++;
        } else {
            Node<T> node = new Node<>(lastNode, null, data);
            lastNode.next = node;
            lastNode = node;
            length ++;
        }
        return data;
    }

    public void list(){
        Node<T> temp = firstNode;
        while(!ObjectUtils.isEmpty(temp)){
            System.out.println(temp.getData());
            temp = temp.next;
        }
    }

    class Node<T> {
        //上一个节点
        private Node pre;
        //下一个节点
        private Node next;
        //数据
        private T data;

        public Node(Node pre, Node next, T data) {
            this.pre = pre;
            this.next = next;
            this.data = data;

        }


        public Node getPre() {
            return pre;
        }

        public void setPre(Node pre) {
            this.pre = pre;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }
}
