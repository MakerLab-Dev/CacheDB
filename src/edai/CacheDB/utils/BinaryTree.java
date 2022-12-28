package edai.CacheDB.utils;

public class BinaryTree<T extends Comparable<T>> {
    private TreeNode<T> root;

    public BinaryTree() {
        this.root = null;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public void insert(T value) {
        TreeNode<T> newNode = new TreeNode<>(value);
        if (root == null) {
            this.root = newNode;
        } else {
            TreeNode<T> currentNode = root;
            while (true) {
                int comparison = value.compareTo(currentNode.getValue());
                if (comparison == 0) {
                    currentNode.setValue(value);
                    break;
                }
                if (comparison < 0) {
                    if (currentNode.getLeft() == null) {
                        currentNode.setLeft(newNode);
                        break;
                    } else {
                        currentNode = currentNode.getLeft();
                    }
                } else {
                    if (currentNode.getRight() == null) {
                        currentNode.setRight(newNode);
                        break;
                    } else {
                        currentNode = currentNode.getRight();
                    }
                }
            }
        }
    }

    public TreeNode<T> search(T value) {
        TreeNode<T> currentNode = root;
        while (currentNode != null) {
            if (value.compareTo(currentNode.getValue()) < 0) {
                currentNode = currentNode.getLeft();
            } else if (value.compareTo(currentNode.getValue()) > 0) {
                currentNode = currentNode.getRight();
            } else {
                return currentNode;
            }
        }
        return null;
    }

    public void remove(T value) {
        TreeNode<T> toRemove = this.search(value);
        if (toRemove != null) {
            // If the node to remove has two children, replace it with its successor
            if (toRemove.getLeft() != null && toRemove.getRight() != null) {
                TreeNode<T> successor = this.getSuccessor(toRemove);
                this.remove(successor.getValue());
                toRemove.setValue(successor.getValue());
            } else {
                // If the node to remove has one or zero children, replace it with its child
                TreeNode<T> child = toRemove.getLeft() != null ? toRemove.getLeft() : toRemove.getRight();
                if (toRemove == root) {
                    root = child;
                } else {
                    TreeNode<T> parent = this.getParent(toRemove);
                    if (parent.getLeft() == toRemove) {
                        parent.setLeft(child);
                    } else {
                        parent.setRight(child);
                    }
                }
            }
        }
    }

    public void clear() { this.root = null; }

    private TreeNode<T> getParent(TreeNode<T> node) {
        return this.getParent(node, root);
    }

    private TreeNode<T> getParent(TreeNode<T> node, TreeNode<T> currentNode) {
        if (node == root) {
            return null;
        } else if (currentNode.getLeft() != node && currentNode.getRight() != node) {
            return node.getValue().compareTo(currentNode.getValue()) < 0 ? this.getParent(node, currentNode.getLeft()) : this.getParent(node, currentNode.getRight());
        } else {
            return currentNode;
        }
    }

    private TreeNode<T> getSuccessor(TreeNode<T> node) {
        TreeNode<T> currentNode = node.getRight();
        while (currentNode.getLeft() != null) {
            currentNode = currentNode.getLeft();
        }
        return currentNode;
    }

    public int size() {
        return this.size(this.root);
    }

    private int size(TreeNode<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + this.size(node.getLeft()) + this.size(node.getRight());
        }
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public Object[] toArray() {
        Object[] array = new Object[this.size()];
        this.fillArrayInOrder(this.root, array, 0);
        return array;
    }

    private int fillArrayInOrder(TreeNode<T> node, Object[] array, int index) {
        if (node != null) {
            index = this.fillArrayInOrder(node.getLeft(), array, index);
            array[index++] = node.getValue();
            index = this.fillArrayInOrder(node.getRight(), array, index);
        }
        return index;
    }
}
