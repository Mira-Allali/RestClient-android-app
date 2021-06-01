package com.example.apirest.Utils;

public class Apis {

    public static final String URL_001="http://192.168.1.10:8080/persons/";

    public static PersonService getPersonaService(){
        return  Client.getClient(URL_001).create(PersonService.class);
    }
}
