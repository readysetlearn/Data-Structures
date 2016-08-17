package data.structures;

import java.util.NoSuchElementException;


public class BinaryMaxHeap {
    
    public BinaryMaxHeap(final int MAX_SIZE) {
        heap = new int[MAX_SIZE + FRONT];//extra element at front simplifies calculations (http://stackoverflow.com/questions/22900388/why-in-a-heap-implemented-by-array-the-index-0-is-left-unused)
        end = 0;
        this.MAX_SIZE = MAX_SIZE;
    }
    
    public void insert(final int key) {//in heaps, the value being stored is called the "key"
        if(end == MAX_SIZE) {
            throw new NoSuchElementException("heap is full");
        }
        
        heap[++end] = key;
        heapifyUp(end); 
        

    }
    
    /*INPUT i: index of child*/
    private int getParentIndx(final int i) {
        return (i / 2);
    }
    
    /*INPUT i: index of parent*/
    private int getLeftChildIndex(final int i) {
        return (i * 2);
    }
    
    /*INPUT i: index of parent*/
    private int getRightChildIndex(final int i) {
        return (i * 2 + 1);
    }
    
    /*return true if node at position is leaf*/
    private boolean isLeaf(int position) {
        return ((2 * position) > end);
    }
    
    /*swap two elements in heap*/
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    
    /*consider maknig this a public method,
    so that the user can insert a bunch of keys
    and only have to heapify once*/
    private void heapifyUp(int position) {//this function could be written in a recursive way
        int childIndx = position;
        int newKey = heap[childIndx];
        
        while(childIndx > FRONT && newKey > heap[getParentIndx(childIndx)]) {
            heap[childIndx] = heap[getParentIndx(childIndx)];
            childIndx = getParentIndx(childIndx);
        }
        
        heap[childIndx] = newKey;
    }
    
    /*display contents of heap, including unused first element*/
    public void dump() {
        for(int i = 0; i <= end; i++) {
            System.out.println("index: " +i+ " key: "+heap[i]);
         }
    }
    
    public void pprint() {
        for (int i = FRONT; i <= (int)Math.floor(end / 2); i++) {
            System.out.print("Parent: " + heap[i] + " Left child: " + heap[2 * i]);
            if(2 * i + 1 <= end) {//must check as parent may have no right child
                    System.out.print(" Right child: " + heap[2 * i  + 1]);
            }
            System.out.println();
        }
        assert isHeap() : "not a heap";
    }
    
    /*returns number of nodes*/
    public int getSize() {
        return end;
    }
    
    /*returns true iff array has heap property*/
    /*consider making this a static method so any array can be tested*/
    public boolean isHeap() {
        for(int i = FRONT; i <= end / 2; i++) {            
            if (2 * i + 1 <= end && heap[i] < heap[2 * i + 1] || heap[i] < heap[2 * i]) {
                return false;
            }
        }
        
        return true;
			
    }

    /*remove and return the max value (the root)*/
    public int removeMax() {
        final int MAX = heap[FRONT];
        heap[FRONT] = heap[end--];
        heapifyDown(FRONT);

        return MAX;
    }
    
    
    /*compares node at position with its children and
    swaps it with the largest child if its larger than it*/
    private void heapifyDown(int position) {
        /*pesudeocode:
            while(position has leaf) {
                choose largest leaf
                if bigger than swap
                else break
            }
        */
        
        while(!isLeaf(position)) {
            //get largest of children
            int largestChildIndex;
            if(heap[getLeftChildIndex(position)] > heap[getRightChildIndex(position)]) {
                largestChildIndex = getLeftChildIndex(position);
            } else {
                largestChildIndex = getRightChildIndex(position);
            }
            //check if child is larger than parent
            if(heap[position] < heap[largestChildIndex]) {
                //if it is, then swap
                swap(position, largestChildIndex);
                position = largestChildIndex;
            } else {
                //if it's not, heap property is satisfied
                break;
            }
        }
    }
    
    /*return the heap as array*/
    public int[] getHeapArray() {
        return heap;
    }
    
    
    private final int[] heap;//array where keys(values) are stored
    private int end;//pointer to last used element
    private final int MAX_SIZE;//space allocated for heap
    private final int FRONT = 1;//first key is inserted at index 1, not 0
}
