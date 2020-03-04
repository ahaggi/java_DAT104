package modell;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    
    private List<Item> items = new ArrayList<>();
    
    public void leggTilItem(Item item) {
        items.add(item);
    }
    
    public void slettItem(Item item) {
        items.remove(item);
    }
    
    public void slettItem_navn(String item_navn) {
    	boolean funnet = false;
    	for (int i = 0; i < items.size() && !funnet; i++) {
    		if (items.get(i).getItem_navn().equals(item_navn)) {
                items.remove(items.get(i));
                funnet=true;
			}

		}
    }
    public List<Item> getItems() {
        return items;
    }
}
