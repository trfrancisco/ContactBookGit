package contactBook;
import java.util.*;
public class ContactBook {

    private HashMap<String, Contact> contactMap;
    private ArrayList<Contact> listContact;
    private HashMap<Integer, ArrayList<Contact>> listNumbers;
    private int totalContacts;
    private int next;
    private int repeatNumbers;


    public ContactBook(){
        next=0;
        totalContacts=0;
        listContact = new ArrayList<Contact>();
        contactMap = new HashMap<String, Contact>();
        listNumbers = new HashMap<Integer, ArrayList<Contact>>();
        repeatNumbers=0;
    }
    //Pre: name != null
    public  boolean hasContact(String name){
        return contactMap.containsKey(name);
    }

    //Pre: name!= null && !hasContact(name)
    public void addContact(String name, int phone, String email){
        Contact newContact = new Contact(name, phone, email);
        contactMap.put(name, newContact);
        listContact.add(newContact);
        insertListNumbers(phone, newContact);
        totalContacts++;
    }

    //Pre: name != null && hasContact(name)
    public void deleteContact(String name){
        Contact toDelete= getContact(name);
        contactMap.remove(name);
        listContact.remove(toDelete);
        removeListNumbers(toDelete);
        totalContacts--;
    }

    //Pre: name != null && hasContact(name)
    public int getPhone(String name){
        return getContact(name).getPhone();
    }

    //Pre: name != null && hasContact(name)
    public String getEmail(String name){
        return getContact(name).getEmail();
    }

    //Pre: name != null && hasContact(name)
    public void setPhone(String name, int phone){
        Contact contact = getContact(name);
        removeListNumbers(contact);
        contact.setPhone(phone);
        insertListNumbers(phone, contact);
    }

    //Pre: name != null && hasContact(name)
    public void setEmail(String name, String email){
        getContact(name).setEmail(email);
    }

    public int getNumberOfContacts(){
        return totalContacts;
    }

    public void initializeIterator(){
        next=0;
    }

    public boolean hasNext(){
        return next<totalContacts;
    }

    //Pre: hasNext()
    public Contact next(){
        return listContact.get(next++);
    }

    //Pre: phone != null
    public boolean hasNumber(int phone){
        return listNumbers.containsKey(phone);
    }

    //Pre: phone != null
    public String getContactByPhone(int phone){
        return listNumbers.get(phone).get(0).getName();
    }

    public boolean hasRepeatedNumbers(){
        return repeatNumbers>0;
    }

    //Pre: name != null
    private Contact getContact(String name){
        return contactMap.get(name);
    }

    //Pre: hasContact(name)
    private void removeListNumbers(Contact toDelete){
        if(listNumbers.get(toDelete.getPhone()).size()==1)
            listNumbers.remove(toDelete.getPhone());
        else{
            listNumbers.get(toDelete.getPhone()).remove(toDelete);
            repeatNumbers--;
        }
    }
    //Pre: hasContact(name) && phone !=null
    private void insertListNumbers(int phone, Contact newContact){
        if(listNumbers.containsKey(phone)) {
            listNumbers.get(phone).add(newContact);
            repeatNumbers++;
        }else {
            ArrayList<Contact> list = new ArrayList<Contact>();
            list.add(newContact);
            listNumbers.put(phone,list);
        }
    }
}
