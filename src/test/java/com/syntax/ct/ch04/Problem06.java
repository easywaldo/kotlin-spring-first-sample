package com.syntax.ct.ch04;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Problem06 {
    @Test
    void test() {
        System.out.println("===============================");
        System.out.println(solution("AB", 1));
        System.out.println(solution("z", 1));
        System.out.println(solution("a B z", 4));
        System.out.println("===============================");
    }

    public String solution(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (char c: s.toCharArray()) {
            sb.append(push(c, n));
        }
        return sb.toString();
    }

    public char push(char c, int n) {
        if (!Character.isAlphabetic((c))) return c;

        int offset = Character.isUpperCase(c) ? 'A' : 'a';
        int position = c - offset;

        position = (position + n) % ('Z' - 'A' + 1);
        return (char) (offset + position);
    }
}
