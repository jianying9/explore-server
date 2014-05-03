package com.explore.item.service;

import com.explore.config.ActionGroupNames;
import com.explore.config.ActionNames;
import com.explore.item.entity.ItemEntity;
import com.explore.item.localservice.ItemLocalService;
import com.wolf.framework.data.TypeEnum;
import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.service.Service;
import com.wolf.framework.service.ServiceConfig;
import com.wolf.framework.service.parameter.OutputConfig;
import com.wolf.framework.worker.context.MessageContext;
import java.util.List;

/**
 *
 * @author aladdin
 */
@ServiceConfig(
        actionName = ActionNames.INQUIRE_ITEM,
        page = true,
        returnParameter = {
            @OutputConfig(name = "itemId", typeEnum = TypeEnum.CHAR_32, desc = "物品id"),
            @OutputConfig(name = "itemName", typeEnum = TypeEnum.CHAR_32, desc = "物品名称"),
            @OutputConfig(name = "dataUrl", typeEnum = TypeEnum.CHAR_32, desc = "文件地址"),
            @OutputConfig(name = "desc", typeEnum = TypeEnum.CHAR_255, desc = "描述"),
            @OutputConfig(name = "point", typeEnum = TypeEnum.LONG, desc = "积分值")
        },
        response = true,
        group = ActionGroupNames.ITEM,
        description = "分页查询物品列表")
public class InquireItemServiceImpl implements Service {
    
    @InjectLocalService()
    private ItemLocalService itemLocalService;
    
    @Override
    public void execute(MessageContext messageContext) {
        long pageIndex = messageContext.getPageIndex();
        long pageSize = messageContext.getPageSize();
        long pageTotal = this.itemLocalService.inquireItemCount();
        messageContext.setPageTotal(pageTotal);
        List<ItemEntity> itemEntityList = this.itemLocalService.inquireItem(pageIndex, pageSize);
        messageContext.setEntityListData(itemEntityList);
        messageContext.success();
    }
}
