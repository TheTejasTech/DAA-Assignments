public class Minmax {

    public static class Pair {
        int min, max;
    }

    protected static Pair minmax(int arr[], int low, int high) {
        Pair p = new Pair();

        if (low == high) {
            p.min = p.max = arr[low];
            return p;
        }
        if (high == low + 1) {
            if (arr[low] < arr[high]) {
                p.min = arr[high];
                p.max = arr[low];
            } else {
                p.min = arr[low];
                p.max = arr[high];
            }
        }
        int mid = (low + high) / 2;
        Pair left = minmax(arr, low, mid);
        Pair right = minmax(arr, mid + 1, high);

        p.min = Math.min(left.min, right.min);
        p.max = Math.max(left.max, right.max);
        return p;
    }

    public static void main(String args[]) {
        int[] arr = { 3, 4, 5, 2, 1 };

        Pair result = minmax(arr, 0, arr.length - 1);
        System.out.println("the min : " + result.min + " and max element is : " + result.max);
    }
}
