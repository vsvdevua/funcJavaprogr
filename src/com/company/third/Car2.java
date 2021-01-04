package com.company.third;


import java.util.*;

public class Car2 {
    private final  int gasLevel;
    private final String color;
    private final List<String> passengers;
    private final List<String> trunkContents;

    public Car2(int gasLevel, String color, List<String> passengers, List<String> trunkContents) {
        this.gasLevel = gasLevel;
        this.color = color;
        this.passengers = passengers;
        this.trunkContents = trunkContents;
    }

      public int getGasLevel() {
        return gasLevel;
    }

    public String getColor() {
        return color;
    }

    public List<String> getPassengers() {
        return passengers;
    }

    public List<String> getTrunkContents() {
        return trunkContents;
    }


    @Override
    public String toString() {
        return "Car2{" +
                "gasLevel=" + gasLevel +
                ", color='" + color + '\'' +
                ", passengers=" + passengers +
                ","+ (trunkContents != null ? "trunkContents=" + trunkContents : "no trunk") +
                '}';
    }


    public static Car2 withGasColorPassengers(
            int gas, String color,String...passengers){
        List<String> p = Collections.unmodifiableList( Arrays.asList( passengers ) );
        Car2 self = new Car2( gas,color,p,null );
        return self;
    }
    public static Car2 withGasColorPassengersAndTrunk(
            int gas, String color,String...passengers){
        List<String> p = Collections.unmodifiableList( Arrays.asList( passengers ) );
        Car2 self = new Car2( gas,color,p,Arrays.asList( "jack","wrench", "spare wheel" ) );
        return self;
    }
    public static Predicate<Car2> getGasLevelCarCriterion(int threshold) {
            return (car2)->car2.gasLevel>=threshold;
        }

    public static Predicate<Car2> getColorCriterion(String...colors){
        Set<String> colorSet = new HashSet<>( Arrays.asList( colors ) );
        return c -> colorSet.contains( c.color );}

    public static Predicate<Car2> getFourPassengerCriterion(){
        return c -> c.passengers.size()==4;
    }
    public static Predicate getRedCars() {
        return RED_CARs;
            }
    public static final Predicate<Car2> RED_CARs= c ->c.color.equals( "Red" );

    public static Comparator<Car2> getGasComparator(){
        return gasComparator;
    }
    private static final Comparator<Car2> gasComparator= Comparator.comparingInt( o -> o.gasLevel );
    private static final Comparator<Car2> gasComparator2= (o1,  o2) ->    o1.gasLevel-o2.gasLevel;

}
