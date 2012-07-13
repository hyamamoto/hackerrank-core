package jp.freepress.hackerrank.splash;

import java.util.Arrays;

import com.google.gson.JsonElement;

public class JsonGameGameImpl implements JsonGameGame {

  private String n;

  private String id;

  // 1 - 10000

  private String cph_number;

  private String fact_question;

  private String fact_cph_answer;

  private String[] wiki_link;

  // 10001 -

  private String cph_question;

  private String sample_question;

  private String sample_cph_answer;

  private JsonElement source;

  private JsonGameGameImpl() {
    super();
  }

  @Override
  public String getN() {
    return n;
  }

  @Override
  public void setN(String n) {
    this.n = n;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String getCph_number() {
    return cph_number;
  }

  @Override
  public void setCph_number(String cph_number) {
    this.cph_number = cph_number;
  }

  @Override
  public String getFact_question() {
    return fact_question;
  }

  @Override
  public void setFact_question(String fact_question) {
    this.fact_question = fact_question;
  }

  @Override
  public String getFact_cph_answer() {
    return fact_cph_answer;
  }

  @Override
  public void setFact_cph_answer(String fact_cph_answer) {
    this.fact_cph_answer = fact_cph_answer;
  }

  @Override
  public String[] getWiki_link() {
    return wiki_link;
  }

  @Override
  public void setWiki_link(String[] wiki_link) {
    this.wiki_link = wiki_link;
  }

  @Override
  public String getCph_question() {
    return cph_question;
  }

  @Override
  public void setCph_question(String cph_question) {
    this.cph_question = cph_question;
  }

  @Override
  public String getSample_question() {
    return sample_question;
  }

  @Override
  public void setSample_question(String sample_question) {
    this.sample_question = sample_question;
  }

  @Override
  public String getSample_cph_answer() {
    return sample_cph_answer;
  }

  @Override
  public void setSample_cph_answer(String sample_cph_answer) {
    this.sample_cph_answer = sample_cph_answer;
  }

  @Override
  public JsonElement getSource() {
    return source;
  }

  @Override
  public void setSource(JsonElement source) {
    this.source = source;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("GameGame [n=");
    builder.append(n);
    builder.append(", id=");
    builder.append(id);
    if (cph_number != null) {
      builder.append(", cph_number=");
      builder.append(cph_number);
    }
    if (fact_question != null) {
      builder.append(", fact_question=");
      builder.append(fact_question);
    }
    if (fact_cph_answer != null) {
      builder.append(", fact_cph_answer=");
      builder.append(fact_cph_answer);
    }
    if (wiki_link != null) {
      builder.append(", wiki_link=");
      builder.append(Arrays.toString(wiki_link));
    }
    if (cph_question != null) {
      builder.append(", cph_question=");
      builder.append(cph_question);
    }
    if (sample_question != null) {
      builder.append(", sample_question=");
      builder.append(sample_question);
    }
    if (sample_cph_answer != null) {
      builder.append(", sample_cph_answer=");
      builder.append(sample_cph_answer);
    }
    if (source != null) {
      builder.append(", source=");
      builder.append(source);
    }
    builder.append("]");
    return builder.toString();
  }

}
