package fact.it.utopia_userservice.repository;

import fact.it.utopia_userservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUserID(int userID);
    List<User> findFirst10ByOrderByScoreDesc();

}
