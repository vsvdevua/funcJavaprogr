package com.company.second;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class PassengerCountOrder implements Comparator<Car1> {

    @Override
    public int compare(Car1 car1, Car1 car2) {
        return car1.getPassengers().size()-car2.getPassengers().size();
    }
}


public class CarScratch1 {

    public static <E> Criteria<E> negate(Criteria<E> crit){
        return x-> !crit.test( x );
    }
    public static <E> Criteria<E> and(Criteria<E> first, Criteria<E> second){
        return  x->first.test( x ) && second.test( x );
    }
    public static <E> Criteria<E> or(Criteria<E> first, Criteria<E> second){
        return  x->first.test( x ) || second.test( x );
    }
    public static <E>void showAll(List<E> lc){
        for (E c1:lc) {
            System.out.println(c1);
        }
        System.out.println("-----------------------------------------------");
    }
    public static List<Car1> getColoredCars(Iterable<Car1> in, String color){
        List<Car1> output = new ArrayList<>(  );
        for (Car1 c1:in) {
            if (c1.getColor().equals( color)){
                output.add( c1 );
            }
        }
        return output;
    }

    public static <E> List<E> getByCriterion(Iterable<E> in, Criteria<E> crit){
        List<E> output = new ArrayList<>(  );
        for (E c1:in) {
            if (crit.test( c1 )){
                output.add( c1 );
            }
        }
        return output;
    }
    public static List<Car1> getCarsByGasLevel(Iterable<Car1> in, int gasLevel){
        List<Car1> output = new ArrayList<>(  );
        for (Car1 c1:in) {
            if (c1.getGasLevel()>= gasLevel){
                output.add( c1 );
            }
        }
        return output;
    }
    public static void main(String[] args) {
        List<Car1> cars1= Arrays.asList(
                Car1.withGasColorPassengers( 6,"Red","Jim","Sheyla" ),
                Car1.withGasColorPassengers( 3,"Black","Joe","Ridcully" ),
                Car1.withGasColorPassengers( 7,"Green","Johm","magrat" ),
                Car1.withGasColorPassengers( 9,"Red","Joseph","Gillian","Anne","Dr.Mahmut" ),
               Car1.withGasColorPassengers( 6,"Silver","Jeremy","Hyrum", "Locke","Bonzo" ));

        showAll( cars1 );

        cars1.sort(new PassengerCountOrder() );
        showAll( getColoredCars( cars1,"Black" ) );
        showAll( cars1 );
        showAll( getByCriterion( cars1, Car1.getRedCar()) );
        showAll( getByCriterion( cars1, Car1.getGasLevelCarCriterion( 6 ) ) );
        cars1.sort( Car1.getGasComparator() );
        showAll( cars1 );
        showAll( getByCriterion( cars1, Car1.getFourPassengerCriterion() ) );
        showAll( getByCriterion( cars1, c -> c.getPassengers().size()==2) );
        Criteria <Car1>x = c -> c.getColor().equals( "Silver" );
      boolean b =  ((Criteria<Car1>) (c->c.getColor().equals( "Black" )))
              .test( Car1.withGasColorPassengers( 0,"Black" ) );

        boolean e =  ((Strange) (c->c.getColor().equals( "Black" )))
                .stuff( Car1.withGasColorPassengers( 0,"Black" ) );



    List<String> colors = Arrays.asList( "Coral", "Pink", "Orange","Gold", "Blue","violeit" );
    showAll( getByCriterion( colors, st->st.length()>4 ) );
        showAll( getByCriterion( colors, st->Character.isUpperCase( st.charAt( 0 ) ) ));

        LocalDate today =LocalDate.now();
        List<LocalDate> dates = Arrays.asList( today, today.plusDays( 1 ), today.plusDays( 7 ),today.minusDays( 1 ) );
        showAll( getByCriterion( dates, ld ->ld.isAfter( today ) ) );

        showAll( getByCriterion( cars1, Car1.getGasLevelCarCriterion( 7 ) ) );
        showAll( getByCriterion( cars1, Car1.getGasLevelCarCriterion( 3 ) ) );
        showAll( getByCriterion( cars1, Car1.getColorCriterion( "Black" ,"Red") ) );

        Criteria<Car1> level7 =Car1.getGasLevelCarCriterion( 7 );
        showAll( getByCriterion( cars1, level7 ) );
        Criteria<Car1> Notlevel7 =CarScratch1.negate( level7 );
        showAll( getByCriterion( cars1,Notlevel7 ) );

        Criteria<Car1> isRed = Car1.getColorCriterion( "Red" );
        Criteria<Car1> fourPassengers = Car1.getFourPassengerCriterion();
        Criteria<Car1> redFourPassengers = and( isRed, fourPassengers );
        showAll( getByCriterion( cars1, redFourPassengers ) );


        Criteria<Car1> isBlack = Car1.getColorCriterion( "Black" );
        Criteria<Car1> blackOrFourPassengers = or( isBlack, fourPassengers );
        showAll( getByCriterion( cars1, blackOrFourPassengers ) );
    }

}