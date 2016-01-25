package edu.princeton.cs.algs4;

// Split an integer array, so that the difference between the left half's sum and right half's sum is minimal,
// return the index of the split position 
public class splitHalf {
	public static void main(String[] args){
		int[] a = {3,-1,2,4,8};
		int split = split(a);
		System.out.print(split);
	}
	public static int split(int[] a){
		int leftsum = 0;
		int rightsum = 0;
		int diff = 0;
		int min_diff = Integer.MAX_VALUE;
		int index = 0;;
		for(int j = 0; j < a.length; j++){
			rightsum += a[j];
		}
		for(int i = 0; i < a.length; i++){
			leftsum += a[i];
			rightsum -= a[i];
			diff = Math.abs(leftsum - rightsum);
			if(diff < min_diff){
				min_diff = diff;
				index = i;
			}
		}
		return index;
	}
}
