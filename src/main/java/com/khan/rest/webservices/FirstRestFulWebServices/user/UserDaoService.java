package com.khan.rest.webservices.FirstRestFulWebServices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	private static int usersCount = 0;
	static {
		users.add(new User(++usersCount, "Khan", LocalDate.now().minusYears(25), "khan@gmail.com"));
		users.add(new User(++usersCount, "pathan", LocalDate.now().minusYears(50), "pathan@gmail.com"));
		users.add(new User(++usersCount, "tigher", LocalDate.now().minusYears(52), "tigher@gmail.com"));
		users.add(new User(++usersCount, "jim", LocalDate.now().minusYears(40), "jim@gmail.com"));
		users.add(new User(++usersCount, "kabir", LocalDate.now().minusYears(35), "kabir@gmail.com"));
	}
	public List<User> findAll(){
		return users;
	}
	public User findOne(int id) {
		Predicate<? super User> predicate =user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);
	}
	public User save(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}
//	public User findOne(int id);
	public void deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);
	}
}
