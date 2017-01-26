package clrs;

/**
 * Created by macbook on 2017/1/26.
 */
public class CountingSort {
    public static int[] CountingSort(int[] A){
        int min = A[0],max = A[0];
        int [] B = new int[A.length];
        for (int i:A){
            min = min >i ?  i: min;
            max = max<i ? i:max;
        }
        int k = max -min +1;
        int [] C = new int[k];
        for (int i:A)
            C[i-min]++;
        for(int i=1;i<C.length;++i){
            C[i]=C[i]+C[i-1];
        }
        for(int i=A.length-1;i>=0;--i){
            B[--C[A[i]-min]]=A[i];
        }
        return B;
    }
    public static void main(String args[]){
        int[] B ={1,1,3,3,2,2,2};
        int[] A = new  int[B.length];
        A = CountingSort(B);
        for (int i = 0;i<A.length;i++)
            System.out.print(A[i]+ "  ");
    }
}
