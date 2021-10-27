class Test {
    public static int[] Floyd2(IteratedFunction head){
        IteratedFunction tortoise = head;
        IteratedFunction hare = head;
        int lambda=1; //length of cycle
        int mu=0; //index of the first element in cycle (i.e. tail of cycle)
        int indicator = 0;
        int[] ordered_pair = new int[3];
        while (tortoise != null && hare != null && hare.next() != null){ //phase one: find cycle
            tortoise = tortoise.next();
            indicator++;
            hare = hare.next().next();
            indicator++;
            indicator++;
            if(tortoise.equivalent(hare)){
                tortoise = tortoise.next();
                indicator++;
                while(!tortoise.equivalent(hare)){ //phase two: find lambda
                    lambda++;
                    tortoise = tortoise.next();
                    indicator++;
                }
                tortoise = head;
                indicator++;
                while(!tortoise.equivalent(hare)){ //phase three: find mu
                    mu++;
                    tortoise = tortoise.next();
                    indicator++;
                    hare=hare.next();
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

    public static int[] Brent2(IteratedFunction head){
        int ind=0;
        IteratedFunction p1 = head;
        IteratedFunction p2 = head.next();
        ind++;
        int[] ordered_pair = new int[3];
        int lambda = 1; //length of cycle 
        int power = 1;
        int mu=0;        //index of first element in cycle
        while (!p1.equivalent(p2) && p2 != null){ //phase one: find cycle and lambda
            if (lambda == power){
                power = power*2; 
                p1 = p2; //reset p1 to previous position of p2
                lambda = 0;
            }
            p2 = p2.next(); //only hare moves
            ind++;
            lambda++;
        }
        if(p2 == null){
            return new int[]{-1, -1, ind};
        }
        p1 = head; //prep for phase two
        p2 = head;
        ind++;
        ind++;
        for(int i=1; i<=lambda;i++){ 
            p2=p2.next();
            ind++;
        }
        while(!p1.equivalent(p2)){ //phase two: find mu
            mu++;
            p1 = p1.next();
            ind++;
            p2 = p2.next();
            ind++;
        }
        ordered_pair[0]=mu;
        ordered_pair[1]=lambda;
        ordered_pair[2]=ind;
        return ordered_pair;
    }

    public static int[] gatherData(int l, int k){
        System.out.println("Values of l and k: ["+ l + ", " + k + "]");
        CycleDetectionAlg list_a = CycleDetectionAlg.makeList(l, k);
        int[] Floyd_pair_a = CycleDetectionAlg.Floyd1(list_a);
        int[] Brent_pair_a = CycleDetectionAlg.Brent1(list_a);
        int[] ordered_pair = new int[2];
        ordered_pair[0] = Floyd_pair_a[2];
        ordered_pair[1] = Brent_pair_a[2];
        System.out.println("Floyd's indicator: " + ordered_pair[0]);
        System.out.println("Brent's indicator: " + ordered_pair[1]);
        System.out.println();
        return ordered_pair;
    }

    public static int[] gatherData(int l){
        System.out.println("Value of l: ["+ l + "]");
        CycleDetectionAlg list_a = CycleDetectionAlg.makeList(l);
        int[] Floyd_pair_b = CycleDetectionAlg.Floyd1(list_a);
        int[] Brent_pair_b = CycleDetectionAlg.Brent1(list_a);
        int[] ordered_pair = new int[2];
        ordered_pair[0] = Floyd_pair_b[2];
        ordered_pair[1] = Brent_pair_b[2];
        System.out.println("Floyd's indicator: " + ordered_pair[0]);
        System.out.println("Brent's indicator: " + ordered_pair[1]);
        System.out.println();
        return ordered_pair;
    }
}