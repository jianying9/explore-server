package com.explore.item.service;

import com.explore.config.ActionGroupNames;
import com.explore.config.ActionNames;
import com.explore.item.entity.ItemEntity;
import com.explore.item.localservice.ItemLocalService;
import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.service.Service;
import com.wolf.framework.service.ServiceConfig;
import com.wolf.framework.worker.context.MessageContext;

/**
 *
 * @author aladdin
 */
@ServiceConfig(
        actionName = ActionNames.DELETE_ITEM,
        importantParameter = {"itemId"},
        parametersConfigs = {ItemEntity.class},
        response = true,
        group = ActionGroupNames.ITEM,
        description = "删除物品")
public class DeleteItemServiceImpl implements Service {
    
    @InjectLocalService()
    private ItemLocalService itemLocalService;
    
    @Override
    public void execute(MessageContext messageContext) {
        String itemId = messageContext.getParameter("itemId");
        this.itemLocalService.deleteItem(itemId);
        messageContext.success();
    }
}
