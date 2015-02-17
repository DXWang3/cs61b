public class IntList2 {
    public int head;
    public IntList2 tail;

    public IntList2(int head0, IntList2 tail0) {
        head = head0; tail = tail0;
    }

    public static IntList2 mystery(IntList2 L) {
        if (L == null || L.tail == null) {
            return L;
        } else {
            IntList2 x = mystery(L.tail);
            L.tail.tail = L;
            L.tail = null;
            return x;
        }
    }
    // -- -- --

    public String toString() {
        String result = "";
        IntList2 y = this;
        while (y != null) {
            result = result + y.head + " ";
            y = y.tail;
        }
        return result;
    }

    public static void main(String[] args) {
        IntList2 x = new IntList2(2, new IntList2(3, new IntList2(4, new IntList2(5, null))));
        System.out.println(x);
        IntList2 y = mystery(x);
        System.out.println(y);
    }
}
