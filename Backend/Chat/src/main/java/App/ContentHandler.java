package App;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jasper on 21-6-2016.
 */
public class ContentHandler{

    private Gson gson = new Gson();
    private Database db = new Database();


    public String login(String username, String password){
        Map<String, Integer> response = new HashMap<>();

        if(username != null && password != null){


            Integer uid = db.checkCredentials(username, password);
            if(uid != null) {
                response.put("uid", uid);
            }else{
                response.put("uid", null);
            }
        }

        return gson.toJson(response);
    }

    public String retrieveChatrooms(String uid){


        Integer parsedUid = Integer.parseInt(uid);

        return gson.toJson(db.getChatroomList(parsedUid));
    }

    public String retrieveChat(String roomnumber){


        Integer parsedRoomnumber = Integer.parseInt(roomnumber);

        return gson.toJson(db.retrieveChat(parsedRoomnumber));
    }

    public String sendMessage(String message, String roomnumber, String uid){
        Integer parsedUid = Integer.parseInt(uid);
        Integer parsedRoomnumber = Integer.parseInt(roomnumber);

        String response = "Message not added";

        if(db.insertMessage(parsedUid, parsedRoomnumber, message)){
            response = "Message added to DB";
        }

        return gson.toJson(response);

    }

    public String joinRoom(String roomnumber, String uid){
        Integer parsedRoomnumber = Integer.parseInt(roomnumber);
        Integer parsedUid = Integer.parseInt(uid);

        return gson.toJson(db.joinRoom(parsedRoomnumber, parsedUid));
    }
}
