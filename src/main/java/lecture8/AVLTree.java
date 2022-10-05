package lecture8;

class Node {
    private int key;
    private int height = 1;
    private Node left;
    private Node right;

    public Node(){}

    public Node(int key, Node left, Node right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }
    public Node(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}

public class AVLTree{
    private Node root;

    public AVLTree insert(int key) {
        this.root = insert(key, root);
        return this;
    }

    private Node insert(int key, Node node) {
        if (node == null) {
            return new Node(key);
        }
        if (key < node.getKey()) {
            node.setLeft(insert(key, node.getLeft()));
        } else if (key > node.getKey()) {
            node.setRight(insert(key, node.getRight()));
        } else {
            return node; //не добавляет копию
        }
        updateHeight(node);
        return applyRotation(node);
    }

    public void delete(int key){
        root = delete(key, root);
    }
    private Node delete(int key, Node node) {
        if (node == null) {
            return null;
        }
        if (key < node.getKey()) {
            node.setLeft(delete(key, node.getLeft()));
        } else if (key > node.getKey()) {
            node.setRight(delete(key, node.getRight()));
        } else {
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }
            node.setKey(max(node.getLeft()));
            node.setLeft(delete(node.getKey(), node.getLeft()));
        }
        updateHeight(node);
        return applyRotation(node);
    }

    private int max(Node node) {
        if (node.getRight() != null) {
            return max(node.getRight());
        }
        return node.getKey();
    }

    private int min(Node node) {
        if (node.getLeft() != null) {
            return min(node.getLeft());
        }
        return node.getKey();
    }

    private void updateHeight(Node node) {
        int maxHeight = Math.max(height(node.getLeft()), height(node.getRight()));
        node.setHeight(maxHeight + 1);
    }

    private int height(Node node) {
        return node != null ? node.getHeight() : 0;
    }

    private Node applyRotation(Node node) {
        int balance = balance(node);
        if (balance > 1) {
            if (balance(node.getLeft()) < 0) {
                node.setLeft(rotateLeft(node.getLeft()));
            }
            return rotateRight(node);
        }
        if (balance < -1) {
            if (balance(node.getRight()) > 0) {
                node.setRight(rotateRight(node.getRight()));
            }
            return rotateLeft(node);
        }
        return node;
    }

    private Node rotateLeft(Node node) {
        Node rightNode = node.getRight();
        Node centerNode = rightNode.getLeft();
        rightNode.setLeft(node);
        node.setRight(centerNode);
        updateHeight(node);
        updateHeight(rightNode);
        return rightNode;
    }

    private Node rotateRight(Node node) {
        Node leftNode = node.getLeft();
        Node centerNode = leftNode.getRight();
        leftNode.setRight(node);
        node.setLeft(centerNode);
        updateHeight(node);
        updateHeight(leftNode);
        return leftNode;
    }

    private int balance(Node node) {
        return node != null ? height(node.getLeft()) - height(node.getRight()) : 0;
    }

    public void treverse(){
        traverseInOrder(root);
    }
    private void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.getLeft());
            System.out.println(node.getKey());
            traverseInOrder(node.getRight());
        }
    }
}
