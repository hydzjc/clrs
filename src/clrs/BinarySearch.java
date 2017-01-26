package clrs;

/**
 * Created by macbook on 2017/1/23.
 */
public class BinarySearch {
    public static int BinarySearch(int[] A,int x){
        int a=0,b=A.length;
        while(a<=b){
            int  m =(a+b)/2;
            if(A[m]>x){
                b=m-1;
            }else if (A[m]<x){
                a=m+1;
            }else
                return m;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] A = {1, 3, 4, 5, 7, 8, 9, 10};
        int s = BinarySearch(A,10);
        System.out.print(s);
    }
}
