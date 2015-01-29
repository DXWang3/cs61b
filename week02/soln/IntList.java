public class IntList {
    public int head;
    public IntList tail;

    public IntList(int head0, IntList tail0) {
        head = head0; tail = tail0;
    }

    public IntList() {
        this(0, null);
    }

    public static void skip(IntList L) {
        while (L != null && L.tail != null) {
            L.tail = L.tail.tail;
            L = L.tail;
        }
    }

 
    public static IntList mystery(IntList L) {
        if (L == null || L.tail == null) {
            return L;
        } else {
            IntList x = mystery(L.tail);
            L.tail.tail = L;
            L.tail = null;
            return x;
        }
    }

   public String toString() {
        String result = "";
        IntList y = this;
        while (y != null) {
            result = result + y.head + " ";
            y = y.tail;
        }
        return result;
    }

    public static void main(String[] args) {
        IntList x = new IntList(2, new IntList(3, new IntList(4, new IntList(5, null))));
        System.out.println(x);
        IntList y = mystery(x);
        System.out.println(y);
        skip(y);
        System.out.println(y);
        x = new IntList(1, new IntList(2, new IntList(3, new IntList(4, new IntList(5, null)))));
        skip(x);
        System.out.println(x);
    }
}
