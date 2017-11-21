
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import api.ApiUtil;
import datastructures.PostBody;

public class PostValidatorTest {

	PostBody pb = new PostBody();
	ApiUtil util = new ApiUtil();

	@Before
	public void setUp() {
		pb.setHanesst_id(1);
		pb.setPost_parent(-1);
		pb.setPost_title("ayyyyy");
		pb.setPost_type("story");
		pb.setPwd_hash("dsadfdsf");
		pb.setUsername("dsfasdf");
	}

	@Test
	public void validateValidPost() {
		assertTrue(util.validatePost(pb));
	}

	@Test
	public void validateNoHanesstIdPost() {
		pb.setHanesst_id(null);
		assertTrue(!util.validatePost(pb));
	}

	@Test
	public void validateNoPostParentPost() {
		pb.setPost_parent(null);
		assertTrue(!util.validatePost(pb));
	}

//	@Test
//	public void validateNoPostTitlePost() {
//		pb.setPost_title(null);
//		assertTrue(!util.validatePost(pb));
//		pb.setPost_title("");
//		assertTrue(!util.validatePost(pb));
//	}

	@Test
	public void validateNoPostType() {
		pb.setPost_type(null);
		assertTrue(!util.validatePost(pb));
		pb.setPost_type("");
		assertTrue(!util.validatePost(pb));
	}

	@Test
	public void validateNoPwdPost() {
		pb.setPwd_hash(null);
		assertTrue(!util.validatePost(pb));
		pb.setPwd_hash("");
		assertTrue(!util.validatePost(pb));
	}

	@Test
	public void validateNoUsernamePost() {
		pb.setUsername(null);
		assertTrue(!util.validatePost(pb));
		pb.setUsername("");
		assertTrue(!util.validatePost(pb));
	}

}
