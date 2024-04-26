//1221020 Anas Al Sayed
package application;

import java.util.Date;

public class Martyr  {
	private String martyrName;
    private String dateOfMartyrdom;
    
    
 // Constructor to initialize Martyr object with name and date.
    public Martyr(String martyrName, String dateOfMartyrdom) {
		super();
		this.martyrName = martyrName;
		this.dateOfMartyrdom = dateOfMartyrdom;
	}
    
    // Getter and setter for martyrName.
    public String getMartyrName() {
        return martyrName;
    }
    public void setMartyrName(String martyrName) {
        this.martyrName = martyrName;
    }
    
    // Getter and setter for dateOfMartyrdom.
	public String getDateOfMartyrdom() {
        return dateOfMartyrdom;
    }
    public void setDateOfMartyrdom(String dateOfMartyrdom) {
        this.dateOfMartyrdom = dateOfMartyrdom;
    }
    
    // Compare two Martyr objects based on their date of martyrdom.
	public int compareTo(Martyr o) {
		// Create a new Date object for the current date.
		Date testDate=new Date();
        
        // Parse the date strings to long values.
        long firstLong=testDate.parse(this.getDateOfMartyrdom());
	    Date date1 = new Date(firstLong);
        long secondLong=testDate.parse(((Martyr)o).getDateOfMartyrdom());
	    Date date2 = new Date(secondLong);

        // Compare the two dates and return the result.
        int result = date1.compareTo(date2);
    	return result;	
    	
	}

}
