package com.xchanging.ops.dao;

import org.springframework.stereotype.Repository;

import com.xchanging.ops.model.Account;

@Repository("accountDao")
public class AccountDaoImpl extends AbstractDaoImpl<Integer, Account> implements AccountDAO {

	

}
