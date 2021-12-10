import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;





public class RegexpSplit {

    public static void main(String[] args) {
        //splitTest();
        //patternMatchingTest();
        cityPatternTest();
        //patternMatchingWithGroupsTest();
    }

    private static void splitTest(){
        String[] strs = {"", ";", "aa", "aa;", ";aa", "aa;aa", ";;", "aa;;aa"};
        Arrays.stream(strs).forEach(s->{
            System.out.println("'"+s+"'"+" splits by ';':");
            String[] splitted = s.split(";");
            System.out.println(Arrays.toString(splitted) + " with len "+splitted.length);
        });
    }


    private static void cityPatternTest(){
        Pattern cityPattern = Pattern.compile("^ *((гор)|(г))?\\.? ?(?<city>([^. ].*[^. ]))[. ]*$", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

        var city = List.of("г Иркутск", "гор.Иркутск", " иркутск.", "Г Иркутск");

        /*
        In the official release of Java 7, the constructs to support the named capturing group are:
        => (?<name>capturing text) to define a named group "name"
        => \k<name> to backreference a named group "name"
        => ${name} to reference to captured group in Matcher's replacement string
        => Matcher.group(String name) to return the captured input subsequence by the given "named group".
         */

        city.stream()
            .map(cityPattern::matcher)
            .forEach(m->{
                boolean find = m.find(); // find attempts to find next match in given string
                System.out.println(find);
                if (find) System.out.println(m.group("city"));
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

    private static void patternMatchingWithGroupsTest(){
        if (false){
            System.out.println("first matching:");
            String s = """
            NA30U06A196301675;\040
            XA30U196252239;\040
            
            NA30U06A196301675;
            NA30S08A197186715;
            NA30S08A197185789;
            1240Af192554327;
            NA30U06A196210219;
            kfdjklg:fkldg..;
            фолплор;
            NA30U06A197131292;
            1240Ac192600169;
            BA30S9902A0330;""";

            //Pattern pattern = Pattern.compile("((?<serial>[-\\w\\d]+)|(?<notserial>.*))(;[ \n]*\n)");
            Pattern pattern = Pattern.compile("(?<serial>[-\\w\\d]+)?(;[ \n]*\n)");
            Matcher matcher = pattern.matcher(s);
            //System.out.println(matcher.group());





            matcher.results().forEach(m->{
                System.out.println(m.groupCount());

                System.out.println("[0]: "+m.group(0)); // всё выражение - группа номер 0
                System.out.println("[1]: "+m.group(1)); // следующая группа - в данном случае "serial" - если группа не найдена, то она null
                System.out.println("[2]: "+m.group(2)); // следующая группа - в данном случае (;[ \n]*\n)")
                System.out.println("---------------------------------------->");
            });

            //matcher.reset();
            //matcher.results().forEach(m-> System.out.println(m.group()));

            System.out.println();
        }


        if (false){
            String s = """
                NA30U06A196301675;\040
                XA30U196252239;\040
                            
                NA30U06A196301675;
                NA30S08A197186715;
                аывапвап  gg\040\040
                            
                NA30S08A197185789;
                \040\040
                1240Af192554327;
                NA30U06A196210219;
                  а\040\040
                kfdjklg:fkldg..;
                фолплор;
                NA30U06A197131292;
                1240Ac192600169;
                BA30S9902A0330;""";

            Pattern pattern = Pattern.compile("(?<serial>(?<number>[-\\w\\d]+)(;[ ]*\n))|(?<emptyLine>[ ]*\n)|(?<dich>.+\n)");
            Matcher matcher = pattern.matcher(s);
            //System.out.println(matcher.group());





            matcher.results().forEach(m->{
                System.out.println(m.groupCount());

                // если группа не найдена, то она null
                System.out.println("[0]: "+m.group(0)); // группа [0] - всё выражение
                System.out.println("[1]: "+m.group(1)); // группа [1] ["serial"]
                System.out.println("[2]: "+m.group(2)); // группа [2] ["number"]
                System.out.println("[3]: "+m.group(3)); // группа [3] - (;[ \n]*]\n)
                System.out.println("[4]: "+m.group(4)); // группа [4] ["emptyLine"]
                System.out.println("[5]: "+m.group(5)); // группа [5] ["dich"]
                System.out.println("---------------------------------------->");
            });

            //matcher.reset();
            //matcher.results().forEach(m-> System.out.println(m.group()));

            System.out.println();
        }





        {
            String s = """
                NA30U06A196301675;
                XA30U196252239;\040\r
                \040\040\040
                аывапвап  gg\040\040
                  а\040\040
                kfdjklg:fkldg..;""";

            Pattern pattern = Pattern.compile("(?<serial>( *)(?<number>[- .,!+a-zA-Z\\d]*[-.,!+a-zA-Z\\d])( *)(;[ ]*(\n(\r\n)|$)))|((?<emptyLine>[ ]*)(\n|(\r\n)))|((?<dich>.+)(\n|(\r\n)|$))");
            Matcher matcher = pattern.matcher(s);



            matcher.results().forEach(m->{
                System.out.println(m.groupCount());

                // если группа не найдена, то она null
                // группа - это всё выражение или любое выражение в ()
                System.out.println("[\"number\"]: "+m.group(3)); // группа [3] ["number"]
                System.out.println("[\"emptyLine\"]: "+m.group(9)); // группа [8] ["emptyLine"]
                System.out.println("[\"dich\"]: "+m.group(13)); // группа [11] ["dich"]
                System.out.println("---------------------------------------->");
            });


            System.out.println();
        }


    }




}

