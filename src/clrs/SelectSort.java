package clrs;

/**
 * Created by huangyd on 2017/1/22.
 * 先找出最小的数字和A[1]进行交换，再找出第二小的数和A[2]交换
 */
public class SelectSort {
    private static void SelectSort(int[] A){
        int  l = A.length;
        for (int i = 0;i<l-1;i++){
            int key = A[i];
            int flag = i;
            for(int j = i+1;j<l;j++){
                if (key> A[j]) {
                    key = A[j];
                    flag = j;
                }
            }
            A[flag]=A[i];
            A[i]=key;
        }
    }
    public static void  main(String[] args){
        int[] A ={1,20,13,11,4,7,9};
        SelectSort(A);
        for (int i = 0;i<A.length;i++)
            System.out.print(A[i]+ "  ");

    }

}
