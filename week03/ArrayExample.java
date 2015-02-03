class ArrayExample {
	public static void reverse(int[] arr) {
		int len = arr.length;
		for (int i = 0; i < len/2; i++) {
			int temp = arr[i];
			arr[i] = arr[len- i -1];
			arr[len- i -1] = temp;
		}
	}
	public static void main(String[] args) {
		int[] test1 = {1,3,3,7,42};
		printArr(test1);
		reverse(test1);
		printArr(test1);

	}

	public static void printArr(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			System.out.print(arr[i] + ", ");
		}
		System.out.print(arr[arr.length - 1] + "\n"); 
	}
	public static int mid(int[] arr) {
		return arr[arr.length/2];
	}
}