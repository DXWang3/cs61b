class BuggySums {

    public static int buggySum1(int[] a) {
        int total = 0;
        for (int i = 1; i < a.length; i++) { 
            total += a[i];
        }
        return total;
    }
    // -- -- --

    public static int buggySum2(int[] a) {
        int total = 0;
        for (int i = 0; i <= a.length; i++) {
            total += a[i];
        }
        return total;
    }
    // -- -- --

    public static int buggySum3(int[] a) {
        int i = 0;
        int total = 0;
        while (i < a.length) {
            total += a[i];
        }
        return total;
    }
    // -- -- --
}
