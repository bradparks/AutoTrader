package com.jam.trading;

import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.Balance;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by btt on 14.12.2016.
 */
public class PrettyWallet {

    Map<Currency,Balance> oldBalanceMap;

    public PrettyWallet(){

    }

    public void prettyBalance(Map<Currency, Balance> currencyBalanceMap) {
        ArrayList<String> out = new ArrayList<String>(currencyBalanceMap.size());

        for (Balance balance:
                currencyBalanceMap.values()) {
            Currency balanceCurrency = balance.getCurrency();
            String message = "Currency: "+ balanceCurrency.toString()+"\n";
            message += " Tradable: "+String.valueOf(balance.getAvailableForWithdrawal()+"\n");
            //message += " Total: "+balance.getTotal()+"\n";
            message += " Changed this round: "+balance
                    .getAvailableForWithdrawal().compareTo(
                            oldBalanceMap.get(balanceCurrency)
                            .getAvailableForWithdrawal())+"\n";
            out.add(message);
        }
        System.out.println(out);
        oldBalanceMap.putAll(currencyBalanceMap);
    }
}
