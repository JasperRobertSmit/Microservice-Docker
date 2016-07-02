package App;

import com.google.gson.Gson;

import static spark.Spark.*;

/**
 * Created by Jasper on 15-6-2016.
 */
public class Main {
    public static void main(String[] args) {

        CorsFilter.apply();

        Gson gson = new Gson();

        ContentHandler contentHandler = new ContentHandler();

        get("/login", (request, response) -> contentHandler.login(request.queryParams("username"), request.queryParams("password")));

        get("/retrievechatrooms", (request, response) -> contentHandler.retrieveChatrooms(request.queryParams("uid")));

        get("/retrievechat", (request, response) -> contentHandler.retrieveChat(request.queryParams("roomnumber")));

        get("/sendmessage", (request, response) -> contentHandler.sendMessage(request.queryParams("message"), request.queryParams("roomnumber"), request.queryParams("uid")));

        get("/joinroom", (request, response) -> contentHandler.joinRoom(request.queryParams("roomnumber"), request.queryParams("uid")));

        //Easy Debugging
//        Database db = new Database();
//        String username = "jasper";
//        String password = "wachtwoord";

//        if(db.checkCredentials(username, password)){
//            System.out.println("== TRUE");
//
//        }
//
//        System.out.println(db.getId(username, password));

//        System.out.println(contentHandler.retrieveChat("2"));
//        System.out.println(contentHandler.sendMessage("Nieuw bericht", "2", "2"));



    }
}

