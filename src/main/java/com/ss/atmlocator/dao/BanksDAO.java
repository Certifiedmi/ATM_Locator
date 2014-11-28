package com.ss.atmlocator.dao;

import com.ss.atmlocator.entity.AtmNetwork;
import com.ss.atmlocator.entity.Bank;
import com.ss.atmlocator.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olavin on 22.11.2014.
 */
@Repository
public class BanksDAO {
    private static final int UNASSIGNED_NETWORK = -1;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Bank> getBanksList(){
        List<Bank> banks;
        TypedQuery<Bank> query = entityManager.createQuery("SELECT b FROM Bank AS b",Bank.class);
        banks = query.getResultList();

        return banks;
    }

    @Transactional
    public Bank newBank(){
        Bank bank = new Bank();
        bank.setId(0); // no ID for new Bank
        AtmNetwork network = entityManager.find(AtmNetwork.class, UNASSIGNED_NETWORK);
        bank.setNetwork(network);
        bank.setLogo("default_logo.png");
        bank.setIconAtm("default_atm.png");
        bank.setIconOffice("default_office.png");
        return bank;
    }

    public Bank getBank(int id){
        Bank bank = (Bank)entityManager.find(Bank.class, id);
        return bank;
    }

    @Transactional
    public Bank saveBank(Bank bank){
        return (Bank)entityManager.merge(bank);
    }

    @Transactional
    public void deleteBank(int bank_id){
        Bank bank = (Bank)entityManager.find(Bank.class, bank_id);
        entityManager.remove(bank);
    }

}
