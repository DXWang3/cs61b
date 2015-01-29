class Swap2 {
    public static void swap(Point p) {
        int temp = p.x;
        p.x = p.y;
        p.y = temp;
    }

    public static void main(String[] args) {
        Point x = new Point(2, 5);
        System.out.println("x: " + x.x + ", " + "y: " + x.y);
        swap(x);
        System.out.println("x: " + x.x + ", " + "y: " + x.y);
    }
}
