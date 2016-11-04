/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 *
 * @author kaspe
 */
public class Calc {
    
    private String value;

    public Calc() {

    }

    public void calcRate(String fromCurrency, String toCurreny, String amount) {
        value = String.valueOf(((Float.valueOf(fromCurrency) * Float.valueOf(amount)) / 100) / (Float.valueOf(toCurreny) / 100));
    }

}
