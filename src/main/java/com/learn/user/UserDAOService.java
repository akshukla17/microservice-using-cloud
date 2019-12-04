package com.learn.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.learn.post.Post;

@Component
public class UserDAOService {

//	private static List<User> users = new ArrayList<User>();
//	private static int userCount = 4;
//	static {
//		users.add(new User(1, "ram", new Date()));
//		users.add(new User(2, "mickel", new Date()));
//		users.add(new User(3, "krishna", new Date()));
//		users.add(new User(4, "shiva", new Date()));
//	}

	@Autowired
	private UserRepository userRepository;
	// find all users
	public List<User> findAll() {
		return userRepository.findAll();
	}

	// find user by id
	public Optional<User> findOne(@PathVariable int id) {
		return userRepository.findById(id);
//		return users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
	}

	// save new user
	public User save(User user) {
		User savedUser = userRepository.save(user);
//		if (user.getId() == null) {
//			user.setId(++userCount);
//		}
		return savedUser;

	}

	public void deleteUser(int id) {
//		return users.removeIf(user -> user.getId() == id);
		userRepository.deleteById(id);
	}

	public Optional<User> allPostOfuser(int id) {
		return userRepository.findById(id);
	}
}
