package com.jam.trading;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.kraken.KrakenExchange;
import org.knowm.xchange.service.polling.account.PollingAccountService;
import org.knowm.xchange.service.polling.trade.PollingTradeService;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App 
{

    private static AccountInfo accountInfo;
    private static PrettyWallet prettyWallet;

    public static void main(String[] args ) throws Exception {

        Exchange kraken = createExchange();
        PollingAccountService accountService = kraken.getPollingAccountService();
        accountInfo = accountService.getAccountInfo();
        prettyWallet = new PrettyWallet();
        PollingTradeService tradeService = kraken.getPollingTradeService();
        AutoTrader autoTrader = new AutoTrader(tradeService,accountInfo,prettyWallet);

            try {
                printPrettyBalance();
                autoTrader.run();
            } catch (Exception e){
                e.printStackTrace();
            }


    }

    private static void printPrettyBalance() {
        prettyWallet.prettyBalance(accountInfo.getWallet().getBalances());
    }

    public static Exchange createExchange() {
        Exchange krakenExchange = ExchangeFactory.INSTANCE.createExchange(KrakenExchange.class.getName());
        krakenExchange.getExchangeSpecification().setApiKey(JOptionPane.showInputDialog("Insert API key here"));
        krakenExchange.getExchangeSpecification().setSecretKey(JOptionPane.showInputDialog("Insert SecretKey here"));
        krakenExchange.getExchangeSpecification().setUserName(JOptionPane.showInputDialog("Insert User Name here"));
        krakenExchange.applySpecification(krakenExchange.getExchangeSpecification());
        return krakenExchange;
    }
}
