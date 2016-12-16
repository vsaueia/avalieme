package br.com.digithobraisl.avalieme;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AvalieMe {

    @CrossOrigin(origins = "*")
    @RequestMapping("/avaliacao")
    public List<AvalieMeResponse> avaliacao(@RequestParam(value = "nome") String nome) throws IOException {

        List<AvalieMeResponse> responses = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        String urlTypeformQuestionarioUm = "https://api.typeform.com/v1/form/RJRIvz?key=e2091ac4b93b4bfd26494c50c97a0e3bd96d5cdb&completed=true";
        String consumeJSONString = restTemplate.getForObject(urlTypeformQuestionarioUm, String.class);
        TypeFormResponse typeFormResponse = new ObjectMapper().readValue(consumeJSONString, TypeFormResponse.class);
        Conversor primeiroFormulario = new Conversor(typeFormResponse);
        responses.add(new AvalieMeResponse("AVALIAÇÃO - DESENVOLVEDOR 1.2", primeiroFormulario.obterResultados(nome),
                primeiroFormulario.obterPontosFortes(), primeiroFormulario.obterPontosDeMelhorias()));

        String urlTypeformQuestionarioDois = "https://api.typeform.com/v1/form/TfjnBY?key=e2091ac4b93b4bfd26494c50c97a0e3bd96d5cdb&completed=true";
        consumeJSONString = restTemplate.getForObject(urlTypeformQuestionarioDois, String.class);
        typeFormResponse = new ObjectMapper().readValue(consumeJSONString, TypeFormResponse.class);
        Conversor segundoFormulario = new Conversor(typeFormResponse);
        responses.add(new AvalieMeResponse("AVALIAÇÃO - DESENVOLVEDOR 1.3", segundoFormulario.obterResultados(nome),
                segundoFormulario.obterPontosFortes(), segundoFormulario.obterPontosDeMelhorias()));

        return responses;
    }
}
