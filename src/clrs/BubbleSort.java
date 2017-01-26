package clrs;

/**
 * Created by macbook on 2017/1/25.
 */
public class BubbleSort {
    public static void BubbleSort(int[] A){
        for (int i = 1;i<A.length;i++)
        {
            int tmp =A[i-1];
            for(int j = A.length-1;j >= i;j--){
                if (tmp< A[j]) {
                    int s = A[j];
                    A[j]= tmp;
                    tmp = s;
                }
            }
            A[i-1]=tmp;
        }
    }


    public static void  main(String[] args){
        int[] B ={1,20,13,11,4,7,9};
        BubbleSort(B);
        for (int i = 0;i<B.length;i++)
            System.out.print(B[i]+ "  ");

    }
}
