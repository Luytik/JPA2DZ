package currencyTest;

import CurrencyExchange.Currency;
import CurrencyExchange.GetCurrency;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class TestCurrency {

    @Test
    public void getCurrencies() {

        String currencyGSON = "";
        Currency[] currenciesT;

        try {
            URL url = new URL("https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String text = "";
            for (; ; ) {
                text = br.readLine();

                if (text == null)
                    break;
                currencyGSON += text;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson g = new GsonBuilder().create();
        currenciesT = g.fromJson(currencyGSON, Currency[].class);

        GetCurrency gc = new GetCurrency();

        assertEquals(currenciesT[0].toString(), gc.getCurrencies()[0].toString());
    }
}
