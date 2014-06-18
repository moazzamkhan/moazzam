package com.pesqol.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
// can use in method only.
public @interface Constraint {
	// NOT NULL - Indicates that a column cannot store NULL value
	// UNIQUE - Ensures that each row for a column must have a unique value
	// PRIMARY KEY - A combination of a NOT NULL and UNIQUE. Ensures that a
	// column (or combination of two or more columns) have an unique identity
	// which helps to find a particular record in a table more easily and
	// quickly
	// FOREIGN KEY - Ensure the referential integrity of the data in one table
	// to match values in another table
	// CHECK - Ensures that the value in a column meets a specific condition
	// DEFAULT - Specifies a default value when specified none for this column
	public boolean NOT_NULL() default false;

	public boolean UNIQUE() default false;

	public String CHECK() default "";

	public boolean PRIMARY_KEY() default false;

	public boolean FOREIGN_KEY() default false;

	public boolean AUTO_INCREMENT() default false;

	public String DEFAULT_VALUE() default "";
}
