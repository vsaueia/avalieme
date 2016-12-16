package br.com.digithobraisl.avalieme;

import br.com.digithobraisl.avalieme.BuscadorDeRespostaDeAutoAvaliacao;
import br.com.digithobraisl.avalieme.Question;
import br.com.digithobraisl.avalieme.Response;
import br.com.digithobraisl.avalieme.TypeFormResponse;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

public class BuscadorDeRespostaDeAutoAvaliacaoTeste {

    public static final String NOME_DO_COLABORADOR = "Evenlyn";
    public static final String TEXTFIELD_34236876 = "textfield_34236876";
    public static final String TEXTFIELD_34236877 = "textfield_34236877";

    @Test
    public void deve_achar_a_resposta_de_auto_avaliacao() {


        Question quemRespondeu = question(TEXTFIELD_34236876, "Qual o seu nome?");
        Question paraQuemRespondeu = question(TEXTFIELD_34236877, "Quem você está avaliando?");

        Response respostaEsperada = new Response();
        respostaEsperada.setCompleted("1");
        respostaEsperada.setToken("132");
        respostaEsperada.setAnswers(new HashMap<String, String>() {{
            put(TEXTFIELD_34236876, NOME_DO_COLABORADOR);
            put(TEXTFIELD_34236877, "Eu mesma");
            put("list_34236879_choice", "Possui um bom conhecimento sobre o tema. Está praticando sem maiores problemas, obtendo bons resultados.");
        }});

        TypeFormResponse typeFormResponse = new TypeFormResponse();
        typeFormResponse.setResponses(Arrays.asList(respostaEsperada));
        typeFormResponse.setQuestions(Arrays.asList(paraQuemRespondeu, quemRespondeu));

        List<Response> respostas = BuscadorDeRespostaDeAutoAvaliacao.of(typeFormResponse).buscar(NOME_DO_COLABORADOR);

        assertThat(respostas, hasItem(respostaEsperada));
    }

    private Question question(String textfield_34236876, String question) {
        Question quemRespondeu = new Question();
        quemRespondeu.setId(textfield_34236876);
        quemRespondeu.setQuestion(question);
        return quemRespondeu;
    }
}
