package org.lia.commands;

import org.lia.managers.CommandManager;
import org.lia.models.Coordinates;
import org.lia.models.Organization;
import org.lia.models.Product;
import org.lia.models.UnitOfMeasure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UpdateCommand implements Command {
    private static final long serialVersionUID = 1785464768755190753L;
    private String login;
    private String password;
    long productId;

    public UpdateCommand() {
    }

    public Product product;

    public String description() {
        return "update product by it's id. Pattern: update (long)id (String)newName (Integer)newPrice(may be null, use empty string) (String)newPartNumber (Integer)newManufactureCost";
    }

    public void execute(String[] arguments, String login, String password) {
        this.login = login;
        this.password = password;
        productId = Long.parseLong(arguments[1]);
        try {
            Integer price;
            try {
                if (arguments[3].isBlank()) {
                    price = null;
                } else {
                    price = Integer.parseInt(arguments[3]);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.out.println("price is not correct, try again" + arguments[2]);
                return;
            }
            try {
                Integer.parseInt(arguments[5]);
            } catch (NumberFormatException e) {
                System.out.println("manufactureCost is not correct, try again");
                return;
            }
            Scanner in = new Scanner(System.in);
            Coordinates coords;
            long x;
            double y;
            while (true) {
                try {
                    if (arguments.length >= 7) {
                        x = Integer.parseInt(arguments[6]);
                    } else {
                        System.out.println("Enter coordinates (long)x");
                        System.out.print("> ");
                        x = in.nextLong();
                    }
                    if (arguments.length >= 8) {
                        y = Double.parseDouble(arguments[7]);
                    } else {
                        System.out.println("Enter coordinates (double)y");
                        System.out.print("> ");
                        y = in.nextDouble();
                    }
                    coords = new Coordinates(x, y);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Coordinates are wrong");
                    return;
                } catch (InputMismatchException e) {
                    System.out.println("Wrong coordinates, try again");
                    in.nextLine();
                }
            }
            UnitOfMeasure resUnitOfMeasure;
            if (arguments.length >= 9) {
                resUnitOfMeasure = UnitOfMeasure.valueOf(arguments[8]);
            } else {
                ArrayList<String> unitOfMeasures = new ArrayList<>();
                System.out.println("Enter one of unit of measure:");
                for (UnitOfMeasure c : UnitOfMeasure.values()) {
                    System.out.println(c);
                    unitOfMeasures.add(c.name());
                }
                System.out.print("> ");
                in.nextLine();
                String unitOfMeasure = in.nextLine().toUpperCase();
                while (!unitOfMeasures.contains(unitOfMeasure) & !unitOfMeasure.isBlank()) {
                    System.out.println("Wrong unit of measure, please try again:");
                    System.out.print("> ");
                    unitOfMeasure = in.nextLine().toUpperCase();
                }
                if (unitOfMeasure.isBlank()) {
                    resUnitOfMeasure = null;
                } else {
                    resUnitOfMeasure = UnitOfMeasure.valueOf(unitOfMeasure);
                }
            }
            Organization org;
            while (true) {
                try {
                    if (arguments.length >= 10) {
                        org = new Organization(arguments[9], arguments[10], Integer.parseInt(arguments[11]));
                    } else {
                        System.out.println("Enter organization (String)name");
                        System.out.print("> ");
                        String name = in.nextLine();
                        System.out.println("Enter organization (String)fullName. Press enter to leave this field empty");
                        System.out.print("> ");
                        String fullName = in.nextLine();
                        if (fullName.isBlank()) {
                            fullName = "";
                        }
                        Integer employeesCount;
                        while (true) {
                            try {
                                System.out.println("Enter organization (Integer)employeesCount. Press enter to leave this field empty");
                                System.out.print("> ");
                                String inEmployeesCount = in.nextLine();
                                if (inEmployeesCount.isBlank()) {
                                    employeesCount = null;
                                } else {
                                    employeesCount = Integer.parseInt(inEmployeesCount);
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Wrong employeesCount. Please try again:");
                            }
                        }
                        org = new Organization(name, fullName, employeesCount);
                    }
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e + ". Please try again");
                }
            }

            product = new Product(arguments[2], coords, price, arguments[4],
                    Integer.parseInt(arguments[5]), resUnitOfMeasure, org);
        } catch (IllegalArgumentException e) {
            System.out.println(e + ". Please try again");
        }
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
