package app.com.moviez.anant.moviez.data;

import android.test.AndroidTestCase;

/**
 * Created by anant on 2015-10-18.
 */
public class TestPractice extends AndroidTestCase{

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }


    public void testThatDemonstratesAssertions() throws Throwable {
        int a = 5;
        int b = 3;
        int c = 5;
        int d = 10;


        assertEquals("X should be equal", a, c);
        assertTrue("Y should be true", d > a);
        assertFalse("Z should be false", a == b);


        if (b > d) {
            fail("XX should never happen");
        }
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
