package com.explore.item.service;

import com.explore.config.ActionGroupNames;
import com.explore.config.ActionNames;
import com.explore.item.entity.ItemEntity;
import com.explore.item.localservice.ItemLocalService;
import com.wolf.framework.data.TypeEnum;
import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.service.Service;
import com.wolf.framework.service.ServiceConfig;
import com.wolf.framework.service.parameter.InputConfig;
import com.wolf.framework.service.parameter.OutputConfig;
import com.wolf.framework.worker.context.MessageContext;
import java.util.Map;

/**
 *
 * @author aladdin
 */
@ServiceConfig(
        actionName = ActionNames.INSERT_ITEM,
        importantParameter = {
            @InputConfig(name = "itemName", typeEnum = TypeEnum.CHAR_32, desc = "物品id"),
            @InputConfig(name = "point", typeEnum = TypeEnum.CHAR_32, desc = "物品id"),
            @InputConfig(name = "desc", typeEnum = TypeEnum.CHAR_32, desc = "物品id"),
            @InputConfig(name = "dataUrl", typeEnum = TypeEnum.CHAR_32, desc = "物品id")
        },
        returnParameter = {
            @OutputConfig(name = "itemId", typeEnum = TypeEnum.CHAR_32, desc = "物品id"),
            @OutputConfig(name = "itemName", typeEnum = TypeEnum.CHAR_32, desc = "物品名称"),
            @OutputConfig(name = "dataUrl", typeEnum = TypeEnum.CHAR_32, desc = "文件地址"),
            @OutputConfig(name = "desc", typeEnum = TypeEnum.CHAR_255, desc = "描述"),
            @OutputConfig(name = "point", typeEnum = TypeEnum.LONG, desc = "积分值")
        },
        response = true,
        group = ActionGroupNames.ITEM,
        description = "增加物品")
public class InsertItemServiceImpl implements Service {

    @InjectLocalService()
    private ItemLocalService itemLocalService;

    @Override
    public void execute(MessageContext messageContext) {
        Map<String, String> parameterMap = messageContext.getParameterMap();
        ItemEntity itemEntity = this.itemLocalService.insertItem(parameterMap);
        messageContext.setEntityData(itemEntity);
        messageContext.success();
    }
}
