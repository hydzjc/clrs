package clrs;

import com.sun.tools.javac.util.List;

import java.util.ArrayList;

/**
 * Created by huangyd on 2017/1/22.
 *插入排序：
 */




public class InsertionSort {
    public static void  InsertionSort(int[] B){
        int l = B.length;
        for (int i = 2;i<l;i++)
        {
            int key =  B[i];
            int j = i-1;
            for (; j>=0 && B[j]> key ;j--){
                B[j+1]=B[j];
            }
            B[j+1]=key;

        }
    }

    public static void  main(String[] args){
        int[] B ={1,20,13,11,4,7,9};
        InsertionSort(B);
        for (int i = 0;i<B.length;i++)
            System.out.print(B[i]+ "  ");

    }
}
