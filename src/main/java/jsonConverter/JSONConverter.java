/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonConverter;

import com.google.gson.Gson;
import entity.User;
import java.util.List;

/**
 *
 * @author kaspe
 */
public class JSONConverter {

    private static final Gson gson = new Gson();

    public static User getUserFromJson(String js) {
        return gson.fromJson(js, User.class);
    }

    public static String getJSONFromUser(User p) {
        return gson.toJson(p);
    }

    public static String getJSONFromUsers(List<User> data) {
        return gson.toJson(data);
    }
}
