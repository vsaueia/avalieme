package br.com.digithobraisl.avalieme;

public enum  PerguntaCompetencia {

    PRINCIPIOS_AGEIS_E_LEAN("PRINCÍPIOS ÁGEIS E LEAN", "Conhece e pratica os 4 valores e os 12 princípios descritos no manifesto ágil e os 7 princípios " +
            "Lean: Eliminar o desperdício, integrar qualidade, criar conhecimento, adiar compromissos, entregar rápido, respeitar as pessoas e otimizar o todo;");


    private String pergunta;
    private String questao;

    PerguntaCompetencia(String pergunta, String questao) {
        this.pergunta = pergunta;
        this.questao = questao;
    }
}
