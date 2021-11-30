package com.gd2.dkit.johnloane;

/**
 * Regular Expressions
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String simpleRegex = "I am a string. So I am";
        System.out.println(simpleRegex);
        String yourRegex = simpleRegex.replaceAll("I", "You");
        System.out.println(yourRegex);

        String alphanumeric = "abcDeeeF12Ghiiiiiijkl99z";
        // . will match everything
        System.out.println(alphanumeric.replaceAll(".", "Y"));

        //^ refers to the start of the string
        System.out.println(alphanumeric.replaceAll("^abcDeee", "YYY"));

        System.out.println(alphanumeric.matches("^hello"));
        System.out.println(alphanumeric.matches("abcDeeeF12Ghiiiiiijkl99z"));
    }
}
