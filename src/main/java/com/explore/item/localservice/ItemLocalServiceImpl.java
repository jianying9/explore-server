package com.explore.item.localservice;

import com.explore.config.TableNames;
import com.explore.item.entity.ItemEntity;
import com.explore.key.localservice.KeyLocalService;
import com.wolf.framework.dao.REntityDao;
import com.wolf.framework.dao.annotation.InjectRDao;
import com.wolf.framework.dao.condition.InquirePageContext;
import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.local.LocalServiceConfig;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aladdin
 */
@LocalServiceConfig(
        interfaceInfo = ItemLocalService.class,
        description = "兑换物品操作内部接口")
public class ItemLocalServiceImpl implements ItemLocalService {

    @InjectRDao(clazz = ItemEntity.class)
    private REntityDao<ItemEntity> itemEntityDao;
    //
    @InjectLocalService()
    private KeyLocalService keyLocalService;

    @Override
    public void init() {
        //初始化用户表的初始id
        long value = this.keyLocalService.getNextKeyValue(TableNames.ITEM);
        if (value <= 100000) {
            this.keyLocalService.updateNextKeyValue(TableNames.ITEM, 100000);
        }
    }

    @Override
    public ItemEntity insertItem(Map<String, String> parameterMap) {
        long nextKeyValue = this.keyLocalService.nextKeyValue(TableNames.ITEM);
        parameterMap.put("itemId", Long.toString(nextKeyValue));
        return this.itemEntityDao.insertAndInquire(parameterMap);
    }

    @Override
    public void deleteItem(String itemId) {
        this.itemEntityDao.delete(itemId);
    }

    @Override
    public List<ItemEntity> inquireItem(long pageIndex, long pageSize) {
        InquirePageContext inquirePageContext = new InquirePageContext();
        inquirePageContext.setPageIndex(pageIndex);
        inquirePageContext.setPageSize(pageSize);
        return this.itemEntityDao.inquire(inquirePageContext);
    }

    @Override
    public long inquireItemCount() {
        return this.itemEntityDao.count();
    }
}
