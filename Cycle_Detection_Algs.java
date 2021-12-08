public abstract class Cycle_Detection_Algs{
    //a completely abstract class used to group related methods with empty bodies 
	public abstract Cycle_Detection_Algs next();
    public abstract boolean equivalent(Cycle_Detection_Algs node);
    public abstract long value();

    public static long[] Floyd(Cycle_Detection_Algs head){
        Cycle_Detection_Algs tortoise = head;
        Cycle_Detection_Algs hare = head;
        long lambda=1; //length of cycle
        long mu=0; //index of the first element in cycle
        //(i.e. tail of cycle)
        long indicator = 0;
        long[] ordered_pair = new long[5];
         //phase one: find cycle
        while (tortoise != null && hare != null && hare.next() != null){
            tortoise = tortoise.next();
            indicator++;
            hare = hare.next().next();
            indicator++;
            indicator++;
            if(tortoise.equivalent(hare)){
                tortoise = tortoise.next();
                indicator++;
                //phase two: find lambda
                while(!tortoise.equivalent(hare)){ 
                    lambda++;
                    tortoise = tortoise.next();
                    indicator++;
                }
                tortoise = head;
                indicator++;
                //phase three: find mu
                while(!tortoise.equivalent(hare)){
                    mu++;
                    tortoise = tortoise.next();
                    indicator++;
                    hare=hare.next();
                    indicator++;
                }
                ordered_pair[0]= mu;
                ordered_pair[1]=lambda;
                ordered_pair[2]=indicator;
                ordered_pair[3]=tortoise.value();
                ordered_pair[4]=hare.value();
                return ordered_pair;
            }
        }
        //results if no cycle is found
        return new long[]{-1,-1, indicator, -1, -1};
    }

    public static long[] Brent(Cycle_Detection_Algs head){
        long ind=0;
        Cycle_Detection_Algs tortoise = head;
        Cycle_Detection_Algs hare = head.next();
        ind++;
        long[] ordered_pair = new long[5];
        long lambda = 1; //length of cycle 
        long power = 1;
        long mu=0;        //index of first element in cycle
        //phase one: find cycle and lambda
        while (!tortoise.equivalent(hare) && hare != null){ 
            if (lambda == power){
                power = power*2; 
                tortoise = hare; //reset p1 to previous position of p2
                lambda = 0;
            }
            hare = hare.next(); //only hare moves
            ind++;
            lambda++;
        }
        if(hare == null){
            return new long[]{-1, -1, ind, -1,-1};
        }
        tortoise = head; //prep for phase two
        hare = head;
        ind++;
        ind++;
        for(long i=1; i<=lambda;i++){ 
            hare=hare.next();
            ind++;
        }
        //phase two: find mu
        while(!tortoise.equivalent(hare)){
            mu++;
            tortoise = tortoise.next();
            ind++;
            hare = hare.next();
            ind++;
        }
        ordered_pair[0]=mu; //index of the first element in cycle
        ordered_pair[1]=lambda; //length of cycle
        ordered_pair[2]=ind;
        ordered_pair[3]=tortoise.value(); //value of tortoise
        ordered_pair[4]=hare.value(); //value of hare
        return ordered_pair;
    }
}