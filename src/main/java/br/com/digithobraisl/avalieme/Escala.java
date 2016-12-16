package br.com.digithobraisl.avalieme;

import java.util.HashMap;
import java.util.Map;

public class Escala {

    private static Map<String, Integer> map = new HashMap<>();

    static {
        map.put("Não possui conhecimento e experiência. É um iniciante no tema.", 1);
        map.put("Possui um pequeno conhecimento e pouca habilidade. Está dando os primeiros passos no tema e está aprendendo.", 2);
        map.put("Possui um bom conhecimento sobre o tema. Está praticando sem maiores problemas, obtendo bons resultados.", 3);
        map.put("Possui um bom conhecimento e habilidade sobre o tema. Também toma a frente dos problemas para resolvê-los.", 4);
        map.put("Domina o tema. é um expert. Destaca-se por ensinar outras pessoas. É uma referência para a equipe.", 5);
        map.put("Consegue combinar conhecimentos para melhorar ainda mais seu trabalho, além de ser um líder natural para esse tema.", 6);
        map.put("Cria novos conhecimentos sobre o assunto, domina totalmente sua execução e é uma inspiração para a equipe.", 7);
    }

    public static int nota(String resposta) {
        return map.get(resposta) != null ? map.get(resposta) : 0;
    }

}
