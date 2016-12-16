package br.com.digithobraisl.avalieme;

import java.util.List;

public class AvalieMeResponse {

    private String formulario;
    private List<Avaliacao> avaliacoes;
    private List<String> pontosFortes;
    private List<String> pontosDeMelhorias;


    public AvalieMeResponse(String formulario, List<Avaliacao> avaliacoes, List<String> pontosFortes, List<String> pontosDeMelhorias) {
        this.formulario = formulario;
        this.avaliacoes = avaliacoes;
        this.pontosFortes = pontosFortes;
        this.pontosDeMelhorias = pontosDeMelhorias;
    }

    public String getFormulario() {
        return formulario;
    }

    public void setFormulario(String formulario) {
        this.formulario = formulario;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public List<String> getPontosFortes() {
        return pontosFortes;
    }

    public void setPontosFortes(List<String> pontosFortes) {
        this.pontosFortes = pontosFortes;
    }

    public List<String> getPontosDeMelhorias() {
        return pontosDeMelhorias;
    }

    public void setPontosDeMelhorias(List<String> pontosDeMelhorias) {
        this.pontosDeMelhorias = pontosDeMelhorias;
    }
}
