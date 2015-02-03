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
    public static Object findMiddle(SList list) {
        SListNode slowJumper = list.head;
        SListNode fastJumper = list.head;
        while (fastJumper != null && fastJumper.next != null && fastJumper.next.next != null) {
            slowJumper = slowJumper.next;
            fastJumper = fastJumper.next.next;
        }
        return slowJumper.item;
    }


    public static void main(String[] args) {
        SList l = new SList(new SListNode(1, new SListNode(2, new SListNode(3,new SListNode(4)))));
        System.out.println(l);
        System.out.println(findMiddle(l));
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
