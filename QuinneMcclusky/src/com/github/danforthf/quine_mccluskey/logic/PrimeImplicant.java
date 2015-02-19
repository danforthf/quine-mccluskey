package com.github.danforthf.quine_mccluskey.logic;

import java.util.LinkedList;

public class PrimeImplicant {

        private LinkedList<Integer> minterms;
        private int[] variableExpression;
        private int numOfVariables;
        
        public PrimeImplicant(int minterm, int numOfVariables){
            minterms = new LinkedList<Integer>();
            minterms.add(new Integer(minterm));
            
            this.numOfVariables = numOfVariables;
            
            variableExpression = findExpression();
            
        }
        
        
        public PrimeImplicant(PrimeImplicant p1, PrimeImplicant p2) {
            this.numOfVariables = p1.getNumOfVariables();
            
            // copy all minterms of first prime implicant
            minterms = new LinkedList<Integer>(p1.getMinterms());
            
            // if any minterms of seconds prime implicant are not found, add.
            for (int i = 0; i < p2.getMinterms().size(); i++) {
                if (!minterms.contains(p2.getMinterms().get(i))) {
                    minterms.add(p2.getMinterms().get(i));
                }
            }
            
            this.variableExpression = findExpression();
        }
        
        private int[] findExpression(){
            int[] expression = new int[numOfVariables];
            
            if (minterms.size() == 1){
                for (int i = 0; i < numOfVariables; i++) {
                    expression[expression.length - 1 - i] = ((minterms.getFirst().intValue() >> i) & 1);
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
            return true;
        }

        /**
         * @return the numOfVariables
         */
        public int getNumOfVariables() {
            return numOfVariables;
        }

        /**
         * @return the minTerms
         */
        public LinkedList<Integer> getMinterms() {
            return minterms;
        }
        
        
}
