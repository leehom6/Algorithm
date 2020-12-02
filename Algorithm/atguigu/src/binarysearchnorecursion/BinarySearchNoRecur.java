package binarysearchnorecursion;

public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 8, 43, 667, 6578, 12345};
        int index = binaryResearchNoRecur(arr, 667);
        System.out.println(index);
    }

    /**
     * 二分查找算法----非递归
     *
     * @param arr    需要进行查找的数组
     * @param target 要查找的目标盘
     * @return 找到的话，返回下标；没有找到的话返回-1
     */
    public static int binaryResearchNoRecur(int[] arr, int target) {
        int left = 0; //查找范围的左边界
        int right = arr.length - 1;//查找范围的右边界
        int mid = (left + right) / 2;
        while (left <= right) {
            if (arr[mid] == target) {
                //找到目标，返回下标
                return mid;
            } else if (arr[mid] > target) {
                //如果中间值大于目标值，那么说明目标值可能在左半边，那么我们应该讲查找范围缩小到左半边
                right = mid - 1;
                mid = (left + right) / 2;
            } else {
                //如果arr[mid]<target,我们应该将查找范围缩小到右半边
                left = mid + 1;
                mid = (left + right) / 2;
            }
        }
        //如果跑完了整个程序，说明没有找到，返回-1
        return -1;
    }
}
