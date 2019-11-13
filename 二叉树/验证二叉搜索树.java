package com.yizijun.utils;


import java.util.Stack;

/**
 * description
 *
 * @author yizijun
 * @version 1.0.0
 * @since 2019-10-26
 */
public class Algorithm {


    /**
     * 通过中序遍历验证二叉搜索树
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {

        //这样处理不是很优雅
        long pre = Long.MIN_VALUE;

        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {

            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            TreeNode treeNode = stack.pop();

            //如果当前值小于等于前一个值就不满足二叉搜索树
            if (treeNode.val <= pre) {
                return false;
            }

            pre = treeNode.val;

            root = treeNode.right;

        }

        return true;
    }

    /**
     * 验证二叉搜索树
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValid(root, null, null);
    }

    /**
     * 不能简单的通过当前节点和其左右两个节点来判断是否满足二叉搜索树(current.val > current.left.val && current.right.val > current.val)
     *
     * 需要通过子树的上界和下界来判断
     *
     * @param node
     * @param upper
     * @param lower
     * @return
     */
    private boolean isValid(TreeNode node, Integer upper, Integer lower) {
        if (node == null) {
            return true;
        }

        //左子树大于等于上界直接返回
        if (upper != null && node.val >= upper) {
            return false;
        }

        //右子树小于等于下界直接返回
        if (lower != null && node.val <= lower) {
            return false;
        }

        //继续判断左子树
        if (!isValid(node.left, node.val, lower)) {
            return false;
        }

        //继续判断右子树
        if (!isValid(node.right, upper, node.val)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();
    }
}
