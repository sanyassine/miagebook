package utils.sync.thread;

import beans.Bean;
import persistence.DataMapper;
import utils.sync.run.SynchronizeRunnable;

public abstract class SyncObjectThread extends Thread {
	protected Bean object;
	protected DataMapper mapper;
	protected static final long FREQUENCE = 5 * 1000;
	public SyncObjectThread(Bean obj, DataMapper mapper) {
		object = obj;
		this.mapper = mapper;
	}
	
	public void start() {
		super.start();
		SynchronizeRunnable runnable = getSynchronizedRunnable();
		while(true) {
			runnable.run();
			try {
				Thread.sleep(FREQUENCE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public abstract SynchronizeRunnable getSynchronizedRunnable();
	
	
}
