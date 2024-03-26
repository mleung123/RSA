import java.math.BigInteger;

public class Keys {
    private BigInteger n; //product of two primes
    private BigInteger e; //exponent
    private BigInteger d; //private key

    //takes in the product of the two primes n, the exponent e, and the private key d(optional).
    public Keys(BigInteger n_, BigInteger e_, BigInteger d_){
        n=n_;
        e=e_;
        d=d_;
    }

    public Keys(BigInteger n_,BigInteger e_){
        n=n_;
        e=e_;
    }

    public BigInteger getD() {
        return d;
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getN() {
        return n;
    }

    public String getKey(){
        System.out.println(n.bitLength()+";"+e.toString()+";"+d.toString());
        return n.bitLength()+";"+n.toString()+";"+e.toString();
    }

    public void setD(BigInteger d) {
        this.d = d;
    }
}
