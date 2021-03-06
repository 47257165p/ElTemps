package com.example.poblenou.eltemps;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.poblenou.eltemps.json.Forecast;
import com.example.poblenou.eltemps.json.List;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by 47257165p on 29/10/15.
 */

//Interfaz encargada de rellenar la URL con los datos deseados.
interface OpenWeatherMapService {
    @GET("forecast/daily")
    Call<Forecast> dailyForecast(
            @Query("q") String city,
            @Query("mode") String format,
            @Query("units") String units,
            @Query("cnt") Integer num,
            @Query("appid") String appid);
}

//Clase que utilizaremos para crear el objeto encargado de Retrofit
public class OpenWeatherController {

    //Atributos necesarios para este objeto
    private final OpenWeatherMapService service;
    private final String FORECAST_BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private final String CITY = "Barcelona";
    private final String APPID = "2d16371b18d582695a502b8396b9673d";


    //Objeto que nos crea el retrofit con la URL base y llama al a interfaz para rellenar con las preferencias deseadas.
    public OpenWeatherController() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(FORECAST_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        service = retrofit.create(OpenWeatherMapService.class);
    }

    //Método encargado de actualizar la llamada con los datos que deseemos, así como de recibir el archivo json y transformarlo de la forma que deseemos.
    public void updateForecasts(final ArrayAdapter<String> adapter) {
        Call<Forecast> forecastCall = service.dailyForecast(
                CITY, "json", "metric", 14, APPID
        );
        forecastCall.enqueue(new Callback<Forecast>() {

            //El siguiente método interno se encarga de realizar la conversión en caso de que la URL envíe una respuesta
            @Override
            public void onResponse(Response<Forecast> response, Retrofit retrofit) {
                Forecast forecast = response.body();

                ArrayList<String> forecastStrings = new ArrayList<>();
                for (List list : forecast.getList()) {
                    String forecastString = getForecastString(list);
                    forecastStrings.add(forecastString);
                }

                adapter.clear();
                adapter.addAll(forecastStrings);
            }
            @Override
            public void onFailure(Throwable t) {
                Log.e("Update Forecasts", Arrays.toString(t.getStackTrace()));
            }
        });
    }

    private String getForecastString(List list) {
        Long dt = list.getDt();
        java.util.Date date = new java.util.Date(dt * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("E d/M");
        String dateString = dateFormat.format(date);

        String description = list.getWeather().get(0).getDescription();

        Long min = Math.round(list.getTemp().getMin());
        Long max = Math.round(list.getTemp().getMax());

        return String.format("%s - %s - %s/%s",
                dateString, description, min, max
        );
    }
}