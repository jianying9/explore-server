package com.explore.item.localservice;

import com.explore.item.entity.ItemEntity;
import com.wolf.framework.local.Local;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aladdin
 */
public interface ItemLocalService extends Local {
    //
    public ItemEntity insertItem(Map<String, String> parameterMap);
    
    public void deleteItem(String itemId);
    
    public List<ItemEntity> inquireItemEntity(int pageIndex, int pageSize);
}
