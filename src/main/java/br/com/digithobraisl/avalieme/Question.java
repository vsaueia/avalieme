package br.com.digithobraisl.avalieme;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Question {
    private String id;
    private String question;
    private Long field_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Long getField_id() {
        return field_id;
    }

    public void setField_id(Long field_id) {
        this.field_id = field_id;
    }
}
