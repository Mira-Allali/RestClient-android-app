package com.example.apirest.Utils;

import com.example.apirest.Model.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PersonService {

    @GET("list")
    Call<List<Person>> getPersonas();

    @POST("add")
    Call<Person>addPersona(@Body Person persona);

    @POST("update/{id}")
    Call<Person>updatePersona(@Body Person persona, @Path("id") int id);

    @POST("remove/{id}")
    Call<Person>deletePersona(@Path("id")int id);

}
