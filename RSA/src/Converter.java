import javax.swing.*;
import java.math.BigInteger;
import java.sql.Array;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;

//Contains functions for encrypting and decrypting messages, along with finding factors
public class Converter {

    private Keys myKey;

    public Converter(Keys myKey_, BigInteger d_){
        myKey=myKey_;
    }

    public Converter(Keys myKey_){
        myKey=myKey_;

    }
    public Converter(){

    }

    public long[] stringToLong(String message_) {

        return BitSet.valueOf(message_.getBytes()).toLongArray();

    }
    // Given a message and a key, returns the message encrypted using the given key.
    public String encrypt(Keys key, String message_){

        String secret = "";

        long[] longMessage = stringToLong(message_);

        // Encrypt each character in the message
        for (int i = 0; i < longMessage.length; i++) {
            BigInteger msgVal = BigInteger.valueOf(longMessage[i]);

            // Encrypt each long using modular exponentiation
            BigInteger result = msgVal.modPow(key.getE(), key.getN());

            // Append the encrypted result to secret
            secret += result.toString();

            // Append comma if it's not the last long in the message
            if (i < longMessage.length - 1) {
                secret += ",";
            }
        }
        return secret;
    }
    //Given a key and a target string, decrypts the string and returns it
    public String decrypt(Keys key,String secret){
        String[] secretArray=secret.split(",");

        //BigInteger[] bigArray=new BigInteger[secretArray.length];
        long[] decryptedArray=new long[secretArray.length];

        for (int i=0;i<decryptedArray.length;i++){
            decryptedArray[i]=new BigInteger(secretArray[i]).modPow(key.getD(),key.getN()).longValue();

        }
        return longToString(decryptedArray);
    }
    // Input: Long array
    // Returns the string form of the array.
    public String longToString(long[] longMessage_) {

        BitSet bitSet=BitSet.valueOf(longMessage_);
        return new String(bitSet.toByteArray());

    }
    // Given a key(presumably one we don't know the private key of),
    // finds and returns the private key using fermat's factorization method
    public BigInteger fermat(Keys key){
        // Get the square root of the key's modulus (n)
        BigInteger x = key.getN().sqrt();
        // Initialize y to zero
        BigInteger y = BigInteger.ZERO;
        // Get the modulus (n) of the key
        BigInteger n = key.getN();

        // While the difference of squares of x and y is not equal to n
        while (!((x.pow(2)).subtract(y.pow(2)).equals(n))){
            // If (x^2 - y^2) is less than n, increment x
            if ((x.pow(2).subtract(y.pow(2))).compareTo(n) < 0){
                x = x.add(BigInteger.ONE);
            }
            // If (x^2 - y^2) is greater than n, increment y
            if ((x.pow(2).subtract(y.pow(2))).compareTo(n) > 0){
                y = y.add(BigInteger.ONE);
            }
        }

        // Calculate the totient function
        // φ(n) = n - (x * 2) + 1
        BigInteger totient = n.subtract(x.multiply(BigInteger.TWO)).add(BigInteger.ONE);

        // Calculate and return the private key d = e^(-1) mod φ(n)
        return key.getE().modInverse(totient);
    }
    // Partial implementation of Dixon's Factorization Method.
    public BigInteger dixonMethod(Keys key, String message_){

        BigInteger x=key.getN().sqrt();
        BigInteger y=BigInteger.ZERO;
        BigInteger n=key.getN();



        ArrayList<BigInteger> factorBase=sieve(50);

        //an arraylist of arrays, where the elements are r, f(r), and the parity
        ArrayList<BigInteger[]> matrice=new ArrayList<BigInteger[]>();

        int currentIndex=0; //index used in matrix, could subtract from r but that would be confusing

        //matrice.size()+1 because the last one could be incorrect.
        for(BigInteger r=n.sqrt().add(BigInteger.ONE);matrice.size()+10>factorBase.size();r.add(BigInteger.ONE)){

            BigInteger parity=BigInteger.ZERO;

            BigInteger divisorR=r.pow(2).remainder(n);
            for (BigInteger bigInteger : factorBase) {
                while (divisorR.remainder(bigInteger)==BigInteger.ZERO){
                    divisorR=divisorR.remainder(bigInteger);
                    //possibly inefficient.  could this be done at the end of the for loop, just once?
                    parity=flip(parity);
                }
            }
            if(divisorR.equals(BigInteger.ONE)){
                matrice.add(currentIndex,new BigInteger[]{r,r.pow(2).remainder(n),parity});
                currentIndex++;
            }
        }
        for (BigInteger[] bigIntegers : matrice) {

        }

        return BigInteger.ONE;
    }

    public BigInteger flip(BigInteger big){
        if (big.equals(BigInteger.ONE)){
            big=BigInteger.ZERO;
        }
        else{
            big=BigInteger.ONE;
        }
        return big;
    }
    // implementation of the sieve of eratosthenes
    public ArrayList<BigInteger> sieve(int number){
        boolean[] primesBoolean=new boolean[number-1];

        for (int i=0;i<primesBoolean.length;i++) {
            primesBoolean[i]=true;
        }

        for (int increment=2; increment<=Math.sqrt(number);increment+=1){
            if (primesBoolean[increment*2]=true){//if the next multiple of the index isn't prime
                for (int nextindex=increment*2; nextindex<primesBoolean.length;nextindex+=increment){

                    primesBoolean[nextindex]=false;

                }
            }
        }
        ArrayList<BigInteger> primes=new ArrayList<>();
        for (int i=2;i<primesBoolean.length;i++) {

            if(primesBoolean[i]){
                primes.add(new BigInteger(String.valueOf(i)));
            }
        }
        return primes;
    }
    //(x^2)-(y^2)= kn since if congruent to  0mod(n), you got to be a multiple of n
    //(x+y)(x-y)=kn,
    //start from top, proceed until you get to one

}
