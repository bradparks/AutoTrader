package com.jam.trading;

import com.google.common.base.Function;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.service.polling.trade.PollingTradeService;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by btt on 14.12.2016.
 */
public class AutoTrader implements Runnable{

    private final PrettyWallet wallet;
    private Cache<Currency, Balance> balances =
            CacheBuilder.newBuilder().expireAfterWrite(20, TimeUnit.SECONDS)
                    .build(CacheLoader.from(
                    new Function<Currency, Balance>() {
                        @Nullable
                        public Balance apply(@Nullable Currency currency) {
                            return account.getWallet().getBalance(currency);
                        }
                    }
            ));
    private PollingTradeService connection;
    private OpenOrders orders;
    private AccountInfo account;

    public AutoTrader(PollingTradeService connection, AccountInfo accountInfo, PrettyWallet prettyWallet) throws Exception {
    this.connection = connection;
    this.orders = connection.getOpenOrders();
    this.account = accountInfo;
    this.wallet = prettyWallet;
    }


    public void run() {
        boolean safe = true;
        balances.putAll(account.getWallet().getBalances());
        Map<Currency,Balance> newBalances = balances.asMap();
        while (safe){

            //TRADE
            wallet.prettyBalance(newBalances);
        }

    }
}
