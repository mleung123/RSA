//Mick L.
import java.util.Arrays;

// Class containing various methods for finding primes
public class PrimeFinder4 extends PrimeFinder {
    boolean isPrime(int number) {
        if (number==1){
            return true;
        }
        if (number<=0){
            return false;
        }
        boolean[] answer=this.sieve(number);
        //System.out.println(Arrays.toString(answer));

        System.out.println(answer.length);
        return answer[answer.length-1];

    }
    int countPrimes(int limit) {
        boolean[] result=this.sieve(limit);
        //System.out.println(Arrays.toString(result));
        int a =0;
        for (int num=0;num<result.length-1;num+=1){
            if (result[num]==true){

                //System.out.println(result[num]);
                a+=1;
            }
        }
        return a;
    }
    private boolean[] sieve(int number){
        boolean[] primes=new boolean[number-1];
        //System.out.println(primes.length);

        for (int len=0;len<=number-2;len+=1){
            primes[len]=true;
        }
        for (int increment=2; increment<=Math.sqrt(number);increment+=1){
            if (primes[increment+increment-2]=true){//if the next multiple of the index isn't prime
                for (int nextindex=increment-2+increment; nextindex<=number-2;nextindex+=increment){
                    //System.out.println(nextindex);
                    primes[nextindex]=false;
                }
            }

        }
        System.out.println("asdlfkjalwekfjs");
        return primes;
    }
    public String toString() {
        return "finder 4, which ran the Sieve of Eratosthenes";
    }
}
