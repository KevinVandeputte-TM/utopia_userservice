package fact.it.utopia_userservice.controller;

import fact.it.utopia_userservice.model.User;
import fact.it.utopia_userservice.model.UserDTO;
import fact.it.utopia_userservice.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepo;

    public UserController(UserRepository userRepository){
        this.userRepo = userRepository;
    }

    private Random rnd = new Random();

    @PostConstruct
    public void fillDBtemp(){
        if(userRepo.count()==0){
            for (int j = 1; j < 11; j++){
                User u = new User();
                u.setUserID(j);
                u.setName("user_"+j);
                u.setBirthyear(2003);
                u.setScore(rnd.nextInt(10));
                userRepo.save(u);
            }
        }
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }
    @GetMapping("/user/{userID}")
    public User getUserById(@PathVariable int userID){
        return userRepo.findByUserID(userID);
    }

    @GetMapping("/highscores")
    public List<User> getHighscores(){
        return userRepo.findFirst10ByOrderByScoreDesc();
    }

    /*Create new User*/
    @PostMapping("/user")
    public User create(@RequestBody UserDTO user){
        User peristentUser = new User(user.getUserID(), user.getName(),user.getInterest(),user.getBirthyear(),user.getScore());
        userRepo.save(peristentUser);
        return peristentUser;
    }

    /*Update User*/
    @PutMapping("/user")
    public ResponseEntity<Void> updateUser(@RequestBody UserDTO userToUpdate){
        Optional<User> userOptional = Optional.ofNullable(userRepo.findByUserID(userToUpdate.getUserID()));
        //if user exists --> update score and stations visited
        if(userOptional.isPresent()){
            User u = userOptional.get();
            u.setScore(userToUpdate.getScore());
            u.setStationsVisited(userToUpdate.getStationsVisited());
            userRepo.save(u);
            return new ResponseEntity<>( HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/user/{userID}")
    public ResponseEntity<Void>deleteUser(@PathVariable int userID){
        Optional<User>user= Optional.ofNullable(userRepo.findByUserID(userID));
        if(user.isPresent()){
            User userToDelete = user.get();
            userRepo.delete(userToDelete);
            return new ResponseEntity<>( HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
