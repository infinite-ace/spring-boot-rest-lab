package com.infinitelambda.userservice.migrations;

import com.infinitelambda.userservice.model.User;
import com.infinitelambda.userservice.model.UserService;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;

// Here we change the id of the migration in order to run it
@ChangeUnit(id="client-initializer-4", order = "1", author = "mongock")
public class UserInitializerChange {

    private final MongoTemplate mongoTemplate;

    private final UserService userService;

    public UserInitializerChange(MongoTemplate mongoTemplate,
                                   UserService userService) {
        this.mongoTemplate = mongoTemplate;
        this.userService = userService;
    }


    /**
     * This is the method with the migration code
     **/
    @Execution
    public void changeSet() {
        User user = new User();
        user.setId("zj1103-12xcny1-su12na-12n12msc");
        user.setFavouriteNumber(4);
        user.setFirstName("Michael");
        user.setLastName("Bradley");

        userService.addUser(user);
    }

    /**
     * This method is mandatory even when transactions are enabled.
     * They are used in the undo operation and any other scenario where transactions are not an option.
     * However, note that when transactions are avialble and Mongock need to rollback, this method is ignored.
     **/
    @RollbackExecution
    public void rollback() {
        userService.deleteUser(userService.findUserById("zj1103-12xcny1-su12na-12n12msc"));
//        mongoTemplate.deleteMany(new Document());
    }
}