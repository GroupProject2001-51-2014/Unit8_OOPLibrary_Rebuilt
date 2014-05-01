

package unit8_ooplibrary;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;
import javax.swing.ImageIcon;

/**
 *
 * @author John Mambo <john.mambo@gmail.com>
 */
public abstract class LibraryMaterial {
    
    private String author;
    private String title;
    private BigDecimal price;
    private short publicationYear;
    private ImageIcon coverImage;
    
    public LibraryMaterial() {
        this.author = "";
        this.title = "";
        this.price = new BigDecimal("0.0");
        this.publicationYear = 0;
        this.coverImage = null;
    }
    
    public LibraryMaterial(String author, String title, BigDecimal price, short publicationYear, ImageIcon coverImage) {
        this.author = author;
        this.title = title;
        this.price = price;
        this.publicationYear = publicationYear;
        this.coverImage = coverImage;
    }

    public ImageIcon getCoverImage() {
        return this.coverImage;
    }

    public void setCoverImage(ImageIcon coverImage) {
        this.coverImage = coverImage;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public short getPublicationYear() {
        return this.publicationYear;
    }

    public void setPublicationYear(short publicationYear) {
        this.publicationYear = publicationYear;
    }
    
    public String displayInfo(){
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
        double dPriceValue = getPrice().doubleValue();
        String sPriceValue = n.format(dPriceValue);
      
        StringBuilder str = new StringBuilder();
        str.append(String.format("Title: %s%n",getTitle()));
        str.append(String.format("Author: %s%n",getAuthor()));
        str.append(String.format("Publication Year: %s%n",getPublicationYear()));
        str.append(String.format("Price: %s%n",sPriceValue));
        return str.toString();
    }
    public ImageIcon displayCover(){
        return this.getCoverImage();
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.author);
        hash = 17 * hash + Objects.hashCode(this.title);
        hash = 17 * hash + Objects.hashCode(this.price);
        hash = 17 * hash + this.publicationYear;
        hash = 17 * hash + Objects.hashCode(this.coverImage);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LibraryMaterial other = (LibraryMaterial) obj;
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (this.publicationYear != other.publicationYear) {
            return false;
        }
        return true;
    }
}
