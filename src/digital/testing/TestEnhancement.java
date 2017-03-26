/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digital.testing;

import digital.segmentation.Enhancement;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Sayanthini
 */
public class TestEnhancement {

    @Test
    public void testAdd() {
        Enhancement en = new Enhancement();
        System.out.println("Testing add...");
        int result = en.add(10, 20);
        assertEquals(30, result);
    }
}
