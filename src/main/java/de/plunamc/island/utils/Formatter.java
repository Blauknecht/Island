package de.plunamc.island.utils;

import org.apache.commons.lang.StringUtils;

public class Formatter {


    public static String smallCapsFormatter(String unformatted) {
        StringBuilder sb = new StringBuilder();
        for(char c : unformatted.toCharArray()) {
            if(Character.isDigit(c) || Character.isSpaceChar(c) || !Character.isLetter(c)) {
                sb.append(c);
                continue;
            }

            if(Character.toLowerCase(c) == 'x' || Character.toLowerCase(c) == 'q' || Character.toLowerCase(c) == 'ä'|| Character.toLowerCase(c) == 'ü'|| Character.toLowerCase(c) == 'ö') {
                sb.appendCodePoint(Character.codePointOf("LATIN SMALL LETTER " + Character.toUpperCase(c)));
                continue;
            }

            sb.appendCodePoint(Character.codePointOf("LATIN LETTER SMALL CAPITAL " + Character.toUpperCase(c)));
        }

        return sb.toString();
    }

    public static String formatNumber(int _number) {
        String number = String.valueOf(_number);
        String finalNumber = "";
        String previousNumbers = "";
        String pastNumbers = "";
        String ending = "";
        if(number.length() == 4) {
            pastNumbers = number.substring(1);
            ending = "k";
        } else if(number.length() == 5) {
            pastNumbers = number.substring(2);
            ending = "k";
        } else if(number.length() == 6) {
            pastNumbers = number.substring(3);
            ending = "k";
        } else if(number.length() == 7) {
            pastNumbers = number.substring(1);
            ending = "m";
        } else if(number.length() == 8) {
            pastNumbers = number.substring(2);
            ending = "m";
        } else if(number.length() == 9) {
            pastNumbers = number.substring(3);
            ending = "m";
        } else if(number.length() == 10) {
            pastNumbers = number.substring(1);
            ending = "b";
        } else if(number.length() == 11) {
            pastNumbers = number.substring(2);
            ending = "b";
        } else if(number.length() == 12) {
            pastNumbers = number.substring(3);
            ending = "b";
        } else if(number.length() == 13) {
            pastNumbers = number.substring(1);
            ending = "t";
        } else if(number.length() == 14) {
            pastNumbers = number.substring(2);
            ending = "t";
        } else if(number.length() == 15) {
            pastNumbers = number.substring(3);
            ending = "t";
        }
        previousNumbers = number.replaceFirst(pastNumbers,"");
        String reversed = StringUtils.reverse(pastNumbers);
        int count = reversed.length();
        for (int i = 0; i < reversed.length(); i++) {
            if(reversed.charAt(i) != '0') {
                break;
            } else {
                count--;
            }
        }
        for (int i = 0; i < pastNumbers.length()-count; i++) {
            reversed = reversed.substring(1);
        }
        pastNumbers = StringUtils.reverse(reversed);
        finalNumber = previousNumbers+(!pastNumbers.equals("") ? ","+pastNumbers+ending : ending);
        return finalNumber;
    }
}
