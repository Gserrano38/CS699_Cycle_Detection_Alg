
public class CycleDetectionAlg extends Test{ //CycleDetectionAlg subclass inherits the attributes and methods from Test class (superclass)

    public class Node implements IteratedFunction{ //create a node class with two attributes data and next
        int data;
        Node next; //pointer to the next node

        public Node(int data){ //constructor
            this.data = data;
            this.next = null;
        }

        public IteratedFunction next(){ //interface method
            return next;
        }

        public boolean equivalent(IteratedFunction node){ //interface method
            if(node instanceof Node && ((Node)node).data == data){
                return true;
            } else{
                return false;
            }
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

    public static void main(String[] args) {  
        gatherData(100, 100);
        gatherData(100, 150);
        gatherData(100, 200);
        gatherData(100, 250);
        gatherData(100, 300);
        System.out.println("---------------------------------------");
        gatherData(100, 100);
        gatherData(150, 100);
        gatherData(200, 100);
        gatherData(250, 100);
        gatherData(300, 100);
        System.out.println("---------------------------------------");
        gatherData(50);
        gatherData(100);
        gatherData(150);
        gatherData(200);
        gatherData(250);
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
    public static int[] Floyd1(CycleDetectionAlg head){
        Node tortoise = head.head;
        Node hare = head.head;
        int lambda=1; //length of cycle
        int mu=0; //index of the first element in cycle (i.e. tail of cycle)
        int indicator = 0;
        int[] ordered_pair = new int[3];
        while (tortoise != null && hare != null && hare.next != null){ //phase one: find cycle
            tortoise = tortoise.next;
            indicator++;
            hare = hare.next.next;
            indicator++;
            indicator++;
            if(tortoise == hare){
                tortoise = tortoise.next;
                indicator++;
                while(tortoise != hare){ //phase two: find lambda
                    lambda++;
                    tortoise = tortoise.next;
                    indicator++;
                }
                tortoise = head.head;
                indicator++;
                while(tortoise != hare){ //phase three: find mu
                    mu++;
                    tortoise = tortoise.next;
                    indicator++;
                    hare=hare.next;
                    indicator++;
                    
                }
                ordered_pair[0]= mu;
                ordered_pair[1]=lambda;
                ordered_pair[2]=indicator;
                return ordered_pair;
            }
        }
        return new int[]{-1,-1, indicator};
    }

    

    //Brent's Cycle Detection Algorithm
    public static int[] Brent1(CycleDetectionAlg head){
        int ind=0;
        Node p1 = head.head;
        Node p2 = head.head.next;
        ind++;
        int[] ordered_pair = new int[3];
        int lambda = 1; //length of cycle 
        int power = 1;
        int mu=0;        //index of first element in cycle
        while (p1 != p2 && p2 != null){ //phase one: find cycle and lambda
            if (lambda == power){
                power = power*2; 
                p1 = p2; //reset p1 to previous position of p2
                lambda = 0;
            }
            p2 = p2.next; //only hare moves
            ind++;
            lambda++;
        }
        if(p2 == null){
            return new int[]{-1, -1, ind};
        }
        p1 = head.head; //prep for phase two
        p2 = head.head;
        ind++;
        ind++;
        for(int i=1; i<=lambda;i++){ 
            p2=p2.next;
            ind++;
        }
        while(p1 != p2){ //phase two: find mu
            mu++;
            p1 = p1.next;
            ind++;
            p2 = p2.next;
            ind++;
        }
        ordered_pair[0]=mu;
        ordered_pair[1]=lambda;
        ordered_pair[2]=ind;
        return ordered_pair;
    }
}
