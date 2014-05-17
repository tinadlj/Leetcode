/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int sumNumbers(TreeNode root) {
        if (root==null)
            return 0;
        
    return sum(0,root);
    }
    public int sum(int last, TreeNode root){
        int result=0;
        result=last*10+root.val;
        if(root.left==null&&root.right==null)
            return result;
        int left=0;
        int right=0;
        
        if(root.left!=null)
            left=sum(result,root.left);
        if(root.right!=null)
            right=sum(result,root.right);
        
        return left+right;
    }
    
}