package addressBook;

import addressBook.Entry;
import addressBook.Entry.Builder;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


/**
 * Main class
 * 
 * @author LaiQX
 */
public class AddressBook {
  // use a ArrayList to store all the information in the address book
  private ArrayList<Entry> allEntry = new ArrayList<Entry>();
  
  /**
   * an entry builder to build the entries in this address book
   * 
   * @return an entry builder
   */
  public Builder EntryBuilder() {
    return new Builder(this);
  }
  
  /**
   * Add entry to the address book
   * 
   * @param entry the added entry
   */
  public void addEntry(Entry entry) {
    allEntry.add(entry);
  }
  
  /**
   * remove all the entries that contain the input string
   * 
   * @param user_input input string
   */
  public void removeEntriesContain(String user_input) {
    for (Entry e : allEntry) {
      if (e.toString().contains(user_input)) {
        allEntry.remove(e);
      }
    }
  }
  

  /**
   * shows all the entries contain the input keyword string
   * 
   * @param user_input
   * @return a List of entries that contain the input string
   */
  public List<Entry> searchEntriesContain(String user_input) {
    List<Entry> result = new ArrayList<Entry>();
    for (Entry e : allEntry) {
      if (e.toString().contains(user_input)) {
        result.add(e);
      }
    }
    return result;
  }
  
  /**
   * save all the information in the address book to a file
   * 
   * @param fileName the name of the saved file
   * @throws FileNotFoundException thrown if the file is not found
   */
  public void saveToFile(String fileName) throws FileNotFoundException {
    PrintWriter writer = new PrintWriter(fileName);
    for (Entry e : allEntry) {
      writer.println(e.toSaveFormat());
    }    
    writer.close();
  }
  
  /**
   * read entries from a formatted address book file and add them to the 
   * address book
   * 
   * @param inputFile  name of input file
   * @throws FileNotFoundException thrown if input file not found
   */
  public void readFromFile(String inputFile) throws FileNotFoundException {
    File input = new File(inputFile);
    Scanner scanner = new Scanner(input);
    String line;
    String[] lineToken;
    while (scanner.hasNextLine()) {
      line = scanner.nextLine();
      lineToken = line.split("##Save#Format##");
      this.EntryBuilder().name(lineToken[0]).postalAddress(lineToken[1]).phoneNumber(lineToken[2])
          .emailAddress(lineToken[3]).note(lineToken[4]).build();
    }
    scanner.close();
  }
  
  /**
   * present a list of entries to the standard output
   * 
   * @param ListOfEntry a List of entries
   */
  public void showEntryList(List<Entry> ListOfEntry){
    for (Entry i : ListOfEntry) {
      System.out.println("=======================");
      System.out.println(i.toString());
      System.out.println("=======================");
    }
  }
}
