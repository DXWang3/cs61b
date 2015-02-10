class IntListBug {

    /**
      * Returns a list consisting of the elements of A followed by the
      * elements of B. May NOT modify items of A.
      */
    public static IntList buggyCatenate(IntList A, IntList B) {
        IntList C = new IntList(A.head, A.tail);
        // -- -- --
        IntList Apointer = A.tail, Cpointer = C;
        while (Apointer != null) {
            Cpointer.tail = new IntList(Apointer.head, null);
            Apointer = Apointer.tail;
            Cpointer = Cpointer.tail;
        }
        // Old code, no longer needed:
        // IntList list2 = C;
        // while (list2.tail != null) {
        //     list2 = list2.tail;
        // }
        // list2.tail = B;
        Cpointer.tail = B;
        // -- -- --
        return C;
    }

    public static void main(String[] args) {
        IntList A = IntList.list(1, 2, 3);
        IntList B = IntList.list(4, 5, 6);

        System.out.println(A);    // 1 2 3
        IntList C = buggyCatenate(A, B);
        System.out.println(C);    // 1 2 3 4 5 6  this seems to work
        System.out.println(A);    // 1 2 3 4 5 6  <-- fixed; now prints 1 2 3
    }
}
