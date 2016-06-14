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
        heapifyUp(); 
        

    }
    
    /*INPUT i: index of child*/
    private int getParentIndx(final int i) {
        return ((int)Math.floor((i / 2)));//result is automatically truncated to int
    }
    
    /*consider maknig this a public method,
    so that the user can insert a bunch of keys
    and only have to heapify once*/
    private void heapifyUp() {//this function could be written in a recursive way
        int childIndx = end;//start heapify process at last inserted node
        int newKey = heap[childIndx];//this is value that was just added
        
        while(childIndx > FRONT && newKey > heap[getParentIndx(childIndx)]) {//not sure if the logic is correct in this line, I think depending on > or < it changes whether it's a max, min or some other type of heap
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
            if(2 * i + 1 <= end)//must check as parent may have no right child
                    System.out.print(" Right child: " + heap[2 * i  + 1]);
            
            System.out.println();
        }
        assert isHeap() : "not a heap";
    }
    
    /*returns true iff array has heap property*/
    /*consider making this a static method so any array can be tested*/
    private boolean isHeap() {
        for(int i = FRONT; i <= Math.ceil(end / 2); i++) {
            if(2*i+1 <= end && heap[i] < heap[2*i+1])
                    return false;
            else if(2*i <= end && heap[i] < heap[2*i])
                    return false;
        }
        
        return true;
			
    }

    
    
    
    private final int[] heap;//array where keys(values) are stored
    private int end;//pointer to last used element
    private final int MAX_SIZE;//space allocated for heap
    private final int FRONT = 1;//first key is inserted at index 1, not 0
}