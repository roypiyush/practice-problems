package com.personal.math;

public class DecimalToHexadecimal {

    public static void main(String[] args) {

        System.out.println(decToHex(10));
        System.out.println(decToHex(127));
        System.out.println(decToHex(255));

        System.out.println(hexToDec("ff"));
        System.out.println(hexToDec("80"));

        System.out.println(hexToDec(decToHex(321)));
        System.out.println(hexToDec(decToHex(400)));
        System.out.println(hexToDec(decToHex(10024)));
        System.out.println(hexToDec(decToHex(342344324)));
    }

    public static String decToHex(int x) {
        if (x == 0) {
            return "0";
        } else if (x < 0) {
            return "-" + positiveDecToHex(-x);
        } else {
            return positiveDecToHex(x);
        }
    }

    public static int hexToDec(String hexdecimal) {
        boolean isNeg = hexdecimal.charAt(0) == '-';
        if (isNeg) {
            return -positiveHexToDec(hexdecimal.substring(1));
        } else {
            return positiveHexToDec(hexdecimal);
        }
    }

    private static int positiveHexToDec(String hexDecimal) {
        int HEX = 16;
        int power = 1;

        int value = 0;

        int size = hexDecimal.length();
        int i = size - 1;
        while (i >= 0) {
            int v = power * decimalValue(hexDecimal.charAt(i));
            value += v;
            power *= HEX;
            i--;
        }

        return value;

    }

    private static String positiveDecToHex(int decimal) {
        int HEX = 16;

        StringBuilder sb = new StringBuilder();
        while (decimal > 0) {
            int rem = decimal % HEX;
            sb.append(hexValue(rem)); 
            decimal = (decimal - rem) / HEX;
        }
        return sb.reverse().toString();
    }

    static String hexValue(int x) {
        if (x < 10) {
            return Integer.toString(x);
        } else if (x == 10) {
            return "a";
        } else if (x == 11) {
            return "b";
        } else if (x == 12) {
            return "c";
        } else if (x == 13) {
            return "d";
        } else if (x == 14) {
            return "e";
        } else {
            return "f";
        }
    }

    private static int decimalValue(char hexValue) {
        switch (hexValue) {
            case '0' : return 0;
            case '1' : return 1;
            case '2' : return 2;
            case '3' : return 3;
            case '4' : return 4;
            case '5' : return 5;
            case '6' : return 6;
            case '7' : return 7;
            case '8' : return 8;
            case '9' : return 9;
            case 'a' : return 10;
            case 'b' : return 11;
            case 'c' : return 12;
            case 'd' : return 13;
            case 'e' : return 14;
            case 'f' : return 15;
            default: throw new RuntimeException("Invalid hex value " + hexValue);
        }
    }

}