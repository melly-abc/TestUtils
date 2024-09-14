package test.util.time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.jupiter.api.Test;

public class BenchTimeTest {
	
	@Before
	public void setUp() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    	Field instance = BenchTime.class.getDeclaredField("instance");
    	instance.setAccessible(true);
    	instance.set(BenchTime.newInstance(), null);
	}
	
	
    @Test
    public void newInstanceCase001() throws NoSuchFieldException, SecurityException {
    	BenchTime.newInstance();
    	assertTrue(BenchTime.newInstance() instanceof BenchTime);
    }
    
    @Test
    public void start001() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    	BenchTime.newInstance().start();
    	Field startTime = BenchTime.class.getDeclaredField("startTime");
    	startTime.setAccessible(true);
    	long val = (long) startTime.get(BenchTime.newInstance());
    	assertTrue(val > 0);
    }
    
    @Test
    public void stop001() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    	Field isMeasured = BenchTime.class.getDeclaredField("isMeasured");
    	isMeasured.setAccessible(true);
    	isMeasured.set(BenchTime.newInstance(), true);
    	BenchTime.newInstance().stop();
    	Field stopTime = BenchTime.class.getDeclaredField("stopTime");
    	stopTime.setAccessible(true);
    	long val = (long) stopTime.get(BenchTime.newInstance());
    	assertTrue(val > 0);
    }
    
    @Test
    public void stop002() {
        try {
    	    BenchTime.newInstance().stop();
        }catch (RuntimeException e) {
        	assertEquals(e.getMessage(), "The measurement has not been started.");
        }
    }
    
    @Test
    public void stop003() throws InterruptedException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
    	    Field startTime = BenchTime.class.getDeclaredField("startTime");
    	    startTime.setAccessible(true);
    	    startTime.set(BenchTime.newInstance(), System.currentTimeMillis());
        	Field isMeasured = BenchTime.class.getDeclaredField("isMeasured");
        	isMeasured.setAccessible(true);
        	isMeasured.set(BenchTime.newInstance(), true);
        	Thread.sleep(1);
    	    long res = BenchTime.newInstance().stop();
    	    assertTrue(res > 0);
    }
    
}
