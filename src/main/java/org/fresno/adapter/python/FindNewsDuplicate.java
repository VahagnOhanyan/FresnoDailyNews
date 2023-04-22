package org.fresno.adapter.python;


import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.FactoryBean;

public class FindNewsDuplicate implements FactoryBean<HelloService> {

    @Override
    public HelloService getObject() throws Exception {

        //Here is the actual code that interprets our python file.
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("src\\main\\java\\org\\fresno\\adapter\\python\\HelloServicePython.py");
        PyObject buildingObject = interpreter.get("HelloServicePython").__call__();

//Cast the created object to our Java interface
        return (HelloService) buildingObject.__tojava__(HelloService.class);
    }

    @Override
    public Class<?> getObjectType() {
        return HelloService.class;
    }

}

