package com.github.danforthf.quine_mccluskey.logic;

import java.util.LinkedList;

public class PrimeImplicant {

    private LinkedList<Integer> minterms;
    private int[] variableExpression;
    private int numOfVariables;
    private boolean reduced;

    /**
     * Constructs a PrimeImplicant object from a single minterm with the
     * specified total number of variables. For example, a call with a minterm
     * of 7 and 3 total variables is equivalent to the logical statement 'A AND
     * B AND C' or 'A=1 AND B=1 AND C=1'.
     * 
     * @param minterm
     *            minterm in form of sum of products
     * @param numOfVariables
     *            total number of variables
     * @throws IllegalArgumentException
     *             if numOfVariables < 0
     * @throws IllegalArgumentException
     *             if minterm > 2^numOfVariables - 1 (invalid minterm)
     */
    public PrimeImplicant(int minterm, int numOfVariables) {
        if (numOfVariables < 1) {
            throw new IllegalArgumentException(
                    "Number of variables must be positive.");
        }

        if ((Math.pow(2, numOfVariables) - 1) < minterm || minterm < 0) {
            throw new IllegalArgumentException("Invalid minterm.");
        }

        minterms = new LinkedList<Integer>();
        minterms.add(new Integer(minterm));

        this.numOfVariables = numOfVariables;

        variableExpression = findExpression();

    }

    /**
     * Constructs a PrimeImplicant object by reducing 2 other PrimeImplicant
     * objects. Throws an exception if the 2 objects are not able to be
     * combined. Resulting PrimeImplicant retains each associated minterm and
     * creates a new logical expression.
     * 
     * @param p1
     *            first PrimeImplicant to reduce
     * @param p2
     *            second PrimeImplicant to reduce
     * @throws IllegalArgumentException
     *             if the two PrimeImplicant objects can not be combined.
     */
    public PrimeImplicant(PrimeImplicant p1, PrimeImplicant p2) {

        if (!p1.reducesWith(p2)) {
            throw new IllegalArgumentException(
                    "Prime implicants are irreducable.");
        }

        this.numOfVariables = p1.numOfVariables;

        // copy all minterms of first prime implicant
        minterms = new LinkedList<Integer>(p1.minterms);

        // if any minterms of seconds prime implicant are not found, add.
        for (int i = 0; i < p2.minterms.size(); i++) {
            if (!minterms.contains(p2.minterms.get(i))) {
                minterms.add(p2.minterms.get(i));
            }
        }

        this.variableExpression = findExpression();
    }

    /**
     * Returns an array of integers to be interpreted as a logical expression
     * for all minterms in the prime implicant. For example, for the minterms 7
     * and 6, the function would return the array {1, 1, -1}, where the value -1
     * is used to indicate that the term is not shared by both terms. This is
     * equivalent to the logical statement (A AND B AND C) OR (A AND B AND NOT
     * C) which reduces to A AND B. This method does not check whether the
     * logical combination of minterms is valid.
     * 
     * @return logical expression expressed by minterms
     */
    private int[] findExpression() {
        int[] expression = new int[numOfVariables];

        if (minterms.size() == 1) {
            for (int i = 0; i < numOfVariables; i++) {
                expression[expression.length - 1 - i] = ((minterms.getFirst()
                        .intValue() >> i) & 1);
            }
        } else {
            for (int j = 0; j < numOfVariables; j++) {
                for (int k = 0; k < minterms.size() - 1; k++) {
                    int var1 = minterms.get(k).intValue() >> j & 1;
                    int var2 = minterms.get(k + 1).intValue() >> j & 1;

                    if (var1 == var2) {
                        expression[expression.length - 1 - j] = var1;
                    } else {
                        expression[expression.length - 1 - j] = -1;
                        break;
                    }
                }
            }
        }

        return expression;
    }

    /**
     * Returns the number of ones, i.e. true variables in the prime implicant's
     * logical expression. Example: ABC or Min(7) returns 3 for 3 positive terms.
     * 
     * @return number of ones or 'trues' in the logical expression
     */
    public int numOfOnes() {
        int ones = 0;
        for (int i = 0; i < numOfVariables; i++) {
            if (variableExpression[i] == 1) {
                ones++;
            }
        }

        return ones;
    }

    /**
     * Returns a String representation of the prime implicant's logical
     * expression. The expression ABCD' is represented by the String
     * "[ 1 1 1 0 ]". The expression ABC for a 4 variable logical statement is
     * represented by "[ 1 1 1 - ]", where the "-" character indicates the term
     * not included in the expression.
     * 
     * @return String representation of the logical expression
     */
    public String expressionStr() {
        String s = "[ ";

        for (int i = 0; i < variableExpression.length; i++) {
            if (variableExpression[i] == -1) {
                s += "- ";
            } else {
                s += variableExpression[i] + " ";
            }
        }

        s += "]";

        return s;

    }

    /**
     * Returns a String representation of each minterm included in the prime
     * implicant's logical expression. The String follows the format
     * "Min(minterm1, minterm2, ...)".
     * 
     * @return String representation of the minterms included in the prime
     *         implicant
     */
    public String mintermStr() {
        String s = "Min(";

        for (int i = 0; i < minterms.size(); i++) {
            if (minterms.get(i) == minterms.peekLast()) {
                s += minterms.get(i).toString() + ")";
            } else {
                s += minterms.get(i).toString() + ", ";
            }
        }

        return s;
    }

    public boolean reducesWith(PrimeImplicant other) {
        if (this.numOfVariables != other.numOfVariables) {
            return false;
        } else if ((Math.abs(this.numOfOnes() - other.numOfOnes())) > 1) {
            return false;
        } else {
            int differingTerms = 0;
            for (int i = 0; i < this.numOfVariables; i++) {
                if (this.variableExpression[i] != other.variableExpression[i]) {
                    differingTerms++;
                    if (differingTerms > 1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Returns the total number of logical variables.
     * 
     * @return the total number of logical variables
     */
    public int getNumOfVariables() {
        return numOfVariables;
    }

    /**
     * Returns a LinkedList containing every numeric minterm representation
     * covered by the prime implicant.
     * 
     * @return a LinkedList containing each numeric minterm representation.
     */
    public LinkedList<Integer> getMinterms() {
        return minterms;
    }

    /**
     * Returns true if the prime implicant has been reduced and thus covered by
     * a new prime implicant.
     * 
     * @return if the prime implicant has been reduced and covered by another
     */
    public boolean isReduced() {
        return reduced;
    }

    /**
     * Sets where the prime implicant has been reduced and covered by a new
     * prime implicant.
     * 
     * @param reduced
     *            whether the prime implicant has been reduced
     */
    public void setReduced(boolean reduced) {
        this.reduced = reduced;
    }

}
