package main.java.com.min.baekjoon.dp;

import java.math.BigInteger;
import java.util.Scanner;

public class B1010 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            int N = scanner.nextInt();
            int M = scanner.nextInt();

            System.out.println(fact(M).divide((fact(M - N).multiply(fact(N)))));
        }
    }

    public static BigInteger fact(int value) {
        BigInteger result = BigInteger.ONE;

        if (value != 0) {
            for (int i = value; i > 0; i--) {
                result = result.multiply(BigInteger.valueOf(i));
            }
        }

        return result;
    }
}
