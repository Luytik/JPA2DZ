package CurrencyExchange;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class GetCurrency {
    private String currencyGSON = "";
    private Currency[] currencies;
    public GetCurrency(){
        try {
            URL url = new URL("https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String text = "";
            for(;;){
                text = br.readLine();

                if(text == null)
                    break;
                currencyGSON += text;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson g = new GsonBuilder().create();
        currencies = g.fromJson(currencyGSON, Currency[].class);
    }

    public Currency getUSDtoUAH(){
        return currencies[0];
    }
    public Currency getEURtoUAH(){
        return currencies[1];
    }
    public Currency getBTCtoUAH(){
        return currencies[2];
    }
    public Currency[] getCurrencies(){
        return currencies;
    }


}
