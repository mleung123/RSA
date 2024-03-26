
import java.lang.reflect.Array;
import java.util.Arrays;
//Mick L.
public abstract class PrimeFinder {

    // a function to determine if a number is prime
    // returns true when the number is prime, and false otherwise

    abstract boolean isPrime(int number);


    // a function to count primes
    // returns the number of primes below a given limit

    int countPrimes(int limit) {
        int count=0;


        for (int increment=2;increment<limit;increment+=1){
            if (this.isPrime(increment)){

                count+=1;
            }
        }

        return count;
    }

}