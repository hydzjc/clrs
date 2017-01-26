package clrs;

/**
 * Created by macbook on 2017/1/25.
 */
public class QuickSort {
    //use the last value as the standard
    public static int Partation(int[] A,int b,int e){
        int x= A[e];
        int i = b-1;
        for (int j = b;j<=e-1;j++){
            if(x>=A[j]){
                i=i+1;
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }
        }
        int tmp = A[i+1];
        A[i+1] = A[e];
        A[e] = tmp;
        return i+1;
    }

    public static void QuictSort(int[] A,int  b,int e){
        if (b<e) {
            int q = Partation(A, b, e);
            QuictSort(A, b, q - 1);
            QuictSort(A, q + 1, e);
        }
    }
    //test QuictSort
    public static void  main(String[] args){
        int[] B ={1,20,13,11,4,7,9};
        QuictSort(B,0,B.length-1);
        for (int i = 0;i<B.length;i++)
            System.out.print(B[i]+ "  ");

    }
}
