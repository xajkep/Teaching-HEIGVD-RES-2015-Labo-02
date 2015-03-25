package ch.heigvd.schoolpulse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Olivier Liechti
 */
@Retention( value = RetentionPolicy.RUNTIME)
@Target( value = { ElementType.METHOD})
public @interface TestAuthor {
  String[] githubId();
}
