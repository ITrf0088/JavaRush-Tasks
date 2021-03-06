package com.javarush.task.task37.task3709.connectors;

import com.javarush.task.task37.task3709.security.SecurityChecker;
import com.javarush.task.task37.task3709.security.SecurityCheckerImpl;

public class SecurityProxyConnector implements Connector {

    private String resourceString;
    SimpleConnector simpleConnector;
    SecurityChecker checker = new SecurityCheckerImpl();

    public SecurityProxyConnector(String resourceString) {
        this.resourceString = resourceString;
        simpleConnector = new SimpleConnector(resourceString);
    }

    @Override
    public void connect() {
        if(checker.performSecurityCheck()){
            simpleConnector.connect();
        }
    }
}
