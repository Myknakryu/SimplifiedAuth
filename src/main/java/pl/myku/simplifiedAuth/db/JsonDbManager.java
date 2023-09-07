package pl.myku.simplifiedAuth.db;

import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import pl.myku.simplifiedAuth.SimplifiedAuth;
import pl.myku.simplifiedAuth.Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.StandardCharsets;

public class JsonDbManager implements IDbManager {
    private final File dbFile;
    private JsonObject db;
    public static final Gson GSON = new Gson();
    public JsonDbManager(String path, String filename){
        dbFile = new File(path + filename + ".json");
        reloadDb();
    }

    public void reloadDb(){
        db = new JsonObject();
        if (!dbFile.exists()) {
            db.add("Users", new JsonArray());
            saveDb();
        }
        try {
            BufferedReader bufferedReader = Files.newReader(dbFile, StandardCharsets.UTF_8);
            db = GSON.fromJson(bufferedReader, JsonObject.class);
        } catch (Exception e) {
            SimplifiedAuth.LOGGER.error(e.getMessage());
        }
    }

    public void saveDb(){
        try {
            if(!dbFile.exists()){
                dbFile.getParentFile().mkdirs();
                dbFile.createNewFile();
            }
            BufferedWriter bufferedWriter = Files.newWriter(dbFile, StandardCharsets.UTF_8);
            String json = GSON.toJson(db);
            bufferedWriter.write(json);
            bufferedWriter.close();
        } catch (Exception e) {
            SimplifiedAuth.LOGGER.error(e.getMessage());
        }
    }
    private JsonObject findPlayer(String username){
        if (!db.getAsJsonArray("Users").isEmpty()) {
            for(int i = 0; i < db.size(); i++){
                JsonObject tmpUser = db.getAsJsonArray("Users").get(i).getAsJsonObject();
                if(tmpUser.get("user").getAsString().equals(username)){
                    return tmpUser;
                }
            }
        }
        return null;
    }

    public Boolean loginPlayer(String username, String password){
        JsonObject player = findPlayer(username);
        if(player != null){
            return Utils.checkPassword(password, player.get("password").getAsString());
        }
        return false;
    }

    public void changePassword(String username, String password){
        JsonObject player = findPlayer(username);
        if (player == null){
            return;
        }
        player.addProperty("password", Utils.hashPassword(password));
        saveDb();
    }

    public Boolean isPlayerRegistered(String username){
        return findPlayer(username) != null;
    }

    public void addPlayerToDatabase(String username, String password){
        JsonObject player = new JsonObject();
        player.addProperty("user", username);
        player.addProperty("password", Utils.hashPassword(password));
        db.getAsJsonArray("Users").add(player);
        saveDb();
    }
}