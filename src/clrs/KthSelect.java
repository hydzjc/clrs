package clrs;

import clrs.QuickSort;
/**
 * Created by macbook on 2017/1/26.
 * k means the K-th smallest number
 */
public class KthSelect {
    public  static int KthSelect(int[] A,int b,int e ,int k){
        if (b == e)
            return A[b];
        int q = QuickSort.Partation(A,b,e);
        int s = q-b+1;
        if (s == k)
            return A[q];
        else if (s<k){
            return KthSelect(A,q+1,e,k-s);
        }
        else{
            return KthSelect(A,b,q-1,k);
        }
    }
    public static void main(String [] args){
        int[] A = {4,6,8,5,2,3,7,9};
        int kth =3;
        System.out.println("the "+kth +"th smallest is  :" + KthSelect(A,0,A.length-1,kth));
    }
}
