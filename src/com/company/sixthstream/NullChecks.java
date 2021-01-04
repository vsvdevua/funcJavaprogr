package com.company.sixthstream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class NullChecks {

    public static void main(String[] args) {
        Map<String, Car2> owners = new HashMap<>(  );
        owners.put( "Sheila", Car2.withGasColorPassengers( 6,"Red", "Fred", "Jim", "Sheyla" ) );
        owners.put( "Librarian", Car2.withGasColorPassengers( 3,"Octarine", "Rincewind", "Ridcully") );
        owners.put( "Ogg", Car2.withGasColorPassengersAndTrunk( 9,"Black", "Watherwax", "Magrat" ) );

        String owner = "Sheila";
        Car2 c = owners.get( owner );
        List<String> trunkItems = c.getTrunkContents();
        System.out.println(owner + " has "+ trunkItems + " in the car");
        System.out.println("************************************************************");

        Optional<Map<String, Car2>> pwnersOpt = Optional.of( owners );
        pwnersOpt
                .map( m->m.get( owner ) )
                .map( x->x.getTrunkContentsOpt()
                    .map( l->l.toString() ).orElse( "nothing" ))
                .map( x->owner + " has "+ x+ " in the car" )
                .ifPresent( m-> System.out.println(m) );
    }
}
