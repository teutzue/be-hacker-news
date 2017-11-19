package datastructures;

import java.util.ArrayList;
import java.util.List;

public class Post {

	private List<Post> children = new ArrayList<>();
	private Post parent;
	private PostBody thisPost;
	
	
	public Post (Post parent, PostBody thisPost) {
		this.parent=parent;
		this.thisPost=thisPost;
		
	}
	
	public Post (PostBody thisPost) {
		this.thisPost=thisPost;
	}
	
	public Integer getParentId() {
		return parent.getThisPostId();
	}
	
	
	public List<Post> getChildren() {
		return children;
	}
	
	public Post getParent() {
		return parent;
	}
	
	public PostBody getThisPost() {
		return thisPost;
	}
	
	public void setThisPost(PostBody thisPost) {
		this.thisPost = thisPost;
	}
	
	public void addChild(Post child) {
		this.children.add(child);
	}
	
	public void setParent(Post parent) {
		this.parent = parent;
	}
	
	public Integer getThisPostId() {
		return thisPost.getHanesst_id();
	}
	
	
	
	
}
