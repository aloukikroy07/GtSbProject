package com.gt.repository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.gt.model.User;

@Repository
public class UserRepositoryClass {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<User> findUserById(User u) {
		String sql = "SELECT * FROM T_user where TX_USER_NAME = \'"+u.getTxUserName()+"\' and TX_USER_PASSWORD = \'"+u.getTxUserPassword()+"\'";
		List <User> userData = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));
		return userData;
	}
	
	public List<User> findUser(Integer userId) {
		String sql = "SELECT * FROM T_user where ID_USER_KEY = '"+userId+"'";
		List <User> userData = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));
		return userData;
	}
	
	public List<User> getAllUsers() {
		String sql = "select ID_USER_KEY, TX_USER_NAME, TX_USER_FUL_NAME, TX_USER_EMAIL, TX_USER_MOBILE from t_user order by ID_USER_KEY ASC";
		List <User> userData = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));
		return userData;
	}
	
	public int addUser(User u, Integer loggedUser) {
		List<User> user = null;
		Integer loggedUserId = loggedUser;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yy h:mm:ss.SSSSSS a");
		String formattedDate = sdf.format(timestamp);
		String sql1 = "SELECT * FROM T_user where TX_USER_NAME = '"+u.getTxUserName()+"'";
		List <User> userData = jdbcTemplate.query(sql1, BeanPropertyRowMapper.newInstance(User.class));
		if (userData.isEmpty()) {
			String sql = "insert into T_user (ID_USER_VER, ID_USER_MOD_KEY, DT_MOD_TIME, TX_USER_NAME, TX_USER_FUL_NAME, TX_USER_EMAIL, TX_USER_PASSWORD, TX_USER_MOBILE) values (0, '"
					      +loggedUserId+"', '"+formattedDate+"', '"+u.getTxUserName()+"', '"+u.getTxUserFulName()+"', '"+u.getTxUserEmail()+"', '"+u.getTxUserPassword()+"', '"+u.getTxUserMobile()+"')";
			jdbcTemplate.update(sql);
			user = findUserById(u);
			if (!user.isEmpty()){
				return 1;
			}
			else {
				return 0;
			}
		}
		else {
			return 0;
		}
	}
	
	public List<Integer> findSubMenuKeyById(User user) {
		/*getting submenu key which was permitted to user*/
		String sql = "select id_submenu_key from T_permission where id_user_key = "+user.getIdUserKey();
		List <Integer> submenuData = jdbcTemplate.queryForList(sql, Integer.class);
		
		return submenuData;
	}
	
	public String concateIntegers (List<Integer> subMenuKeys) {
		
		String subMenus = "";
		for (int i = 0; i < subMenuKeys.size(); i++) {
			subMenus = subMenus + subMenuKeys.get(i).toString();
			if(i != (subMenuKeys.size()-1)) {
				subMenus = subMenus + ", ";
			}
		}
		return subMenus;
	}
	
	public List<Integer> findMenuKey(List<Integer> subMenuKeys) {	
		String subMenus = concateIntegers(subMenuKeys);
		String sql = "select distinct id_menu_key from T_submenu where id_menu_key in ( "+subMenus+" )";
		List <Integer> submenuData = jdbcTemplate.queryForList(sql, Integer.class);
		
		return submenuData;
	}
	
	public List<String> findMenuByMenuId(Integer menuKeys) {
		String sql = "select tx_menu_name from T_menu where id_menu_key = "+menuKeys;
		List <String> menuName = jdbcTemplate.queryForList(sql, String.class);
		
		return menuName;
	}
	
	public List<String> findSubMenuName(List<Integer> subMenuKeys, Integer menuKey) {
		String subMenus = concateIntegers(subMenuKeys);
		String sql = "select tx_submenu_name from T_submenu where id_menu_key = "+menuKey+" and id_submenu_key in ( "+subMenus+" )";
		List <String> submenuName = jdbcTemplate.queryForList(sql, String.class);
		
		return submenuName;
	}
	
	public Integer deleteUser(Integer id) {
		List<User> user = null;
		String sql = "DELETE FROM T_user where ID_USER_KEY = '"+id+"'";
		jdbcTemplate.update(sql);
		user = findUser(id);
		if (!user.isEmpty()){
			return 0;
		}
		else {
			return 1;
		}
	}
	
	public Integer UpdateUser(Integer loggedUser, Integer id, String uname, String fname, String eaddr, String mobno, String pass) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yy h:mm:ss.SSSSSS a");
		String formattedDate = sdf.format(timestamp);
		String sql = "UPDATE T_USER SET ID_USER_MOD_KEY = '"+loggedUser+"', DT_MOD_TIME = '"+formattedDate+"', TX_USER_NAME = '"+uname+"', TX_USER_FUL_NAME = '"+fname+"', TX_USER_EMAIL = '"+eaddr+"', TX_USER_PASSWORD = NVL('"+pass+"', TX_USER_PASSWORD), TX_USER_MOBILE = '"+mobno+"' where ID_USER_KEY = '"+id+"'";
		jdbcTemplate.update(sql);
		
		String sql1 = "SELECT * FROM T_USER WHERE ID_USER_MOD_KEY = '"+loggedUser+"' and DT_MOD_TIME = '"+formattedDate+"' and TX_USER_NAME = '"+uname+"' and TX_USER_FUL_NAME = '"+fname+"' and TX_USER_EMAIL = '"+eaddr+"' and TX_USER_PASSWORD = '"+pass+"' and TX_USER_MOBILE = '"+mobno+"' and ID_USER_KEY = '"+id+"'";
		List<User> userData = jdbcTemplate.query(sql1, BeanPropertyRowMapper.newInstance(User.class));
		List<User> user = null;
		user = userData;
		if(!user.isEmpty()) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	public String ChangePassword(String oldPassword, String newPassword, Integer userID) {
		String sql = "select TX_USER_PASSWORD from T_USER where ID_USER_KEY = '"+userID+"'";
		List<User> userData = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));
		if(userData.get(0).getTxUserPassword().equals(oldPassword)){
			String sql1 = "UPDATE T_USER SET TX_USER_PASSWORD = '"+newPassword+"' where ID_USER_KEY = '"+userID+"'";
			jdbcTemplate.update(sql1);
			return "success";
		}
		
		else {
			return "failed";
		}
	}
	
	public List<User> GetAllUsers(){
		String sql = "select * from t_user order by TX_USER_NAME ASC";
		List<User> userData = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));
		return userData;
	}
}
