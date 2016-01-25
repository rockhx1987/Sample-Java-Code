package edu.princeton.cs.algs4;

// remove consecutive duplicate letters
public class removeConsecDup {
	public static String remove(String s){
		StringBuilder sb = new StringBuilder();
		sb.append(s.charAt(0));
		for(int i = 1; i < s.length(); i++){
			while(s.charAt(i) == s.charAt(i-1)){
				i++;
				if(i == s.length()){
					return sb.toString();
				}
			}
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}
	public static void main(String[] args){
		String a = "bblommmbergg";
		String b = remove(a);
		System.out.print(b);
	}
}
