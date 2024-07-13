package com.personal;

public class LongestPrefix {
    public static void main(String[] args) {
        final String[] strings = {"aaflower","aaflow","aaflight"};
        final int size = strings.length;
        int prefixLength = getPrefixLength1(strings, size);
        if (prefixLength == 0) {
            System.out.println("###########3");
        } else {
            System.out.println(strings[0].substring(0, prefixLength));
        }
    }

    // private static int getPrefixLength(String[] strings, int size) {
    //     int count = 0;
    //     int position = 0;
    //     boolean flag = true;
    //     while (flag) {
    //         char c, s = strings[0].charAt(position);
    //         for (int i = 0; i < size; i++) {
    //             String item = strings[i];
    //             if (position < item.length()) {
    //                 c = item.charAt(position);
    //             } else {
    //                 flag = false;
    //                 break;
    //             }
    //             if (s != c) {
    //                 flag = false;
    //                 break;
    //             } else {
    //                 count++;
    //             }
    //         }
    //         position++;
    //         if (count != size) {
    //             break;
    //         } else {
    //             count = 0;
    //         }
    //     }
    //     return position - 1;
    // }

    private static int getPrefixLength1(String[] strings, int size) {
        int position = 0;
        boolean flag = true;
        while (flag) {
            flag = false;
            int count = 1;
            for (int i = 0; i < size - 1; i++) {
                if (strings[i].charAt(position) == strings[i + 1].charAt(position)) {
                    count++;
                } else {
                    break;
                }
            }
            if (count == size) {
                flag = true;
                position++;
            }
        }
        return position;
    }
}
