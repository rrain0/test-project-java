import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class ScriptsUnderJava {
    public static void main(String[] args) throws Exception{

        //НЕ ДОДЕЛАНО НЕ РАБОТАЕТ
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
        Object obj = engine.eval("""
                         function hello() {
                             print('"Hello, world"');
                         }

                         hello();
                         """);

    }
}
