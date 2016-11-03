/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler;

import entity.Currency;
import entity.ExchangeRates;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import security.IUserFacade;
import security.UserFacadeFactory;
import xml.XmlReader;

/**
 *
 * @author kaspe
 */
public class getExchangeRate extends DefaultHandler implements Runnable {

    private ExchangeRates newRates;
    private String temp = "";
    private IUserFacade facade = UserFacadeFactory.getInstance();
    private ExchangeRates newestRate;

    public ExchangeRates getRate() {
        return newestRate;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start Document (Sax-event)");
        newRates = new ExchangeRates();
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End Document (Sax-event)");
        facade.addExchangeRates(newRates);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        Currency currency = new Currency();

        if (localName.equalsIgnoreCase("dailyrates")) {
            for (int i = 0; i < attributes.getLength(); i++) {
                newRates.setDailyRates(attributes.getValue(i));
            }
        }
        if (localName.equalsIgnoreCase("currency")) {
            for (int i = 0; i < attributes.getLength(); i++) {
                if (attributes.getLocalName(i).equalsIgnoreCase("code")) {
                    currency.setCode(attributes.getValue(i));
                }
                if (attributes.getLocalName(i).equalsIgnoreCase("desc")) {
                    currency.setDesc(attributes.getValue(i));
                }
                if (attributes.getLocalName(i).equalsIgnoreCase("rate")) {
                    currency.setRate(attributes.getValue(i));
                }
            }
        }
        if (currency.getCode() != null && currency.getDesc() != null && currency.getRate() != null) {
            newRates.addCurrency(currency);
            temp = newRates.getCurrency().get(newRates.getCurrency().size() - 1).getCode();
        }
        System.out.println("");

    }

    public boolean getData() {
        try {
            System.out.println("--------------------Starting-----------------------------------------");
            XMLReader xr = XMLReaderFactory.createXMLReader();
            xr.setContentHandler(new XmlReader());
            URL url = new URL("http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en");
            xr.parse(new InputSource(url.openStream()));
            return true;
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateRate() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String data = df.format(cal.getTime());
        newestRate = facade.getExhangeRates(data);
    }

    @Override
    public void run() {

        if (getData()) {
            updateRate();
        }

    }

}
