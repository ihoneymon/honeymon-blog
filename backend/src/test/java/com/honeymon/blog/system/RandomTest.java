package com.honeymon.blog.system;

import java.util.Random;

import org.junit.Test;

public class RandomTest {

    @Test
    public void random() {
        System.out.println("Randon long: " + new Random().nextLong());
    }
}
