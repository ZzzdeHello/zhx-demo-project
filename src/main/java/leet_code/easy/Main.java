package leet_code.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zzzde
 * @Date 2023/10/23
 */
public class Main {

    /**
     * 获取二叉树的中序遍历的结果，放入List集合中
     *
     * @param root 二叉树根节点
     * @return
     */
    public List<Integer> getInorderResult(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        inorder(root, res);
        return res;
    }

    /**
     * 中序遍历
     */
    private void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }


}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}