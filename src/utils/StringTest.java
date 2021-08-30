package utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {

    public static void main(String[] args) {
        //splitTest();
        patternMatchingTest();
    }

    private static void splitTest(){
        String[] strs = {"", ";", "aa", "aa;", ";aa", "aa;aa", ";;", "aa;;aa"};
        Arrays.stream(strs).forEach(s->{
            System.out.println("'"+s+"'"+" splits by ';':");
            String[] splitted = s.split(";");
            System.out.println(Arrays.toString(splitted) + " with len "+splitted.length);
        });
    }

    private static void patternMatchingTest(){
        {
            System.out.println("first matching:");
            /*"NA30U06A196301675;\n" +
            "XA30U196252239;\n" +
            "NA30U06A196301675;\n" +
            "NA30S08A197186715;\n" +
            "NA30S08A197185789;\n" +
            "1240Af192554327;\n" +
            "NA30U06A196210219;\n" +
            "NA30U06A197131292;\n" +
            "1240Ac192600169;\n" +
            "BA30S9902A0330;"*/
            String s = """
            NA30U06A196301675;
            XA30U196252239;
            NA30U06A196301675;
            NA30S08A197186715;
            NA30S08A197185789;
            1240Af192554327;
            NA30U06A196210219;
            NA30U06A197131292;
            1240Ac192600169;
            BA30S9902A0330;""";

            Pattern pattern = Pattern.compile("[-\\w\\d]+");
            Matcher matcher = pattern.matcher(s);
            //System.out.println(matcher.group());
            System.out.println("matches?: "+matcher.matches());
            System.out.println("groupCount: "+matcher.toMatchResult().groupCount());

            System.out.println("find?: "+matcher.find());
            System.out.println(matcher.toMatchResult().start());

            System.out.println("find?: "+matcher.find(1));
            System.out.println(matcher.toMatchResult().start());
            System.out.println(matcher.toMatchResult().end());



            matcher.reset();
            matcher.results().forEach(m-> System.out.println(m.group()));

            System.out.println();
        }

        {
            System.out.println("second matching:");
            String s = """
                ;""";

            Pattern pattern = Pattern.compile("[-\\w\\d]+");
            Matcher matcher = pattern.matcher(s);
            //System.out.println(matcher.group());
            System.out.println("matches?: "+matcher.matches());
            System.out.println("groupCount: "+matcher.toMatchResult().groupCount());
            System.out.println("find?: "+matcher.find());

            matcher.reset();
            matcher.results().forEach(m-> System.out.println(m.group()));

            System.out.println();
        }

    }
}

