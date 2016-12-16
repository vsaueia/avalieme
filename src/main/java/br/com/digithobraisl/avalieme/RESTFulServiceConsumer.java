package br.com.digithobraisl.avalieme;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

public class RESTFulServiceConsumer {
 
    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        
        String consumeJSONString = restTemplate.getForObject("https://api.typeform.com/v1/form/bCqaHo?key=e2091ac4b93b4bfd26494c50c97a0e3bd96d5cdb", String.class);
        System.out.println("JSON String: "+consumeJSONString);

        HashMap<String, String> hashMap = restTemplate.getForObject("https://api.typeform.com/v1/form/bCqaHo?key=e2091ac4b93b4bfd26494c50c97a0e3bd96d5cdb",  HashMap.class);
        System.out.println("JSON Map: "+hashMap);

    }
 
}