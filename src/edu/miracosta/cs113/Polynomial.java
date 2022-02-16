package edu.miracosta.cs113;

import java.util.LinkedList;

public class Polynomial {

    private final static char PLUS_SYMBOL = '+' ;

    //CLASS MEMBER

    private LinkedList<Term> termList ;

    //CONSTRUCTORS
    public Polynomial() {
        this.termList = new LinkedList() ;
    }

    public Polynomial(Polynomial original) {
        this.termList = new LinkedList() ;

        if(original != null) {
            for(int i = 0 ; i < original.getNumTerms() ; i++) {
                termList.add(new Term(original.getTerm(i))) ;
            }
        }
    }

    public void add(Polynomial polynomial) {
        for(int i = 0 ; i < polynomial.getNumTerms() ; i++) {
            this.addTerm(new Term(polynomial.getTerm(i))) ;
        }
    }

    public Term getTerm(int location) throws IndexOutOfBoundsException {
        try {
            return this.termList.get(location) ;
        } catch(IndexOutOfBoundsException ioobe) {
            throw new IndexOutOfBoundsException
                    ("Location provided is <0 or >= number of Terms in Polynomial.") ;
        }
    }

    //add Term to correct position in list ordered from highest exponent to lowest
    //if provided term (argument) exponent value is same as a local term, add both together and insert result into position
    //that was occupied by local term. unless coefficient is ... turns to 0? maybe use comparable?

    public void addTerm(Term term) {

        if(term == null) {
            return ;
        }

        int insertIndex = -1 ;
        int i = 0 ;
        boolean addToList = true ;

        while(insertIndex == -1 && i < termList.size() && addToList) {
            Term localTerm = termList.get(i) ;
            if(term.compareTo(localTerm) == 0) {
                localTerm.addition(term) ;
                addToList = false;
                if(termList.get(i).getCoefficient() == 0) {
                    termList.remove(i);
                }
            } else if(term.compareTo(localTerm) > 0) {
                insertIndex = i;
            }
            i++;
        }

        if(addToList) {
            if(insertIndex >= 0) {
                termList.add(insertIndex, term);
            } else {
                termList.add(term);
            }
        }
    }

    public int getNumTerms() {
        return this.termList.size() ;
    }

    public void clear() {
        this.termList.clear(); ;
    }

    @Override
    public String toString() {
        String polynomial = "" ;
        if(termList == null || termList.isEmpty()) {
            return "0"; //add 0 to pass test?
        } else {
            for(int i = 0; i < termList.size(); i++) {
                if(i == 0) {
                    if(termList.get(i).toString().contains(Character.toString(PLUS_SYMBOL))) {
                        polynomial += termList.get(i).toString().replace(Character.toString(PLUS_SYMBOL), "") ;
                    } else {
                        polynomial += termList.get(i).toString() ;
                    }
                } else {
                    polynomial += termList.get(i).toString() ;
                }
//                if(i+1 > termList.size() && termList.get(i+1).getCoefficient() < 0 ) {
//                    polynomial += termList.get(i).toString() ;
//                } else if (i == termList.size()-1) {
//                    polynomial += termList.get(i).toString() ;
//                } else {
//                    polynomial += termList.get(i).toString() ;
//                }
            }
        }
        return polynomial ;
    }


}
