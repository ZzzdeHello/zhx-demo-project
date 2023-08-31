package algorithm.mergeSort;

// 归并排序算法：归并排序是我们常用的八大排序中的一种，
// 其排序思路中和快速排序算法一样使用到了递归的思想，同时在归并排序中还用到了一个算法，
// 就是有序数组合并算法。配合递归与有序数组合并算法，归并排序能够高效且稳定的完成排序，归并排序的优点在于其时间复杂度低，
// 稳定性高，但是缺点也是有的，那就是空间复杂度很高。

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] test = new int[] {7,3,1,2,0,6,4,5} ;
        mergeSort(test,0,test.length -1 );
        System.out.println(Arrays.toString(test));
    }

    // 拆分动作
    public static void mergeSort(int[] arr,int left,int right){
        if(left>=right){ //递归跳出循环的地方； >=是因为，在右边界拆分时候 用的mid+1 。如果是 == 时候，数组为0 的时候，右边界会无法跳出递归
            return; // 一直拆分成 数组长度为 0，或者 1 。并且用来分出左右两个部分
        }
        int mid = (left + right) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr, mid+1, right);
        merge(arr, left, mid, right);
        //上边为拆分过程
    }

    //需要注意的是整个合并过程中并没有将两个被合并的数组单独拎出来，二者始终是存在于一个数组地址上的
    public static void merge(int[] arr,int left,int mid,int right){
        int s1 = left;//根据拿到的左边界，我们定其为第一个数组的指针
        int s2 = mid+1;//根据中间位置，让中间位置右移一个单位，那就是第二个数组的指针
        int[] temp = new int[right - left+1];//根据左右边界相减我们得到这片空间的长度，以此声明额外空间
        int i = 0;//定义额外空间的指针
        while(s1<=mid && s2 <=right){
            if(arr[s1]<=arr[s2]){//如果第一个数组的指针数值小于第二个数组的，那么其放置在临时空间上
                temp[i++] = arr[s1++];
            }else{//否则是第二个数组的数值放置于其上
                temp[i++] = arr[s2++];
            }
        }
        while(s1<=mid){//如果这是s1仍然没有到达其终点，那么说明它还有剩
            temp[i++] = arr[s1++];//因为我们知道每个参与合并的数组都是有序数组，因此直接往后拼接即可
        }
        while(s2<=right){//同上
            temp[i++] = arr[s2++];
        }
        for(int j = 0;j<temp.length;j++){//数组复制
            arr[j+left] = temp[j];
        }
    }
}
