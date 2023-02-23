package org.sentillo.gepard.generator.terrain.javascript;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;

import org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory;

public class JavaScriptRunner {

    private ScriptEngine scriptEngine;
    private Bindings bindings;

    public JavaScriptRunner(){
        scriptEngine = new NashornScriptEngineFactory()
                .getScriptEngine(); //Class filter is not there,
        // it destroys usage of TerrainColor class TODO
        bindings = scriptEngine.getBindings(ScriptContext.ENGINE_SCOPE);
    }

    public Object run(String code) throws Exception{
        Object result = null;

        result = scriptEngine.eval(code);


        return result;
    }

    public void bind(String name, Object object){
        bindings.put(name, object);
    }
}
