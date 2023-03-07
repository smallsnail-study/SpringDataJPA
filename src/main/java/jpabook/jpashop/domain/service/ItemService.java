package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {  //ItemRepository에 단순히 위임만 한다.

    private final ItemRepository itemRepository;

    @Transactional  // 상단에 readOnly가 true이므로 @Transactional을 별도 설정하지 않으면 저장되지 않는다.
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItems() {
        return itemRepository.fidnAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
