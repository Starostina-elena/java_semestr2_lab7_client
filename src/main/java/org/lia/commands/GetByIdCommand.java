package org.lia.commands;


import org.lia.models.Product;

public class GetByIdCommand implements Command {
    public Product product;
    private static final long serialVersionUID = 1785464768755190753L;
    private String login;
    private String password;

    private long id;

    public String description() {
        return "returns element with this id";
    }

    public void execute(String[] arguments, String login, String password) {
        this.login = login;
        this.password = password;
        id = Integer.parseInt(arguments[1]);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
