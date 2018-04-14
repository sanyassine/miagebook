package utils.sync.thread;

import beans.Bean;
import persistence.DataMapper;
import utils.sync.run.CommentSyncRunnable;
import utils.sync.run.SynchronizeRunnable;

public class CommentSyncThread extends SyncObjectThread {

	public CommentSyncThread(Bean obj, DataMapper mapper) {
		super(obj, mapper);
	}
	
	@Override
	public SynchronizeRunnable getSynchronizedRunnable() {
		return new CommentSyncRunnable(object, mapper);
	}
	
	

}
