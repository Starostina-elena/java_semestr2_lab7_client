package org.lia.commands;

import org.lia.models.Product;

import java.io.Serializable;

public class CountByPartNumberCommand implements Command {
    private static final long serialVersionUID = 1785464768755190753L;
    public Product product;
    private String login;
    private String password;
    private String partNumber;

    public String description() {
        return "shows number of element with specified part number. Pattern: count_by_part_number (String)partNumber";
    }

    public void execute(String[] arguments, String login, String password) {
        this.login = login;
        this.password = password;
        partNumber = arguments[1];
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
