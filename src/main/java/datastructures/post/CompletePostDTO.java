package datastructures.post;

public class CompletePostDTO {

	private PostBody post;
	private Long count;
	
	/**
	 * @param post
	 * @param count
	 */
	public CompletePostDTO(PostBody post, Long count) {
		this.post = post;
		this.count = count;
	}
	
	public CompletePostDTO() {
		
	}
	public PostBody getPost() {
		return post;
	}
	public void setPost(PostBody post) {
		this.post = post;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	
}
