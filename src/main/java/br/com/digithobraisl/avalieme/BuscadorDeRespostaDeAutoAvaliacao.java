package br.com.digithobraisl.avalieme;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BuscadorDeRespostaDeAutoAvaliacao {

    private static final String EU = "EU";
    private static final String QUEM_RESPENDEU = "Qual o seu nome?";
    private static final String PARA_QUEM_RESPENDEU = "Quem você está avaliando?";

    private TypeFormResponse typeFormResponse;
    private String chaveDeQuemRespondeu;
    private String chaveDeParaQuemRespondeu;

    private BuscadorDeRespostaDeAutoAvaliacao(TypeFormResponse typeFormResponse) {
        this.typeFormResponse = typeFormResponse;
    }

    public static BuscadorDeRespostaDeAutoAvaliacao of(TypeFormResponse typeFormResponse) {
        return new BuscadorDeRespostaDeAutoAvaliacao(typeFormResponse);
    }

    public List<Response> buscar(String nome) {
        List<Response> respostasEncontradas = new ArrayList<>();
        List<Response> responses = typeFormResponse.getResponses();
        chaveDeQuemRespondeu = buscarChaveDe(QUEM_RESPENDEU);
        chaveDeParaQuemRespondeu = buscarChaveDe(PARA_QUEM_RESPENDEU);

        responses.forEach(response -> {
            if (existeRespostaPara(nome, response)) {
                respostasEncontradas.add(response);
            }
        });

        return respostasEncontradas;
    }

    private String buscarChaveDe(String chave) {
        List<Question> questions = typeFormResponse.getQuestions();
        return questions.stream().filter(question -> question.getQuestion().equals(chave)).map(Question::getId).findAny().orElse("");
    }

    private boolean existeRespostaPara(String nome, Response response) {
        Map<String, String> answers = response.getAnswers();
        String quemRespondeu = answers.get(chaveDeQuemRespondeu).trim().toUpperCase();
        String respondeuParaQuem = answers.get(chaveDeParaQuemRespondeu).trim().toUpperCase();
        return quemRespondeu.equals(nome.toUpperCase()) && (respondeuParaQuem.contains(nome.toUpperCase()) || respondeuParaQuem.contains(EU));
    }

}

