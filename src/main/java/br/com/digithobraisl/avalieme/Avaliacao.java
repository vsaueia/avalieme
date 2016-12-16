package br.com.digithobraisl.avalieme;

import java.math.BigDecimal;

class Avaliacao {
    private String competencia;
    private BigDecimal auto;
    private BigDecimal time;

    public Avaliacao(String competencia, BigDecimal auto, BigDecimal time) {
        this.competencia = competencia;
        this.auto = auto;
        this.time = time;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }

    public BigDecimal getAuto() {
        return auto;
    }

    public void setAuto(BigDecimal auto) {
        this.auto = auto;
    }

    public BigDecimal getTime() {
        return time;
    }

    public void setTime(BigDecimal time) {
        this.time = time;
    }
}
