package warehouse;

/*
 *
 * This class implements a warehouse on a Hash Table like structure, 
 * where each entry of the table stores a priority queue. 
 * Due to your limited space, you are unable to simply rehash to get more space. 
 * However, you can use your priority queue structure to delete less popular items 
 * and keep the space constant.
 * 
 * @author Ishaan Ivaturi
 */ 
public class Warehouse {
    private Sector[] sectors;
    
    // Initializes every sector to an empty sector
    public Warehouse() {
        sectors = new Sector[10];

        for (int i = 0; i < 10; i++) {
            sectors[i] = new Sector();
        }
    }
    
    /**
     * Provided method, code the parts to add their behavior
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    public void addProduct(int id, String name, int stock, int day, int demand) {
        evictIfNeeded(id);
        addToEnd(id, name, stock, day, demand);
        fixHeap(id);
    }

    /**
     * Add a new product to the end of the correct sector
     * Requires proper use of the .add() method in the Sector class
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    private void addToEnd(int id, String name, int stock, int day, int demand) {
        // IMPLEMENT THIS METHOD
        //to get last digit use modulus id
        int num = id % 10;
        //create warehouse product and input in.
        Product fresh = new Product(id, name, stock, day, demand);
        sectors[num].add(fresh);
        
    }

    /**
     * Fix the heap structure of the sector, assuming the item was already added
     * Requires proper use of the .swim() and .getSize() methods in the Sector class
     * @param id The id of the item which was added
     */
    private void fixHeap(int id) {
         //to get last digit use modulus id
        int num = id % 10;
        int space = sectors[num].getSize();
        //space.getSize();
        //find the size of a sector of the warehouse 
        //and then basically sort the indexes of space.
        sectors[num].swim(space);


        // IMPLEMENT THIS METHOD
    }

    /**
     * Delete the least popular item in the correct sector, only if its size is 5 while maintaining heap
     * Requires proper use of the .swap(), .deleteLast(), and .sink() methods in the Sector class
     * @param id The id of the item which is about to be added
     */
    private void evictIfNeeded(int id) {
         //to get last digit use modulus id
        int num = id % 10;
        Sector dark = sectors[num];
        //if there is 5 products you use .swap(1st index, last index) 
        //since 1st item in the array is always the min popularity 
        //then use .deletelast() and 
        //then .sink(the first index)
        if(dark.getSize() == 5){
            dark.swap(1, dark.getSize());
            dark.deleteLast();
            dark.sink(1);
        }
       // IMPLEMENT THIS METHOD
    }

    /**
     * Update the stock of some item by some amount
     * Requires proper use of the .getSize() and .get() methods in the Sector class
     * Requires proper use of the .updateStock() method in the Product class
     * @param id The id of the item to restock
     * @param amount The amount by which to update the stock
     */
    public void restockProduct(int id, int amount) {
        int num = id % 10;
        int fit = sectors[num].getSize();
        //loop must start with 1 since this a heap and not a regular array
        for(int i = 1; i <= fit; i++){
            if(sectors[num].get(i).getId() == id){
                //use update stock
                sectors[num].get(i).updateStock(amount);
            }
        }
        // IMPLEMENT THIS METHOD
    }
    
    /**
     * Delete some arbitrary product while maintaining the heap structure in O(logn)
     * Requires proper use of the .getSize(), .get(), .swap(), .deleteLast(), .sink() and/or .swim() methods
     * Requires proper use of the .getId() method from the Product class
     * @param id The id of the product to delete
     */
    public void deleteProduct(int id) {
        int num = id % 10;
        int index=0;
        int fit = sectors[num].getSize();
         //loop must start with 1 since this a heap and not a regular array
         //index is a value holder
        for(int i = 1; i <=fit; i++){
            if(sectors[num].get(i).getId() == id){
                index = i;
                break;
                //keep heap outside loop to get actual result
            /*sectors[num].swap(i, fit);
            sectors[num].deleteLast();
            sectors[num].sink(i);*/
            }
        }
            if(index!=0){
                sectors[num].swap(index, fit);
                sectors[num].deleteLast();
                sectors[num].sink(index);
            }
        // IMPLEMENT THIS METHOD
    }
    
    /**
     * Simulate a purchase order for some product
     * Requires proper use of the getSize(), sink(), get() methods in the Sector class
     * Requires proper use of the getId(), getStock(), setLastPurchaseDay(), updateStock(), updateDemand() methods
     * @param id The id of the purchased product
     * @param day The current day
     * @param amount The amount purchased
     */
    public void purchaseProduct(int id, int day, int amount) {
        int num = id % 10;
        Sector dark = sectors[num];
        int fit = sectors[num].getSize();
        //heap not array start with 1 not 0
        for(int i = 1; i <=fit; i++){
            //Product celine = dark.get(i);
            sectors[num].get(i);
            if(sectors[num].get(i).getId() == id ){
                if(sectors[num].get(i).getStock() > amount){
                    sectors[num].get(i).setLastPurchaseDay(day);
             //its stock is decreased by the amount purchased
                    sectors[num].get(i).updateStock(amount*-1);
             // its demand is increased by the amount purchased
                    sectors[num].get(i).updateDemand(amount);
                    dark.sink(i);
                }
                //return;
            }
            //just incase these cases happen
            else if((sectors[num].get(i).getStock() - amount) < 0 || sectors[num].get(i).getId() != id ){
                break;
            }
            
            /*if(sectors[num].get(i).getId() == id && check if stock is greater than amount which is greater than 0
             * sectors[num].get(i).setLastPurchaseDay(day);
             * sectors[num]
             * sectors[num]
             * sectors[num].sink(i);
             */
            /*if(sectors[num].get(i).getId() == id && (sectors[num].get(i).getStock() - amount) >= 0){
             sectors[num].get(i).setLastPurchaseDay(day);
             //its stock is decreased by the amount purchased
             sectors[num].get(i).updateStock(amount);
             // its demand is increased by the amount purchased
             sectors[num].get(i).updateDemand(amount);
             sectors[num].sink(i);
            } */

        }
        // IMPLEMENT THIS METHOD
    }
    
    /**
     * Construct a better scheme to add a product, where empty spaces are always filled
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    public void betterAddProduct(int id, String name, int stock, int day, int demand) {
        int num = id %10;
        int fit = sectors[num].getSize();
        //compare sector size with its full length 
        Product baby = new Product(id, name, stock, day, demand);
        baby.setStock(stock);
        baby.setDemand(demand);
        baby.setLastPurchaseDay(day);
        baby.setId(id);
        baby.setName(name);
        if(fit < fit -1){
            sectors[num].add(baby);
            //break;
        }
        else {
            
            //create new value to compare
            int x = id + 1;
            int xmod = x%10;
            while( xmod!= num){
                //compare size to past length
                if(sectors[xmod].getSize() < fit){
                    Product temp = new Product(id, name, stock, day, demand);
                    sectors[xmod].add(temp);
                    //use fixheap method to make sure heap is correct
                    fixHeap(x);
                    //return;
                    break;
                }
                //incrementing var
                x++;
            }
            sectors[num].add(baby);
        
        }
        
        // IMPLEMENT THIS METHOD
    }

    /*
     * Returns the string representation of the warehouse
     */
    public String toString() {
        String warehouseString = "[\n";

        for (int i = 0; i < 10; i++) {
            warehouseString += "\t" + sectors[i].toString() + "\n";
        }
        
        return warehouseString + "]";
    }

    /*
     * Do not remove this method, it is used by Autolab
     */ 
    public Sector[] getSectors () {
        return sectors;
    }
}
