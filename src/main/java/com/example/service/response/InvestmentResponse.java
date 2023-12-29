package com.example.service.response;

import com.example.portfolio.Stock;

import java.util.List;

public class InvestmentResponse {
    private List<Stock> investments;

    public List<Stock> getInvestments() {
        return investments;
    }

    public void setInvestments(List<Stock> investments) {
        this.investments = investments;
    }
}
