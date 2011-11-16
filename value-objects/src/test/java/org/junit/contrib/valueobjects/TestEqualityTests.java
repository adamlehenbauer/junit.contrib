package org.junit.contrib.valueobjects;

import org.junit.Test;

/**
 * @author Adam Lehenbauer <adamlehenbauer>
 */
public class TestEqualityTests {

    @Test
    public void should_accept_Double() {
        new EqualityTests().shouldConformToEqualsAndHashcode(new Instances<Double>() {

            public Double a() {
                return new Double("3.14159265");
            }

            public Double b() {
                return new Double("2.71828183");
            }
        });
    }

    @Test
    public void should_accept_String() {
        new EqualityTests().shouldConformToEqualsAndHashcode(new Instances<String>() {

            public String a() {
                return new String("some kind of string");
            }

            public String b() {
                return new String("a totally different string");
            }
        });
    }

    //TODO be more specific about what should fail
    @Test(expected = AssertionError.class)
    public void should_not_accept_Object() {
        new EqualityTests().shouldConformToEquals(new Instances<Object>() {

            public Object a() {
                return new Object();
            }

            public Object b() {
                return new Object();
            }
        });
    }
}
