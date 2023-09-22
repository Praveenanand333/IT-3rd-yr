public class Generic {

    // Generic method to find the maximum element in an array
    public static <T extends Comparable<T>> T findMax(T[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array is empty or null.");
        }

        T max = array[0];
        for (T element : array) {
            if (element.compareTo(max) > 0) {
                max = element;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Integer[] intArray = {3, 7, 1, 9, 4};
        Double[] doubleArray = {2.5, 5.2, 1.7, 4.8};
        String[] strArray = {"apple", "banana", "cherry"};

        System.out.println("Maximum Integer: " + findMax(intArray));
        System.out.println("Maximum Double: " + findMax(doubleArray));
        System.out.println("Maximum String: " + findMax(strArray));
    }
}