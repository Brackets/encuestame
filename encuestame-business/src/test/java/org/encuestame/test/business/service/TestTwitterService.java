/*
 ************************************************************************************
 * Copyright (C) 2001-2010 encuestame: system online surveys Copyright (C) 2010
 * encuestame Development Team.
 * Licensed under the Apache Software License version 2.0
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to  in writing,  software  distributed
 * under the License is distributed  on  an  "AS IS"  BASIS,  WITHOUT  WARRANTIES  OR
 * CONDITIONS OF ANY KIND, either  express  or  implied.  See  the  License  for  the
 * specific language governing permissions and limitations under the License.
 ************************************************************************************
 */

package org.encuestame.test.business.service;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.lang.RandomStringUtils;
import org.encuestame.persistence.domain.security.Account;
import org.encuestame.persistence.domain.security.SocialAccount;
import org.encuestame.persistence.domain.security.UserAccount;
import org.encuestame.social.api.TwitterAPITemplate;
import org.encuestame.social.api.support.TwitterAPIOperations;
import org.encuestame.test.business.service.config.AbstractServiceBase;
import org.encuestame.test.config.AbstractBaseUnitBeans;
import org.encuestame.utils.TweetPublishedMetadata;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

/**
 * {@link TwitterAPIOperations} test case.
 * @author Picado, Juan juan@encuestame.org
 * @since Feb 13, 2010 5:05:12 PM
 * @version $Id$
 */
public class TestTwitterService extends AbstractServiceBase {

    /** {@link TwitterAPIOperations}.  */
    //@Autowired
    public TwitterAPIOperations twitterService;

    /** {@link Account}. **/
    private Account user;

    private UserAccount userAccount;

    private SocialAccount socialTwitterAccount;

    /**
     * Before.
     */
    @Before
    public void before(){
        this.user = createAccount();
        this.userAccount = createUserAccount("jota", this.user);
        this.socialTwitterAccount = createDefaultSettedSocialAccount(this.userAccount);
        this.twitterService = new TwitterAPITemplate("", "","", "");
    }

    /**
     * Test Public Tweet.
     * @throws Exception
     */
    @Test
    public void testPublicTweet() throws Exception{
        final String testTweet = RandomStringUtils.randomAlphabetic(5);
       // final String tweet = twitterService.updateStatus(testTweet);
        //assertNotNull(tweet.getId());
    }

    /**
     * @return the twitterService
     */
    public TwitterAPIOperations getTwitterService() {
        return twitterService;
    }

    /**
     * @param twitterService the twitterService to set
     */
    public void setTwitterService(TwitterAPIOperations twitterService) {
        this.twitterService = twitterService;
    }
}
