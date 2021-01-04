package com.company.fifth;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

public class Scratch {

public static <E>ToIntFunction<E> compareWithThis(E target, Comparator<E> comp){
    return x ->comp.compare( target,x );
}
public static <E> Predicate<E> comparesGreather(ToIntFunction<E> comp){
    return x ->comp.applyAsInt( x )<0;
}
    public static <E>void showAll(List<E> lc){
        for (E c2:lc) {
            System.out.println(c2);
        }
        System.out.println("-----------------------------------------------");
    }
    static List<Car2> getColoredCars(Iterable<Car2> in, String color){
        List<Car2> output = new ArrayList<>(  );
        for (Car2 c2:in) {
            if (c2.getColor().equals( color)){
                output.add( c2 );
            }
        }
        return output;
    }

    static <E> List<E> getByCriterion(Iterable<E> in, Predicate<E> crit){
        List<E> output = new ArrayList<>(  );
        for (E c2:in) {
            if (crit.test( c2 )){
                output.add( c2 );
            }
        }
        return output;
    }
    static List<Car2> getCarsByGasLevel(Iterable<Car2> in, int gasLevel){
        List<Car2> output = new ArrayList<>(  );
        for (Car2 c2:in) {
            if (c2.getGasLevel()>= gasLevel){
                output.add( c2 );
            }
        }
        return output;
    }

    public static void main(String[] args) {
        List<Car2> cars2= Arrays.asList(
                Car2.withGasColorPassengers( 6,"Red","Jim","Sheyla" ),
                Car2.withGasColorPassengers( 3,"Black","Joe","Ridcully" ),
                Car2.withGasColorPassengers( 7,"Green","Johm","magrat" ),
                Car2.withGasColorPassengers( 9,"Red","Joseph","Gillian","Anne","Dr.Mahmut" ),
                Car2.withGasColorPassengers( 6,"Silver","Jeremy","Hyrum", "Locke","Bonzo" ));

        showAll( cars2 );

        cars2.sort(new PassangerCountOrd() );
        showAll( getColoredCars( cars2,"Black" ) );
        showAll( cars2 );
        showAll( getByCriterion( cars2, Car2.getRedCars()) );
        showAll( getByCriterion( cars2, Car2.getGasLevelCarCriterion( 6 ) ) );
        cars2.sort( Car2.getGasComparator() );
        showAll( cars2 );
        showAll( getByCriterion( cars2, Car2.getFourPassengerCriterion() ) );
        showAll( getByCriterion( cars2, c -> c.getPassengers().size()==2) );
        Predicate<Car2> x = c -> c.getColor().equals( "Silver" );
        boolean b =  ((Predicate<Car2>) (c->c.getColor().equals( "Black" )))
                .test( Car2.withGasColorPassengers( 0,"Black" ) );

        boolean e =  ((CarStrange) (c->c.getColor().equals( "Black" )))
                .stuff( Car2.withGasColorPassengers( 0,"Black" ) );



        List<String> colors = Arrays.asList( "Coral", "Pink", "Orange","Gold", "Blue","violeit" );
        showAll( getByCriterion( colors, st->st.length()>4 ) );
        showAll( getByCriterion( colors, st->Character.isUpperCase( st.charAt( 0 ) ) ));

        LocalDate today =LocalDate.now();
        List<LocalDate> dates = Arrays.asList( today, today.plusDays( 1 ), today.plusDays( 7 ),today.minusDays( 1 ) );
        showAll( getByCriterion( dates, ld ->ld.isAfter( today ) ) );

        showAll( getByCriterion( cars2, Car2.getGasLevelCarCriterion( 7 ) ) );
        showAll( getByCriterion( cars2, Car2.getGasLevelCarCriterion( 3 ) ) );
        showAll( getByCriterion( cars2, Car2.getColorCriterion( "Black" ,"Red") ) );

        Predicate<Car2> level7 = Car2.getGasLevelCarCriterion( 7 );
        showAll( getByCriterion( cars2, level7 ) );
        Predicate<Car2> Notlevel7 =  level7.negate();
        showAll( getByCriterion( cars2,Notlevel7 ) );

        Predicate<Car2> isRed = Car2.getColorCriterion( "Red" );
        Predicate<Car2> fourPassengers = Car2.getFourPassengerCriterion();
        Predicate<Car2> redFourPassengers = isRed.and(  fourPassengers );
        showAll( getByCriterion( cars2, redFourPassengers ) );


        Predicate<Car2> isBlack = Car2.getColorCriterion( "Black" );
        Predicate<Car2> blackOrFourPassengers = isBlack.or(  fourPassengers );
        showAll( getByCriterion( cars2, blackOrFourPassengers ) );
        Car2 bert = Car2.withGasColorPassengers( 5, "Blue" );
        ToIntFunction <Car2> compareWithBert = compareWithThis( bert, Car2.getGasComparator() );
        for (Car2 c : cars2){
            System.out.println("comparing" + c + "with bert "+ compareWithBert.applyAsInt( c ));

        }

        showAll( getByCriterion( cars2,comparesGreather( compareWithBert ) ) );
    }
}
/*

F f(E e) -> Function<E,F>
boolean f(E e) -> Predicate<E>
void f(E e) -> Consumer<E>
E f() -> Supplier<E>
E f(E e) -> unary Operator<E>
E f(E e) -> binary Operator<E>
G f(E e, F f)-> BiFunction<E,F,G>


 */