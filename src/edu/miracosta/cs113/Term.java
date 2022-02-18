package edu.miracosta.cs113;

public class Term implements Comparable<Term>, Cloneable {

    //CONSTANT VARIABLES

    private static final int DEFAULT_COEFFICIENT = 1 ;
    private static final int DEFAULT_EXPONENT = 1 ;
    private static final char VARIABLE_SYMBOL = 'x' ;
    private static final char EXPONENT_SYMBOL = '^' ;
    private static final char PLUS_SYMBOL = '+' ;
    private static final char MINUS_SYMBOL = '-' ;


    //INSTANCE VARIABLES

    private int coefficient ;
    private int exponent ;

    //CONSTRUCTORS

    //default
    public Term() {
        this.setAll(DEFAULT_COEFFICIENT, DEFAULT_EXPONENT) ;
    }

    //full
    public Term(int coefficient, int exponent) throws IllegalArgumentException {
        if(!this.setAll(coefficient, exponent)){
            throw new IllegalArgumentException("Term full constructor illegal argument passed.") ;
        }
    }

    //copy
    public Term(Term original) {
        if(original == null) {
            throw new NullPointerException("Term copy constructor null argument passed.") ;
        }
        this.setAll(original.getCoefficient(), original.getExponent()) ;
    }

    //parsing
    public Term(String term) {

        if(term == null || term.length() == 0 ) {
            throw new IllegalArgumentException
                    ("\nNo terms String provided to constructor.\n") ;
        }

        if(term.contains(Character.toString(VARIABLE_SYMBOL))) {

            String[] parts = term.split(Character.toString(VARIABLE_SYMBOL)) ;

            coefficient = parseCoefficientString(parts[0]) ;

            if(parts.length == 2) {
                exponent = parseExponentString(parts[1]) ;
            } else {
                exponent = 1 ; //5x^1
            }
        } else {
            coefficient = parseCoefficientString(term) ;
            exponent = 0 ; //5x^0
        }
        this.setAll(coefficient, exponent);

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

    //SETTERS / MUTATORS

    public boolean setCoefficient(int coefficient) {
        this.coefficient = coefficient ;
        return true ;
    }

    public boolean setExponent(int exponent) {
        this.exponent = exponent ;
        return true ;
    }

    public boolean setAll(int coefficient, int exponent) {
        return this.setCoefficient(coefficient) && this.setExponent(exponent) ;
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
            return 0 ;
        }

    }

    //HELPER METHODS

    public void addition(Term otherTerm) {

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

        //if the coefficient is 0, don't build the term
        if(coefficient == 0) {
            return constructedTerm ;
        } else if(coefficient > 0) {
            constructedTerm += PLUS_SYMBOL ;
        } else if(coefficient == -1){
            constructedTerm += MINUS_SYMBOL ;
        }

        //if exponent is 0, just return the coefficient value
        if(exponent == 0) {
            constructedTerm += String.valueOf(coefficient) ;

        //check if exponent
        } else if(exponent == 1) {
            //if the coefficient is 1 just print the variable symbol without exponent symbol and exponent variable
            if (coefficient == 1) {
                constructedTerm += Character.toString(VARIABLE_SYMBOL) ;

                //if coefficient is -1 print the minus symbol without exponent symbol and exponent variable
            } else if (coefficient == -1) {
                constructedTerm += Character.toString(VARIABLE_SYMBOL) ;

                //otherwise, print all >1 or <-1 coefficient values and variable symbol without
            } else {
                constructedTerm += String.valueOf(coefficient) + Character.toString(VARIABLE_SYMBOL) ;
            }
        } else {
            //if coefficient is 1, just print the variable
            if(coefficient == 1) {
                constructedTerm += Character.toString(VARIABLE_SYMBOL) + Character.toString(EXPONENT_SYMBOL)
                        + exponent ;
            } else if(coefficient == -1) {
                constructedTerm += Character.toString(VARIABLE_SYMBOL) + Character.toString(EXPONENT_SYMBOL) + exponent ;
            } else {
                constructedTerm += String.valueOf(coefficient) + Character.toString(VARIABLE_SYMBOL)
                        + Character.toString(EXPONENT_SYMBOL) + exponent ;
            }
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

    //double positive - check if exponent is larger
    //double negative
    @Override
    public int compareTo(Term other) {

        Term otherTern = (Term) other ;

        if(other == null) {
            throw new IllegalArgumentException("null given to compareTo method in Term") ;
        }

        if(this.exponent > otherTern.getExponent()) {
            return 1 ;
        } else if(this.exponent < otherTern.getExponent()) {
            return -1 ;
        } else {
            return 0 ;
        }

    }

    @Override
    public Term clone() {
        return new Term(this) ;
    }

}
