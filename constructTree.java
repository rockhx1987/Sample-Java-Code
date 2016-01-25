package edu.princeton.cs.algs4;

import java.util.LinkedList;

// Construct a tree from preorder and inorder traversals, and print it out in bottom up level order 
public class constructTree {
	public static TreeNode constructT(int[] pre, int[] in){
		int i = 0, j = 0;
		TreeNode root = new TreeNode(Integer.MIN_VALUE);
		TreeNode node = null;
		TreeNode cur = root;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while(j < in.length){
			 if (stack.peek().val == in[j]){
	                node = stack.pop(); 
	                j++; 
	            }
	            else if (node!=null){
	                cur = new TreeNode(pre[i]); 
	                node.right = cur;
	                node=null; 
	                stack.push(cur); 
	                i++; 
	            }
	            else {
	                cur = new TreeNode(pre[i]); 
	                stack.peek().left = cur; 
	                stack.push(cur);
	                i++; 
	            }
		}
		return root.left;
	}
	
	static void LevelOrderBottom(TreeNode root) 
    {
       
      if(root == null) return;
      else{
          LinkedList<LinkedList<Integer>> res = new LinkedList<LinkedList<Integer>>();
          LinkedList<TreeNode> q = new LinkedList<TreeNode>();
          LinkedList<Integer> sublist = new LinkedList<Integer>();
          int curlevel = 1;
          int nxtlevel = 0;
          q.add(root); 
          while(!q.isEmpty()){ 
        	  TreeNode cur = q.poll();
              sublist.add(cur.val);
              //System.out.print(cur.data + " ");
              curlevel--;
              if(cur.left != null){
                  q.add(cur.left);
                  nxtlevel++;
              }
              if(cur.right != null){
                  q.add(cur.right);
                  nxtlevel++;
              }
              if(curlevel == 0){
                  //System.out.print("\n");
                  curlevel = nxtlevel;
                  nxtlevel = 0;
                  res.add(0,sublist);
                  sublist = new LinkedList<Integer>(); 
              }
          }
          for(int i = res.size()-1; i>=0; i--){
              System.out.print(res.poll());
          }
      }
    }
	
	public static void main(String[] args){
		int[] inorder = {2,3,4,5,6,7,8};
		int[] preorder = {5,3,2,4,7,6,8};
		TreeNode rt = constructT(preorder, inorder);
		LevelOrderBottom(rt);
	}
}
