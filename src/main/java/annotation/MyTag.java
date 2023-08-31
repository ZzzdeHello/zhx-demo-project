package annotation;


import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyTag {
    String name () default "" ;
    int size () default 0 ;
}
