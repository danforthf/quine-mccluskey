package com.github.danforthf.quine_mccluskey.logic;

import java.util.LinkedList;

public class PrimeImplicant {

        private LinkedList<Integer> minTerms;
        private int[] variableExpression;
        private int numOfVariables;
        
        public PrimeImplicant(int minterm, int numOfVariables){
            minTerms = new LinkedList<Integer>();
            minTerms.add(new Integer(minterm));
            
            this.numOfVariables = numOfVariables;
            
            variableExpression = findExpression();
            
        }
        
        public PrimeImplicant(PrimeImplicant p1, PrimeImplicant p2) {
            
        }
        
        private int[] findExpression(){
            int[] expression = new int[numOfVariables];
            
            if (minTerms.size() == 1){
                for (int i = 0; i < numOfVariables; i++) {
                    expression[expression.length - 1 - i] = ((minTerms.getFirst().intValue() >> i) & 1);
                }
            } else {
                for (int j = 0; j < numOfVariables; j++) {
                    for (int k = 0; k < minTerms.size() - 1; k++) {
                        int var1 = minTerms.get(k).intValue() >> j & 1;
                        int var2 = minTerms.get(k + 1).intValue() >> j & 1;
                        
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
        
        public boolean reducesWith(PrimeImplicant other) {
            return false;
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
        public LinkedList<Integer> getMinTerms() {
            return minTerms;
        }
        
        
}
