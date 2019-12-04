package com.learn.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class UserDAOService {

	private static List<User> users = new ArrayList<User>();
	private static int userCount = 4;
	static {
		users.add(new User(1, "ram", new Date()));
		users.add(new User(2, "mickel", new Date()));
		users.add(new User(3, "krishna", new Date()));
		users.add(new User(4, "shiva", new Date()));
	}

	// find all users
	public List<User> findAll() {
		return users;
	}

	// find user by id
	public User findOne(@PathVariable int id) {
		return users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
	}

	// save new user
	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++userCount);
		}
		return user;

	}

	public boolean deleteUser(int id) {
		return users.removeIf(user -> user.getId() == id);
	}
}
