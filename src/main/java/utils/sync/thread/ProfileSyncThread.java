package utils.sync.thread;

import beans.Bean;
import persistence.DataMapper;
import utils.sync.run.ProfileSyncRunnable;
import utils.sync.run.SynchronizeRunnable;

public class ProfileSyncThread extends SyncObjectThread {

	public ProfileSyncThread(Bean obj, DataMapper mapper) {
		super(obj, mapper);
	}

	@Override
	public SynchronizeRunnable getSynchronizedRunnable() {
		return new ProfileSyncRunnable(object, mapper);
	}

}
