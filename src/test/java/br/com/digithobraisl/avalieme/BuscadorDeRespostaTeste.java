package br.com.digithobraisl.avalieme;

import br.com.digithobraisl.avalieme.BuscadorDeResposta;
import br.com.digithobraisl.avalieme.Question;
import br.com.digithobraisl.avalieme.Response;
import br.com.digithobraisl.avalieme.TypeFormResponse;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class BuscadorDeRespostaTeste {

    private static final String NOME_DO_AVALIADOR = "Evenlyn";
    private static final String NOME_DO_AVALIADO = "Jhonatan";
    private static final String TEXTFIELD_34236876 = "textfield_34236876";
    private static final String TEXTFIELD_34236877 = "textfield_34236877";

    @Test
    public void deve_buscar_as_respostas_para_um_nome() throws Exception {
        Response respostaEsperada = criarResponse();
        TypeFormResponse typeFormResponse = criarTypeFormResponse(respostaEsperada);

        List<Response> respostas = BuscadorDeResposta.of(typeFormResponse).buscar(NOME_DO_AVALIADO);

        assertThat(respostas, hasItem(respostaEsperada));
    }

    @Test
    public void nao_deve_encontrar_as_respostas_para_um_nome() throws Exception {
        TypeFormResponse typeFormResponse = criarTypeFormResponse(criarResponse());
        String nomeDoAvaliado = "Thiago";

        List<Response> respostas = BuscadorDeResposta.of(typeFormResponse).buscar(nomeDoAvaliado);

        assertTrue(respostas.isEmpty());
    }

    private TypeFormResponse criarTypeFormResponse(Response respostaEsperada) {
        Question quemRespondeu = question(TEXTFIELD_34236876, "Qual o seu nome?");
        Question paraQuemRespondeu = question(TEXTFIELD_34236877, "Quem você está avaliando?");


        TypeFormResponse typeFormResponse = new TypeFormResponse();
        typeFormResponse.setResponses(Arrays.asList(respostaEsperada));
        typeFormResponse.setQuestions(Arrays.asList(paraQuemRespondeu, quemRespondeu));
        return typeFormResponse;
    }

    private Response criarResponse() {
        Response respostaEsperada = new Response();
        respostaEsperada.setCompleted("1");
        respostaEsperada.setToken("132");
        respostaEsperada.setAnswers(new HashMap<String, String>() {{
            put(TEXTFIELD_34236876, NOME_DO_AVALIADOR);
            put(TEXTFIELD_34236877, NOME_DO_AVALIADO);
            put("list_34236879_choice", "Possui um bom conhecimento sobre o tema. Está praticando sem maiores problemas, obtendo bons resultados.");
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
