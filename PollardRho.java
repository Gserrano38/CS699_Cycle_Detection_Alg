import java.lang.Math;
public class PollardRho extends IteratedFunction{
    private int a;
    private int N;

    public PollardRho(int a, int N){ 
        this.a = a;
        this.N = N;
    }

    public IteratedFunction next(){ //interface method
        int function =  (a*a)+1;
        int mod =  function % N ;
        PollardRho x = new PollardRho(mod, N);
        return x;
    }

    public boolean equivalent(IteratedFunction node){ //interface method
        if (node instanceof PollardRho){
            int x = ((PollardRho)node).value(); 
            int y = Math.abs((a-x));
            int z = getGCD(y, N);
           return z > 1;
        } else {
            return false;
        }
    }

    public static int getGCD(int a, int b){
        while (b != 0){
            int remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }

    @Override
    public int value() {
        return a;
    }
}
