package ver2;

import java.util.LinkedHashMap;
import java.util.Map;

public class ItemBox {

	Map<String, Integer> itemBox = new LinkedHashMap<>();

	private String itemName;
	private int itemNum;

	ItemBox(){

	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public Map<String, Integer> getItemBox() {
		return itemBox;
	}

	public void setItemBox(String itemName, int itemNum) {

		if(this.itemBox.containsKey(itemName)){  // 同じアイテムをすでに所持している場合
			itemNum = this.itemBox.get(itemName) + itemNum;
		}

		this.itemBox.put(itemName, itemNum);

	}

//	public void showItem() {
//
//
//
//
//
//	}


}
