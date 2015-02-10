public class SList {
    private SListNode head;
    public SList(SListNode head) {
        this.head = head;
    }
    public SList() {
        this(null);
    }

    public static Object middle(SList list) {
        IntList slow = list;
        IntList fast = list;
        while (                        ) {









        }
        return                             ;
    }

    public String toString() {
        String result = "";
        for (SListNode cur = head; cur != null; cur = cur.next)
            result += cur.item.toString() + " ";
        return result;
    }

    public static void main(String[] args) {
        SList l = new SList(new SListNode(1, new SListNode(2, new SListNode(3))));
        System.out.println("l = " + l);
        System.out.println("l middle: " + middle(l));
    }
}

class SListNode {
    Object item; SListNode next;
    SListNode(Object item, SListNode next) {
        this.item = item; this.next = next;
    }
    SListNode(Object item) {
        this(item, null);
    }
}
