package njurestaurant.njutakeout.springcontroller.user;

import io.swagger.annotations.*;
import njurestaurant.njutakeout.blservice.admin.AdminBlService;
import njurestaurant.njutakeout.blservice.user.UserBlService;
import njurestaurant.njutakeout.response.InfoResponse;
import njurestaurant.njutakeout.response.WrongResponse;
import njurestaurant.njutakeout.response.event.EventLoadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class userController {
    private final UserBlService userBlService;

    @Autowired
    public userController(UserBlService userBlService) {
        this.userBlService = userBlService;
    }
    
}
