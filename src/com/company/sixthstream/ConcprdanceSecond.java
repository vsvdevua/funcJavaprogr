package com.company.sixthstream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*


www.gutenberg.org/ebooks
 */
public class ConcprdanceSecond {
    private static final Pattern WORD_BREAK = Pattern.compile( "\\W+" );
    private static final Comparator<Map.Entry<String, Long>> valueOrder=
            Map.Entry.comparingByValue();
    private static final Comparator<Map.Entry<String, Long>> reversedValue =
            valueOrder.reversed();
//1
//    public static Optional<Stream<String>> lines(Path p){
//        try{
//            return  Optional.of( Files.lines( p ));
//        }catch (IOException ioe){
//            System.err.println("File read failed " +ioe.getMessage() );
//            return Optional.empty();
//        }
//    }
  //2
//    public static <E,F> Function <E, Optional<F>> wrap (ExceptionFunction<E,F> op){
//        return e->{
//            try{
//              return Optional.of( op.apply( e ));
//            }catch (Throwable t){
//                return Optional.empty();
//            }
//        };
//    }
    public static void main(String[] args) throws IOException {
       List<String> filenames = Arrays.asList(
               "jane austin.txt"
//               ,
//               "BadBook.txt",
//               "Emma.txt",
//               "SenseAndSensibility.txt"
       );
       filenames.stream()
               .map( Paths::get )
              // .flatMap( ConcprdanceSecond::lines )

               //.map( ConcprdanceSecond::lines )
             //  .map( wrap( p->Files.lines( p ) ))
               .map( Either.wrap( p->Files.lines( p ) ) )
               //.peek( o->{if(!o.isPresent()) System.err.println("Bad file!");} )
               .peek( e->e.handle( System.err::println ) )
               .filter( e->e.success() )
               .flatMap( Either::get )
              // .filter( Optional::isPresent )
             //  .flatMap( Optional::get )
                .flatMap( WORD_BREAK::splitAsStream )
                .filter( w->w.length()>0 )
                //  .map( w->w.toLowerCase() )
                .map( String::toLowerCase )
                .collect( Collectors.groupingBy( Function.identity(), Collectors.counting() ))
                .entrySet().stream()
                .sorted( reversedValue )
                .limit( 200 )
                .map( l->  String.format("%20s : %5d", l.getKey(), l.getValue()) )
                .forEach(System.out::println);
        //.forEach( l-> System.out.printf("%20s : %5d\n", l.getKey(), l.getValue()) );
    }
}