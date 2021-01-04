package com.company.fifth;

import java.util.Comparator;

public class PassangerCountOrd implements Comparator<Car2> {
    @Override
    public int compare(Car2 firstCar, Car2 secondCar) {

            return firstCar.getPassengers().size()-secondCar.getPassengers().size();
        }

}
