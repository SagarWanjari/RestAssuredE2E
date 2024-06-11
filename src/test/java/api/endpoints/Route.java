package api.endpoints;

public class Route {

    public static String base_URL = "petstore.swagger.io/v2";

    //USER Module

    public static String createUser = base_URL+"/user";
    public static String getUser = base_URL+"/user/{username}";
    public static String updateUser = base_URL+"/user/{username}";
    public static String deleteUser = base_URL+"/user/{username}";


}
