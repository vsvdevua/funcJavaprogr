package com.company.third;


public interface Predicate<E> {
    boolean test(E e);
    default Predicate<E> negate(){
        return x-> !this.test( x );
    }
    default Predicate<E> and(Predicate<E> second){
        return  x->this.test( x ) && second.test( x );
    }
   default Predicate<E> or(Predicate<E> second){
        return  x->this.test( x ) || second.test( x );
    }


//     static <E> Criterio<E> negate(Criterio<E> crit){
//        return x-> !crit.test( x );
//    }
//    static <E> Criterio<E> and(Criterio<E> first, Criterio<E> second){
//        return  x->first.test( x ) && second.test( x );
//    }
//    static <E> Criterio<E> or(Criterio<E> first, Criterio<E> second){
//        return  x->first.test( x ) || second.test( x );
//    }
}
