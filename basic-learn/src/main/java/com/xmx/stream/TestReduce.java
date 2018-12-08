package com.xmx.stream;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2018-12-06 19:46
 **/
public class TestReduce {

    @Data
    static class Account {
        BigDecimal ammount;
    }

    public static void main(String[] args) {
        Account account1 = new Account();
        Account account2 = new Account();
        Account account3 = new Account();
        account1.setAmmount(new BigDecimal(10));
        account2.setAmmount(new BigDecimal(20));
        account3.setAmmount(new BigDecimal(30));
        List<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);
        accounts.add(account3);

        Optional<BigDecimal> remain = accounts.stream().map(Account::getAmmount).reduce(BigDecimal::add);

        System.out.println(remain);
    }
}
