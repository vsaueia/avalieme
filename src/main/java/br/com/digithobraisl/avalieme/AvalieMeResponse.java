package br.com.digithobraisl.avalieme;

import java.util.List;

public class AvalieMeResponse {

    private String formulario;
    private List<Avaliacao> avaliacaos;

    public AvalieMeResponse(String formulario, List<Avaliacao> avaliacaos) {
        this.formulario = formulario;
        this.avaliacaos = avaliacaos;
    }

    public String getFormulario() {
        return formulario;
    }

    public void setFormulario(String formulario) {
        this.formulario = formulario;
    }

    public List<Avaliacao> getAvaliacaos() {
        return avaliacaos;
    }

    public void setAvaliacaos(List<Avaliacao> avaliacaos) {
        this.avaliacaos = avaliacaos;
    }
}
