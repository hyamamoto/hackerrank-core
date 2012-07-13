package jp.freepress.hackerrank.splash;

import static jp.freepress.hackerrank.splash.NumberCodec.decode;
import jp.freepress.hackerrank.util.Searcher_ListOfChiefExecutiveOfficers;
import jp.freepress.hackerrank.util.Searcher_ListOfNationalCapitals;
import jp.freepress.hackerrank.util.Searcher_ListOfNovelistFromUS;
import jp.freepress.hackerrank.util.WikiDicMap;
import junit.framework.TestCase;

// NOTE: this really isn't a test but a snippet
public class NumberCodecTest extends TestCase {

  public void testNumberCodec_MappingDecode() {

    WikiDicMap listOfNovelist = new Searcher_ListOfNovelistFromUS();
    WikiDicMap listOfCapitals = new Searcher_ListOfNationalCapitals();
    WikiDicMap listOfCEO = new Searcher_ListOfChiefExecutiveOfficers();

    listOfNovelist.setupMap();
    listOfCapitals.setupMap();
    listOfCEO.setupMap();

    String test;
    String seed1;
    String seed2;

    test = "ilyh wkrxvdqg, vhyhq kxqguhg dqg iliwb-vla";
    seed1 = listOfCapitals.getMap().get("Indonesia"); // "Jakarta";
    seed2 = "Mdnduwd";
    decode(test, seed1, seed2);

    test = "oxda cqxdbjwm jwm brgch-cqann";
    seed1 = "DuBose Heyward";
    seed2 = "MdKxbn Qnhfjam";
    decode(test, seed1, seed2);

    test = "knaj ymtzxfsi, jnlmy mzsiwji fsi xncyd-ktzw";
    seed1 = "Jean M. Auel";
    seed2 = "Ojfs R. Fzjq";
    decode(test, seed1, seed2);

    test = "ilyh wkrxvdqg, wkuhh kxqguhg dqg vlawb-ilyh";
    seed1 = "Stephen A. Schwarzman";
    seed2 = "Vwhskhq D. Vfkzducpdq";
    decode(test, seed1, seed2);

    // the author of the book Speak
    test = "jnlmy ymtzxfsi, xjajs mzsiwji fsi snsjyd-knaj";
    seed1 = listOfNovelist.getMap().get("Speak"); // "Laurie Halse Anderson"
    seed2 = "Qfzwnj Mfqxj Fsijwxts";
    System.err.println(seed1);
    decode(test, seed1, seed2);

    // the author of the book The Confessions of Nat Turner
    test = "mvby aovbzhuk, zpe obukylk huk upulaf-zpe";
    seed1 = listOfNovelist.getMap().get("The Confessions of Nat Turner");
    seed2 = "Dpsspht Zafyvu";
    decode(test, seed1, seed2);

    // the author of the book Jurgen, A Comedy of Justice
    seed1 = listOfNovelist.getMap().get("Jurgen, A Comedy of Justice");// "James Branch Cabell"
    seed2 = "Sjvnb Kajwlq Ljknuu";
    test = "cqann cqxdbjwm, oren qdwmanm jwm brgch-cqann";
    decode(test, seed1, seed2);

    seed1 = listOfNovelist.getMap().get("Years of Grace"); // Margaret Ayer Barnes
    System.out.println(seed1);
    seed2 = "Lzqfzqds Zxdq Azqmdr";
    test = "nmd sgntrzmc, ehud gtmcqdc zmc entqsx";
    decode(test, seed1, seed2);

    seed1 = listOfNovelist.getMap().get("The Oxygen Man"); // Steve Yarbrough
    seed2 = "Abmdm Gizjzwcop";
    test = "mqopb bpwcaivl, bpzmm pcvlzml ivl nqnbg-aqf";
    decode(test, seed1, seed2);

    seed1 =
        listOfNovelist.getMap().get(
            "Epilogue and Testimony of Kohen Ben Gadol, The Red Letters, "
                + "The Last Sounding of the Shofar, The Torah, B'reishith,"
                + "The Complete Book of Genesis, "
                + "Judeo/Christian Qur'an, The Red Letters, My Isha, Psalms of Praise");
    seed2 = "Rhq Qnahm Cntfkzrr Rgdozqc";
    test = "entq sgntrzmc, ehud gtmcqdc zmc dhfgsx-svn";
    decode(test, seed1, seed2);

    // 103
    //seed1 = listOfNovelist.getMap().get(""); // ?
    //seed2 = "";
    //test = "gbgx mahnltgw, lxoxg angwkxw tgw mpxgmr";
    //decode(test, seed1, seed2);

    seed1 = listOfNovelist.getMap().get("The Fan Man");
    seed2 = "Pbeebtf Dhmspbgdex";
    test = "gbgx angwkxw tgw ybymr-hgx";
    decode(test, seed1, seed2);

    // 164
    seed1 = listOfNovelist.getMap().get("The Fuck-Up");
    seed2 = "Bsuivs Ofstftjbo";
    test = "fjhiu uipvtboe, uisff ivoesfe boe uxfouz-gjwf";
    decode(test, seed1, seed2);

    // 247
    seed1 = listOfCEO.getMap().get("S. C. Johnson &amp; Son".replaceAll("&amp;", "&"));
    seed2 = "Livfivx Jmwo Nslrwsr MMM";
    test = "wmb xlsywerh, xas lyrhvih erh xlmvxc";
    decode(test, seed1, seed2);

    // 249
    seed1 = listOfNovelist.getMap().get("Watch Your Mouth");
    seed2 = "Livqmt Pivltmz";
    test = "amdmv bpwcaivl, bpzmm pcvlzml ivl vqvmbg-bew";
    decode(test, seed1, seed2);

    // 329
    seed1 = listOfNovelist.getMap().get("Lamb in His Bosom");
    seed2 = "Trifczev Dzccvi";
    test = "fev kyfljreu, vzxyk yleuivu reu kyzikp-wzmv";
    decode(test, seed1, seed2);

    // 392
    seed1 = listOfCapitals.getMap().get("Comoros");
    seed2 = "Npspoj";
    test = "ojof uipvtboe, tjy ivoesfe";
    decode(test, seed1, seed2);

    // 461
    seed1 = listOfCEO.getMap().get("Tata Steel");
    seed2 = "W Hpocpmvhvi";
    test = "zdbco ocjpnviy viy nzqziot-ocmzz";
    decode(test, seed1, seed2);

    // 583
    seed1 = listOfNovelist.getMap().get("The Heart Has Wings");
    seed2 = "Niqbp Jitleqv,";
    test = "wvm bpwcaivl, vqvm pcvlzml ivl mqopbg";
    decode(test, seed1, seed2);

    // 603
    seed1 = listOfNovelist.getMap().get("\"Elegies for the Water\"".replace("\\\"", "\""));
    seed2 = "Zrsvsz Voo Gsvvskwc";
    test = "osqrd rexnbon kxn pspdi-xsxo";
    decode(test, seed1, seed2);

    // 707
    seed1 = listOfNovelist.getMap().get("The Balloonist");
    seed2 = "JxzAlkxia Exoofp";
    test = "bfdeq qelrpxka, qeobb erkaoba xka pbsbkqv";
    decode(test, seed1, seed2);

    // 785
    seed1 = listOfNovelist.getMap().get("Abeng");
    seed2 = "Dztyvccv Tczww";
    test = "wzmv yleuivu reu knf";
    decode(test, seed1, seed2);

    // 1627
    seed1 = listOfNovelist.getMap().get("Stet");
    seed2 = "Pgsky Ingvsgt,";
    test = "totk znuaygtj gtj totkze-totk";
    decode(test, seed1, seed2);

  }
}
