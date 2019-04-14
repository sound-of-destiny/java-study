package other;

import java.math.BigInteger;
import java.util.Scanner;

public class BigIntegerTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        BigInteger s = sc.nextBigInteger();

        BigInteger ten = BigInteger.valueOf(10);
        BigInteger two = new BigInteger("2");

        BigInteger S = new BigInteger(a);
        BigInteger m = S.mod(ten);

        BigInteger w = s.shiftLeft(8);

        s = s.add(BigInteger.ONE);

        System.out.println(w);
    }
}
