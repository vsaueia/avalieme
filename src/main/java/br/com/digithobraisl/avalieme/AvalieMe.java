package br.com.digithobraisl.avalieme;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@RestController
public class AvalieMe {

    @RequestMapping("/avaliacao")
    public List<Avaliacao> avaliacao(@RequestParam(value = "nome") String nome) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String consumeJSONString = restTemplate.getForObject("https://api.typeform.com/v1/form/bCqaHo?key=e2091ac4b93b4bfd26494c50c97a0e3bd96d5cdb&completed=true", String.class);
        TypeFormResponse typeFormResponse = new ObjectMapper().readValue(consumeJSONString, TypeFormResponse.class);
        return new Conversor(typeFormResponse).obterResultados(nome);
    }
}
