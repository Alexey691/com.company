package com.company;

class RomanNumber {

    int intValue;

    RomanNumber(String paramValue) {
        int lastNumber = 0;
        for (int i = paramValue.length() - 1; i >= 0; i--) {
            char romanChar = paramValue.charAt(i);
            switch (romanChar) {
                case 'C' -> {
                    intValue = processDecimal(100, lastNumber, intValue);
                    lastNumber = 100;
                }
                case 'L' -> {
                    intValue = processDecimal(50, lastNumber, intValue);
                    lastNumber = 50;
                }
                case 'X' -> {
                    intValue = processDecimal(10, lastNumber, intValue);
                    lastNumber = 10;
                }
                case 'V' -> {
                    intValue = processDecimal(5, lastNumber, intValue);
                    lastNumber = 5;
                }
                case 'I' -> {
                    intValue = processDecimal(1, lastNumber, intValue);
                    lastNumber = 1;
                }
                default -> {
                    intValue = -1;
                    return;
                }
            }
        }

    }

    public static int processDecimal(int number, int lastNumber, int previousNumber) {
        if (lastNumber > number) {
            return previousNumber - number;
        } else {
            return previousNumber + number;
        }
    }

}
