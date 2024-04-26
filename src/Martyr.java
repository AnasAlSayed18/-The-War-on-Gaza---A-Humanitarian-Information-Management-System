import java.util.Date;

public class Martyr implements Comparable {
	private String martyrName;
    private String dateOfMartyrdom;
    
    public String getMartyrName() {
        return martyrName;
    }
    public void setMartyrName(String martyrName) {
        this.martyrName = martyrName;
    }
    public Martyr(String martyrName, String dateOfMartyrdom) {
		super();
		this.martyrName = martyrName;
		this.dateOfMartyrdom = dateOfMartyrdom;
	}
	public String getDateOfMartyrdom() {
        return dateOfMartyrdom;
    }
    public void setDateOfMartyrdom(String dateOfMartyrdom) {
        this.dateOfMartyrdom = dateOfMartyrdom;
    }
	@Override
	public int compareTo(Object o) {
		
		
		
	        Date d1=new Date();
            long s=d1.parse(this.getDateOfMartyrdom());
		    Date date1 = new Date(s);
	       
		  

            long s2=d1.parse(((Martyr)o).getDateOfMartyrdom());
		    Date date2 = new Date(s2);

           
	        int result = date1.compareTo(date2);
	        
        	return result;

	        

		
		
		
		
	}

}
