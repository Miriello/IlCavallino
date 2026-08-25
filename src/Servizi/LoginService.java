package Servizi;


import DAO.AccountDAO;
import DAO.PersonaDAO;
import Persone.Persona;
import Utility.Account;

public class LoginService {
    private AccountDAO accountDAO;
    private PersonaDAO personaDAO;

    public LoginService(){
        this.accountDAO= new AccountDAO();
        this.personaDAO= new PersonaDAO();
    }

    public Persona login(String username, String password){
        Account account = accountDAO.findByUsername(username);
        if( account == null ){
            return null;
        }
        if (!account.getPassword().equals(password)){
            return null;
        }
        return personaDAO.findByCf(account.getCfOperatore());
    }
}