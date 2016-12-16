package br.com.digithobraisl.avalieme;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

public class RepostaDoTypeFormTeste {
    public static final String NOME_DO_COLABORADOR = "Evelyn";
    public static final String TEXTFIELD_34236876 = "textfield_34236876";
    public static final String TEXTFIELD_34236877 = "textfield_34236877";

    @Test
    public void name() {
        TypeFormResponse response = criarTypeFormResponse(criarResponse());
        Conversor conversor = new Conversor(response);

        //Avaliacao ingles = conversor.obterResultados("Renan");

//        Assert.assertEquals(ingles.getAuto(), 3);
    }

    private TypeFormResponse criarTypeFormResponse(Response respostaEsperada) {
        Question quemRespondeu = question(TEXTFIELD_34236876, "Qual o seu nome?");
        Question paraQuemRespondeu = question(TEXTFIELD_34236877, "Quem você está avaliando?");
        Question ingles = question("list_34236893_choice", "INGLÊS");

        TypeFormResponse typeFormResponse = new TypeFormResponse();
        typeFormResponse.setResponses(Arrays.asList(respostaEsperada));
        typeFormResponse.setQuestions(Arrays.asList(paraQuemRespondeu, quemRespondeu, ingles));

        return typeFormResponse;
    }

    private Response criarResponse() {
        Response respostaEsperada = new Response();
        respostaEsperada.setCompleted("1");
        respostaEsperada.setToken("132");
        respostaEsperada.setAnswers(new HashMap<String, String>() {{
            put(TEXTFIELD_34236876, NOME_DO_COLABORADOR);
            put(TEXTFIELD_34236877, NOME_DO_COLABORADOR);
            put("list_34236893_choice", "Possui um bom conhecimento sobre o tema. Está praticando sem maiores problemas, obtendo bons resultados.");
        }});
        return respostaEsperada;
    }

    private Question question(String textfield_34236876, String question) {
        Question quemRespondeu = new Question();
        quemRespondeu.setId(textfield_34236876);
        quemRespondeu.setQuestion(question);
        return quemRespondeu;
    }
}
