package api;

import datastructures.User;
import datastructures.post.PostBody;

public class ApiUtil {

	public boolean validatePost(PostBody pb) {
		boolean valid = true;
		if (pb.getPost_parent() == null)
			return false;
//		if (pb.getPost_title() == null || pb.getPost_title().equals(""))
//			return false;
		if (pb.getPost_parent() == null)
			return false;
		if (pb.getPost_type() == null || pb.getPost_type().equals(""))
			return false;
		if (pb.getPwd_hash() == null || pb.getPwd_hash().equals(""))
			return false;
		if (pb.getUsername() == null || pb.getUsername().equals(""))
			return false;
		if (pb.getHanesst_id() == null)
			return false;

		return valid;
	}

	public boolean validateUser(User u) {
		boolean valid = true;

		if (u.getUser_name() == null || u.getUser_name().equals("")) {
			return false;
		}

		if (u.getUser_pwd() == null || u.getUser_pwd().equals("")) {
			return false;
		}

		return valid;
	}
}
