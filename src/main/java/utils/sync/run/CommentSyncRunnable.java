package utils.sync.run;

import beans.Bean;
import beans.Comment;
import persistence.CommentMapper;
import persistence.DataMapper;

public class CommentSyncRunnable extends SynchronizeRunnable{

	public CommentSyncRunnable(Bean obj, DataMapper mapper) {
		super(obj, mapper);
	}

	@Override
	public Bean findNewVersion() {
		Comment comment = (Comment) object;
		CommentMapper postMapper = (CommentMapper) mapper;
		return postMapper.find(comment.getIdComment());
	}

	@Override
	public boolean equalsObject(Bean b1, Bean b2) {
		Comment c1 = (Comment)b1;
		Comment c2 = (Comment)b2;
		return c1.equals(c2);
	}

}
