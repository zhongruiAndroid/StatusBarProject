package com.test.statusbarproject;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void sf() {
        AtomicBoolean atomicBoolean=new AtomicBoolean();
        System.out.println(atomicBoolean.getAndSet(true));
        System.out.println(atomicBoolean.get());
    }
    @Test
    public void ssff() {

        BigDecimal bg = new BigDecimal("3").setScale(2, BigDecimal.ROUND_DOWN);
        System.out.println(bg+"=====");
    }
}