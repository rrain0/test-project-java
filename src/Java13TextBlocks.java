public class Java13TextBlocks {
    //Text Blocks released in Java 15
    public static void main(String[] args) {
        //text block

        System.out.println("----------------------------------");

        //отступ по крайнему левому символу
        String s2 = """
                <html>
                    <body>
                        <p>shhshfhsaf</p>
                    </body>
                </html>
                """;//количество пробелов здесь - отступ всего текста, последний перенос строки считается
        System.out.println(s2);
        System.out.println("----------------------------------");

        String s3 = """
             <html>
                    <body>
                        <p>shhshfhsaf</p>
                    </body>
                </html>""";//отступ по крайнему левому символу
        System.out.println(s3);
        System.out.println("----------------------------------");

        // \<line-terminator> подаляет вставку новой строки, экранируется им же самим
        String literal = """
                Lorem ipsum dolor sit amet, \
                consecteur adipiscing elit, \
                sed do eiusmod tempor incididunt\\
                U""";
        System.out.println(literal);
        System.out.println("----------------------------------");

        // \s предотвращает удаление пробелов в конце линии
        String colors = """
                red  \s
                green
                blue \s
                """;
        System.out.println(colors);
        System.out.println("----------------------------------");
    }
}
