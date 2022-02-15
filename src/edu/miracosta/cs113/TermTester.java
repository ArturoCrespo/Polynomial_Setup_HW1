package edu.miracosta.cs113;

public class TermTester {
    public static void main (String[] args) {

        System.out.println("*---------------- Good Tests ----------------*\n") ;

        Term goodTest1 = new Term("+5");
        Term goodTest2 = new Term("-3");
        Term goodTest3 = new Term("+x");
        Term goodTest4 = new Term("-x");
        Term goodTest5 = new Term("+6x");
        Term goodTest6 = new Term("-9x");
        Term goodTest7 = new Term("+x^3");
        Term goodTest8 = new Term("-x^5");
        Term goodTest9 = new Term("+x^-2");
        Term goodTest10 = new Term("-x^-7");
        Term goodTest11 = new Term("+7x^4");
        Term goodTest12 = new Term("-2x^13");
        Term goodTest13 = new Term("+25x^-8");
        Term goodTest14 = new Term("-54x^-17");

        System.out.println("Test 1: " + goodTest1.toString());
        System.out.println("Test 2: " + goodTest2.toString());
        System.out.println("Test 3: " + goodTest3.toString());
        System.out.println("Test 4: " + goodTest4.toString());
        System.out.println("Test 5: " + goodTest5.toString());
        System.out.println("Test 6: " + goodTest6.toString());
        System.out.println("Test 7: " + goodTest7.toString());
        System.out.println("Test 8: " + goodTest8.toString());
        System.out.println("Test 9: " + goodTest9.toString());
        System.out.println("Test 10: " + goodTest10.toString());
        System.out.println("Test 11: " + goodTest11.toString());
        System.out.println("Test 12: " + goodTest12.toString());
        System.out.println("Test 13: " + goodTest13.toString());
        System.out.println("Test 14: " + goodTest14.toString());

        System.out.println("*---------------- Good Tests Complete ----------------*\n\n") ;


       //Term badTest1 = new Term("352+-33") ;
       System.out.println("*---------------- Bad Test 1 Complete ----------------*\n") ;
       //Term badTest2 = new Term("Really Bad Test");
       System.out.println("*---------------- Bad Test 2 Complete ----------------*\n") ;
       //Term badTest3 = new Term("3^2") ;
       System.out.println("*---------------- Bad Test 3 Complete ----------------*\n") ;

        System.out.println("*---------------- Polynomial Test ----------------*\n\n") ;

       Polynomial goodPolynomial = new Polynomial() ;

       goodPolynomial.addTerm(goodTest1);
       goodPolynomial.addTerm(goodTest2);
       goodPolynomial.addTerm(goodTest8);
       goodPolynomial.addTerm(goodTest11);

       System.out.println(goodPolynomial.toString());

    }
}
