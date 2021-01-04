package com.company.first;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
class PassengerCountOrder implements Comparator<Car>{

    @Override
    public int compare(Car car1, Car car2) {
        return car1.getPassengers().size()-car2.getPassengers().size();
    }
}

interface CarCriterion{
    boolean test(Car c);
}


public class CarScratch {
    public static void showAll(List<Car> lc){
        for (Car c:lc) {
            System.out.println(c);
        }
        System.out.println("-----------------------------------------------");
    }
    public static List<Car> getColoredCars(Iterable<Car> in, String color){
        List<Car> output = new ArrayList<>(  );
        for (Car c:in) {
            if (c.getColor().equals( color)){
                output.add( c );
            }
        }
        return output;
    }

    public static List<Car> getCarsByCriterion(Iterable<Car> in, CarCriterion crit){
        List<Car> output = new ArrayList<>(  );
        for (Car c:in) {
            if (crit.test( c )){
                output.add( c );
            }
        }
        return output;
    }
    public static List<Car> getCarsByGasLevel(Iterable<Car> in, int gasLevel){
        List<Car> output = new ArrayList<>(  );
        for (Car c:in) {
            if (c.getGasLevel()>= gasLevel){
                output.add( c );
            }
        }
        return output;
    }
    public static void main(String[] args) {
        List<Car> cars= Arrays.asList(
                Car.withGasColorPassengers( 6,"Red","Jim","Sheyla" ),
                Car.withGasColorPassengers( 3,"Black","Joe","Ridcully" ),
                Car.withGasColorPassengers( 7,"Green","Johm","magrat" ),
                Car.withGasColorPassengers( 9,"Yellow","Joseph","Gillian","Anne","Dr.Mahmut" ),
                Car.withGasColorPassengers( 6,"Silver","Jeremy","Hyrum", "Locke","Bonzo" ));

        showAll( cars );

        cars.sort(new PassengerCountOrder() );
        showAll( getColoredCars( cars,"Black" ) );
        showAll( cars );
        showAll( getCarsByCriterion( cars, Car.getRedCar()) );
        showAll( getCarsByCriterion( cars, Car.getGasLevelCarCriterion( 6 ) ) );
    }

}
