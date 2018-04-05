package utils.sync.run;

import beans.Bean;
import beans.Post;
import persistence.DataMapper;
import persistence.PostMapper;

public class PostSyncRunnable extends SynchronizeRunnable{

	public PostSyncRunnable(Bean object, DataMapper mapper) {
		super(object, mapper);
	}

	@Override
	public Bean findNewVersion() {
		Post post = (Post) object;
		PostMapper postMapper = (PostMapper) mapper;
		return postMapper.find(post.getIdPost());
	}

	@Override
	public boolean equalsObject(Bean b1, Bean b2) {
		Post p1 = (Post)b1;
		Post p2 = (Post)b2;
		return p1.equals(p2);
	}

}
