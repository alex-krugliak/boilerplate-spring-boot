package com.web;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.web.controller.wrapper.UserView;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Created by alex on 27.02.18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class WebTest {

    private static final Logger logger = LoggerFactory.getLogger(WebTest.class);

    @Test
    public void getUsers() {
        HttpClient client = HttpClientBuilder.create().build();

        HttpRequestBase request = new HttpGet("http://127.0.0.1:8090/users?page=0&size=10&dir=DESC");
        request.addHeader("Content-Type", "application/json");

        setupBaseRequestParams(request);
        try {
            HttpResponse response = client.execute(request);
            int responseCode = response.getStatusLine().getStatusCode();
            Assert.assertEquals("Response not OK", responseCode, 200);

            String jsonResponse = IOUtils.toString(response.getEntity().getContent());
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(jsonResponse, JsonArray.class);
            JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
            UserView userView = gson.fromJson(jsonObject, UserView.class);

            Assert.assertEquals("Wrong users pages amount", userView.getName(), "Vasia3");

        } catch (IOException ex) {
            logger.error("Error, message - " + ex.getMessage());
            throw new RuntimeException("Can't get the users list");
        }

    }

    private void setupBaseRequestParams(HttpRequestBase request) {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(10 * 1000)
                .setConnectionRequestTimeout(10 * 1000)
                .setSocketTimeout(15 * 1000).build();
        request.setConfig(requestConfig);
    }
}
