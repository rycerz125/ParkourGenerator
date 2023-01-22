package org.sentillo.gepard.generator.terrain.javascript;

import org.openjdk.nashorn.api.scripting.ClassFilter;

public class JavaScriptClassFilter implements ClassFilter {

    @Override
    public boolean exposeToScripts(String s) {
      return s.equals("org.sentillo.gepard.utils.Vector3d");
    }
}
