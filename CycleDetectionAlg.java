import java.lang.Math;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
public class CycleDetectionAlg { //CycleDetectionAlg subclass inherits the attributes and methods from Test class (superclass)

    public class Node extends IteratedFunction{ //create a node class with two attributes data and next
        int data;
        Node next; //pointer to the next node

        public Node(int data){ //constructor
            this.data = data;
            this.next = null;
        }

        @Override
        public IteratedFunction next() {
            return next;
        }

        @Override
        public boolean equivalent(IteratedFunction node) {
            return this==node;
        }

        @Override
        public int value() {
            return data;
        }
    }

    //represents the head and tail of linked list    
    public Node head = null;    
    public Node tail = null;    

    //add element
    public void addNode(int data) {    
        Node newNode = new Node(data);  
        // newNode.next = head;
        // head = newNode;
            if (head==null){
                head = newNode;
                tail= newNode;
            }
            else{    
                tail.next = newNode; //newNode will be added after tail such that tail's next will point to newNode
                tail = newNode;  //newNode will become new tail of the list 
            }   
         
    }    
   
    //count total # of nodes
    public int countNodes() {    
        int count = 0;       
        Node current = head;    
            
        while(current != null) {       
            count++;    
            current = current.next;    
        }    
        return count;    
    }    

    //display content of nodes
    public void display() {       
        Node current = head;    //current node point to head
        System.out.println("Nodes of linked list: ");
        if(head == null) {    
            System.out.println("List is empty");    
            return;    
        }        
        while(current != null) {   
            System.out.print(current.data + " ");    
            current = current.next;    
        }    
        System.out.println();    
    } 

    public static CycleDetectionAlg makeList(int l, int k){
        CycleDetectionAlg list = new CycleDetectionAlg();
        for (int i=0; i<= l; i++){
            list.addNode(i);   
        }
        Node start = list.tail;
        for(int j=0; j<k-1; j++){
            list.addNode(j);
        }
        list.tail.next = start;
        return list;
    }

    public static CycleDetectionAlg makeList(int l){
        CycleDetectionAlg list = new CycleDetectionAlg();
        for (int i=0; i<= l; i++){
            list.addNode(i);   
        }
        return list;
    }
    public static void main(String[] args) { 
    factor(10);
    }

    public static void factor(int N){
        //right now our PollardRho implementation uses 2 as the starting value
        //for every possible pair between 2-1024
        //use Floyd
        try{
            FileWriter Write = new FileWriter("C:\\users\\gabes\\Desktop\\CS699_Cycle_Detection_Alg\\Data.txt");
            int[] returning_array = new int[3];
            int[] arr = new int[N];
            int number = 2;
            for(int i=0; i<arr.length; i++){
                arr[i] = number;
                number++;
            }
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    int a = arr[j];
                    int b = arr[i];
                    PollardRho function = new PollardRho(a, b);
                    int[] pairs = IteratedFunction.Floyd(function);
                    int f = PollardRho.getGCD(Math.abs(pairs[3]-pairs[4]), b);
                    returning_array[0] = a;
                    returning_array[1] = b;
                    returning_array[2] = f;
                    Write.write("a: " + a + " b: " + b + " factor: " + f + System.getProperty("line.separator"));
                }
            }
            Write.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
}
