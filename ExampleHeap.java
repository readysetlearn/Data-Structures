package data.structures;


public class ExampleHeap {


    public static void main(String[] args) {
        BinaryMaxHeap myHeap = new BinaryMaxHeap(8);
        
        myHeap.insert(3);
        myHeap.insert(4);
        myHeap.insert(5);
        myHeap.insert(1);
        myHeap.insert(10);
        myHeap.insert(53);
        myHeap.insert(11);
        myHeap.insert(2);

        


        myHeap.pprint();
    }
    
}
