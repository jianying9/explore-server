package com.explore.user.service;

import com.explore.config.ActionGroupNames;
import com.explore.config.ActionNames;
import com.explore.config.ResponseFlags;
import com.explore.user.entity.UserEntity;
import com.explore.user.entity.UserPointEntity;
import com.explore.user.localservice.UserLocalService;
import com.wolf.framework.data.TypeEnum;
import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.service.Service;
import com.wolf.framework.service.ServiceConfig;
import com.wolf.framework.service.parameter.OutputConfig;
import com.wolf.framework.session.Session;
import com.wolf.framework.worker.context.MessageContext;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aladdin
 */
@ServiceConfig(
        actionName = ActionNames.INQUIRE_POINT,
        returnParameter = {
            @OutputConfig(name = "myPoint", typeEnum = TypeEnum.LONG, desc = "用户赚取的点数"),
            @OutputConfig(name = "userId", typeEnum = TypeEnum.CHAR_32, desc = "用户id"),
            @OutputConfig(name = "promoterPoint", typeEnum = TypeEnum.LONG, desc = "推广赚取的点数"),
            @OutputConfig(name = "consumePoint", typeEnum = TypeEnum.LONG, desc = "历史消费的点数")
        },
        response = true,
        group = ActionGroupNames.USER,
        description = "查询用户积分")
public class InquirePointServiceImpl implements Service {

    @InjectLocalService()
    private UserLocalService userLocalService;

    @Override
    public void execute(MessageContext messageContext) {
        Session session = messageContext.getSession();
        String userId = session.getSid();
        UserPointEntity userPointEntity = this.userLocalService.inquireUserPointByUserId(userId);
        if (userPointEntity == null) {
            UserEntity userEntity = this.userLocalService.inquireUserByUserId(userId);
            if (userEntity == null) {
                messageContext.setFlag(ResponseFlags.FAILURE_USER_ID_NOT_EXIST);
            } else {
                Map<String, String> resultMap = new HashMap<String, String>(4, 1);
                resultMap.put("userId", userId);
                resultMap.put("myPoint", "0");
                resultMap.put("promoterPoint", "0");
                resultMap.put("consumePoint", "0");
                messageContext.setMapData(resultMap);
                messageContext.success();
            }
        } else {
            messageContext.setEntityData(userPointEntity);
            messageContext.success();
        }
    }
}
