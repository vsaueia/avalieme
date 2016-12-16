package br.com.digithobraisl.avalieme;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;

public class TypeFormApiConsumer {
 
    public static void main(String args[]) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        
        String consumeJSONString = restTemplate.getForObject("https://api.typeform.com/v1/form/bCqaHo?key=e2091ac4b93b4bfd26494c50c97a0e3bd96d5cdb&completed=true", String.class);
        System.out.println("JSON String: "+consumeJSONString);
        TypeFormResponse typeFormResponse = new ObjectMapper().readValue(consumeJSONString, TypeFormResponse.class);

        HashMap<String, String> hashMap = restTemplate.getForObject("https://api.typeform.com/v1/form/bCqaHo?key=e2091ac4b93b4bfd26494c50c97a0e3bd96d5cdb",  HashMap.class);
        System.out.println("JSON Map: "+hashMap);

    }
 
}