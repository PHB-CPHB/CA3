/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author kaspe
 */
@Entity
public class ExchangeRates implements Serializable {

    @Id
    private String dailyRates;
    
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Currency> currency = new ArrayList();

    public ExchangeRates(String dailyRates, List<Currency> currency) {
        this.dailyRates = dailyRates;
        this.currency = currency;
    }
    
    public ExchangeRates(String dailyRates, Currency currency) {
        this.dailyRates = dailyRates;
        this.currency.add(currency);
    }
    
    public ExchangeRates() {
    }
    
    

    public List<Currency> getCurrency() {
        return currency;
    }

    public void setCurrency(List<Currency> currency) {
        this.currency = currency;
    }
    
    public void addCurrency(Currency currency){
        this.currency.add(currency);
    }

    public String getDailyRates() {
        return dailyRates;
    }

    public void setDailyRates(String dailyRates) {
        this.dailyRates = dailyRates;
    }


}
