package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {

    Faker faker;
    User userPayload;

    @BeforeClass
    public void setUp(){
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        userPayload.setUsername(faker.name().username());
        userPayload.setPassword(faker.internet().password());

    }

    @Test(priority = 1)
    public  void testPostUser(){
       Response response = UserEndPoints.createUser(userPayload);
       response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

    }

    @Test(priority = 2)
    public void  testGetUserByName()
    {
        Response response = UserEndPoints.getUser(userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }


    @Test(priority = 3)
    public void testUpdateUserByName(){
        //update data using payload

        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPoints.updateUser(userPayload ,this.userPayload.getUsername());
        response.then().log().body().statusCode(200);

        Assert.assertEquals(response.getStatusCode(),200);

        //Validate data after update

        Response responseAfterUpdate = UserEndPoints.getUser(this.userPayload.getUsername());
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
    }

    @Test(priority = 4)
    public void  testDeleteUser(){
        Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());

        Assert.assertEquals(response.getStatusCode(),200);

    }
}
