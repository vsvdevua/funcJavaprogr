package com.company.sixthstream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamVersion {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("LightCoral", "pink",
                "Orange","Gold","plum", "Blue", "limegreen");


          strings.stream().forEach( s->System.out.println(">"+s));


       Stream<String> upperCase = strings.stream().filter( s -> Character.isUpperCase( s.charAt( 0 ) ) );

        System.out.println("-----------------------------------------------------------");

        upperCase.forEach( s -> System.out.println("> "+s) );
        System.out.println("-----------------------------------------------------------");
        strings.forEach( s -> System.out.println("> "+s) );
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");

        strings.stream().filter( x->Character.isUpperCase( x.charAt( 0 ) ) )
                .map( x->x.toUpperCase() )
                .forEach( x-> System.out.println(x) );

       List<Car2> carIter =
                Arrays.asList(
                        Car2.withGasColorPassengers( 6,"Red","Jim","Sheyla" ),
                        Car2.withGasColorPassengers( 3,"Black","Joe","Ridcully" ),
                        Car2.withGasColorPassengers( 7,"Green","Johm","magrat" ),
                        Car2.withGasColorPassengers( 9,"Red","Joseph","Gillian","Anne","Dr.Mahmut" ),
                        Car2.withGasColorPassengers( 6,"Silver","Jeremy","Hyrum", "Locke","Bonzo" ));

        carIter.stream().filter( car2 -> car2.getGasLevel()>6 )
                .map( car2 -> car2.getPassengers().get( 0 )+" is driving a "+ car2.getColor() + " car with lot of fuel")
                .forEach( car2 -> System.out.println("> "+car2) );

        carIter.stream().map( car2 ->car2.addGas( 4 ))
                .forEach( car2 -> System.out.println(">>  "+ car2) );
        System.out.println("-----------------------------------------------------------");
        carIter.stream().filter( car2 -> car2.getPassengers().size()>3 )
                .flatMap( car2 -> car2.getPassengers().stream() )
                .map( s->s.toUpperCase() )
                .forEach(s-> System.out.println(s) );

        System.out.println("-----------------------------------------------------------");
        carIter.stream().flatMap( c ->  c.getPassengers().stream()
                .map( p-> p+ " is riding in a "+ c.getColor() +" car"))
                .forEach( s -> System.out.println(s) );


    }
}
