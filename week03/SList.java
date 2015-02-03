public class SList {
    private SListNode head;
    private int size;

    public SList(SListNode head) {
        this.head = head;
    }

    public SList() {
        this(null);
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int length() {
        return size;
    }

    public String toString() {
        String result = "";
        for (SListNode cur = head; cur != null; cur = cur.next)
            result += cur.item.toString() + " ";
        return result;
    }

    public static void main(String[] args) {
        System.out.println(l.isEmpty());
        SList l = new SList(new SListNode(1, new SListNode(2, new SListNode(3))));
        System.out.println(l);
        System.out.println(l.isEmpty());
    }
}

class SListNode {
    Object item;
    SListNode next;

    SListNode(Object item, SListNode next) {
        this.item = item;
        this.next = next;
    }

    SListNode(Object item) {
        this(item, null);
    }
}
