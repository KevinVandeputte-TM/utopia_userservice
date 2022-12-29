package fact.it.utopia_userservice.repository;

import fact.it.utopia_userservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    public User findByUserID(int userID);
}
