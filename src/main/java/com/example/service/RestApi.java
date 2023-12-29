package com.example.service;

import com.example.portfolio.Investment;
import com.example.portfolio.Stock;

import java.util.List;

public interface RestApi {
    public void saveInvestment(Investment investment);

    public List<Stock> getInvestments();
}
