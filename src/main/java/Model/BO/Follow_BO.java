package Model.BO;

import java.util.ArrayList;
import java.util.List;

import Model.BEAN.Following_Post;
import Model.BEAN.User;
import Model.DAO.Follow_DAO;
import Model.DAO.User_DAO;

public class Follow_BO {

	private static Follow_BO instance;

	public static Follow_BO getInstance() {
		if (instance == null) {
			instance = new Follow_BO();
		}
		return instance;
	}

	private Follow_BO() {
	}

	public List<User> getFollowing(int id) throws Exception{
		return Follow_DAO.getInstance().getFollowing(id);
	}
	
	public ArrayList<Integer> getIdFollowing(int id) throws Exception{
		return Follow_DAO.getInstance().getIdFollowing(id);
	}
	
	public ArrayList<Following_Post> getFollowingPost(ArrayList<Integer> listIdFollowing) throws Exception{
		return Follow_DAO.getInstance().getFollowingPost(listIdFollowing);
	}
}
