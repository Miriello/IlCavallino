package Utility;

public class Account {
    private String username;
    private String password;
    private String cfOperatore;

    public Account(String username, String password, String cfOperatore){
        this.username=username;
        this.password=password;
        this.cfOperatore=cfOperatore;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getCfOperatore(){
        return cfOperatore;
    }
}
