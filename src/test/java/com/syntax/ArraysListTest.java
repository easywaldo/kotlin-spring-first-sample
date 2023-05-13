package com.syntax;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;

@SpringBootTest
public class ArraysListTest {
    @Test
    public void test() {
        var list = Arrays.asList(10, 20, 30, 60, 10, 100, 900);
        System.out.println(list);

        // Max
        int max = Collections.max(list);
        System.out.println(max);

        // Min
        int min = Collections.min(list);
        System.out.println(min);
    }
}
