import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

@SuppressWarnings("serial")
public class JunitJourney extends journey {




	public JunitJourney(route r, ArrayList<time> t) {
		super(r, t);
	}

	@Test
	public void test() {
		time t1=new time(11,11);
		time t2=new time(12,12);
		time t3=new time(13,13);
		assertEquals(1,comparetime(t2,t1));
		assertEquals(0,comparetime(t2,t3));
		assertEquals(1,comparetime(t3,t3));
	}

}
