package Persone;

import Utility.Account;

public class Credenziali {
    private Persona persona;
    private Account account;

    public Credenziali(Persona p, Account a){
        this.persona=p;
        this.account=a;
    }

    public Persona getPersona(){
        return persona;
    }

    public Account getAccount(){
        return account;
    }
}
