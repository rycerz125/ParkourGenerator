package org.sentillo.gepard.generator.terrain.javascript;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;

import org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory;

//TODO add class filter! https://docs.oracle.com/javase/10/nashorn/nashorn-java-api.htm#JSNUG126
public class JavaScriptRunner {

    private static NashornScriptEngineFactory engineFactory;
    private ScriptEngine scriptEngine;
    private Bindings bindings;

    public JavaScriptRunner(){
        if(engineFactory == null){
            engineFactory = new NashornScriptEngineFactory();
        }

        scriptEngine = engineFactory.getScriptEngine(new JavaScriptClassFilter());
        bindings = scriptEngine.getBindings(ScriptContext.ENGINE_SCOPE);
    }

    public Object run(String code){
        Object result = null;

        try{
            result = scriptEngine.eval(code);
        }
        catch(Exception ex){
            return new JavaScriptRunnerFail();
        }
        return result;
    }

    public void bind(String name, Object object){
        bindings.put(name, object);
    }
}
