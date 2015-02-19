package com.github.danforthf.quine_mccluskey.logic;

import java.util.LinkedList;

public class ImplicantReduction {
    
    private LinkedList<PrimeImplicant> finalImplicants;
    private LinkedList<PrimeImplicant>[] implicants;
    
    private int numOfVariables;
    
    public ImplicantReduction(LinkedList<Integer> minterms, int numOfVariables) {
        
        if (minterms.isEmpty()) {
            throw new IllegalArgumentException("Must be at least one minterm.");
        }
        
        if (numOfVariables < 1) {
            throw new IllegalArgumentException("Must be at least one variable.");
        }
        
        this.numOfVariables = numOfVariables;
        
        // Empty list for final answer.
        finalImplicants = new LinkedList<PrimeImplicant>();
        // Initialize array with empty lists of implicants.
        for (int i = 0; i < numOfVariables; i++) {
            implicants[i] = new LinkedList<PrimeImplicant>();
        }
        
        // Process and construct new implicant for every minterm.
        while (!minterms.isEmpty()) {
            int minterm = minterms.pop().intValue();
            PrimeImplicant implicant = new PrimeImplicant(minterm, numOfVariables);
            
            implicants[implicant.numOfOnes()].add(implicant);
        }
        
    }
    
    public void reduce() {
        // tracks implicant size that we are working on.
        int currentImplicantSize = 1;
        
        do {
            combineImplicants();
            removeImplicants(currentImplicantSize);
            
            currentImplicantSize *= 2;
        } while (!isReduced());
    }

    /**
     * @param currentImplicantSize
     */
    private void removeImplicants(int currentImplicantSize) {
        // Remove reduced implicants and move irreducible implicants to final list.
        for (int m = 0; m < implicants.length; m++) {
            for (int n = 0; n < implicants[m].size(); n++) {
                if (implicants[m].get(n).implicantSize() == currentImplicantSize) {
                    if (implicants[m].get(n).isReduced()) {
                        implicants[m].remove(n);
                    } else {
                        finalImplicants.add(implicants[m].remove(n));
                    }
                }
            }
        }
    }

    /**
     * 
     */
    private void combineImplicants() {
        // Iterate over every implicant comparing to next group (in term of
        // number of ones) for possible combinations.
        for (int j = 0; j < implicants.length - 1; j++) {
            for (int k = 0; k < implicants[j].size(); k++) {
                for (int l = 0; l < implicants[j + 1].size(); l++) {
                    if (implicants[j].get(k).reducesWith(
                            implicants[j + 1].get(l))) {
                        PrimeImplicant newImp = new PrimeImplicant(
                                implicants[j].get(k),
                                implicants[j + 1].get(l));
                        
                        implicants[newImp.numOfOnes()].add(newImp);

                        implicants[j].get(k).setReduced(true);
                        implicants[j + 1].get(l).setReduced(true);
                    }
                }
            }
        }
    }
    
    private boolean isReduced() {
        for (int i = 0; i < implicants.length; i++) {
            if (!implicants[i].isEmpty()) {
                return false;
            }
        }
        
        return false;
    }
}
