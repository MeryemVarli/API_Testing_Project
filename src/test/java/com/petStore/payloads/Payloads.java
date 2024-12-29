package com.petStore.payloads;

public class Payloads {



    public static  String getPetPayload(int id,String name){


        return "{\"id\":"+id+",\n" +
                "  \"name\":\""+name+"\",\n" +
                "  \"status\":\"available\",\n" +
                "  \"category\":\n" +
                "  {\n" +
                "    \"id\":1200,\n" +
                "    \"name\":\"Lilia\"\n" +
                "  },\n" +
                "  \"photoUrls\":[\n" +
                "    \"some dog images\",\"another image\",\"one images\"\n" +
                "  ],\n" +
                "  \"tags\":[\n" +
                "    {\n" +
                "      \"id\":555,\"name\":\"Roxy\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";


    }


















}
