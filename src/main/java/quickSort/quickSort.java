package quickSort;

public class quickSort {
    /**
     * Entry point for the application.
     * <p>
     * Initializes an array of integers from 1 to 9, sorts it using the quickSort algorithm, and prints the sorted results to the console.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        quickSort(nums, 0, nums.length - 1);
        for (int num : nums) {
            System.out.println(num);
        }
    }

    /**
     * Sorts a segment of an integer array in-place using the QuickSort algorithm.
     *
     * <p>This method partitions the portion of the array defined by the {@code left} and {@code right}
     * indices by selecting the element at {@code left} as the pivot. It rearranges the segment so that all
     * elements less than the pivot come before it, and all elements greater than or equal follow it.
     * The method then recursively sorts the partitions.</p>
     *
     * @param nums the array to sort
     * @param left the starting index of the segment to sort
     * @param right the ending index of the segment to sort
     */
    public static void quickSort(int[] nums, int left, int right) {
        if (left >= right) return;

        int pivot = nums[left];
        int i = left;
        int j = right;

        while (i < j) {
            while (i < j && nums[j] >= pivot) {
                j--;
            }
            nums[i] = nums[j];

            while (i < j && nums[i] <= pivot) {
                i++;
            }
            nums[j] = nums[i];
        }

        nums[i] = pivot;
        quickSort(nums, left, i - 1);
        quickSort(nums, i + 1, right);
    }
}
