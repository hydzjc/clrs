package clrs;

import com.sun.scenario.effect.Merge;

/**
 * Created by macbook on 2017/1/25.
 * This is big heap
 * The array begins from  1 in the Clrs book,but it begins from 0 in my program.
 * TO_DO  怎么把heapsize保存到数组里面，不作为一个参数，并且尽可能少的移动
 */
public class HeapSort {
    private static int  HeapRight(int i ){
        return 2*i+1;
    }
    private static int HeapLeft(int i ){
        return 2*i+2;
    }
    private static void MaxHeapify(int[] A,int i,int heapsize){
        int Bigvalue = i;
        if(HeapLeft(i)<= heapsize && A[HeapLeft(i)] > A[i]){
            Bigvalue = HeapLeft(i);
        }
        if(HeapRight(i)<= heapsize && A[HeapRight(i)] > A[Bigvalue]){
            Bigvalue = HeapRight(i);
        }
        if(Bigvalue != i){
            int tmp = A[i];
            A[i] = A[Bigvalue];
            A[Bigvalue] = tmp;
            MaxHeapify(A,Bigvalue,heapsize);
        }
    }
    public static void BuildHeap(int[] A,int heapsize){
        //int heapsize = A.length-1;
        for (int i = heapsize/2 ;i>=0 ;i--)
            MaxHeapify(A,i,heapsize);
    }
    public static void HeapSort(int[] A){
        for(int i = A.length-1;i>= 1;i--)
        {
            int tmp = A[i];
            A[i] = A[0];
            A[0] = tmp;
            MaxHeapify(A,0,i-1);
        }

    }
    public static void  main(String[] args){
        int[] B ={1,20,13,11,4,7,9,15,23,34};
        BuildHeap(B,B.length-1);
        for (int i = 0;i<B.length;i++)
            System.out.print(B[i]+ "  ");
        System.out.println();
        HeapSort(B);
        for (int i = 0;i<B.length;i++)
            System.out.print(B[i]+ "  ");
    }
}
