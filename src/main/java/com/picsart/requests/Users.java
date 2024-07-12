package com.picsart.requests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.picsart.constants.Endpoints;
import com.picsart.dtos.Posts;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static io.restassured.RestAssured.given;

@Slf4j
public class Users {

    public static <R> List<R> get(String serverUrl, Endpoints endPoint, Class<R> responseType) {
        RestAssured.baseURI = serverUrl;
        log.info("get");

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .when()
                        .get(serverUrl + endPoint.getPath());

        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, responseType);
        try {
            return objectMapper.readValue(response.asString(), listType);
        } catch (JsonProcessingException jsonProcessingException) {
            log.error("Unable to process json response", jsonProcessingException);
            return null;
        }
    }

    public static <T, R> R post(String serverUrl, Endpoints endPoint, T newPost, Class<R> responseType) {
        RestAssured.baseURI = serverUrl;
        log.info("post");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = null;
        try {
            jsonPayload = objectMapper.writeValueAsString(newPost);
        } catch (JsonProcessingException jsonProcessingException) {
            log.error("Unable to process json object", jsonProcessingException);
        }

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .when()
                        .body(jsonPayload)
                        .post(serverUrl + endPoint.getPath());

        try {
            return objectMapper.readValue(response.asString(), responseType);
        } catch (JsonProcessingException jsonProcessingException) {
            log.error("Unable to process json response", jsonProcessingException);
            return null;
        }
    }
}
