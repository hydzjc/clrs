package clrs;

import com.sun.scenario.effect.Merge;

/**
 * Created by huangyd on 2017/1/22.
 *
 */
public class MergeSort {
/**
*@param
 * A 数组
 *   b数组起始位置
 *   m归并排序的第一个数字的最后一个数字的位置（第二个数组从他下一位开始）
 *   e:数组的最后位置，主要不是指向空，而是最后一位。
 */
    private static void Merge(int[] A,int b,int m,int e ){
        int i = b,j = m+1;
        int[] B  = new int[e+1];
        int s= b;
        while( i<= m && j <= e){
           if (A[i]<A[j]){
               B[s++]=A[i++];
           }else{
               B[s++]=A[j++];
           }
        }
        while(i<=m){
            B[s++]=A[i++];
        }
        while(j<=e){
            B[s++]=A[j++];
        }
        //System.arraycopy(A,b,B,b,e-b+1);
        for (int aa= b;aa<=e;aa++)
               A[aa]=B[aa];
        return;
    }
    public static void MergeSort(int[] A,int b ,int e){

        if( b<e ){
            int m = (b+e)/2;
            MergeSort(A,b,m);
            MergeSort(A,m+1,e);
            Merge(A,b,m,e);
        }
        return;
    }



    public static void main(String[] args){
        int[] A ={1,20,13,11,4,7,9};

        MergeSort(A,0,A.length-1);
        for (int i = 0;i<A.length;i++)
            System.out.print(A[i]+ "  ");

    }
}
