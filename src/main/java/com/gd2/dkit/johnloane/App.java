package com.gd2.dkit.johnloane;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        System.out.println(alphanumeric.replaceAll("jkl99z$", "The end"));
        System.out.println(alphanumeric.replaceAll("[aei][Fj]", "X"));

        System.out.println("harry".replaceAll("[Hh]arry", "Harry"));

        //String alphanumeric = "abcDeeeF12Ghiiiiiijkl99z";
        System.out.println(alphanumeric);
        System.out.println(alphanumeric.replaceAll("[^ej]", "X"));

        //Replace 345678abcdef with "X"
        System.out.println(alphanumeric.replaceAll("[345678abcdef]", "X"));
        System.out.println(alphanumeric.replaceAll("[a-fA-F3-8]", "X"));
        //What does ? mean in regular expression?
        //Don't care about case - case insensitive
        System.out.println(alphanumeric.replaceAll("(?i)[a-f3-8]", "X"));

        //Replace all digits with "X"
        System.out.println(alphanumeric.replaceAll("[0-9]", "X"));
        System.out.println(alphanumeric.replaceAll("\\d", "X"));
        System.out.println(alphanumeric.replaceAll("\\D", "X"));

        String hasWhitespace = "I have blanks and\ta tab and also a newline\n";
        System.out.println(hasWhitespace);
        //1. Remove all whitespace
        //2. Remove the tab
        //3. Remove anything which is not whitespace
        System.out.println(hasWhitespace.replaceAll("\\s", ""));
        System.out.println(hasWhitespace.replaceAll("\t", " "));
        System.out.println(hasWhitespace.replaceAll("(?i)[0-9A-Z]", ""));
        System.out.println(hasWhitespace.replaceAll("\\b", "X"));

        //String alphanumeric = "abcDeeeF12Ghiiiiiijkl99z";
        //Replace anything that starts with abcDe and two further e's with YYY
        System.out.println(alphanumeric);
        System.out.println(alphanumeric.replaceAll("^abcDe{3}", "YYY"));
        System.out.println(alphanumeric.replaceAll("^abcDe+", "YYY"));
        System.out.println(alphanumeric.replaceAll("^abcDe+", "YYY"));
        System.out.println(alphanumeric.replaceAll("^abcDe{2,5}", "YYY"));
        //One or more h's followed by zero or more i's followed by a j
        System.out.println(alphanumeric);
        System.out.println(alphanumeric.replaceAll("h+i*j", "YYY"));

        StringBuilder htmlText = new StringBuilder("<h1>Heading</h1>");
        htmlText.append("<h2>Sub-heading</h2>");
        htmlText.append("<p>Paragraph</p>");
        htmlText.append("<p>Another paragraph</p>");
        htmlText.append("<h2>Summary</h2>");
        htmlText.append("<p>Here is the summary</p>");

        String h2Pattern = "<h2>";
        Pattern pattern = Pattern.compile(h2Pattern);
        Matcher matcher = pattern.matcher(htmlText);
        System.out.println(matcher.matches());
        System.out.println(htmlText);
        matcher.reset();
        int count = 0;
        while(matcher.find())
        {
            count++;
            System.out.println("Occurrence " + count + " : " + matcher.start() + " to " + matcher.end());
        }

        String h2GroupPattern = "(<h2>.*?</h2>)";
        Pattern groupPattern = Pattern.compile(h2GroupPattern);
        Matcher groupMatcher = groupPattern.matcher(htmlText);
        System.out.println(groupMatcher.matches());
        groupMatcher.reset();

        while(groupMatcher.find())
        {
            System.out.println("Occurrence: " + groupMatcher.group(1));
        }

        String h2Textgroups = "(<h2>)(.*?)(</h2>)";
        Pattern h2TextPattern = Pattern.compile(h2Textgroups);
        Matcher h2TextMatcher = h2TextPattern.matcher(htmlText);

        while(h2TextMatcher.find())
        {
            System.out.println("Occurrence: " + h2TextMatcher.group(0));
        }

        // tstvtk
        String tvTest = "ts";
        //TODO Homework what is this doing when it is greedy?
        String tNotVRegExp = "t(!v)";
        Pattern tNotVPattern = Pattern.compile(tNotVRegExp);
        Matcher tNotVMatcher = tNotVPattern.matcher(tvTest);

        count = 0;

        while(tNotVMatcher.find())
        {
            count++;
            System.out.println("Occurrence " + count + " : " + tNotVMatcher.start() + " to " + tNotVMatcher.end());
        }

        String phone1 = "1234567890"; //Should not match
        String phone2 = "(123) 456-7890"; //Should match
        String phone3 = "123 456-7890"; //Should not match
        String phone4 = "(123)456-7890"; //Should not match

        String regex = "^([\\(]{1}[\\d]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[\\d]{4})$";

        //Use matches to test a regular expression
        System.out.println("phone1 = " + phone1.matches(regex));
        System.out.println("phone2 = " + phone2.matches(regex));
        System.out.println("phone3 = " + phone3.matches(regex));
        System.out.println("phone4 = " + phone4.matches(regex));

        //Irish mobile phone numbers
        //+353 8[3, 5,6,7,9] 1234567
        String mPhone1 = "+353 87 9096989"; //valid
        String mPhone2 = "+353 82 3038978"; //invalid - 2 not valid
        String mPhone3 = "353 87 1234567"; //invalid - no plus
        String mPhone4 = "+353897654321"; //invalid - no spaces

        String mRegex = "^([\\+]{1}353[ ]{1}[8]{1}[35679]{1}[ ]{1}[\\d]{7})$";

        //Use matches to test a regular expression
        System.out.println("phone1 = " + mPhone1.matches(mRegex));
        System.out.println("phone2 = " + mPhone2.matches(mRegex));
        System.out.println("phone3 = " + mPhone3.matches(mRegex));
        System.out.println("phone4 = " + mPhone4.matches(mRegex));

        //Visa Card
        //Start with 4
        //Older version has 4 followed by 12 digits
        //Newer versions have 4 followed by 15 digits
        //Write a regex that will check for valid visa cards
        String visa1 = "4444444444444"; //should match
        String visa2 = "5444444444444"; //should not match
        String visa3 = "4444444444444444"; //should match
        String visa4 = "4444"; //should not match

        String visaRegex = "?????";

        System.out.println("visa1 " + visa1.matches(visaRegex));


    }
}
