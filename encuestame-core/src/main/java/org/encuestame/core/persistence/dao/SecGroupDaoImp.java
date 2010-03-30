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
package org.encuestame.core.persistence.dao;

import java.util.Collection;
import java.util.List;

import org.encuestame.core.persistence.dao.imp.ISecGroups;
import org.encuestame.core.persistence.pojo.SecGroups;
import org.hibernate.HibernateException;

/**
 * Security Group Dao.
 * @author Picado, Juan Carlos juan@encuestame.org
 * @since May 05, 2009
 * @version $Id$
 */
public class SecGroupDaoImp extends AbstractHibernateDaoSupport implements
        ISecGroups {

    /**
     * Find all groups.
     */
    @SuppressWarnings("unchecked")
    public List<SecGroups> findAllGroups() {
        return super.findAll("from SecGroups");
    }

    /**
     * Load Groups By User.
     * @param userId user id
     * @return list of groups.

    @SuppressWarnings("unchecked")
    public Collection<SecGroupUser> loadGroupsByUser(Long userId) {
        return getHibernateTemplate().findByNamedParam("from SecGroupUser  "
         +"where secUsers.uid = :uid", "uid", userId);
    }*/

    /**
     *
     */
    public SecGroups getGroupById(Long groupId) throws HibernateException {
        return (SecGroups) getHibernateTemplate().get(SecGroups.class,
               groupId);
    }

    /**
     * Find group by Id.
     * @param groupId group id.
     * @return group
     */
    public SecGroups find(final Long groupId) {
        return (SecGroups) getHibernateTemplate().get(SecGroups.class, groupId);
    }
}