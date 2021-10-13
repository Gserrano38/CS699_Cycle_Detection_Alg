
/**
 * Floyd
 * next steps: create method for regular loop too
 * read and implement Floyd's alg
 */
public class CycleDetectionAlg {
    
    public class Node{ //create a node class with two attributes data and next
        int data;
        Node next; //pointer to the next node

        public Node(int data){ //constructor
            this.data = data;
            this.next = null;
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
        if(head == null) {    
            System.out.println("List is empty");    
            return;    
        }    
        System.out.println("Nodes of linked list: ");    
        while(current != null) {   
            System.out.print(current.data + " ");    
            current = current.next;    
        }    
        System.out.println();    
    } 

    public static void main(String[] args) {    
            
        CycleDetectionAlg List = new CycleDetectionAlg();    
            
        //Counts the nodes present in the given list    
        //System.out.println("Count of nodes present in the list: " + List.countNodes()); 
        //System.out.println("Applying Floyd's Alg: " + Floyd(head));
        
        CycleDetectionAlg l = makeList(4,3);
        //l.display();
        int[] pair = Floyd(l);
        System.out.println("Floyd's alg: " + pair[0] + pair[1]);
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

    //Floyd's Cycle Detection Algorithm
    public static int[] Floyd(CycleDetectionAlg head){
        Node tortoise = head.head;
        Node hare = head.head;
        int k=1;
        int l=0;
        int[] ordered_pair = new int[2];
        while (tortoise != null && hare != null && hare.next != null){
            tortoise = tortoise.next;
            hare = hare.next.next;
            if(tortoise == hare){
                System.out.println(tortoise.data + " " + hare.data);
                tortoise = tortoise.next;
                while(tortoise != hare){
                    k++;
                    tortoise = tortoise.next;
                }
                tortoise = head.head;
                while(tortoise != hare){
                    l++;
                    tortoise = tortoise.next;
                    hare=hare.next;
                    
                }
                ordered_pair[0]= l;
                ordered_pair[1]=k;
                return ordered_pair;
            }
        }
        return new int[]{-1,-1};
    }

    //Brent's Cycle Detection Algorithm
    public static int[] Brent(CycleDetectionAlg head){
        Node p1 = head.head;
        Node p2 = head.next;
        int[] ordered_pair = new int[2];
        int length = 1;
        int power = 1;
        int k=0;
        while (p1 != p2 && p2 != null){
            if (length == power){
                power = power*2; //move p2 in powers of 2
                p1 = p2; //reset p1 to previous position of p2
                length = 0; //reset length
            }
            p2 = p2.next;
            length++;
        }
        p1 = head.head;
        p2 = head.head;
        for(int i=1; i<=length;i++){
            p2=p2.next;
        }
        while(p1 != p2){
            k++;
            p1 = p1.next;
            p2 = p2.next;
        }
        ordered_pair[0]=length;
        ordered_pair[1]=k;
        return ordered_pair;
    }
}