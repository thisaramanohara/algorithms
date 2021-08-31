public class AVLTree {

    public class Node {
        int key;
        int height;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
        }
    }

    private Node root;

    //to get the leftmost leaf
    private Node mostLeftChild(Node node) {
        Node current = node;
        //to find the left most leaf
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    //to get the height
    public int height(Node node) {
        return node==null ? -1 : node.height;
    }

    //to update the height of a node
    private void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    //get the balance
    public int getBalance(Node node) {
        return node==null ? 0 : height(node.right) - height(node.left);
    }

    //function to rotate right
    public Node rotateRight(Node y) {
        Node x = y.left;
        Node z = x.right;
        //rotating
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);

        //returning the cuurent root
        return x;
    }

    //funtion to rotate left
    public Node rotateLeft(Node y) {
        Node x = y.right;
        Node z = x.left;
        //rotating
        x.left = y;
        y.right = z;
        updateHeight(y);
        updateHeight(x);


        //return the current root
        return x;
    }

    //to keep the AVLTree balanced after any changes
    public Node rebalance(Node z) {
        updateHeight(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (height(z.right.right) > height(z.right.left)) {
                z = rotateLeft(z);
            } else {
                z.right = rotateRight(z.right);
                z = rotateLeft(z);
            }
        } else if (balance < -1) {
            if (height(z.left.left) > height(z.left.right)) {
                z = rotateRight(z);
            } else {
                z.left = rotateLeft(z.left);
                z = rotateRight(z);
            }
        }
        return z;
    }

    //to insert a node
    public Node insert(Node node, int key) {
        if (node==null) {
            return new Node(key);
        } else if (node.key > key) {
            node.left = insert(node.left, key);
        } else if (node.key < key) {
            node.right = insert(node.right, key);
        } else {
            //if it exists
            throw new RuntimeException("Duplicate key !!!");
        }
        return rebalance(node);
    }

    //to delete a node
    public Node delete(Node node, int key) {
        if(node==null) {
            return node;
        } else if (node.key > key) {
            node.left = delete(node.left, key);
        } else if (node.key < key) {
            node.right = delete(node.right, key);
        } else {
            //if the key is equal to node's key
            if (node.left == null || node.right == null) {
                node = (node.left == null) ? node.right : node.left;
            } else {
                Node mostLeftChild = mostLeftChild(node.right);
                node.key = mostLeftChild.key;
                node.right = delete(node.right, node.key);
            }
        }
        if (node != null) {
            node = rebalance(node);
        }
        return node;
    }

    //to find a node
    public Node find(int key) {
        Node current = root;
        while (current != null) {
            if (current.key == key) {
                break;
            }
            current = current.key < key ? current.right : current.left;
        }
        return current;
    }

    //pre-order traversal
    public void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    //in-order traversal
    public void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    //post-order traversal
    public void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.key + " ");
        }
    }

    public static void main(String[] args) {

        //create a AVLtree
        AVLTree tree = new AVLTree();

        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 40);
        tree.root = tree.insert(tree.root, 50);
        tree.root = tree.insert(tree.root, 25);

        System.out.println("pre-order traversal is : ");
        tree.preOrder(tree.root);

        System.out.println();

        System.out.println("in-order traversal is : ");
        tree.inOrder(tree.root);

        System.out.println();

        System.out.println("post-order traversal is : ");
        tree.postOrder(tree.root);

    }

}
