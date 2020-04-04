package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.mockito.internal.matchers.Contains;
import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private int userCount = 3;

	static {
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Eve", new Date()));
		users.add(new User(3, "Jack", new Date()));
	}

	public List<User> findAll() {

		return users;
	}

	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}

	public User findOne(int id) {
		/*
		 * for (User user : users) { if (user.getId() == id) { return user; } }
		 */
		User user = users.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);

		return user;
	}

	public User deleteById(int id) {
		/*Iterator<User> iterator = users.iterator();

		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				return user;
			}
		}*/
//JAVA 8
		users.removeIf(p->p.getId().equals(id));
		return null;
	}
}
