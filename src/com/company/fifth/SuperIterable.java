package com.company.fifth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import java.util.function.Function;
import java.util.function.Predicate;


public class SuperIterable<E> implements Iterable<E> {

  private Iterable<E> self;

    public SuperIterable(Iterable<E> s) {
       self = s;
    }

    public <F> SuperIterable<F>  flatMap(Function<E, SuperIterable<F>> op){
        List<F> results = new ArrayList<>(  );
        self.forEach( e -> op.apply( e ).forEach( f -> results.add( f ) ) );
        return new SuperIterable<>( results );
    }

public <F> SuperIterable<F> map(Function<E, F> op){
        List<F> results = new ArrayList<>(  );
        self.forEach( e ->results.add( op.apply( e )));
        return new SuperIterable<>( results );
}
    public SuperIterable<E> filter(Predicate<E> pred){
        List<E> result = new ArrayList<>(  );
//        for(E e : self){
//            if(pred.test( e )){
//                result.add( e );
//            }
//        }
        self.forEach( e -> {if(pred.test( e )) result.add( e );} );
        return new SuperIterable<>( result );
    }

//public void forEvery(Consumer<E> cons){
//        for (E e:self){
//            cons.accept( e );
//        }
//}
    @Override
    public Iterator<E> iterator() {
        return self.iterator();
    }

    public static void main(String[] args) {
        SuperIterable<String> strings = new SuperIterable<>(
                Arrays.asList("LightCoral", "pink","Orange","Gold","plum", "Blue", "limegreen")
        );
        for (String s:strings){
            System.out.println(">"+s);
        }

        SuperIterable<String> upperCase = strings.filter( s -> Character.isUpperCase( s.charAt( 0 ) ) );

        System.out.println("-----------------------------------------------------------");
//        for (String s:upperCase){
//            System.out.println(">"+s);
//        }
    upperCase.forEach( s -> System.out.println("> "+s) );
        System.out.println("-----------------------------------------------------------");
        strings.forEach( s -> System.out.println("> "+s) );
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------");
        strings.filter( x->Character.isUpperCase( x.charAt( 0 ) ) )
                .map( x->x.toUpperCase() )
                .forEach( x-> System.out.println(x) );

        SuperIterable<Car2> carIter = new SuperIterable<>(
                Arrays.asList(
                        Car2.withGasColorPassengers( 6,"Red","Jim","Sheyla" ),
                        Car2.withGasColorPassengers( 3,"Black","Joe","Ridcully" ),
                        Car2.withGasColorPassengers( 7,"Green","Johm","magrat" ),
                        Car2.withGasColorPassengers( 9,"Red","Joseph","Gillian","Anne","Dr.Mahmut" ),
                        Car2.withGasColorPassengers( 6,"Silver","Jeremy","Hyrum", "Locke","Bonzo" ))
        );
        carIter.filter( car2 -> car2.getGasLevel()>6 )
                .map( car2 -> car2.getPassengers().get( 0 )+" is driving a "+ car2.getColor() + " car with lot of fuel")
                .forEach( car2 -> System.out.println("> "+car2) );

//        carIter.map( car2 -> Car2.withGasColorPassengers( car2.getGasLevel()+4, car2.getColor(),
//                car2.getPassengers().toArray(new String[]{})) )
//                .forEach( car2 -> System.out.println(">>  "+ car2) );

        carIter.map( car2 ->car2.addGas( 4 ))
                .forEach( car2 -> System.out.println(">>  "+ car2) );
        System.out.println("-----------------------------------------------------------");
        carIter.filter( car2 -> car2.getPassengers().size()>3 )
                .flatMap( car2 -> new SuperIterable<>( car2.getPassengers() ) )
                .map( s->s.toUpperCase() )
                .forEach(s-> System.out.println(s) );

        System.out.println("-----------------------------------------------------------");
        carIter.flatMap( c -> new SuperIterable<>( c.getPassengers() )
                    .map( p-> p+ " is riding in a "+ c.getColor() +" car"))
                .forEach( s -> System.out.println(s) );
    }
}
