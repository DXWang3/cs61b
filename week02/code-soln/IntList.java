public class IntList {
    public int head;
    public IntList tail;

    public IntList(int head0, IntList tail0) {
        head = head0; tail = tail0;
    }

    public static void skip (IntList L) {

        // [ //
        while (L != null && L.tail != null) {
            L.tail = L.tail.tail;
            L = L.tail;
        }
        // ] //


    }

}
