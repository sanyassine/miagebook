package utils.sync.run;

import beans.Bean;
import beans.Profile;
import persistence.DataMapper;
import persistence.ProfileMapper;

public class ProfileSyncRunnable extends SynchronizeRunnable{

	public ProfileSyncRunnable(Bean obj, DataMapper mapper) {
		super(obj, mapper);
	}

	@Override
	public Profile findNewVersion() {
		Profile comment = (Profile) object;
		ProfileMapper postMapper = (ProfileMapper) mapper;
		return postMapper.find(comment.getLogin());
	}

	@Override
	public boolean equalsObject(Bean b1, Bean b2) {
		Profile p1 = (Profile)b1;
		Profile p2 = (Profile)b2;
		return p1.equals(p2);
	}

}
