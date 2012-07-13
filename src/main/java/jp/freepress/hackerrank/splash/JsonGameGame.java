package jp.freepress.hackerrank.splash;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.google.gson.JsonElement;


public interface JsonGameGame {

  public String getN();

  public void setN(String n);

  public String getId();

  public void setId(String id);

  public String getCph_number();

  public void setCph_number(String cph_number);

  public String getFact_question();

  public void setFact_question(String fact_question);

  public String getFact_cph_answer();

  public void setFact_cph_answer(String fact_cph_answer);

  public String[] getWiki_link();

  public void setWiki_link(String[] wiki_link);

  public String getCph_question();

  public void setCph_question(String cph_question);

  public String getSample_question();

  public void setSample_question(String sample_question);

  public String getSample_cph_answer();

  public void setSample_cph_answer(String sample_cph_answer);

  public JsonElement getSource();

  public void setSource(JsonElement source);

  public static class Util {
    private static class JsonGameGameIdComparator implements Comparator<JsonGameGame> {
      @Override
      public final int compare(JsonGameGame a, JsonGameGame b) {
        return a.getId() != null ? //
            b.getId() != null ? //
                (a.getId().compareTo(b.getId()))
                : 1 //
            : b.getId() != null ? -1 : 0;
      }
    }

    public static void sortById(List<JsonGameGame> gameList) {
      Collections.sort(gameList, new JsonGameGameIdComparator());
    }

    public static void removeIdDupes(List<JsonGameGame> gameList) {
      sortById(gameList);
      String tmpId = null;
      for (Iterator<JsonGameGame> it = gameList.iterator(); it.hasNext();) {
        JsonGameGame game = it.next();
        if (tmpId != null && tmpId.equals(game.getId())) {
          it.remove();
        }
        tmpId = game.getId();
      }
    }

  }

}

//
// JSON Samples
//

// {
// "game":{
// "n":1,
// "cph_number":"ilyh wkrxvdqg, vhyhq kxqguhg dqg iliwb-vla",
// "fact_question":"What is the capital of Indonesia?",
// "fact_cph_answer":"Mdnduwd",
// "id":1257156,
// "wiki_link":["aHR0cDovL2VuLndpa2lwZWRpYS5vcmcvd2lraS9MaXN0X29mX25vdmVsaXN0\nc19mcm9tX3RoZV9Vbml0ZWRfU3RhdGVz\n",
// "aHR0cDovL2VuLndpa2lwZWRpYS5vcmcvd2lraS9MaXN0X29mX25hdGlvbmFs\nX2NhcGl0YWxz\n",
// "aHR0cDovL2VuLndpa2lwZWRpYS5vcmcvd2lraS9MaXN0X29mX2NoaWVmX2V4\nZWN1dGl2ZV9vZmZpY2Vycw==\n"]
// },
// "ok":true
// }

// {
// "game":
// {
// "n":10001,
// "cph_question":"Decipher 'Cwbnbsijbuaiom'.",
// "sample_question":"What is the capital of Christmas Island?",
// "sample_cph_answer":"Zfscha Zcmb Wipy",
// "id":1747308,
// "source":[
// "aHR0cDovL2VuLndpa2lwZWRpYS5vcmcvd2lraS9MaXN0X29mX25vdmVsaXN0\nc19mcm9tX3RoZV9Vbml0ZWRfU3RhdGVz\n",
// "aHR0cDovL2VuLndpa2lwZWRpYS5vcmcvd2lraS9MaXN0X29mX25hdGlvbmFs\nX2NhcGl0YWxz\n",
// "aHR0cDovL2VuLndpa2lwZWRpYS5vcmcvd2lraS9MaXN0X29mX2NoaWVmX2V4\nZWN1dGl2ZV9vZmZpY2Vycw==\n"]
// },
// "ok":true
// }

// {
// "game":
// {
// "n":11001,
// "cph_question":"Decipher 'Repneykldkxew'.",
// "sample_question":"Where was Yasser Arafat born?",
// "sample_cph_answer":"Ywenk, Aculp",
// "id":1771232,
// "source":"Q29tcHV0YXRpb25hbCBLbm93bGVkZ2UgRW5naW5l\n"
// },
// "ok":true
// }
