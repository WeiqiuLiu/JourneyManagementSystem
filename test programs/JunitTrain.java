import static org.junit.Assert.*;

import org.junit.Test;

@SuppressWarnings("serial")
public class JunitTrain extends train {

	public JunitTrain(int a, String mode, String m) {
		super(a, mode, m);
	}

	@Test
	public void test() {
		train t=new train(1,"FX","running");
		assertEquals("running",t.getmaintain());
		assertEquals(1,t.gettrainid());
		assertEquals("FX",t.getmode());
	}

}
