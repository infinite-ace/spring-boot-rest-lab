package com.infinitelambda.userservice;

import com.infinitelambda.userservice.model.UserService;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeUnit(id="client-initializer", order = "1", author = "mongock")
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
        System.out.println("changeset");
//        thirdPartyService.getData()
//                .stream()
//                .forEach(client -> mongoTemplate.save(client, CLIENTS_COLLECTION_NAME));
    }

    /**
     * This method is mandatory even when transactions are enabled.
     * They are used in the undo operation and any other scenario where transactions are not an option.
     * However, note that when transactions are avialble and Mongock need to rollback, this method is ignored.
     **/
    @RollbackExecution
    public void rollback() {
//        mongoTemplate.deleteMany(new Document());
        System.out.println("rollback");
    }
}