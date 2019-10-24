/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariopizzaria;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author <Frederik Keis Dinsen>
 */
public class ExtraToppingTest {
    
    ExtraTopping sytten = new ExtraTopping(5, 3);
    
    public ExtraToppingTest() {
    }

    @Test
    public void testToppingName() {
        
        String exspected = "Bearnaise";
        
        String actual = sytten.getExtraToppingName();
        
        assertEquals(exspected, actual);
        
        
    }
    
}
