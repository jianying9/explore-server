package com.explore.user.service;

import com.explore.config.ActionGroupNames;
import com.explore.config.ActionNames;
import com.explore.config.ResponseFlags;
import com.explore.user.entity.UserEntity;
import com.explore.user.entity.UserPointEntity;
import com.explore.user.localservice.UserLocalService;
import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.service.Service;
import com.wolf.framework.service.ServiceConfig;
import com.wolf.framework.session.Session;
import com.wolf.framework.worker.context.MessageContext;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aladdin
 */
@ServiceConfig(
        actionName = ActionNames.MAKE_POINT,
        importantParameter = {"myPoint"},
        returnParameter = {"myPoint"},
        parametersConfigs = {UserPointEntity.class},
        response = true,
        group = ActionGroupNames.USER,
        description = "用户赚取积分")
public class MakePointServiceImpl implements Service {
    
    @InjectLocalService()
    private UserLocalService userLocalService;
    
    @Override
    public void execute(MessageContext messageContext) {
        Session session = messageContext.getSession();
        String userId = session.getUserId();
        UserEntity userEntity = this.userLocalService.inquireUserByUserId(userId);
        if (userEntity == null) {
            messageContext.setFlag(ResponseFlags.FAILURE_USER_ID_NOT_EXIST);
        } else {
            long point = Long.parseLong(messageContext.getParameter("myPoint"));
            //增加用户赚取积分
            long newMyPoint = this.userLocalService.increaseMyPoint(userId, point);
            //如果该用户有推广人，则分成10%到推广人
            String promoter = userEntity.getPromoter();
            if (promoter.isEmpty() == false) {
                UserEntity promoterEntity = this.userLocalService.inquireUserByUserId(promoter);
                if (promoterEntity != null) {
                    long promoterPoint = (long) (point * 0.1);
                    this.userLocalService.increasePromoterPoint(userId, promoterPoint);
                }
            }
            Map<String, String> resultMap = new HashMap<String, String>(2, 1);
            resultMap.put("myPoint", Long.toString(newMyPoint));
            messageContext.setMapData(resultMap);
            messageContext.success();
        }
    }
}
