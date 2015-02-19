/**
 * 
 */
package com.github.danforthf.quine_mccluskey.logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Francis Danforth (fadanfor@ncsu.edu)
 */
public class PrimeImplicantTest {
    
    private PrimeImplicant p1;
    private PrimeImplicant p2;
    private PrimeImplicant p3;
    private PrimeImplicant p4;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        p1 = new PrimeImplicant(0, 4);
        p2 = new PrimeImplicant(15, 4);
        
        p3 = new PrimeImplicant(1, 4);
        p4 = new PrimeImplicant(1, 3);
        
    }

    /**
     * Test method for {@link com.github.danforthf.quine_mccluskey.logic.PrimeImplicant#PrimeImplicant(int, int)}.
     */
    @Test
    public void testPrimeImplicantIntInt() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.github.danforthf.quine_mccluskey.logic.PrimeImplicant#PrimeImplicant(com.github.danforthf.quine_mccluskey.logic.PrimeImplicant, com.github.danforthf.quine_mccluskey.logic.PrimeImplicant)}.
     */
    @Test
    public void testPrimeImplicantPrimeImplicantPrimeImplicant() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.github.danforthf.quine_mccluskey.logic.PrimeImplicant#expressionStr()}.
     */
    @Test
    public void testExpressionStr() {
        String testStr1 = "[ 0 0 0 0 ]";
        String testStr2 = "[ 1 1 1 1 ]";
        // Expected value is testStr1. Actual value is call to expressionStr().
        assertEquals("Min(0)", testStr1, p1.expressionStr());
        
        // Expected value is testStr2. Actual value is call to expressionStr().
        assertEquals("Min(15)", testStr2, p2.expressionStr());
        
        String testStr3 = "[ 0 0 0 - ]";
        PrimeImplicant p13 = new PrimeImplicant(p1, p3);
        // Expected value is testStr3. Actual value is call to expressionStr().
        assertEquals("Min(0, 1)", testStr3, p13.expressionStr());
    }

    /**
     * Test method for {@link com.github.danforthf.quine_mccluskey.logic.PrimeImplicant#reducesWith(com.github.danforthf.quine_mccluskey.logic.PrimeImplicant)}.
     */
    @Test
    public void testReducesWith() {
        // Expected value is true. Actual value is call to reducesWith().
        assertTrue("Min(0, 1) is reduceable.", p1.reducesWith(p3));
        
        // Expected value is false. Actual value is call to reducesWith().
        assertFalse("Min(0, 15) is not reduceable.", p1.reducesWith(p2));
        
        // Expected value is false. Actual value is call to reducesWith().
        assertFalse("Different number of variables in each expression.", p3.reducesWith(p4));
    }

    /**
     * Test method for {@link com.github.danforthf.quine_mccluskey.logic.PrimeImplicant#getNumOfVariables()}.
     */
    @Test
    public void testGetNumOfVariables() {
        // Expected value is 3. Actual value is call to numOfVariables().
        assertEquals("3 variable expression.", 3, p4.getNumOfVariables());
        
        // Expected value is 4. Actual value is call to numOfVariables().
        assertEquals("4 variable expression.", 4, p3.getNumOfVariables());
    }

    /**
     * Test method for {@link com.github.danforthf.quine_mccluskey.logic.PrimeImplicant#getMinterms()}.
     */
    @Test
    public void testGetMinterms() {
        fail("Not yet implemented"); // TODO
    }

}
