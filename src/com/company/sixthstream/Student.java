package com.company.sixthstream;

import java.util.*;
import java.util.stream.Collectors;

public class Student {

    private static final NavigableMap<Integer,String> gradeLetters = new TreeMap<>(  );

    static {
        gradeLetters.put(90, "A");
        gradeLetters.put(80, "B");
        gradeLetters.put(70, "C");
        gradeLetters.put(60, "D");
        gradeLetters.put(50, "E");
        gradeLetters.put(0, "F");
    }

    private String name;
    private int score;

    public String getName() {
        return name;
    }
    public String getLettrGrade(){
        return gradeLetters.floorEntry( score ).getValue();
    }

    public int getScore() {
        return score;
    }

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", %score=" + score +
               "  letter " +getLettrGrade();
    }

    public static void main(String[] args) {
        List<Student> school = Arrays.asList(
                new Student( "Fred",71 ),
                new Student( "Jim",38 ),
                new Student( "Sheila",97 ),
                new Student( "Weatherwax",100 ),
                new Student( "Ogg",56 ),
                new Student( "Rincewind",28 ),
                new Student( "Ridcully",65 ),
                new Student( "Magrat",79 ),
                new Student( "Valentine",93 ),
                new Student( "Gikkian",87 ),
                new Student( "Dr.Mahmout",88 ),
                new Student( "Ender",91 ),
                new Student( "Hyrum",72 ),
                new Student( "Locke",91 ),
                new Student( "Bonzo",57 ),
                new Student( "Anne",91 ));
        school.forEach( student -> System.out.println(student) );

        System.out.println("---------------------------------------------------------------");
       Comparator<Map.Entry<String, List<Student>>> comparator =
       // (e1,e2)->e2.getKey().compareTo( e1.getKey() );
        Map.Entry.comparingByKey();
       comparator=comparator.reversed();
        Map<String, List<Student>>table=school.stream().collect( Collectors.groupingBy( s->s.getLettrGrade() ));
        table.entrySet().stream()
                .sorted(comparator)
                .forEach( e-> System.out.println("Students "+ e.getValue() + " have grade "+e.getKey()));

        System.out.println("---------------------------------------------------------------");

        Map<String,Long>tablea=school
                .stream()
                .collect( Collectors.groupingBy( s->s.getLettrGrade(),Collectors.counting()));
        tablea.entrySet().stream()
                .sorted( Map.Entry.comparingByValue() )
                .forEach( e-> System.out.println(e.getValue() + " students got gradle "+e.getKey()) );
    }
}
