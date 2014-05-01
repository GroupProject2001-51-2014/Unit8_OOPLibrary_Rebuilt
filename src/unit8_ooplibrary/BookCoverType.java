
package unit8_ooplibrary;

/**
 *
 * @author namphan
 */
public enum BookCoverType {
    H {
        @Override
        public String toString() {
           return "Hard Cover";
        }
    },
     
    P {
        @Override
        public String toString() {
          return "Paper Back";
        }
    },
    
    L {
        @Override
        public String toString() {
         return "Large";
        }
    },
    
    U {
        @Override
        public String toString() {
         return "Unknown";
        }
    } 
}
//  method to enum the book cover 