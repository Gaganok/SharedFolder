package shared.service;

import shared.model.FileMonitor;

public class Monitor implements Runnable{

	private FileMonitor monitor;

	public Monitor(FileMonitor m){monitor = m;}

	@Override
	public void run() {
		
		System.out.println("Started");
		while(true) {
				System.out.println("Monitoring");
				monitor.monitor();
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					System.out.println(e);
				}
		}
	}
}
