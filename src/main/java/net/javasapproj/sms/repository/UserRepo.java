package net.javasapproj.sms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.javasapproj.sms.entity.User;
@Repository
public interface UserRepo extends CrudRepository<User, Long>{
	User findByUsername(String username);

}
