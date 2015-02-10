class IntListBug {

    /**
      * Returns a list consisting of the elements of A followed by the
      * elements of B. May NOT modify items of A.
      */
    public static IntList buggyCatenate(IntList A, IntList B) {
        IntList C = new IntList(A.head, A.tail);
        // -- -- --
        IntList list2 = C;
        while (list2.tail != null) {
            list2 = list2.tail;
        }
        list2.tail = B;
        return C;
    }

    public static void main(String[] args) {
        IntList A = IntList.list(1, 2, 3);
        IntList B = IntList.list(4, 5, 6);

        System.out.println(A);    // 1 2 3
        IntList C = buggyCatenate(A, B);
        System.out.println(C);    // 1 2 3 4 5 6  this seems to work
        System.out.println(A);    // 1 2 3 4 5 6  oh no!
    }
}
