package com.example.pattern.proxyCache;

import java.util.List;
import android.content.Context;

public class Repository {
	
	private Context context;
	private CommentsDataAccessObject commentDataSource;
	private CommentCache commentCache;
	
	public Repository(Context context) {
	    this.context = context;
	    commentDataSource = new CommentsDataAccessObject(context);
	    commentCache = new CommentCache(commentDataSource);
	    commentDataSource.open();
	}
	
	public Comment createComment(String comment) {
		return commentDataSource.createComment(comment);
	}
	
	public Comment getComment(long commentId){
		
		//using cache model instead of directly accessing commentDAO
		return commentCache.getComment(commentId);
	}
	
	public void deleteComment(Comment comment) {
		commentDataSource.deleteComment(comment);
	}

	public List<Comment> getAllComments() {
		return commentDataSource.getAllComments();
	}

	public void open() {
		commentDataSource.open();
		
	}

	public void close() {
		commentDataSource.close();
	}
	
}
