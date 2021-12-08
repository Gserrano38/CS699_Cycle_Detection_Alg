import java.lang.Math;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;
public class LinkedList { 

    public class Node extends Cycle_Detection_Algs{ //create a node class with two attributes data and next
        long data;
        Node next; //pointer to the next node

        public Node(long data){ //constructor
            this.data = data;
            this.next = null;
        }

        @Override
        public Cycle_Detection_Algs next() {
            return next;
        }

        @Override
        public boolean equivalent(Cycle_Detection_Algs node) {
            return this==node;
        }

        @Override
        public long value() {
            return data;
        }
    }

    //represents the head and tail of linked list    
    public Node head = null;    
    public Node tail = null;    

    //add element
    public void addNode(long data) {    
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
    public long countNodes() {    
        long count = 0;       
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

    public static LinkedList makeList(long l, long k){
        LinkedList list = new LinkedList();
        for (long i=0; i<= l; i++){
            list.addNode(i);   
        }
        Node start = list.tail;
        for(long j=0; j<k-1; j++){
            list.addNode(j);
        }
        list.tail.next = start;
        return list;
    }

    public static LinkedList makeList(long l){
        LinkedList list = new LinkedList();
        for (long i=0; i<= l; i++){
            list.addNode(i);   
        }
        return list;
    }
    public static void main(String[] args) { 
        int[] p = LFSR.find2rule(6);
        for(int i=0; i<p.length; i++){
            System.out.print(p[i] + " ");   
        }
    }

    public static void gatherData(long N){
        try{
            FileWriter Write = new FileWriter("C:\\users\\gabes\\Desktop\\CS699_Cycle_Detection_Alg\\Data.txt");
            long[] arr = new long[(int) N];
            long number = 2;
            for(long i=0; i<arr.length; i++){
                arr[(int) i] = number;
                number++;
            }
            for(long i=0; i<N; i++){
                for(long j=0; j<N; j++){
                    long a = arr[(int) j];
                    long b = arr[(int) i];
                    LinkedList function = makeList(a, b);
                    long[] Floyd_array = Cycle_Detection_Algs.Floyd(function.head);
                    long[] Brent_array = Cycle_Detection_Algs.Brent(function.head);
                    long Floyd_indicator = Floyd_array[2];
                    long Brent_indicator = Brent_array[2];
                    if(Floyd_indicator < Brent_indicator){
                        System.out.println(a + " " + b );
                    }
                    Write.write(a + " " + b + " " + Floyd_indicator + " " + Brent_indicator + System.getProperty("line.separator"));
                }
            }
            Write.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }

    public static long factor(PollardRho obj){
        long[] Cycle = Cycle_Detection_Algs.Brent(obj);
        long first_PR_input = obj.getA();
        long gcd = PollardRho.getGCD(Math.abs(Cycle[3]-Cycle[4]), obj.getN());
        if (gcd == obj.getN() || gcd == 1){
            first_PR_input++;
            long var = obj.setA(first_PR_input);
            PollardRho x = new PollardRho(var, obj.getN());
            long[] values = Cycle_Detection_Algs.Brent(x);
            gcd = x.getGCD(Math.abs(values[3]-values[4]), obj.getN());
        }
        return gcd;
    }
}
