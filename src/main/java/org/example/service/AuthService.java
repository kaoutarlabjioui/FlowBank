package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.repository.impl.InMemoryUserRepository;
import org.example.utils.ConsoleUtils;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;


public class AuthService {

private static final InMemoryUserRepository userRepository= InMemoryUserRepository.getInstance();
 public static User loggedInUser;

  public static String register(){
    String fullName = ConsoleUtils.readString("FullName : ");
    String email;

    while (true){

        email = ConsoleUtils.readString("Email : ");

        String emailRegex = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if ( email.matches(emailRegex)) break;
        System.out.println("Invalid Email try again");
    }
    String password ;
    while (true){

        password = ConsoleUtils.readString("Password (min 6 chars) : ");
        if (password.length()>=6 ) break;
        System.out.println("Invalid Password try again");
    }

    String address = ConsoleUtils.readString("Address : ");

    User user = new User(fullName, email, password, address);

       userRepository.save(user);

       return login(user.getEmail(), password);

}

public static String login(){
      return login(null, null);
}

public static String login (String email, String password){

      if (email==null) {
           email = ConsoleUtils.readString("Email : ");
      }
      if (password==null) {
          password = ConsoleUtils.readString("Password : ");
      }

      Optional<User> user = userRepository.findByEmail(email);
      if(user.isEmpty()) return "User not found ";

      User userObj = user.get();

      if(BCrypt.checkpw(password,userObj.getPassword())){
         loggedInUser = userObj;

         return "login successful" + userObj.getFullName();
      } else return "invalid password";

}

public static void logout(){
      loggedInUser = null;
}


  public static void changePassword() {
    User curentUser = loggedInUser;
    if (curentUser == null) {
        System.out.println("You are not logged in.");
    }

    String newPassword;

    while (true) {
        newPassword = ConsoleUtils.readString("New Password : ");
        if (newPassword.length() >= 6) break;
        System.out.println("Invalid Password try again");
    }
    String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
      InMemoryUserRepository.update(curentUser, hashedPassword);

    System.out.println("Password changed successfully.");

   }
}
