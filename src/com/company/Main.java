package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String[] params = sc.nextLine().split(" ");
        sc.close();
        testData(params);
        System.out.println(calculate(params));

    }

    static void testData(String[] params) throws Exception {
        if (params.length != 3) {
            throw new Exception("Недопустимое количество операндов");
        }
        //testOperation(params[1]);
        if (!(testOperand(params[0]).equals(testOperand(params[2])))) {
            throw new Exception("Операнды в разных форматах");
        }
    }

    static String testOperand(String param) throws Exception {
        String resultTest;
        RomanNumber roman = new RomanNumber(param);
        if (param.matches("[0-9]+") && (param.length() < 2) || param.equals("10")) {
            resultTest = "arabian";
        } else {
            if (roman.intValue > 0 && roman.intValue < 11) {
                resultTest = "roman";
            } else {
                throw new Exception("Недопустимое значение операнда!");
            }
        }
        return resultTest;
    }

    static String calculate(String[] params) throws Exception {
        String typeOperands = testOperand(params[0]);
        String result;
        if (typeOperands.equals("arabian")) {
            int resultInt = executeOperation(Integer.parseInt(params[0]), Integer.parseInt(params[2]), params[1]);
            result = Integer.toString(resultInt);
        } else {
            RomanNumber romanNumber1 = new RomanNumber(params[0]);
            RomanNumber romanNumber2 = new RomanNumber(params[2]);
            result = executeOperation(romanNumber1, romanNumber2, params[1]);
        }
        return result;
    }

    static int executeOperation(int x, int y, String operation) throws Exception {
        int result;
        switch (operation) {
            case "+" -> result = x + y;
            case "*" -> result = x * y;
            case "-" -> result = x - y;
            case "/" -> result = x / y;
            default -> throw new Exception("Неизвестная операция!");
        }
        return result;
    }

    static String executeOperation(RomanNumber x, RomanNumber y, String operation) throws Exception {
        int resultRoman = executeOperation(x.intValue, y.intValue, operation);
        if (resultRoman <= 0) {
            throw new Exception("Результат операции с римскими цифрами должен быть больше нуля!");
        } else {
            return intToRoman(resultRoman);
        }
    }

    public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int times;
        String[] romans = new String[]{"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};
        int[] ints = new int[]{1, 4, 5, 9, 10, 40, 50, 90, 100};
        for (int i = ints.length - 1; i >= 0; i--) {
            times = num / ints[i];
            num %= ints[i];
            while (times > 0) {
                sb.append(romans[i]);
                times--;
            }
        }
        return sb.toString();
    }
}

