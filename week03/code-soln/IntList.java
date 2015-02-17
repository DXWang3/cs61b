public class IntList {
    public int head;
    public IntList tail;

    /** Constructs an IntList from a head int and tail IntList. */
    public IntList(int head, IntList tail) {
        this.head = head;
        this.tail = tail;
    }

    /** Constructs an IntList from the list of arguments. */
    public static IntList list(Integer... args) {
        if (args.length == 0) return null;

        IntList result = new IntList(args[0], null);
        int k; IntList p;
        for (k = 1, p = result; k < args.length; k += 1, p = p.tail) {
            p.tail = new IntList(args[k], null);
        }
        return result;
    }

    /** Returns string representation of the IntList. */
    public String toString() {
        int MAX_NUM_ITEMS = 30;
        String result = "";
        int i = 0;
        for (IntList cur = this; cur != null; cur = cur.tail, ++i) {
            result += cur.head + " ";
            if (i > MAX_NUM_ITEMS) return result + "...";
        }
        return result;
    }
}
