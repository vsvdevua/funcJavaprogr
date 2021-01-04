package com.company.sixthstream;

import java.util.function.Consumer;
import java.util.function.Function;

public class Either <E>{

    private E value;
    private Throwable problem;

    public Either() {
    }

    public static <E> Either<E> sucess (E v){
        Either<E> self = new Either<>();
        self.value = v;
        return self;
    }

    public static <E> Either<E> failure(Throwable t){
        Either<E> self = new Either<>();
        self.problem = t;
        return self;
    }

    public boolean success(){
        return value!=null;
    }

    public boolean failed(){
        return problem!=null;
    }

    public E get(){
        return value;
    }
    public Throwable getProblem(){
        return problem;
    }

    public void use(Consumer<E> cons){
        if(value!=null){
            cons.accept( value );
        }

    }
    public void handle(Consumer<Throwable> cons){
        if(problem!=null){
            cons.accept( problem );
        }
    }

    public static <E,F>Function<E, Either<F>> wrap(ExceptionFunction<E,F> op){
        return e->{
            try{
                return Either.sucess( op.apply( e ) );
            }catch (Throwable t){
                return Either.failure( t );
            }
        };
    }
}
