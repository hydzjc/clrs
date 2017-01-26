package clrs;

/**
 * Created by macbook on 2017/1/25.
 */
public class BiggestSubarray {
    //divide and conquer
    private static int  FindMaxCrossingSubarray(int[] A,int b,int m ,int e){
        int left_sum = -100000;//assume this is the smallest sum...Maybe it's not sometime.
        int sum = 0;
        int max_left = m;
        for (int i = m;i>=b;i--) {
            sum = sum + A[i];
            if(sum >left_sum){
                left_sum = sum;
            }
        }
        int right_sum = -1000000;
        sum = 0;
        for (int j  = m+1 ;j<=e;j++){
            sum = sum +A[j];
            if(sum>right_sum){
                right_sum = sum;
            }
        }
        return right_sum + left_sum;
    }
    public static int max(int a,int b,int c){
        if(a>b && a>c) return a;
        else if (b>c)
            return b;
        return c;

    }
    public static int FindMaxSubarray(int[] A,int b,int e)
    {
        if ( b ==  e)
            return A[b];
        else{
            int mid = (b+e)/2;
            return max(FindMaxSubarray(A,b,mid),FindMaxSubarray(A,mid+1,e),FindMaxCrossingSubarray(A,b,mid,e));
        }
    }
    //linear for finding the max subarray
    public static int FindMaxSubarrayWhitLinear(int[] A){
        int sum = 0;
        int max_sum = 0;
        for (int i = 0; i<= A.length-1;i++){
            sum = sum + A[i];
            if (max_sum < sum ){
                max_sum = sum ;
            }
            if (sum <0) sum = 0;
        }
        return max_sum;
    }

    public static void main(String args[]){
        int[] A = {13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
        int MaxSum = FindMaxSubarray(A,0,A.length-1);
        System.out.println("FindMaxSubarray:MaxSum :"+MaxSum);
        int MaxSum2 = FindMaxSubarrayWhitLinear(A);
        System.out.println("FindMaxSubarrayWhitLinear:MaxSum2 :"+MaxSum2);
     }


}
