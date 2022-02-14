package edu.miracosta.cs113.polynomial_setup_hw1;

public class Term implements Comparable<Term> {

    //CONSTANT VARIABLES

    private static final int DEFAULT_COEFFICIENT = 1 ;
    private static final int DEFAULT_EXPONENT = 1 ;
    private static final boolean DEFAULT_HAS_VARIABLE_SYMBOL = true ;
    private static final char VARIABLE_SYMBOL = 'x' ;
    private static final char EXPONENT_SYMBOL = '^' ;
    private static final char PLUS_SYMBOL = '+' ;
    private static final char MINUS_SYMBOL = '-' ;


    //INSTANCE VARIABLES

    private int coefficient ;
    private int exponent ;
    private boolean hasVariableSymbol ;

    //CONSTRUCTORS

    //default
    public Term() {
        this.setAll(DEFAULT_COEFFICIENT, DEFAULT_EXPONENT, DEFAULT_HAS_VARIABLE_SYMBOL) ;
    }

    //full
    public Term(int coefficient, int exponent, boolean hasVariableSymbol) throws IllegalArgumentException {
        if(!this.setAll(coefficient, exponent, hasVariableSymbol)) {
            throw new IllegalArgumentException("Term full constructor illegal argument passed.") ;
        }
    }

    //copy
    public Term(Term original) {
        if(original == null) {
            throw new NullPointerException("Term copy constructor null argument passed.") ;
        }
        this.setAll(original.getCoefficient(), original.getExponent(), original.getHasVariableSymbol()) ;
    }

    //parsing
    public Term(String term) {

        if(term == null || term.length() == 0 ) {
            throw new IllegalArgumentException
                    ("\nNo terms String provided to constructor.\n") ;
        }

        if(term.contains(Character.toString(VARIABLE_SYMBOL))) {

            hasVariableSymbol = true ;

            String[] parts = term.split(Character.toString(VARIABLE_SYMBOL)) ;

            coefficient = parseCoefficientString(parts[0]) ;

            if(parts.length == 2) {
                exponent = parseExponentString(parts[1]) ;
            } else {
                exponent = 1 ;
            }
        } else {
            hasVariableSymbol = false ;
            coefficient = parseCoefficientString(term) ;
            exponent = 1 ;
        }
        this.setAll(coefficient, exponent, hasVariableSymbol);

        //3x^2^
        //3x - exponent would be 1
        //x - exponent would be 1
        //3x^-2^
        //3x^10^
        //3x^-10^
        //3 -- if no exponent provided then set mExponent to 0
        //if negative is next element, grab -- otherwise go to +

        //3x^3^-5

        //check for second carrot and cut off second carrot
    }

    //GETTERS / ACCESSORS

    public int getCoefficient()
        { return this.coefficient ; }

    public int getExponent()
        { return this.exponent ; }

    public boolean getHasVariableSymbol()
        { return this.hasVariableSymbol ; }

    //SETTERS / MUTATORS

    public boolean setCoefficient(int coefficient) {
        this.coefficient = coefficient ;
        return true ;
    }

    public boolean setExponent(int exponent) {
        this.exponent = exponent ;
        return true ;
    }

    public boolean setHasVariableSymbol(boolean hasVariableSymbol) {
        this.hasVariableSymbol = hasVariableSymbol ;
        return true ;
    }

    public boolean setAll(int coefficient, int exponent, boolean hasVariableSymbol) {
        return this.setCoefficient(coefficient) && this.setExponent(exponent)
                && this.setHasVariableSymbol(hasVariableSymbol) ;
    }

    //PARSING METHODS

    public int parseCoefficientString(String parts) {
        int coefficient ;
        String result ;
        boolean containsDigit = false ;

        if(parts.isEmpty()) {
            return 1 ;
        } else if(parts.contains(Character.toString(PLUS_SYMBOL))) {
            for(char c : parts.toCharArray()) {
                if (Character.isDigit(c)) {
                    containsDigit = true;
                }
            }
            if(containsDigit) {
                result = parts.substring(0, parts.indexOf(PLUS_SYMBOL)) + parts.substring(parts.indexOf(PLUS_SYMBOL) + 1);
                try {
                    coefficient = Integer.parseInt(result);
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("Invalid numeric type for \"Coefficient\" value: application has attempted to convert a String to one of the numeric types, but that the string does not have the appropriate format.\nInvalid coefficient value = " + parts);
                }
                return coefficient;
            } else {
                return 1 ;
            }
        } else if(parts.contains(Character.toString(MINUS_SYMBOL))) {
            for(char c : parts.toCharArray()) {
                if (Character.isDigit(c)) {
                    containsDigit = true;
                }
            }
            if(containsDigit) {
                result = parts;
                try {
                    coefficient = Integer.parseInt(result);
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("Invalid numeric type for \"Coefficient\" value: application has attempted to convert a String to one of the numeric types, but that the string does not have the appropriate format.\nInvalid coefficient value = " + parts);
                }
                return coefficient;
            } else {
                return -1 ;
            }
        } else {
            try {
                coefficient = Integer.parseInt(parts);
            } catch (NumberFormatException nfe) {
                throw new IllegalArgumentException("Invalid numeric type for \"Coefficient\" value: application has attempted to convert a String to one of the numeric types, but that the string does not have the appropriate format.\nInvalid coefficient value = " + parts);
            }
            return coefficient ;
        }
    }

    public int parseExponentString(String parts) {
        int exponent ;
        String result ;

        if(parts.contains(Character.toString(EXPONENT_SYMBOL))) {
            result = parts.substring(0, parts.indexOf(EXPONENT_SYMBOL)) + parts.substring(parts.indexOf(EXPONENT_SYMBOL) + 1);
            try {
                exponent = Integer.parseInt(result) ;
            } catch(NumberFormatException nfe) {
                throw new IllegalArgumentException("Invalid numeric type for \"Exponent\" value: application has attempted to convert a String to one of the numeric types, but that the string does not have the appropriate format.\nInvalid exponent value = " + parts) ;
            }
            return exponent ;
        } else {
            return 1 ;
        }

    }

    //HELPER METHODS

    public void addition(Term otherTerm)
    {

        //check if object is null in which case throw iae exception
        if(otherTerm == null) {
            throw new IllegalArgumentException("Term cannot be null") ;
        }

        //check
        else if(this.exponent != otherTerm.exponent) {
            throw new IllegalArgumentException("Exponent must be equal") ;
        } else {
            this.coefficient += otherTerm.coefficient ;
        }

    }

    //OTHER REQ METHODS / MORE BOILERPLATE CODE

    @Override
    public String toString() {
        String constructedTerm = "";
        if(hasVariableSymbol) {
            if(exponent == 1) {
                if (coefficient == 1) {
                    constructedTerm += Character.toString(VARIABLE_SYMBOL) ;
                } else if (coefficient == -1) {
                    constructedTerm += Character.toString(MINUS_SYMBOL) + Character.toString(VARIABLE_SYMBOL) ;
                } else {
                    constructedTerm = String.valueOf(coefficient) + Character.toString(VARIABLE_SYMBOL) ;
                }
            } else {
                if(coefficient == 1) {
                    constructedTerm += Character.toString(VARIABLE_SYMBOL) + Character.toString(EXPONENT_SYMBOL)
                            + exponent ;
                } else if(coefficient == -1) {
                    constructedTerm += Character.toString(MINUS_SYMBOL) + Character.toString(VARIABLE_SYMBOL)
                            + Character.toString(EXPONENT_SYMBOL) + exponent ;
                } else {
                    constructedTerm = String.valueOf(coefficient) + Character.toString(VARIABLE_SYMBOL)
                            + Character.toString(EXPONENT_SYMBOL) + exponent ;
                }
            }
        } else {
            constructedTerm = String.valueOf(coefficient) ;
        }
        return constructedTerm ;
        //return String.format("\nCoefficient Value: %d\nExponent Value: %d\n", this.coefficient, this.exponent) ;
    }

    //add an x if hasVariableSymbol is true

    //if coefficient is negative and if at the end of the linkedlist dont add a plus sign at end of current string
    //dont automatically add plus, check next Term first
    //termList.get(i).toString()

    @Override
    public boolean equals(Object other) {
        if((!(other instanceof Term))) {
            return false ;
        } else {

            Term otherTerm = (Term)other ;

            return this.coefficient == otherTerm.coefficient &&
                    this.exponent == otherTerm.exponent ;
        }
    }

    @Override
    public int compareTo(Term other) {
        if(other == null) {
            throw new IllegalArgumentException("null given to compareTo method in Term") ;
        }
        return Integer.compare(this.exponent, other.coefficient);
    }

}
