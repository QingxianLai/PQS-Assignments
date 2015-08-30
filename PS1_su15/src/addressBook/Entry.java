package addressBook;

import addressBook.AddressBook;

/**
 * The element in the AddressBook
 * 
 * @author LaiQX
 *
 */
public class Entry {
  
  // properties of each entry
  private final String name;
  private final String postalAddress;
  private final String phoneNumber;
  private final String emailAddress;
  private final String note;
  
  
  /**
   * an entry builder, following the builder pattern.
   * construct with the address book it belong to, e.g.:
   * Entry a = new Entry.Builder(addressBook).name("Steve").build();
   */
  public static class Builder {
    
    // optional parameters -- initialized to default value ""
    private String name = "";
    private String postalAddress = "";
    private String phoneNumber = "";
    private String emailAddress = "";
    private String note = "";
    
    // Required parameter
    private final AddressBook ofBook;
    
    /** 
     * @param book  a string showing the address book to which the entry belongs
     */
    public Builder(AddressBook book) {
      ofBook = book;
    }
    
    /**
     * add name attribute to the Builder
     * 
     * @param val  the name of the entry
     * @return    a builder
     */
    public Builder name(String val)  {
      name = val;
      return this;
    }
    
    /**
     * add postal address to the builder
     * 
     * @param val the postalAddress of the entry
     * @return   a entry builder
     */
    public Builder postalAddress(String val)  {
      postalAddress = val;
      return this;
    }
    
    /**
     * add phone number to the builder 
     * 
     * @param val the phone number (a string) of the entry
     * @return    a entry builder
     */
    public Builder phoneNumber(String val)  {
      phoneNumber = val;
      return this;
    }
    
    /**
     * add email address to the builder
     * 
     * @param val  the email address of the entry
     * @return    a entry builder
     */
    public Builder emailAddress(String val)  {
      emailAddress = val;
      return this;
    }
    
    /**
     * add note to the builder
     * 
     * @param val the note of the entry
     * @return   a entry builder
     */
    public Builder note(String val)  {
      note = val;
      return this;
    }
    
    /**
     *  use the information in this builder to build a entry and put it
     *  into the address book
     */
    public void build() {
      Entry newIput = new Entry(this);
      ofBook.addEntry(newIput);
    }
  }
  
  /**
   * class constructor,  construct the instance with a builder
   * 
   * @param builder  entry builder
   */
  private Entry(Builder builder) {
    name = builder.name;
    postalAddress = builder.postalAddress;
    phoneNumber = builder.phoneNumber;
    emailAddress = builder.emailAddress;
    note = builder.note;
  }
  
  /**
   * conclude all the properties of this entry to an address entry format in 
   * order to save the entry to a file. 
   * 
   * @return a string contain all the information in this entry
   */
  public String toSaveFormat() {
    StringBuilder outputBuilder = new StringBuilder();
    
    outputBuilder.append(name);
    outputBuilder.append("##Save#Format##");
    
    outputBuilder.append(postalAddress);
    outputBuilder.append("##Save#Format##");
    
    outputBuilder.append(phoneNumber);
    outputBuilder.append("##Save#Format##");
    
    outputBuilder.append(emailAddress);
    outputBuilder.append("##Save#Format##");
    
    outputBuilder.append(note);
    
    return outputBuilder.toString();
  }
  
  /**
   * show the entry in a more readable way
   * 
   * @see java.lang.Object#toString()
   */
  public String toString() {
    StringBuilder outputBuilder = new StringBuilder();
    
    outputBuilder.append("Name: ");
    outputBuilder.append(name);
    outputBuilder.append("\n");
    
    outputBuilder.append("Postal Address: ");
    outputBuilder.append(postalAddress);
    outputBuilder.append("\n");
    
    outputBuilder.append("Phone Number: ");
    outputBuilder.append(phoneNumber);
    outputBuilder.append("\n");
    
    outputBuilder.append("Email Address: ");
    outputBuilder.append(emailAddress);
    outputBuilder.append("\n");
    
    outputBuilder.append("Note: ");
    outputBuilder.append(note);
    
    return outputBuilder.toString(); 
  }  
}
