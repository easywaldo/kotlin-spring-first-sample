package com.syntax.ch04;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Problem05 {
    @Test
    void test() {
        int[] result = solution(12345);
        System.out.println("completed....");
    }

    int[] solution(long number) {
        String s = Long.toString(number);
        String reversed = new StringBuilder(s).reverse().toString();
        char[] nc = reversed.toCharArray();

        int[] result = new int[nc.length];
        for (int i = 0; i < nc.length; i++) {
            result[i] = nc[i] - '0';
        }

        return result;
    }
}
