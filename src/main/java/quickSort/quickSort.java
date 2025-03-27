package quickSort;

public class quickSort {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        quickSort(nums, 0, nums.length - 1);
        for (int num : nums) {
            System.out.println(num);
        }
    }

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
