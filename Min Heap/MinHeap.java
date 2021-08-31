public class MinHeap {

    //member variables
    private int[] Heap;
    private int size;
    private int maxSize;

    //initializing front as static with zero
    private static final int FRONT = 0;

    //constructor
    public MinHeap(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;

        //creating the array
        this.Heap = new int[this.maxSize];
    }

    //returning the positions of the parent for the node currently at pos
    private int parent(int pos) {
        return (pos-1)/2;
    }

    //returning the position of the left child for the node currently at pos
    private int leftChild(int pos) {
        return (2*pos)+1;
    }

    //returning the position of the right child for the node currently at pos
    private int rightChild(int pos) {
        return (2*pos)+2;
    }

    //check whether the passed node is a leaf
    private boolean isLeaf(int pos) {
        if (pos > (size/2)-1 && pos <= size-1) {
            return true;
        }
        return false;
    }

    //to swap 2 nodes of the heap
    private void swap(int fpos, int spos) {
        int temp;
        temp = this.Heap[fpos];
        this.Heap[fpos] = this.Heap[spos];
        this.Heap[spos] = temp;
    }

    //to heapify the node at pos
    private void minHeapify(int pos) {
        //if the node is a non-leaf node and greater than any of its child
        if(!isLeaf(pos)) {
            if(Heap[pos] > Heap[leftChild(pos)] || Heap[pos] > Heap[rightChild(pos)]) {
                //swap with the left child and heapify the left child
                if (Heap[leftChild(pos)] < Heap[rightChild(pos)]) {
                    swap(pos, leftChild(pos));
                    minHeapify(leftChild(pos)); //minHeapify for the left child
                }

                //swap with the right child and heapify the right child
                else {
                    swap(pos, rightChild(pos));
                    minHeapify(rightChild(pos));    //minHeapify for the right child
                }
            }
        }

    }

    //to insert a node into the heap
    public void insert(int element) {
        //check the size with the maximum size of the heap
        if (size >= maxSize) {
            return;
        }

        //insert the element to the heap
        Heap[size] = element;
        int current = size;

        //if the current node is less than the parent node then swap them
        while (Heap[current] < Heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }

        size++;
    }

    //to remove and return the minimum element from the heap
    public int remove() {
        int popped = Heap[FRONT];
        Heap[FRONT] = Heap[size-1]; //root is replaced by the last node of the heap
        minHeapify(FRONT);  //do minHeapify

        size--;
        return popped;  //here the minimum value is returned
    }

    //to print the content of the heap
    public void print() {
        for (int i=0; i<=(size/2)-1;i++) {
            //here parent ant its left and right children are printed
            System.out.print(" PARENT : "+Heap[i]+" LEFT CHILD : "+Heap[2*i+1]+" RIGHT CHILD : "+Heap[2*i+2]);

            //a new line
            System.out.println();

        }
    }

    public static void main(String[] args) {

        System.out.println("The min heap is ");

        //creating the minHeap
        MinHeap minHeap = new MinHeap(15);

        //inserting elements to the minHeap
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(17);
        minHeap.insert(10);
        minHeap.insert(84);
        minHeap.insert(19);
        minHeap.insert(6);
        minHeap.insert(22);
        minHeap.insert(9);

        //to print the minHeap
        minHeap.print();

        //removing minimum value from the above heap anf printing it
        System.out.println("The min value is "+minHeap.remove());
    }














}
