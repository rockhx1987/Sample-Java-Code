package alg;

import java.util.ArrayList;
import java.util.Arrays;

// Calculate the cumulative distribution function of an array, return a pair (digit, probability)
public class cdf {
	public static ArrayList<Pair> cdf_array(int[] ar){
		ArrayList<Pair> cdfArray = new ArrayList<Pair>();
		Arrays.sort(ar);
		int n = ar.length;
		for(int i = 1; i < n+1;i++){
			if(i == n){
				Pair e = new Pair(ar[i-1],(double)(i)/n);
				cdfArray.add(e);
				break;
			}
			if(ar[i] > ar[i-1]){
				Pair e = new Pair(ar[i-1],(double)(i)/n);
				cdfArray.add(e);
			}
		}
		return cdfArray;
	}
	
	public static class Pair{
		int point;
		double cdf;
		public Pair(int p, double c){
			this.point = p;
			this.cdf = c;
		}
		public String toString() {
			return "("+point+", "+cdf+")";
		}
	}
	
	public static void main(String[] args){
		int[] a = {2,2,3,1,3,3,5,8,3,6};
		ArrayList<Pair> aa = cdf_array(a);
		System.out.println(aa);
	}
	
}
