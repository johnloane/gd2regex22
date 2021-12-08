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
        String visa5 = "44444444444444";

        String emilyVisaRegex = "^(4[0-9]{12,15})$";
        //Main issue 12,15 means anything between 12 and 15 so 13 and 14 which are not valid will meet the criteria

        String prithviVisaRegex = "^4[0-9]{12}$|^4[0-9]{15}$";

        String anotherVisaRegex = "^4[0-9]{12}([0-9]{3})?$";

        System.out.println("visa1 " + visa1.matches(prithviVisaRegex));
        System.out.println("visa2 " + visa2.matches(prithviVisaRegex));
        System.out.println("visa3 " + visa3.matches(prithviVisaRegex));
        System.out.println("visa4 " + visa4.matches(prithviVisaRegex));
        System.out.println("visa5 " + visa5.matches(prithviVisaRegex));

        //challengeOne();
        //challengeTwo();
        //challengeThree();
        //challengeFour();
        //challengeFive();
        //challengeSix();
        //challengeSeven();
        //challengeEight();
        //Homework challenge
        //challengeNine();
        //challengeTen();
        //challengeEleven();
        //challengeTwelve();
        //challengeThirteen();
        finalChallenge();


    }

    private static void finalChallenge()
    {
        //This should match both challenge 12 and challenge13
        //11111 or 11111-1111 should both match
        String challenge12 = "11111";
        String challenge13 = "11111-1111";
        String prithviRegex = "^\\d{5}(\\-\\d{4})?$";
        String regex = "^\\d{5}$|^\\d{5}\\-\\d{4}$";
        System.out.println(challenge12.matches(regex));
        System.out.println(challenge13.matches(regex));
    }

    private static void challengeThirteen()
    {
        String challenge13 = "11111-1111";
        String niallMRegex = "^\\d{5}\\-\\d{4}$";
        String niallORegex = "^[0-9]{5}\\-[0-9]{4}$";
        String prithviRegex = "^1{5}\\-1{4}$";
        String leeAnneRegex = "11111-1111";
        System.out.println(challenge13.matches(leeAnneRegex));
    }

    private static void challengeTwelve()
    {
        String challenge12 = "11111";
        String niallsRegex = "11111";
        String stephensRegex = "^[0-9]{5}$";
        String regex = "^\\d{5}$";
        System.out.println(challenge12.matches(regex));
    }

    private static void challengeEleven()
    {
        String challenge11 = "{0,2}, {0, 5}, {1, 3} {x, y}, {2, 4}";
        //Only want numbers
        String regex = "\\{([0-9]\\,\\s*[0-9])\\}";
        Pattern pattern11 = Pattern.compile(regex);
        Matcher matcher11 = pattern11.matcher(challenge11);
        System.out.println(matcher11.matches());
        matcher11.reset();
        while(matcher11.find())
        {
            System.out.println("Occurrence " + matcher11.group(1));
        }
    }

    private static void challengeTen()
    {
        String challenge10 = "{0,2}, {0, 5}, {1, 3}, {2, 4}";
        //Want a regex that will throw away the curley brackets and just return the values
        //{0, 2} -> 0, 2
        //Plan find curley brackets //{, use a group to find values inside curley brackets, use pattern and matcher to print out all occurrences
        String regex = "\\{([0-9]\\,\\s*[0-9])\\}";
        String regex2 = "\\{(.+?)\\}"; //? makes it lazys
        Pattern pattern10 = Pattern.compile(regex2);
        Matcher matcher10 = pattern10.matcher(challenge10);
        System.out.println(matcher10.matches());
        matcher10.reset();
        while(matcher10.find())
        {
            System.out.println("Occurrence " + matcher10.group(1));
        }
    }

    private static void challengeNine()
    {
        String challenge9 = "abcd.135\tuvqz.7\ttzik.999\n";
        //Tweak the regex from challenge 8 so that you can find and print all of the numbers in this string
        //Output should be 135, 7, 999
        String regex = "[A-Za-z]+\\.([0-9]+)\\s";
        Pattern pattern9 = Pattern.compile(regex);
        Matcher matcher9 = pattern9.matcher(challenge9);
        System.out.println(matcher9.matches());
        while(matcher9.find())
        {
            System.out.println("Occurrence " + matcher9.group(1));
        }
    }

    private static void challengeEight()
    {
        String challenge8 = "abcd.135uvqz.7tzik.999";
        //Regex should find and extract all of the numbers
        //Should return 135, 7, 999
        String regex = "[A-Za-z]+\\.([0-9]+)";
        Pattern pattern8 = Pattern.compile(regex);
        Matcher matcher8 = pattern8.matcher(challenge8);
        System.out.println(matcher8.matches());
        while(matcher8.find())
        {
            System.out.println("Occurrence " + matcher8.group(1));
        }


    }

    private static void challengeSeven()
    {
        //One or more characters upper or lowercase, followed by a dot, followed by one or more digits
        String challenge7 = "abcd.135";
        String regex = "^[A-Za-z]+\\.[0-9]+$";
        System.out.println(challenge7.matches(regex));
    }

    private static void challengeSix()
    {
        String challenge6 = "aaabcccccccccdddefffg";
        //Write regex which will match any collection of one or more characters that appear in this string
        String regex = "[a-g]+";
        System.out.println(challenge6.matches(regex));
    }

    private static void challengeFive()
    {
        String challenge5 = "Replace all blanks with underscores.";
        System.out.println(challenge5.replaceAll(" ", "_"));
    }

    private static void challengeFour()
    {
        //Do challenge two using the Pattern and Matcher classes
        String one = "I want a bike.";
        String two = "I want a ball.";

        String regex = "I want a \\w+.";
        //String dylansRegexPatternForSentence = "(i.*?[.])";
        Pattern pattern1 = Pattern.compile(regex);
        Matcher matcher1 = pattern1.matcher(two);
        System.out.println(matcher1.matches());
    }

    private static void challengeThree()
    {
        //Should only match I want a bike or I want a ball
        String one = "I want a bike.";
        String two = "I want a ball.";

        String niallsRegex = "I want a (bike|ball).";
        System.out.println(one.matches(niallsRegex));
        System.out.println(two.matches(niallsRegex));
    }

    private static void challengeTwo()
    {
        String one = "I want a bike.";
        String two = "I want a ball.";
        //Write regex which will match either of these options
        String regex = "I want a \\w+.";
        System.out.println(one.matches(regex));
        System.out.println(two.matches(regex));
    }

    private static void challengeOne()
    {
        String challenge1 = "All I want for Christmas is good grades";
        System.out.println(challenge1.matches("All I want for Christmas is good grades"));
    }
}
