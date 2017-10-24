package tn.esprit.Authentificateur;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.ws.rs.*;
@NameBinding //to bind the interface with filter and intercepters
@Retention(RetentionPolicy.RUNTIME)// annotation works on runtime 
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface JWTTokenNeeded {
	String[] role() default {};//to get the role
}
