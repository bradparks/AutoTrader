package com.jam.trading;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.kraken.KrakenExchange;
import org.knowm.xchange.service.polling.account.PollingAccountService;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {

        Exchange kraken = createExchange();
        PollingAccountService accountService = kraken.getPollingAccountService();
        AccountInfo accountInfo = accountService.getAccountInfo();
        System.out.println(accountInfo.getWallet().getBalances().toString());


    }

    public static Exchange createExchange() {
        //TODO ask for these things interactively, or grab a file.
        Exchange krakenExchange = ExchangeFactory.INSTANCE.createExchange(KrakenExchange.class.getName());
        krakenExchange.getExchangeSpecification().setApiKey("API Key");
        krakenExchange.getExchangeSpecification().setSecretKey("Secret==");
        krakenExchange.getExchangeSpecification().setUserName("user");
        krakenExchange.applySpecification(krakenExchange.getExchangeSpecification());
        return krakenExchange;
    }
}
