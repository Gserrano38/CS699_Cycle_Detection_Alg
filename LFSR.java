import java.lang.Math;
public class LFSR extends Cycle_Detection_Algs {
    public boolean[] value;
    public int[] rule;

    public LFSR(boolean[] value, int[] rule){
        this.value = value;
        this.rule = rule;
    } 
    
    public Cycle_Detection_Algs next() {
        boolean[] temp = new boolean[value.length];
        for(int i=0; i<value.length-1; i++){
            temp[i] = value[i+1];
        }
        boolean t=false;
        for(int j=0; j<rule.length; j++){
            int v = rule[j];
            t = (t ^ value[v]);
        }
        temp[value.length-1] = t;

        LFSR collection = new LFSR(temp, rule);
        return collection;
    }

    public boolean equivalent(Cycle_Detection_Algs node) {
        if (node instanceof LFSR){
            boolean[] x = ((LFSR)node).value; 
            if(x.length != value.length){
                return false;
            }
            for(int i=0; i<value.length; i++){
                if(x[i] != value[i]){
                    return false;
                }
            }
            return true;
        } else{
            return false;
        }
    }

    @Override
    public long value() {
        // TODO Auto-generated method stub
        return 0;
    }

    public static int[] find2rule(int n){
        boolean[] value = new boolean[n];
        int[] rule = new int[n];
        int compare = (int) Math.pow(2,n);
        int[] passed = {-1,-1};
        for (int i=0; i<=value.length-1; i++){
            value[i] = false;
        }
        value[n-1] = true;

        for(int i=0; i<value.length; i++){
            for(int j=i+1; j<value.length;j++){
                rule[0] = i;
                rule[1] = j;
                LFSR collection = new LFSR(value, rule);
                long[] test = Cycle_Detection_Algs.Brent(collection);
                if(test[1] == compare-1){
                    passed[0]=i;
                    passed[1]=j;
                }
            }
        }
        return passed;
    }
}
