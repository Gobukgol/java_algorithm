package main.java.com.min.leetcode;

public class InsertIntoBST {


    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            root = new TreeNode(val);
        } else {
            addVal(root, val);
        }

        return root;
    }

    private void addVal(TreeNode node, int val) {
        if (node.val > val) {
            if (node.left != null) {
                addVal(node.left, val);
                return;
            }
            node.left = new TreeNode(val);
        } else {
            if (node.right != null) {
                addVal(node.right, val);
                return;
            }
            node.right = new TreeNode(val);
        }
    }

    private class TreeNode {
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
}
