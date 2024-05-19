package org.lia.commands;

import org.lia.managers.CommandManager;
import org.lia.models.Product;

import java.util.Scanner;

public class ExitCommand implements Command {
    public Product product;
    CommandManager commandManager;
    private String login;
    private String password;

    public ExitCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public String description() {
        return "quits from program";
    }

    public void execute(String[] arguments, String login, String password) {
        this.login = login;
        this.password = password;
        System.out.println("goodbye");
        System.exit(0);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
