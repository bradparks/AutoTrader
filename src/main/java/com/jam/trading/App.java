package com.jam.trading;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.kraken.KrakenExchange;
import org.knowm.xchange.service.polling.account.PollingAccountService;
import org.knowm.xchange.service.polling.marketdata.PollingMarketDataService;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        KrakenExchange kraken = new KrakenExchange();

        PollingMarketDataService marketDataService = kraken.getPollingMarketDataService();
        PollingAccountService accountService = kraken.getPollingAccountService();
        boolean success = false;
        AccountInfo accountInfo = null;
        while (!success) try {
           accountInfo =  accountService.getAccountInfo();
            success = true;
        } catch (IOException e) {
            System.out.println("try again.");
        }

        Ticker ticker = null;
        try {
            ticker = marketDataService.getTicker(CurrencyPair.ETH_BTC);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(accountInfo.getWallet().getBalances().toString());


    }
}
