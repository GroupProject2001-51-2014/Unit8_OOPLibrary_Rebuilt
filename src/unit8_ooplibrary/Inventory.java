
package unit8_ooplibrary;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author John Mambo <john.mambo@gmail.com>
 */
public class Inventory {

    private static final ConcurrentHashMap<Integer, Inventory> libraryMaterials = new ConcurrentHashMap(5,0.9f,3);  
    private static int libraryMaterialID;
    private LibraryMaterial libraryMaterial;
    private Librarian librarian;
    private Date checkOut;
    private Date checkin;
    private boolean depricated;
    
    private Inventory(){
        if (null == Inventory.libraryMaterials) {
            Inventory.libraryMaterialID = 0;
        }else{
            Inventory.libraryMaterialID = Inventory.libraryMaterials.size();
        }
    }
    
    public Inventory(LibraryMaterial libraryMaterial){
        if (null == Inventory.libraryMaterials) {
            Inventory.libraryMaterialID = 0;
        }else{
            Inventory.libraryMaterialID = Inventory.libraryMaterials.size();
        }
        this.libraryMaterial = libraryMaterial;
    }
            
    public Inventory(LibraryMaterial libraryMaterial, Librarian librarian, Date checkOut, Date checkin, boolean depricated) {
        if (null == Inventory.libraryMaterials) {
            Inventory.libraryMaterialID = 0;
        }else{
            Inventory.libraryMaterialID = Inventory.libraryMaterials.size();
        }
        this.libraryMaterial = libraryMaterial;
        this.librarian = librarian;
        this.checkOut = checkOut;
        this.checkin = checkin;
        this.depricated = depricated;
    }

    protected boolean AddInventory(Inventory inventory) throws NullPointerException{
        if (null == Inventory.libraryMaterials) {
            return false;
        }
        try{
            if (Inventory.libraryMaterials.containsKey(Inventory.libraryMaterialID)) {
                return false;
            }
            Inventory.libraryMaterials.put(Inventory.libraryMaterialID, this);
            return true;
        }
        catch(NullPointerException ex){
            throw ex;
        }
    }
    
    protected int getLibraryMaterialID() {
        return Inventory.libraryMaterialID;
    }

    private void setLibraryMaterialID(int libraryMaterialID) {
            Inventory.libraryMaterialID = libraryMaterialID;
    }

    // TODO: Add method to UML
    protected ConcurrentHashMap<Integer, Inventory> getLibraryMaterials() {
        return Inventory.libraryMaterials;
    }

    // add method to add Inventory item addToInventory
    protected LibraryMaterial getLibraryMaterial() {
        return this.libraryMaterial;
    }

    protected void setLibraryMaterial(LibraryMaterial libraryMaterial) {
        this.libraryMaterial = libraryMaterial;
    }

    protected Librarian getLibrarian() {
        return this.librarian;
    }

    protected void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

    protected Date getCheckOut() {
        return checkOut;
    }

    protected void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    protected Date getCheckin() {
        return this.checkin;
    }

    protected void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    protected boolean isDepricated() {
        return this.depricated;
    }
    
    protected int nextLibraryMaterialID() throws NullPointerException {
        if(null != Inventory.libraryMaterials){
            return libraryMaterials.size();
        }
        throw new NullPointerException("Class Inventory Method setLibraryMaterialID(int libraryMaterialID) hasd returned a null because the Inventory Collection is not initialized");
    }

    protected void setDepricated(boolean depricated) {
        this.depricated = depricated;
    }
}