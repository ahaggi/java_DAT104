package no.hib.dat104.lph;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    
    private List<CartItem> items = new ArrayList<>();
    
    public void addItem(CartItem item) {
        items.add(item);
    }
    
    public List<CartItem> getItems() {
        return items;
    }
}
