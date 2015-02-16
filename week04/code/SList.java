public class SList {
    private SListNode head;
    public SList(SListNode head) {
        this.head = head;
    }

    public static Object multiples(SList list, int j, int k) {









    }

    public String toString() {
        String result = "";
        for (SListNode cur = head; cur != null; cur = cur.next)
            result += cur.item.toString() + " ";
        return result;
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
