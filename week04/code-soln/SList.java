public class SList {
    private Node head;
    public SList(Node head) {
        this.head = head;
    }

    public static SList multiples(SList list, int j, int k) {

        // [ //

        SList newList = new SList(null);
        Node oldNode = list.head;

        // Get the kth element
        for (int i = 0; i < k; i++) {
            if (oldNode == null) return newList;
            oldNode = oldNode.next;
        }
        newList.head = new Node(oldNode.item);
        Node newNode = newList.head;
        oldNode = oldNode.next;

        // Keep going through the list and add every j
        for (int i = 1; oldNode != null; i++) {
            if ((i % j) == 0) {
                newNode.next = new Node(oldNode.item);
                newNode = newNode.next;
            }
            oldNode = oldNode.next;
        }

        return newList;

        // ] //

    }

    public String toString() {
        String result = "";
        for (Node cur = head; cur != null; cur = cur.next)
            result += cur.item.toString() + " ";
        return result;
    }

    private static Node n(Object item, Node next) {
        return new Node(item, next);
    }
    private static Node n(Object item) {
        return new Node(item);
    }

    public static void main(String[] args) {
        SList l = new SList(n(0, n(1, n(2, n(3, n(4, n(5, n(6))))))));
        System.out.println(l);
        System.out.println(multiples(l, 2, 0));
        System.out.println(multiples(l, 2, 1));
        System.out.println(multiples(l, 3, 2));
    }
}

class Node {
    Object item; Node next;
    Node(Object item, Node next) {
        this.item = item; this.next = next;
    }
    Node(Object item) {
        this(item, null);
    }
}
