package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndPoints {

    public  static Response createUser(User payload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
        .when().post(Route.createUser);

        return  response;
    }

    public  static  Response getUser(String userName){
        Response response = given()
                .pathParam("username",userName )
        .when()
                .get(Route.getUser);

        return response;

    }

    public static  Response updateUser(User payLoad, String userName){
         Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", userName)
                 .body(payLoad)
         .when()
                .put(Route.updateUser);
         return  response;
    }

    public static  Response deleteUser(String userName){
        Response response = given()
                .contentType(ContentType.JSON)
                .pathParam("username",userName)
        .when().delete(Route.deleteUser);

        return  response;
    }
}
