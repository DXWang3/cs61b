class Swap {
    public static void swap(int a, int b) {
        int temp = b;
        b = a;
        a = temp;
    }

    public static void main(String[] args) {
        int x = 2;
        int y = 5;
        System.out.println("x: " + x + ", " + "y: " + y);
        swap(x, y);
        System.out.println("x: " + x + ", " + "y: " + y);
    }
}
