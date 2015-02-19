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

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        p1 = new PrimeImplicant(0, 4);
        p2 = new PrimeImplicant(15, 4);
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
    }

    /**
     * Test method for {@link com.github.danforthf.quine_mccluskey.logic.PrimeImplicant#reducesWith(com.github.danforthf.quine_mccluskey.logic.PrimeImplicant)}.
     */
    @Test
    public void testReducesWith() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.github.danforthf.quine_mccluskey.logic.PrimeImplicant#getNumOfVariables()}.
     */
    @Test
    public void testGetNumOfVariables() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link com.github.danforthf.quine_mccluskey.logic.PrimeImplicant#getMinTerms()}.
     */
    @Test
    public void testGetMinTerms() {
        fail("Not yet implemented"); // TODO
    }

}
