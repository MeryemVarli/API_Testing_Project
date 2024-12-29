package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class HttpsRequests {




    public static Response GET(){

        return given().accept(ContentType.JSON).when().get()
                .then().statusCode(200).extract().response();
    }






    public static Response GET(String endPoint){

        return given().accept(ContentType.JSON).when().get(endPoint)
                .then().statusCode(200).extract().response();
    }






    public static Response GET(String tokenName,String token){

        return given().accept(ContentType.JSON)
                .header(tokenName,token)
                .when().get()
                .then().statusCode(200).extract().response();


    }




    public static Response POST(String endpoint,String payloads){

        return given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(payloads)
                .when()
                .post(endpoint)
                .then().statusCode(200).extract().response();
    }






    public static Response POST(String endpoint){

        return given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then().statusCode(200).extract().response();
    }





    public static Response POST(String endpoint, String tokenName, String token, String payloads){

        return   given().accept(ContentType.JSON)
                .header(tokenName, token)
                .contentType(ContentType.JSON)
                .body(payloads)
                .when()
                .post(endpoint)
                .then()
                .statusCode(200)
                .extract().response();

    }





    public static Response PUT(String id,String payload){

        return   RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(payload).when().put(id)
                .then().statusCode(200).extract().response();


    }





    public static Response DELETE(String id){

        return RestAssured.given().accept(ContentType.JSON).when()
                .delete(id).then().statusCode(200).extract().response();
    }















}
