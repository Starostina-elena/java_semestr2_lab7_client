package org.lia.commands;

import org.lia.models.Product;

import java.io.Serializable;

public interface Command extends Serializable {
    void execute(String[] arguments, String login, String password);
    String description();
    public Product product = null;
    String login = null;
    String password = null;
    public String getLogin();
    public String getPassword();
}
