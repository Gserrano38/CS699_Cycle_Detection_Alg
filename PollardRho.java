import java.lang.Math;
public class PollardRho extends Cycle_Detection_Algs{
    private long a;
    private long N;

    public PollardRho(long a, long N){ 
        this.a = a;
        this.N = N;
    }

    public Cycle_Detection_Algs next(){ //interface method
        long function =  (a*a)+1;
        long mod =  function % N ;
        PollardRho x = new PollardRho(mod, N);
        return x;
    }

    public boolean equivalent(Cycle_Detection_Algs node){ //interface method
        if (node instanceof PollardRho){
            long x = ((PollardRho)node).value(); 
            long y = Math.abs((a-x));
            long z = getGCD(y, N);
           return z > 1;
        } else {
            return false;
        }
    }

    public static long getGCD(long a, long b){
        while (b != 0){
            long remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }

    @Override
    public long value() {
        return a;
    }

    public long getN(){
        return N;
    }

    public long getA(){
        return a;
    }
    public long setA(long a){
        this.a = a;
        return a;
    }
}
