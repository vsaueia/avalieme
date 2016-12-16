package br.com.digithobraisl.avalieme;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Conversor {
    private final TypeFormResponse typeFormResponse;


    public Conversor(TypeFormResponse respostas) {
        this.typeFormResponse = respostas;
    }

    public List<Avaliacao> obterResultados(String avaliado) {
        Map<String, Avaliacao> avaliacoes = new HashMap<>();

        List<Response> respostasDeAutoAvaliacao = BuscadorDeRespostaDeAutoAvaliacao.of(this.typeFormResponse).buscar(avaliado);
        Map<String, String> autoAvaliacao = respostasDeAutoAvaliacao.get(0).getAnswers();

        for (Question question : typeFormResponse.getQuestions()) {
            String questao = question.getId();
            String respostaParaAQuestao = autoAvaliacao.get(questao);
            int nota = Escala.nota(respostaParaAQuestao);
            if(nota != 0){
                Avaliacao avaliacao = new Avaliacao(question.getQuestion(), BigDecimal.valueOf(nota), BigDecimal.ZERO);
                avaliacoes.put(question.getId(), avaliacao);
            }
        }

        List<Response> respostasDoTime = BuscadorDeResposta.of(this.typeFormResponse).buscar(avaliado);
        List<Map<String, String>> avaliacoesDoTime = respostasDoTime.stream().map(Response::getAnswers).collect(Collectors.toList());

        for (Question question : typeFormResponse.getQuestions()) {
            String questao = question.getId();
            BigDecimal somatorioDaNota = somarNotasDoTimeParaQuestao(avaliacoesDoTime, questao);
            if (somatorioDaNota.compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal quantidadeDeRespostas = quantidadeDeRespotas(avaliacoesDoTime, questao);
                BigDecimal media = somatorioDaNota.divide(quantidadeDeRespostas, 0, BigDecimal.ROUND_CEILING);
                avaliacoes.get(question.getId()).setTime(media);
            }
        }

        return new ArrayList<>(avaliacoes.values());
    }

    private BigDecimal quantidadeDeRespotas(List<Map<String, String>> avaliacoesDoTime, String questao) {

        BigDecimal somatorioDaNota = BigDecimal.ZERO;
        for (Map<String, String> avaliacao : avaliacoesDoTime) {
            if (avaliacao.containsKey(questao)) {
                somatorioDaNota = somatorioDaNota.add(BigDecimal.ONE);
            }
        }

        return somatorioDaNota;
    }

    private BigDecimal somarNotasDoTimeParaQuestao(List<Map<String, String>> avaliacoesDoTime, String questao) {
        BigDecimal somatorioDaNota = BigDecimal.ZERO;
        for (Map<String, String> avaliacao : avaliacoesDoTime) {
            String respostaParaQuestao = avaliacao.get(questao);
            somatorioDaNota = somatorioDaNota.add(BigDecimal.valueOf(Escala.nota(respostaParaQuestao)));
        }

        return somatorioDaNota;
    }
}
