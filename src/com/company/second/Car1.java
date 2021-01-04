package com.company.second;


import java.util.*;

public class Car1 {

    private final  int gasLevel;
    private final String color;
    private final List<String> passengers;
    private final List<String> trunkContents;

    private Car1(int gasLevel,String color,List<String> passengers, List<String> trunkContents){
        this.gasLevel=gasLevel;
        this.color=color;
        this.passengers=passengers;
        this.trunkContents=trunkContents;
    }

    public static Car1 withGasColorPassengers(
            int gas, String color,String...passengers){
        List<String> p = Collections.unmodifiableList( Arrays.asList( passengers ) );
        Car1 self = new Car1( gas,color,p,null );
        return self;
    }
    public static Car1 withGasColorPassengersAndTrunk(
            int gas, String color,String...passengers){
        List<String> p = Collections.unmodifiableList( Arrays.asList( passengers ) );
       Car1 self = new Car1( gas,color,p,Arrays.asList( "jack","wrench", "spare wheel" ) );
        return self;
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
        return "Car1{" +
                "gasLevel=" + gasLevel +
                ", color='" + color + '\'' +
                ", passengers=" + passengers +
                ","+ (trunkContents != null ? "trunkContents=" + trunkContents : "no trunk") +
                '}';
    }
    public static Criteria getRedCar(){
        return RED_CAR;  //singleton
        //return new RedCar(); // every time new object
    }
//    public static final CarsCriteria RED_CAR= new  CarsCriteria(){
//               @Override
//        public boolean test(Car1 c) {
//            return c.color.equals( "Red" );
//        }
//    };

    public static Criteria<Car1> getColorCriterion(String...colors){
        Set<String> colorSet = new HashSet<>( Arrays.asList( colors ) );
        return c -> colorSet.contains( c.color );
    }
    public static final Criteria<Car1> RED_CAR= c -> c.color.equals( "Red" );
    public static Criteria<Car1> getFourPassengerCriterion(){
        return c -> c.passengers.size()==4;
    }


    public static Criteria<Car1> getGasLevelCarCriterion(int threshold) {
        return (c)-> c.gasLevel >= threshold;
    }

    //--------2-------//


//    public static Criteria getGasLevelCarCriterion(int threshold) {
//        return new Criteria<Car1>() {
//
//            @Override
//            public boolean test (Car1 c){
//                return c.gasLevel >= threshold;
//            }
//        } ;
//    }
//----1-----///
//    public static Criteria getGasLevelCarCriterion(int threshold){
//        return new GasLevelCarCriterion(  threshold);
//    }
//    private static class GasLevelCarCriterion implements Criteria<Car1> {
//        private int threshold;
//
//        public GasLevelCarCriterion(int threshold) {
//            this.threshold = threshold;
//        }
//
//        @Override
//        public boolean test(Car1 c) {
//            return c.gasLevel>=threshold;
//        }
//    }


    public static Comparator<Car1> getGasComparator(){
        return gasComparator;
    }
    private static final Comparator<Car1> gasComparator= Comparator.comparingInt( o -> o.gasLevel );
    private static final Comparator<Car1> gasComparator2= (o1,  o2) ->    o1.gasLevel-o2.gasLevel;

}

