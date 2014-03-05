package com.explore.key.localservice;

import com.wolf.framework.local.Local;

/**
 *
 * @author aladdin
 */
public interface KeyLocalService extends Local {

    public long getNextKeyValue(String tableName);
    
    public long nextKeyValue(String tableName);

    public void updateNextKeyValue(String tableName, long value);
}
