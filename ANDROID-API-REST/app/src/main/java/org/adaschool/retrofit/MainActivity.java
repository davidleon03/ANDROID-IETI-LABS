package org.adaschool.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;

import org.adaschool.retrofit.databinding.ActivityMainBinding;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import org.adaschool.retrofit.databinding.ActivityMainBinding;
import org.adaschool.retrofit.dto.BreedsListDto;
import org.adaschool.retrofit.dto.RandomImageDto;
import org.adaschool.retrofit.service.DogApiService;

import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ActivityMainBinding binding;
    private ImageView imageView;
    private DogApiService dogApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DogApiService dogApiService = RetrofitInstance.getRetrofitInstance().create(DogApiService.class);

        Call<BreedsListDto> call = dogApiService.getAllBreeds();
        call.enqueue(new Callback<BreedsListDto>() {
            @Override
            public void onResponse(Call<BreedsListDto> call, Response<BreedsListDto> response) {
                if (response.isSuccessful()) {
                    Map<String, String[]> breeds = response.body().getMessage();
                    for (Map.Entry<String, String[]> entry : breeds.entrySet()) {
                        Log.d(TAG, "--------------Raza----------------: " + entry.getKey());
                        for (String subRaza : entry.getValue()) {
                            Log.d(TAG, "-------------- Subraza----------------: " + subRaza);
                            loadDogName(subRaza);
                        }
                    }

                } else {
                    Log.e(TAG, "Error en la respuesta de la API");
                }
            }

            @Override
            public void onFailure(Call<BreedsListDto> call, Throwable t) {
                Log.e(TAG, "Error al llamar a la API", t);
            }
        });

        Call<RandomImageDto> call2 = dogApiService.getRandomImage();

        call2.enqueue(new Callback<RandomImageDto>() {
            @Override
            public void onResponse(Call<RandomImageDto> call, Response<RandomImageDto> response) {
                if (response.isSuccessful()) {
                    String dogImageUrl = response.body().getMessage();
                    Log.d(TAG, "URL de la imagen: " + dogImageUrl);
                    loadDogInfo(dogImageUrl);
                } else {
                    Log.e(TAG, "Error en la respuesta de la API");
                }
            }

            @Override
            public void onFailure(Call<RandomImageDto> call, Throwable t) {
                Log.e(TAG, "Error al llamar a la API", t);
            }
        });
    }

    private void loadDogInfo(String dogImageUrl) {
        //TextView dogName = findViewById(R.id.textView);
        ImageView myImageView = findViewById(R.id.imageView);
        Glide.with(this)
                .load(dogImageUrl)
                .into(myImageView);
    }

    private void loadDogName(String name){
        TextView dogName = findViewById(R.id.textView);
        dogName.setText(name);
    }

}
