package uz.maniac4j.quiz_monolithic.email;

import org.springframework.stereotype.Service;
import uz.maniac4j.quiz_monolithic.user.User;

@Service
public class EmailService {



    public boolean sendCode(User user){
        try {
            System.out.println(user.getCode());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }


}
