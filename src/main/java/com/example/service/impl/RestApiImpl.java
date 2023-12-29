package com.example.service.impl;

import com.example.portfolio.Investment;
import com.example.portfolio.Stock;
import com.example.service.RestApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RestApiImpl implements RestApi {
    private static final OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new Gson();

    @Override
    public void saveInvestment(Investment investment) {
        String json = gson.toJson(investment);

        // Define your API endpoint URL
        String apiUrl = "http://localhost:9123/api/investments";

        // Build request body with JSON content
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);

        // Build POST request
        Request request = new Request.Builder()
                .url(apiUrl)
                .post(body)
                .build();

        // Send POST request
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                // Request successful, handle response if needed
                String responseBody = response.body().string();
                // Process responseBody or perform actions accordingly
            } else {
                // Request failed, handle error
                System.out.println("Error: " + response.code() + " - " + response.message());
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception appropriately
        }
    }

    @Override
    public List<Stock> getInvestments() {

        // Define your API endpoint URL
        String apiUrl = "http://localhost:9123/api/investments";

        // Build request body with JSON content

        // Build POST request
        Request request = new Request.Builder()
                .url(apiUrl)
                .get()
                .build();

        // Send POST request
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                // Request successful, handle response if needed
                String responseBody = response.body().string();
                // Process responseBody or perform actions accordingly
                // Gson instance to deserialize JSON to InvestmentResponse class
                Gson gson = new GsonBuilder().create();

                // Define the type for Gson to correctly deserialize into List<Investment>
                Type investmentListType = new TypeToken<List<Stock>>() {
                }.getType();

                // Deserialize JSON response into List<Investment>
                List<Stock> investments = gson.fromJson(responseBody, investmentListType);

                // Now you have a list of Investment objects from the response
                for (Stock investment : investments) {
                    System.out.println("Received Investment: " + investment.toString());
                    // Perform actions with each Investment object as needed
                }
                return investments;
            } else {
                // Request failed, handle error
                System.out.println("Error: " + response.code() + " - " + response.message());
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception appropriately
        }
        return new ArrayList<Stock>();
    }

}
