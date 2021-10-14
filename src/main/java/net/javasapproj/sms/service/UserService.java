package net.javasapproj.sms.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javasapproj.sms.entity.User;
import net.javasapproj.sms.repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	UserRepo userRepo;

	public Map<String, String> login(User user) {
		Map<String, String> resp = new HashMap<String, String>();

		String username = user.getUsername();
		String password = user.getPwd();

		// case1: null or empty values received from client
		if (username == null || username.isEmpty() || password == null || password.isEmpty()) {

			resp.put("msg", "pl. enter valid credentials");
			resp.put("status", "4");

			return resp;
		}

		User usr = userRepo.findByUsername(username);

		if (usr == null) {
			// case2: user not found in the database
			resp.put("msg", "Account doesn't exists, pl. signup to continue");
			resp.put("status", "3");

			return resp;
		}

		// case3: check user exists
		if (username.equals(usr.getUsername())) {
			if (password.equals(usr.getPwd())) {

				resp.put("msg", "login successful");
				resp.put("status", "1");

				return resp;
			}

		}

		// case4: provided password is wrong

		resp.put("msg", "pl. check your credentials");
		resp.put("status", "2");

		return resp;

	}
	public User addUser(User user) throws Exception {
		if (user.getUsername() == null || user.getUsername().isEmpty() || user.getPwd() == null
				|| user.getPwd().isEmpty()) {
			throw new Exception("Mandotory fields are missing");
		}
		return userRepo.save(user);
	}

}
