package com.jam.trading;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.kraken.KrakenExchange;
import org.knowm.xchange.service.polling.account.PollingAccountService;

import javax.swing.*;
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
        //TODO ask for these things interactively, or grab a file. Secure everything with PBE.
        Exchange krakenExchange = ExchangeFactory.INSTANCE.createExchange(KrakenExchange.class.getName());
        krakenExchange.getExchangeSpecification().setApiKey(JOptionPane.showInputDialog("Insert API key here"));
        krakenExchange.getExchangeSpecification().setSecretKey(JOptionPane.showInputDialog("Insert SecretKey here"));
        krakenExchange.getExchangeSpecification().setUserName(JOptionPane.showInputDialog("Insert User Name here"));
        krakenExchange.applySpecification(krakenExchange.getExchangeSpecification());
        return krakenExchange;
    }
}
