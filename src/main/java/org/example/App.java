package org.example;

import org.example.controller.Service;

public class App
{
    public static void main( String[] args )
    {
        Service myService = new Service();
        myService.run();
    }
}
