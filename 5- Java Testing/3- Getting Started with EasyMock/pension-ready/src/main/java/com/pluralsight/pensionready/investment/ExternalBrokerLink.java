package com.pluralsight.pensionready.investment;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExternalBrokerLink {

    public static final String INTERNAL_PROXY_URL = "http://123.124.124.03/delegate";

    protected boolean executeInvestmentTransaction(String fundId,
                                                   BigDecimal investmentAmount,
                                                   String direction) throws IOException {
        URL url = new URL(INTERNAL_PROXY_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("DIRECTION", direction);
        con.setRequestProperty("FUND", fundId);
        con.setRequestProperty("AMOUNT", investmentAmount.toPlainString());

        StringBuffer sb = new StringBuffer();
        String result;

        InputStream is = new BufferedInputStream(con.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String inputLine;
        while ((inputLine = br.readLine()) != null) {
            sb.append(inputLine);
        }
        result = sb.toString();

        return Boolean.valueOf(result);
    }

}
