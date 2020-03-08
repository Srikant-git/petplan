package petplan.datafactory;


/*
//import helper.EmailHelper;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.NoSuchElementException;
import petplan.support.JsonParser;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

//import static config.Config.CURRENT_REGION;
//import static core.TAGS.*;
import static petplan.support.DateHelper.getWithAddedYear;
import static org.testng.Assert.assertTrue;

@Data
public class PolicyHolder implements Serializable {
    public static final String ADDRESS_DIR = PROJECT_BASE_DIR + File.separator +
            "src" + File.separator + "main" + File.separator + "java" + File.separator +
            "policy" + File.separator;
    private String firstName;
    private String lastName;
    private String state;
    private String mailingAddress;
    private String zipCode;
    private String city;
    private String email;
    private String phoneNumber;
    private String cardNumber;
    private String exp;
    private String cvc;
    private String emailMe;
    private String password;
    private String IPID;
    private String acctType;
    private String serviceId;
    private List<Quote> quoteList;

    public PolicyHolder() {
        setZipOrPostalMailingAndState();
        setEmailMe("No");
        setFirstName("test");
        setLastName("test");

        setPhoneNumber("8372532544");
        setCardNumber("4111111111111111");
        setExp(getWithAddedYear("MMyy", 4));
        setCvc("444");
        setPassword("test1234");
        setIPID(null);
        setServiceId();
        Random rand = new Random();
        int value = rand.nextInt(2000000000);
        setEmail("testPet" + value + "@yopmail.com");
    }

    public PolicyHolder(int region) {
        this();
        assertTrue(region == 1 || region == 2);
        setServiceId(region);
        setAcctType(region);
        setZipOrPostalMailingAndState(region);
        //setEmail(EmailHelper.getNotRegisteredEmail(region));
    }

    public PolicyHolder(boolean isUSA, String state) {
        this();

        String[] address = getAddressFromFile(isUSA, state);
        int region = isUSA ? 1 : 2;
        setServiceId(region);
        setAcctType(region);
        setZipOrPostalMailingAndState(address);
        //setEmail(EmailHelper.getNotRegisteredEmail(region));
        setQuote(new Quote());
    }

    public static String[] getAddressFromFile(boolean isUsa, String state) {
        String fileName = (isUsa) ? "usa_addresses.json" : "canada_addresses.json";
        JSONObject json = JsonParser.getJSONFromFile(ADDRESS_DIR  + "addresses" + File.separator, fileName);
        return getAddress(isUsa, json, state);
    }

    private static JSONObject getJsonFromFile(boolean isUsa) {
        String fileName = (isUsa) ? "usa_addresses.json" : "canada_addresses.json";
        return JsonParser.getJSONFromFile(ADDRESS_DIR  + "addresses" + File.separator, fileName);
    }

    public static String[] getRandomAddressFromFile(boolean isUsa) {
        JSONObject json = getJsonFromFile(isUsa);
        List<String> states = new ArrayList<String>(json.keySet());
        String state = states.get(new Random().nextInt(states.size()));
        return getAddress(isUsa, json, state);
    }

    @NotNull
    public static String[] getAddress(boolean isUsa, JSONObject json, String state) {
        JSONArray array = (JSONArray) json.get(state);
        if (array == null || array.size() == 0)
            throw new NoSuchElementException("There is no such address for state: " + state
                    + " for region: " + (isUsa ? "usa." : "canada."));
        JSONArray address = (JSONArray) array.get(new Random().nextInt(array.size()));
        return new String[]{
                (String) address.get(0),
                (String) address.get(1),
                (String) address.get(2),
                (String) address.get(3)
        };
    }

    @NotNull
    public static List<String[]> getAddresses(boolean isUsa, String state) {
        JSONObject json = getJsonFromFile(isUsa);
        JSONArray array = (JSONArray) json.get(state);
        if (array == null || array.size() == 0)
            throw new NoSuchElementException("There is no such address for state: " + state
                    + " for region: " + (isUsa ? "usa." : "canada."));
        return Arrays.stream(array.toArray())
                .map(s -> {
                    Object[] address = ((JSONArray) s).toArray();
                    return new String[]{
                            (String) address[0],
                            (String) address[1],
                            (String) address[2],
                            (String) address[3]};
                })
                .collect(Collectors.toList());
    }

    public List<Quote> getQuoteList() {
        if (quoteList == null)
            quoteList = new ArrayList<Quote>();
        return quoteList;
    }

    public int getActivePoliciesCount() {
        if (quoteList == null)
            return 0;
        int count = 0;
        for (int i = 0; i < quoteList.size(); i++) {
            if (quoteList.get(i).isQuoteActive())
                count++;
        }
        return count;
    }

    public int getInactivePoliciesCount() {
        if (quoteList == null)
            return 0;
        int count = 0;
        for (int i = 0; i < quoteList.size(); i++) {
            if (!quoteList.get(i).isQuoteActive())
                count++;
        }
        return count;
    }

    public void addQuoteList(List<Quote> quoteList1) {
        if (quoteList == null)
            quoteList = new ArrayList<>();
        quoteList.addAll(quoteList1);
    }

    public void addQuote(Quote quoteList1) {
        if (quoteList == null)
            quoteList = new ArrayList<>();
        quoteList.add(quoteList1);
    }

    public Quote getQuote(int petNum) {
        assertTrue(quoteList != null && petNum >= 0 && petNum < quoteList.size());
        return quoteList.get(petNum);
    }


    public void setQuote(Quote quote) {
        if (quoteList == null)
            quoteList = new ArrayList<>();
        quoteList.add(quote);
    }

    public String getZipOrPostalCode() {
        return zipCode;
    }

    public void setZipOrPostalMailingAndState() {
        if (CURRENT_REGION.equals(USA)) {
            getRandomAddressFromFile(true);
        } else if (CURRENT_REGION.equals(CANADA)) {
            getRandomAddressFromFile(true);
        } else {
            System.out.println("Please check the CURRENT_DRIVER string in Config.java. the value should be either \"US\" or \"Canada\".");
            throw new NoSuchFieldError("Improper region value.");
        }
    }

    private void setZipOrPostalMailingStateCity(String[][] codeMap) {
        int num = new Random().nextInt(codeMap.length);
        setZipCode(codeMap[num][0]);
        setMailingAddress(codeMap[num][1]);
        setState(codeMap[num][2]);
        setCity(codeMap[num][3]);
    }

    public void setZipOrPostalMailingAndState(int region) {
        assertTrue(region == 1 || region == 2);
        getRandomAddressFromFile(region == 1);
    }

    public void setZipOrPostalMailingAndState(String[] address) {
        assertTrue(address != null);
        setZipCode(address[0]);
        setMailingAddress(address[1]);
        setState(address[2]);
        setCity(address[3]);
    }

    public void changeOtherMailingAddress(boolean isUsa, String state) {
        List<String[]> addresses = getAddresses(isUsa, state);
        if (addresses == null || addresses.size() == 0)
            throw new IllegalStateException("There is no address to change for state: " + state + " for region: " + isUsa);
        for (String[] address : addresses) {
            if (!address[0].equals(zipCode) && !address[1].equals(mailingAddress)) {
                this.zipCode = address[0];
                this.mailingAddress = address[1];
                this.state = address[2];
                this.city = address[3];
                return;
            }
        }
        throw new IllegalStateException("There is no address to change for state: " + state + " for region: " + isUsa);
    }

    public void changeOtherMailingAddressForCurrentState(boolean isUsa) {
        changeOtherMailingAddress(isUsa, this.state);
    }

    public void setServiceId() {
        if (CURRENT_REGION.equals(USA)) {
            serviceId = "1";
        } else if (CURRENT_REGION.equals(CANADA)) {
            serviceId = "2";
        } else {
            System.out.println("Please check the CURRENT_DRIVER string in Config.java. the value should be either \"US\" or \"Canada\".");
            throw new NoSuchFieldError("Improper region value.");
        }
    }

    public void setServiceId(int region) {
        assertTrue(region == 1 || region == 2);
        if (region == 1) {
            serviceId = "1";
        } else {
            serviceId = "2";
        }
    }

    public void setAcctType(int region) {
        assertTrue(region == 1 || region == 2);
        if (region == 1) {
            acctType = "1";
        } else {
            acctType = "0";
        }
    }
}*/
