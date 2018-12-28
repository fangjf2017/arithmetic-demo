package demo.search;

/**
 * Created by fan on 2018/12/28.
 * 查询方法
 */
public class Search {

    //二分法查找(普通循环出现)
    public static int binSearch(int[] array, int key) {
        int start = 0;
        int end = array.length - 1;
        while (start <= end) {
            int mid = (end + start) / 2;
            if (key < array[mid]) {
                end = mid - 1;
            } else if (key > array[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        //找不到则返回-1
        return -1;
    }

    // 二分查找(递归实现)
    public static int binSearch(int srcArray[], int start, int end, int key) {
        int mid = (end - start) / 2 + start;
        if (srcArray[mid] == key) {
            return mid;
        }
        if (start >= end) {
            return -1;
        } else if (key > srcArray[mid]) {
            return binSearch(srcArray, mid + 1, end, key);
        } else if (key < srcArray[mid]) {
            return binSearch(srcArray, start, mid - 1, key);
        }
        return -1;
    }

    //交换
    public static void swap(int[] arr, int i, int j) {
        //普通
//        int tmp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = tmp;
        //位运算
        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];
    }

    //插入排序 升序
    public static void insertSort(int[] a) {
        int i, j, insertNote;
        // 从数组的第二个元素开始循环将数组中的元素插入
        for (i = 1; i < a.length; i++) {
            insertNote = a[i];
            j = i - 1;
            while (j >= 0 && insertNote < a[j]) {
                // 如果要插入的元素小于第j个元素,就将第j个元素向后移动
                a[j + 1] = a[j];
                j--;
            }
            // 直到要插入的元素不小于第j个元素,将insertNote插入到数组中
            a[j + 1] = insertNote;
        }
    }

    //冒泡排序 升序
    public static void select(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    // 选择排序升序
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int tmp = arr[i];
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < tmp) {
                    tmp = arr[j];
                    index = j;
                }
            }
            if (index != i) {
                //交换
                arr[index] = arr[i];
                arr[i] = tmp;
            }
        }
    }


    //去重
    public static void searchRepeatIndex(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    System.out.println("重复元素下标:" + i);
                    break;//去掉这句可以查找重复次数
                }
            }
        }
    }

    public static void main(String[] args) {
        int srcArray[] = {3, 5, 11, 17, 21, 23, 28, 30, 32, 50, 64, 78, 81, 95, 101};
        System.out.println("数据索引:" + binSearch(srcArray, 32));
        System.out.println("数据索引:" + binSearch(srcArray, 0, srcArray.length, 32));
        int Array2[] = {22, 99};
        swap(Array2, 0, 1);
        System.out.println("交换后:" + Array2[0]);
        int Array3[] = {7, 8, 11, 17, 1, 23, 28, 30, 32, 5, 64, 78, 81, 95, 10};
        insertSort(Array3);
        System.out.print("插入排序结果:");
        for (int i = 0; i < Array3.length; i++) {
            System.out.print(Array3[i] + ",");
        }
        System.out.println();
        int Array4[] = {7, 8, 11, 17, 1, 23, 28, 30, 32, 5};
        select(Array4);
        System.out.print("冒泡排序结果:");
        for (int i = 0; i < Array4.length; i++) {
            System.out.print(Array4[i] + ",");
        }
    }

}
