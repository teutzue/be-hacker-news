package datastructures;

import java.util.List;

import datastructures.post.PostBody;

public class WholeStoryDTO {

	private PostBody story;
	private List<PostBody> children;
	private Long count;
	
	public WholeStoryDTO() {
		
	}
	
	public WholeStoryDTO(PostBody story, List<PostBody> children, Long count) {
		this.story = story;
		this.children = children;
		this.count = count;
	}
	public PostBody getStory() {
		return story;
	}
	public void setStory(PostBody story) {
		this.story = story;
	}
	public List<PostBody> getChildren() {
		return children;
	}
	public void setChildren(List<PostBody> children) {
		this.children = children;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	
	
	
}
