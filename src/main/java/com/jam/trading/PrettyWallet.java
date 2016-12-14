package com.jam.trading;

import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.Balance;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by btt on 14.12.2016.
 */
public class PrettyWallet {

    public PrettyWallet(){

    }

    public static void prettyBalance(Map<Currency, Balance> currencyBalanceMap) {
        ArrayList<String> out = new ArrayList<String>(currencyBalanceMap.size());
        for (Balance balance:
                currencyBalanceMap.values()) {
            String message = "Currency: "+balance.getCurrency().toString()+"\n";
            message += " Tradable: "+String.valueOf(balance.getAvailableForWithdrawal()+"\n");
            message += " Total:"+balance.getTotal()+"\n";
            out.add(message);
        }
        System.out.println(out);
    }
}
