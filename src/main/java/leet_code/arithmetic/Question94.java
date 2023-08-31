package leet_code.arithmetic;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树 | 遍历
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 *
 */
public class Question94 {

    public List<Integer> inorderTraversal(MyTreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        inorderTree(root,res);
        return res ;
    }

    // 递归方法
    private void inorderTree(MyTreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorderTree(root.left,res);
        res.add(root.val);
        inorderTree(root.right,res);
    }

    /**
     * 颜色标记法
     *
     * 核心思想如下：
     *
     * 使用颜色标记节点的状态，新节点为白色，已访问的节点为灰色。
     * 如果遇到的节点为白色，则将其标记为灰色，然后将其右子节点、自身、左子节点依次入栈。
     * 如果遇到的节点为灰色，则将节点的值输出。
     *
     */
    public List<Integer> inorderTraversalWithColor(MyTreeNode root) {
        if (root == null) return new ArrayList<>() ;
        // 返回结果
        List<Integer> res = new ArrayList<Integer>();
        // 构建栈
        Stack<ColorNode> stack = new Stack<>();
        // 先将根节点设置为白色，并入栈
        stack.push(new ColorNode(root,"WHITE"));
        while(!stack.isEmpty()){
            ColorNode pop = stack.pop();
            if (pop.color.equals("WHITE")){
                // 如遇白色节点，将其右节点、自己、左节点依次入栈
                if(pop.node.right != null) stack.push(new ColorNode(pop.node.right,"WHITE"));
                // 访问过的节点标记为灰色
                stack.push(new ColorNode(pop.node,"GRAY"));
                if(pop.node.left != null)stack.push(new ColorNode(pop.node.left,"WHITE"));
            }else{
                // 如果遇到的节点颜色为灰色，则将其值输出
                res.add(pop.node.val);
            }
        }
        return res ;
    }

    class ColorNode {
        MyTreeNode node;
        String color;

        public ColorNode(MyTreeNode node,String color){
            this.node = node;
            this.color = color;
        }
    }
    // 栈的出入规则为 先入后出。
    // 二叉树的三种遍历：前序、中序、后序
    // 前序：中左右；对应的入栈顺序则应该是 右左中
    // 中序：左中右；对应的入栈顺序则应该是 右中左
    // 后序：左右中；对应的入栈顺序则应该是 中右左

}
