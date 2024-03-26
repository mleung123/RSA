//Mick L.
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //KeyGenerator keyGenerator=new KeyGenerator(99);
        //keyGenerator.getKey();
        //System.out.println(keyGenerator.getD());
        List<Keys> decryptList=new ArrayList();
        List<Keys> encryptList=new ArrayList();

        //various test cases for decryption.
        decryptList.add(new Keys(new BigInteger("570244738041409"),new BigInteger("201420536578711")));
        decryptList.add(new Keys(new BigInteger("2353768456497383"),new BigInteger("2350534791178009")));
        decryptList.add(new Keys(new BigInteger("10808851504066289"),new BigInteger("6372008721320249")));
        decryptList.add(new Keys(new BigInteger("53125741042342097"),new BigInteger("44820147762013697")));
        decryptList.add(new Keys(new BigInteger("176068406348405647"),new BigInteger("29878992396457117")));

        String message1  = "password";
        String message2  = "cryptography";

        KeyGenerator generator = new KeyGenerator(1024);
        encryptList.add(generator.generate());
        encryptList.add(generator.generate());


        /*
        decryptList.add(new Keys(new BigInteger("590780791245689189"),new BigInteger("247528372975356077")));
        decryptList.add(new Keys(new BigInteger("3947535976332583073"),new BigInteger("493857335280781727")));
        decryptList.add(new Keys(new BigInteger("9304782725019575317"),new BigInteger("1060726662762964605")));
        decryptList.add(new Keys(new BigInteger("38603512705925473039"),new BigInteger("29386718919083976509")));
        decryptList.add(new Keys(new BigInteger("159883163258347326913"),new BigInteger("7146322314978001747")));
        decryptList.add(new Keys(new BigInteger("613015811212646932999"),new BigInteger("116281442917200473737")));
        decryptList.add(new Keys(new BigInteger("2395975439555815802473"),new BigInteger("1950156292155785193049")));
        */


        Converter converter=new Converter();

        decryptList.get(0).setD(converter.fermat(decryptList.get(0)));
        System.out.println(converter.decrypt(decryptList.get(0),"448145482258410"));

        decryptList.get(1).setD(converter.fermat(decryptList.get(1)));
        System.out.println(converter.decrypt(decryptList.get(1),"1941718318849123"));

        decryptList.get(2).setD(converter.fermat(decryptList.get(2)));
        System.out.println(converter.decrypt(decryptList.get(2),"1832916396498631"));

        decryptList.get(3).setD(converter.fermat(decryptList.get(3)));
        System.out.println(converter.decrypt(decryptList.get(3),"17528823626092899"));

        decryptList.get(4).setD(converter.fermat(decryptList.get(4)));
        System.out.println(converter.decrypt(decryptList.get(4),"110770118826648241"));

        String encrypted1 = converter.encrypt(encryptList.get(0), message1);
        String encrypted2 = converter.encrypt(encryptList.get(1), message2);

        System.out.println("Encrypted string 1:" + encrypted1);

        System.out.println("Encrypted string 2:" + encrypted2);

        System.out.println("message1: " + converter.decrypt(encryptList.get(0), encrypted1));
        System.out.println("message2: " + converter.decrypt(encryptList.get(1), encrypted2));

        /*
        keysList.get(5).setD(converter.fermat(keysList.get(5)));
        System.out.println(converter.decrypt(keysList.get(5),"286833519518685508"));

        keysList.get(6).setD(converter.fermat(keysList.get(6)));
        System.out.println(converter.decrypt(keysList.get(6),"1373716523283408062"));

        keysList.get(7).setD(converter.fermat(keysList.get(7)));
        System.out.println(converter.decrypt(keysList.get(7),"8615372328197865004"));

        keysList.get(8).setD(converter.fermat(keysList.get(8)));
        System.out.println(converter.decrypt(keysList.get(8),"36899638124552999214"));

        keysList.get(9).setD(converter.fermat(keysList.get(9)));
        System.out.println(converter.decrypt(keysList.get(9),"22048181066294458025"));

        keysList.get(10).setD(converter.fermat(keysList.get(10)));
        System.out.println(converter.decrypt(keysList.get(10),"601910982592635815939"));

        keysList.get(11).setD(converter.fermat(keysList.get(11)));
        System.out.println(converter.decrypt(keysList.get(11),"564058922672027163176"));
        */

        //System.out.println(converter.sieve(100));
        return;
    }

}
