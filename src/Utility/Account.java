package Utility;

public class Account {
    private String username;
    private String password;

    public Account(String username, String password){
        this.username=username;
        this.password=password;
    }

    private String getUsername(){
        return username;
    }

    private String getPassword(){
        return password;
    }
}
