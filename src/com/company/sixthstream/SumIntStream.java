package com.company.sixthstream;

import java.util.OptionalInt;
import java.util.stream.IntStream;

public class SumIntStream {
    public static void main(String[] args) {
      OptionalInt res= IntStream.iterate( 0, i -> i+1 )
                .limit( 100 )
                .reduce( (a,b)->a+b );
            //    .forEach( i -> System.out.println("> "+i) );
        res.ifPresent( r-> System.out.println("sum is "+r) );
    }
}
