import static org.junit.Assert.*;

import org.junit.Test;

public class JunitControl extends control {

	@Test
	public void test() {
		assertEquals(true,isint(1+""));
		assertEquals(true,istime("11:00"));
		assertEquals(false,istime("1:00"));
		
		assertEquals(11,changestring("11:00").gethour());
		assertEquals(0,changestring("11:00").getminute());
	}

}
