package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new ConcurrentHashMap<>(); //static
    private static long sequence =0L; //static

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updatedParam) {//DTO를 따로 마드는게 낫다
        Item findItem = store.get(itemId);
        findItem.setItemName(updatedParam.getItemName());
        findItem.setPrice(updatedParam.getPrice());
        findItem.setQuantity(updatedParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
