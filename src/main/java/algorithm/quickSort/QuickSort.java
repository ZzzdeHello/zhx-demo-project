package algorithm.quickSort;


// 选择基准值，利用左右两个指针，从数组的头部和尾部开始遍历数据，找出大于和小于基准值的位置
// 如果左侧指针的值 >基准,停止；右侧的指针值 < 基准值，停止。交换位置。
// 一直到左右指针相遇，相遇之后将相遇位置的值和基准值位置交换。
// 这样就基本将数据分为两部分。左侧均小于基准值，右侧均大于基准值
// 利用递归的思想将所有数据处理完毕
public class QuickSort {

    public static void quickSort(int[] arr,int low,int high){
        int i,j,temp,t; // i-左指针, j-右指针 ,t-交换使用的临时的int
        if(low>high){
            return;
        }
        i=low;
        j=high;

        temp = arr[low];//temp就是基准位

        while (i<j) {
            //先看右边，依次往左递减
            while (temp<=arr[j]&&i<j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp>=arr[i]&&i<j) {
                i++;
            }
            //如果满足条件则交换
            if (i<j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        //递归调用左半数组
        quickSort(arr, low, j-1);
        //递归调用右半数组
        quickSort(arr, j+1, high);
    }

    public static void main(String[] args){
        int[] arr = {10,7,2,4,7,62,3,4,2,1,8,9,19};
        quickSort(arr, 0, arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}