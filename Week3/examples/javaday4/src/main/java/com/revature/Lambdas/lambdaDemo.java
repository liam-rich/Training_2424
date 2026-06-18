package com.revature.Lambdas;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class lambdaDemo {
    public static void main(String[] args) {

        List<String> names = List.of("Bob","Alice","Charlie");

        //Functional Interfaces include Predicate, Function, Consumer, and Supplier

        //Predicate  represents a condition that returns true or false
        //Predicate for filtering
        Predicate<String> longWord = s->s.length()>5;
        System.out.println(longWord.test("elephant"));

        //Function Interface takes input, transforms something, and returns a value
        //Function for transforming
        Function<String, Integer> getLength = s->s.length();
        System.out.println(getLength.apply("Java"));

    }
}
