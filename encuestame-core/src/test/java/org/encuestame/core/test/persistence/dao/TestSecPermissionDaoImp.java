/*
 ************************************************************************************
 * Copyright (C) 2001-2009 encuestame: system online surveys Copyright (C) 2009
 * encuestame Development Team.
 * Licensed under the Apache Software License version 2.0
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to  in writing,  software  distributed
 * under the License is distributed  on  an  "AS IS"  BASIS,  WITHOUT  WARRANTIES  OR
 * CONDITIONS OF ANY KIND, either  express  or  implied.  See  the  License  for  the
 * specific language governing permissions and limitations under the License.
 ************************************************************************************
 */
package org.encuestame.core.test.persistence.dao;

import java.util.List;

import org.encuestame.core.persistence.pojo.SecPermission;
import org.encuestame.core.test.service.config.AbstractBase;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Security Permission Dao.
 * @author Picado, Juan juan@encuestame.org
 * @since 13:00:55
 * @version $Id$
 */
public class TestSecPermissionDaoImp extends AbstractBase {

     private SecPermission permission;

    /**
     * Before.
     */
    @Before
    public void initData(){
        permission = createPermission("ENCUESTAME_USER");
    }

    /**
     * Test Load Permission.
     */
    @Test
    public void testloadPermission(){
        final SecPermission retrievedPermission = getSecPermissionDaoImp().loadPermission("ENCUESTAME_USER");
        assertEquals("should be equals", permission.getPermission(), retrievedPermission.getPermission());
    }

    /**
     * Test Load All Permissions.
     */
    @Test
    public void testloadAllPermissions(){
        final List<SecPermission> allPermissions = getSecPermissionDaoImp().loadAllPermissions();
        for (SecPermission secPermission : allPermissions) {
            System.out.println("s "+secPermission.getPermission());
        }
        assertEquals("should be equals", 1, allPermissions.size());
    }
}