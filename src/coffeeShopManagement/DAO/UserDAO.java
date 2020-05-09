package coffeeShopManagement.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import coffeeShopManagement.Model.User;

//This DAO class provides CRUD database operations for table users in the database.
public class UserDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/coffeeshopdatabase?useSSL=false&serverTimezone=UTC";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	
	private static final String INSERT_USER_SQL = "INSERT INTO users" + " (name, email, country) VALUES " 
			+ " (?, ?, ?); "; 
	private static final String SELECT_USER_BY_ID = "SELECT * FROM users where id =?";
	private static final String SELECT_ALL_USERS = "SELECT * FROM users";
	private static final String UPDATE_USER_SQL = "UPDATE users SET name =?, email =?, country =? where id=?";
	private static final String DELETE_USER_SQL = "DELETE from users where id=?";
	
	protected Connection getConnection() {
		Connection connection = null;
		try { 
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	//Create or insert user
	public void insertUser(User user) throws SQLException {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL);) {
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getCountry());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//Update user
	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL);) {
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getCountry());
			preparedStatement.setInt(4, user.getId());
			System.out.println(preparedStatement);
			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	//Select user by id
	public User selectUser(int id) {
		User user = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) { 
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				user = new User(id, name, email, country);
			} 
		}	catch (SQLException e) {
				e.printStackTrace();
		}
		return user;
	}
	
	//Select users
	public List<User> selectAllUsers() {
		List <User> users = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) { 
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				users.add(new User(id, name, email, country));
			} 
		}	catch (SQLException e) {
				e.printStackTrace();
		}
		return users;
	}
	
	//Delete user
	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
}
