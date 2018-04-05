package utils.sync.thread;

import beans.Bean;
import persistence.DataMapper;
import utils.sync.run.PostSyncRunnable;
import utils.sync.run.SynchronizeRunnable;

public class PostSyncThread extends SyncObjectThread {

	public PostSyncThread(Bean obj, DataMapper mapper) {
		super(obj, mapper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public SynchronizeRunnable getSynchronizedRunnable() {
		return new PostSyncRunnable(object, mapper);
	}

}
