package safeCounter;

public class TestCounterSafety {
	
	public static void main(String args[]) throws InterruptedException {
		int noThrds = 1000;
		int addPerThread=1000;
		Counter sharedCount= new Counter();
		
		// allocate threads
		CounterUpdateThread [] thrds= new CounterUpdateThread[noThrds];
		for (int i=0;i<noThrds;i++) {
			thrds[i]=new CounterUpdateThread(sharedCount,addPerThread);
		}
		
		// initiate threads
		for (int i=0;i<noThrds;i++) {
			thrds[i].start();
		}

		// wait for threads
		for (int i=0;i<noThrds;i++) {
			thrds[i].join();
		}

		int expectedVal = noThrds*addPerThread;
		System.out.println("Final value of counter is:" + sharedCount.get() + " and should be:" + expectedVal);
	}
}