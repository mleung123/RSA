import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import static java.math.BigInteger.ONE;

public class KeyGenerator {

    private Random rnd=new SecureRandom();
    private BigInteger p;
    private BigInteger q;
    private BigInteger totient;
    private BigInteger n; //product of two primes
    private BigInteger e; //exponent
    private BigInteger d; //private key
    private int numBits;
    public KeyGenerator(int numBits_){
        numBits = numBits_;

    }
    //generates and returns a key
    public Keys generate(){
        // Create our prime numbers p and q, and their product, n
        do {
            p=new BigInteger(numBits/2, 70,rnd );
            if (numBits%2!=0){
                q=new BigInteger((numBits+1)/2, 70,rnd );
            }
            else{
                q=new BigInteger(numBits/2, 70,rnd );
            }

            n=p.multiply(q);
        } while(n.bitLength()!=numBits);
        //compute the totient
        totient=((n.subtract(p)).subtract(q)).add(ONE);

        // find a valid value for e
        do{
            e=new BigInteger(totient.bitLength(), 70,rnd );
        } while (!e.gcd(totient).equals(ONE) || e.compareTo(totient)>=0 || e.compareTo(ONE)<=0);


        // Compute the private key d
        d=e.modInverse(totient);

        return new Keys(n,e,d);
    }

    public BigInteger getD() {
        return d;
    }

    public String getKey(){
        System.out.println(n.bitLength()+";"+n.toString()+";"+e.toString());
        return n.bitLength()+";"+n.toString()+";"+e.toString();
    }

    }
