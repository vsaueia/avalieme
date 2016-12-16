package br.com.digithobraisl.avalieme;

import java.util.ArrayList;
import java.util.List;

public class BuscadorDeResposta {

    private static final String PARA_QUEM_RESPENDEU = "Quem você está avaliando?";

    private final TypeFormResponse typeFormResponse;
    private String paraQuemRespondeu;

    private BuscadorDeResposta(TypeFormResponse typeFormResponse) {
        this.typeFormResponse = typeFormResponse;
    }

    public static BuscadorDeResposta of(TypeFormResponse typeFormResponse) {
        return new BuscadorDeResposta(typeFormResponse);
    }

    public List<Response> buscar(String nome) {

        List<Response> respostasEncontradas = new ArrayList<>();
        List<Response> responses = typeFormResponse.getResponses();
        paraQuemRespondeu = buscarChaveDe(PARA_QUEM_RESPENDEU);

        responses.forEach(response -> {
            if (existeRespostaPara(nome, response)) {
                respostasEncontradas.add(response);
            }
        });
        return respostasEncontradas;
    }

    private boolean existeRespostaPara(String nome, Response response) {
        return response.getAnswers().get(paraQuemRespondeu).toUpperCase().contains(nome.toUpperCase());
    }

    private String buscarChaveDe(String chave) {
        List<Question> questions = typeFormResponse.getQuestions();
        return questions.stream().filter(question -> question.getQuestion().equals(chave)).map(Question::getId).findAny().orElse("");
    }
}
