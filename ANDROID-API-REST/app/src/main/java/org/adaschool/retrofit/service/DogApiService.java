package org.adaschool.retrofit.service;

import org.adaschool.retrofit.dto.BreedsListDto;
import org.adaschool.retrofit.dto.RandomImageDto;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DogApiService {
    @GET("api/breeds/list/all")
    Call<BreedsListDto> getAllBreeds();

    @GET("api/breeds/image/random")
    Call<RandomImageDto> getRandomImage();
}
