package modell;

import javax.persistence.*;

@Entity
@Table(schema = "handleliste", name = "item")
public class Item {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer item_id;
	private String item_navn;

	public Item() {
		super();
	}

	public Item( String item_navn) {
		super();
		this.item_navn = item_navn;
	}

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public String getItem_navn() {
		return item_navn;
	}

	public void setItem_navn(String item_navn) {
		this.item_navn = item_navn;
	}

}
