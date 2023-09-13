class Solution {
    int search(int[] nums, int target) {
        int pivot = findPivot(nums);

        //if you didn't find the pivot, it means the array is not rotated
        if(pivot == -1){
            // just do normal binary search
            return binarySearch(nums, target, 0, nums.length - 1);
        }

        // if pivot is found, you have 2 ascending order arrays
        if (nums[pivot] == target) {
            return pivot;
        }

        if (target >= nums[0]) {
            return binarySearch(nums, target, 0, pivot - 1);
        }

        return binarySearch(nums, target, pivot + 1, nums.length - 1);
    }

    int binarySearch(int[] arr, int target, int s, int e){

        while (s <= e){
//            find the middle element
//            int mid = (s + e) / 2; // might be possible that (s + e) exceeds the range of int in java
            int mid = s + (e - s) / 2;

            if (target < arr[mid]) {
                e = mid - 1;
            } else if (target > arr[mid]) {
                s = mid + 1;
            } else {
//                answer found
                return mid;
            }
        }
        return -1;
    }

    int findPivot(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            //4 cases over here

            if (mid < end && arr[mid] > arr[mid + 1]) {
                return mid;
            }

            if (mid > start && arr[mid] < arr[mid - 1]) {
                return mid - 1;
            }

            if(arr[mid] <= arr[start]) {
                end = mid - 1;
            } else { // since arr[mid] >= arr[start] then there can be more elements that are greater then start
                // therefore, we will mow search for pivot from (start = mid + 1) to end
                start = mid + 1;
            }

        }
        return - 1;
    }
}