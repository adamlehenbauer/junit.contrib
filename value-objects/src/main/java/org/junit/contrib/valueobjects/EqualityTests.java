package org.junit.contrib.valueobjects;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * @author Adam Lehenbauer <adamlehenbauer>
 */
public class EqualityTests {

//    The equals method implements an equivalence relation on non-null object references:
//
//    It is reflexive: for any non-null reference value x, x.equals(x) should return true.
//    It is symmetric: for any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true.
//    It is transitive: for any non-null reference values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true,
//      then x.equals(z) should return true.
//    It is consistent: for any non-null reference values x and y, multiple invocations of x.equals(y) consistently return true
//      or consistently return false, provided no information used in equals comparisons on the objects is modified.
//    For any non-null reference value x, x.equals(null) should return false.
//    The equals method for class Object implements the most discriminating possible equivalence relation on objects;
//      that is, for any non-null reference values x and y, this method returns true if and only if x and y refer to the same
//      object (x == y has the value true).
//
//    Note that it is generally necessary to override the hashCode method whenever this method is overridden,
//      so as to maintain the general contract for the hashCode method, which states that equal objects must have equal hash codes.

    public <T> void shouldConformToEqualsAndHashcode(Instances<T> instances) {
        shouldConformToEquals(instances);
        shouldConformToHashcode(instances);
    }

    public <T> void shouldConformToEquals(Instances<T> instances) {
        shouldBeReflexive(instances);
        shouldBeSymmetric(instances);
        shouldBeTransitive(instances);
        shouldNotEqualNull(instances);
    }

    public <T> void shouldConformToHashcode(Instances<T> instances) {
        shouldHaveEqualHashcodes(instances);
    }

    public <T> void shouldBeReflexive(Instances<T> instances) {
        T a1 = instances.a();
        T a2 = instances.a();
        //must be distinct but equal instances
        assertThat(a1, not(sameInstance(a2)));
        assertThat(a1, is(a2));
    }

    public <T> void shouldBeSymmetric(Instances<T> instances) {
        T a = instances.a();
        T b = instances.b();
        shouldNotViolateSymmetry(a, b);
        T a2 = instances.a();
        shouldNotViolateSymmetry(a, a2);
    }

    public <T> void shouldNotViolateSymmetry(T a, T b) {
        if(a.equals(b)) {
            if(!b.equals(a))
                throw new AssertionError(String.format("expected symmetric equality, but b (%s) != a (%s)", b, a));
        } else {
            if(b.equals(a))
                throw new AssertionError(String.format("expected symmetric inequality, but a (%s) == b (%s)", a, b));
        }
    }

    public <T> void shouldBeTransitive(Instances<T> instances) {
        T a1 = instances.a();
        T a2 = instances.a();
        T a3 = instances.a();
        shouldNotViolateTransitivity(a1, a2, a3);
    }

    public <T> void shouldNotViolateTransitivity(T a1, T a2, T a3) {
        if(a1.equals(a2)) {
            if(a2.equals(a3)) {
                assertThat(a1, is(a3));
            }
            else {
                assertThat(a1, not(a3));
            }
        } else {
            if(a2.equals(a3)) {
                assertThat(a1, not(a3));
            }
            //can't deduce anything at this point
        }
    }

    public <T> void shouldNotEqualNull(Instances<T> instances) {
        T a = instances.a();
        checkNotNull(a);
        T b = instances.b();
        checkNotNull(b);
    }

    //well behaved objects will always return false, and never throw an exception when given null
    public <T> void checkNotNull(T a) {
        if(a.equals(null))
            throw new AssertionError(String.format("instance (%s) should not equal null", a));
    }

    public <T> void shouldHaveEqualHashcodes(Instances<T> instances) {
        T a1 = instances.a();
        T a2 = instances.a();
        assertThat(a1,  is(a2));
        int h1 = a1.hashCode();
        int h2 = a2.hashCode();
        assertThat(h1, is(h2));
    }
}
