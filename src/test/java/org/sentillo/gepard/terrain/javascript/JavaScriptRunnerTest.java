package org.sentillo.gepard.terrain.javascript;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.Vector3d;

public class JavaScriptRunnerTest {
    @Test
    public void runJavaScript(){
        JavaScriptRunner jsr = new JavaScriptRunner();
        Assertions.assertEquals(4, jsr.run("2+2"));
    }

    @Test
    public void createCube(){
        JavaScriptRunner jsr = new JavaScriptRunner();
        Matrix3d<Boolean> matrix = new Matrix3d<>(); 
        jsr.bind("world", matrix);
        jsr.run("""
            function vector3d(x,y,z) {
                return Java.type("org.sentillo.gepard.utils.Vector3d").of(x,y,z)
            }

            world.setObject(vector3d(0,0,0),true)
        """);
        Assertions.assertEquals(true, matrix.getObject(Vector3d.of(0,0,0)));
    }

    @Test
    public void testClassFilter() {
 
        final String script =
          "var File = Java.type(\"java.io.File\");";
     
        JavaScriptRunner jsr = new JavaScriptRunner();
        Assertions.assertEquals(JavaScriptRunnerFail.class, jsr.run(script).getClass());
      }
}
