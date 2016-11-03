/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

/**
 *
 * @author kaspe
 */
import entity.Currency;
import entity.ExchangeRates;
import java.io.IOException;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.net.URL;
import security.IUserFacade;
import security.UserFacadeFactory;

public class XmlReader extends DefaultHandler {

    ExchangeRates newRates;
    String temp = "";
    IUserFacade facade = UserFacadeFactory.getInstance();

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

    public static void main(String[] argv) {
        try {
            XMLReader xr = XMLReaderFactory.createXMLReader();
            xr.setContentHandler(new XmlReader());
            URL url = new URL("http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en");
            xr.parse(new InputSource(url.openStream()));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
