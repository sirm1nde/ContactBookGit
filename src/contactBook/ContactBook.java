package contactBook;

public class ContactBook {
    static final int DEFAULT_SIZE = 100;

    private int counter;
    private Contact[] contacts;
    private int currentContact;

    public ContactBook() {
        counter = 0;
        contacts = new Contact[DEFAULT_SIZE];
        currentContact = -1;
    }

    //Pre: name != null
    public boolean hasContact(String name) {
        return searchIndex(name) >= 0;
    }

    //Pre: phone != null
    public boolean hasContact(int phone) {
        return searchIndex(phone) >= 0;
    }

    public int getNumberOfContacts() {
        return counter;
    }

    //Pre: name!= null && !hasContact(name)
    public void addContact(String name, int phone, String email) {
        if (counter == contacts.length)
            resize();
        contacts[counter] = new Contact(name, phone, email);
        counter++;
    }

    //Pre: name != null && hasContact(name)
    public void deleteContact(String name) {
        int index = searchIndex(name);
        for(int i=index; i<counter; i++)
            contacts[i] = contacts[i+1];
        counter--;
    }

    //Pre: name != null && hasContact(name)
    public int getPhone(String name) {
        return contacts[searchIndex(name)].getPhone();
    }

    //Pre: name != null && hasContact(name)
    public String getEmail(String name) {
        return contacts[searchIndex(name)].getEmail();
    }

    //Pre: phone != null && hasContact(phone)
    public String getName(int phone) {
        return contacts[searchIndex(phone)].getName();
    }

    //Pre: name != null && hasContact(name)
    public void setPhone(String name, int phone) {
        contacts[searchIndex(name)].setPhone(phone);
    }

    //Pre: name != null && hasContact(name)
    public void setEmail(String name, String email) {
        contacts[searchIndex(name)].setEmail(email);
    }

    private int searchIndex(String name) {
        int i = 0;
        while (i < counter) {
            if (contacts[i].getName().equals(name))
                return i;
            i++;
        }
        return -1;
    }

    private int searchIndex(int phone) {
        int i = 0;
        while (i < counter) {
            if (contacts[i].getPhone() == phone)
                return i;
            i++;
        }
        return -1;
    }

    private void resize() {
        Contact[] tmp = new Contact[2*contacts.length];
        for (int i=0;i<counter; i++)
            tmp[i] = contacts[i];
        contacts = tmp;
    }


    public boolean verifyPhoneNumber(){
        int current = 0;
        while (current < counter) {
            int next = current + 1;
            int currentPhone = contacts[current].getPhone();
            while (next < counter) {
                if (currentPhone == contacts[next].getPhone())
                    return true;
                next++;
            }
            current++;
        }
        return false;
    }

    public void initializeIterator() {
        currentContact = 0;
    }

    public boolean hasNext() {
        return (currentContact >= 0 ) && (currentContact < counter);
    }

    //Pre: hasNext()
    public Contact next() {
        return contacts[currentContact++];
    }

}
