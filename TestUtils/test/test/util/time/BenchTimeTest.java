package test.util.time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import common.helper.ReflectUtils;

public class BenchTimeTest {
	
	@Before
	public void setUp() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		ReflectUtils.setValueInField(BenchTime.newInstance(), "instance", null);
	}
	
	
    @Test
    public void newInstanceCase001() throws NoSuchFieldException, SecurityException {
    	BenchTime.newInstance();
    	assertTrue(BenchTime.newInstance() instanceof BenchTime);
    }
    
    @Test
    public void start001() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    	BenchTime.newInstance().start();
    	long val = (long) ReflectUtils.getValueInField(BenchTime.newInstance(), "startTime");
    	assertTrue(val > 0);
    }
    
    @Test
    public void stop001() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    	ReflectUtils.setValueInField(BenchTime.newInstance(), "isMeasured", true);
    	BenchTime.newInstance().stop();
    	long val = (long) ReflectUtils.getValueInField(BenchTime.newInstance(), "stopTime");
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
        ReflectUtils.setValueInField(BenchTime.newInstance(), "isMeasured", true);
    	ReflectUtils.setValueInField(BenchTime.newInstance(), "startTime", System.currentTimeMillis());
        Thread.sleep(1);
    	long res = BenchTime.newInstance().stop();
    	assertTrue(res > 0);
    }
    
    @Test
    public void stop004() throws InterruptedException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        try {
    	    ReflectUtils.setValueInField(BenchTime.newInstance(), "isMeasured", true);
    	    ReflectUtils.setValueInField(BenchTime.newInstance(), "startTime", System.currentTimeMillis()+1000);
            Thread.sleep(1);
    	    BenchTime.newInstance().stop();
        }catch (RuntimeException e) {
    	    assertEquals(e.getMessage(), "The start time is later than the end time.");
        }
    }
    
    @Test
    public void print001() throws InterruptedException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        try {  	
        	ReflectUtils.setValueInField(BenchTime.newInstance(), "isMeasured", false);
    	    ReflectUtils.setValueInField(BenchTime.newInstance(), "startTime", 10L);
    	    ReflectUtils.setValueInField(BenchTime.newInstance(), "stopTime", 1L);
    	    BenchTime.newInstance().print();
        }catch (RuntimeException e) {
    	    assertEquals(e.getMessage(), "The start time is later than the end time.");
        }
    }
    
    
    
}
