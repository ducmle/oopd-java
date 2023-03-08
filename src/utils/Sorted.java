package utils;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * @overview 
 *  To annotate a <b>collection-typed</b> {@link Field} as being sorted, i.e. the elements of 
 *  a field's value are sorted.
 *   
 * @author dmle
 * 
 * @version 2017
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
@NotAssessable
public @interface Sorted {
  /** the sorting order */
  SortOrder order();
}
