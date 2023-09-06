package pl.myku.simplifiedAuth.db;

public interface IDbManager {
    public Boolean loginPlayer(String username, String password);
    public Boolean isPlayerRegistered(String username);
    public void addPlayerToDatabase(String username, String password);
    public void changePassword(String username, String password);
    public void reloadDb();
}
