package com.dev.neosearch;

import org.apache.log4j.Logger;

public class NeoSearchApp {
	public static void main(String[] args) {
		String key = "DEMO_KEY";
		boolean isTesting =false;
		for(int i =0; i< args.length; i++){
			String s = args[i];
			if(s.length()==2&&s.charAt(0)=='-'&&s.charAt(1)=='t') {
				isTesting = true;
				break;
			}
	         if(s.length()==2&&s.charAt(0)=='-'&&s.charAt(1)=='k') {
	                if(i+1<args.length)
	                    key=args[i+1];
	                break;
	            }
		}
		if(isTesting) {
			testData.getInstance().init();
			run(new NeoRecordTestFetcher(), key);
		}
		else {
			run(new NeoRecordNasaFetcher(), key);
		}
	}
	final static Logger logger = Logger.getLogger(NeoSearchApp.class);
	public static void run(NeoRecordFetcher fetchStrategy, String key) {
		try {
			logger.debug("NeoSearchApp started ...");
			
			NeoSearchScheduler neoManager = new NeoSearchScheduler(fetchStrategy, key);
			Thread t1 = new Thread(neoManager);
			t1.start();
			t1.join();
			logger.debug("NeoSearchApp exited");
		}
		catch(InterruptedException e) {
			logger.debug(e.toString());
		}
	}
}
