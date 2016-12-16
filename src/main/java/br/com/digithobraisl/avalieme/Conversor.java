package br.com.digithobraisl.avalieme;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Conversor {
    private final TypeFormResponse typeFormResponse;
    private final String chaveDoPontoForte;
    private final String chaveDoPontoDeMelhoria;

    private String PONTOS_FORTES = "Quais os pontos fortes dessa pessoa?";
    private String PONTOS_DE_MELHORIA = "Quais os pontos de melhoria dessa pessoa?";
    private Response respostaDaAutoAvaliacao;
    private List<Response> respostasDoTime;


    public Conversor(TypeFormResponse respostas) {
        this.typeFormResponse = respostas;
        chaveDoPontoForte = respostas.getQuestions().stream().filter(question -> question.getQuestion()
                .equals(PONTOS_FORTES)).map(Question::getId).findFirst().orElse("");
        chaveDoPontoDeMelhoria = respostas.getQuestions().stream().filter(question -> question.getQuestion()
                .equals(PONTOS_DE_MELHORIA)).map(Question::getId).findFirst().orElse("");
    }

    public List<Avaliacao> obterResultados(String avaliado) {
        Map<String, Avaliacao> avaliacoes = new HashMap<>();

        List<Response> respostasDeAutoAvaliacao = BuscadorDeRespostaDeAutoAvaliacao.of(this.typeFormResponse).buscar(avaliado);
        if (!respostasDeAutoAvaliacao.isEmpty()) {

            respostaDaAutoAvaliacao = respostasDeAutoAvaliacao.get(0);
            Map<String, String> autoAvaliacao = respostaDaAutoAvaliacao.getAnswers();

            for (Question question : typeFormResponse.getQuestions()) {
                String questao = question.getId();
                String respostaParaAQuestao = autoAvaliacao.get(questao);
                int nota = Escala.nota(respostaParaAQuestao);
                if (nota != 0) {
                    Avaliacao avaliacao = new Avaliacao(question.getQuestion(), BigDecimal.valueOf(nota), BigDecimal.ZERO);
                    avaliacoes.put(question.getId(), avaliacao);
                }
            }

            respostasDoTime = BuscadorDeResposta.of(this.typeFormResponse).buscar(avaliado);
            respostasDoTime.remove(respostaDaAutoAvaliacao);
            List<Map<String, String>> avaliacoesDoTime = respostasDoTime.stream().map(Response::getAnswers).collect(Collectors.toList());

            for (Question question : typeFormResponse.getQuestions()) {
                String questao = question.getId();
                BigDecimal somatorioDaNota = somarNotasDoTimeParaQuestao(avaliacoesDoTime, questao);
                if (somatorioDaNota.compareTo(BigDecimal.ZERO) > 0) {
                    BigDecimal quantidadeDeRespostas = quantidadeDeRespotas(avaliacoesDoTime, questao);
                    BigDecimal media = somatorioDaNota.divide(quantidadeDeRespostas, 0, BigDecimal.ROUND_CEILING);
                    Avaliacao avaliacao = avaliacoes.get(question.getId());
                    if (avaliacao != null) {
                        avaliacao.setTime(media);
                    }
                }
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

    public List<String> obterPontosFortes() {

        List<Response> respostas = new ArrayList<>(respostasDoTime);
        respostas.add(respostaDaAutoAvaliacao);
        List<String> pontosFortes = respostas.stream().map(response -> response.getAnswers().entrySet().stream())
                .flatMap(entryStream -> entryStream.filter(entry -> entry.getKey().equals(chaveDoPontoForte)))
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());
        return pontosFortes;
    }

    public List<String> obterPontosDeMelhorias() {
        List<Response> respostas = new ArrayList<>(respostasDoTime);
        respostas.add(respostaDaAutoAvaliacao);
        List<String> pontosDeMelhoria = respostas.stream().map(response -> response.getAnswers().entrySet().stream())
                .flatMap(entryStream -> entryStream.filter(entry -> entry.getKey().equals(chaveDoPontoDeMelhoria)))
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());

        return pontosDeMelhoria;
    }

    public boolean temResposta() {
        return respostasDoTime != null;
    }
}
