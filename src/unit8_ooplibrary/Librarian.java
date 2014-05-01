
package unit8_ooplibrary;

/**
 *
 * @author John Mambo <john.mambo@gmail.com>
 */
public class Librarian {

    private String firstName;
    private String lastName;
    private int employeeID;
    private String library;
    
    public Librarian(){}
    
    public Librarian(String firstName, String lastName, int employeeID, String library) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeID = employeeID;
        this.library = library;
    }  
    
    protected String getFirstName() {
        return this.firstName;
    }

    protected void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    protected String getLastName() {
        return this.lastName;
    }

    protected void setLastName(String lastName) {
        this.lastName = lastName;
    }

    protected int getEmployeeID() {
        return this.employeeID;
    }

    protected void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    protected String getLibrary() {
        return this.library;
    }

    protected void setLibrary(String library) {
        this.library = library;
    }
}
