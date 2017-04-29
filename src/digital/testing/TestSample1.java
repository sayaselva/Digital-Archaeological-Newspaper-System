/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digital.testing;

import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Sayanthini
 */
public class TestSample1 {

    @Test
    public void testSum() {
        int sum = 7 + 3;
        System.out.println("Testing sum");
        assertEquals(10, sum);
    }

    @Test
    public void testMultiply() {
        int mul = 6 * 7;
        System.out.println("Testing multiply");
        assertEquals(42, mul);

    }
    
    
}
