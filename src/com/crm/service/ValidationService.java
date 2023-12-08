package com.crm.service;

import static com.crm.Main.scanner;
import static com.crm.common.ExceptionMessages.*;

public class ValidationService implements ValidationServiceInterface {

    @Override
    public int validateIntNumber() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(INVALID_INT_VALIDATION);
            return validateIntNumber();
        }

    }

    @Override
    public String validateStringInput() {
        String inputLine = scanner.nextLine();
        if (!inputLine.matches("[a-zA-Z ]+")) {
            System.out.println(INVALID_STRING_VALIDATION);
            return validateStringInput();
        }

        return inputLine;
    }

    @Override
    public double validateDoubleNumber() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(INVALID_DOUBLE_VALIDATION);
            return validateDoubleNumber();
        }

    }
}