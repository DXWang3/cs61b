import java.util.Arrays;

class ArrayExample {
    public static int middle(int[] arr) {
        return arr[arr.length/2];
    }

    // -- -- --
    public static void reverse(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len/2; i++) {
            int temp = arr[i];
            arr[i] = arr[len - i - 1];
            arr[len - i - 1] = temp;
        }
    }

    public static void main(String[] args) {
        int[] test1 = {1, 3, 3, 7, 42};
        System.out.println(Arrays.toString(test1));
        reverse(test1);
        System.out.println(Arrays.toString(test1));
    }
}
