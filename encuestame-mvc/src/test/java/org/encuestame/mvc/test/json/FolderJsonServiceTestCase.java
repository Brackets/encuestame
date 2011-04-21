/*
 ************************************************************************************
 * Copyright (C) 2001-2011 encuestame: system online surveys Copyright (C) 2011
 * encuestame Development Team.
 * Licensed under the Apache Software License version 2.0
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to  in writing,  software  distributed
 * under the License is distributed  on  an  "AS IS"  BASIS,  WITHOUT  WARRANTIES  OR
 * CONDITIONS OF ANY KIND, either  express  or  implied.  See  the  License  for  the
 * specific language governing permissions and limitations under the License.
 ************************************************************************************
 */
package org.encuestame.mvc.test.json;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;

import org.encuestame.mvc.controller.json.MethodJson;
import org.encuestame.mvc.controller.json.survey.FolderJsonServiceController;
import org.encuestame.mvc.test.config.AbstractJsonMvcUnitBeans;
import org.encuestame.persistence.domain.question.Question;
import org.encuestame.persistence.domain.security.Account;
import org.encuestame.persistence.domain.survey.PollFolder;
import org.encuestame.persistence.domain.survey.Survey;
import org.encuestame.persistence.domain.survey.SurveyFolder;
import org.encuestame.persistence.domain.tweetpoll.TweetPoll;
import org.encuestame.persistence.domain.tweetpoll.TweetPollFolder;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link FolderJsonServiceController} Test Case.
 * @author Morales, Diana Paola paola AT encuestame.org
 * @since Apr 20, 2011
 */
public class FolderJsonServiceTestCase extends AbstractJsonMvcUnitBeans {

    /** {@link PollFolder}. **/
    private PollFolder pollFolder;

    /** {@link Account}. **/
    private Account user;

    /** {@link TweetPollFolder} **/
    private TweetPollFolder tweetPollFolder;

    /** {@link SurveyFolder} **/
    private SurveyFolder surveyFolder;

    private TweetPoll tweetPoll;

    private Survey survey;

    @Before
    public void initService(){
        this.user = createAccount();
        this.pollFolder = createPollFolder("My first poll folder", this.user);
        this.tweetPollFolder = createTweetPollFolder("My first tweetPoll folder", this.user);
        this.surveyFolder = createSurveyFolders("My first survey Folder", this.user);
        createPollFolder("My second poll folder", this.user);
        createTweetPollFolder("My second tweetPoll folder", this.user);
        createSurveyFolders("My second survey Folder", this.user);
        final Question question = createQuestion("Who I am?", "");
        this.tweetPoll = createPublishedTweetPoll(this.user, question);
    }

    /**
     * Test create folder json service
     * @throws ServletException
     * @throws IOException
     */
   // @Test
    public void testcreateFolder() throws ServletException, IOException{
        /** Create poll folder json. **/
        Assert.assertEquals(createJsonPollFolder("poll", "Education"), "Education");
        Assert.assertEquals(createJsonPollFolder("poll", "Nicaragua"), "Nicaragua");

        /** Create tweetPoll folder json. **/
        Assert.assertEquals(createJsonPollFolder("tweetPoll", "Health"), "Health");
        Assert.assertEquals(createJsonPollFolder("tweetPoll", "Technology"), "Technology");

        /** Create survey folder json. **/
        Assert.assertEquals(createJsonPollFolder("survey", "Champions"), "Health");
        Assert.assertEquals(createJsonPollFolder("survey", "Sports"), "Technology");

    }

    /**
     * Run Create folder json service.
     * @param actionType
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String createJsonPollFolder(final String actionType, final String folderName) throws ServletException, IOException{
        System.out.println("FolderName ---->"+ folderName);
        System.out.println("Action Type ---->"+ actionType);
        initService("/api/survey/folder/"+actionType+"/create.json", MethodJson.GET);
        setParameter("n", folderName);
        final JSONObject response = callJsonService();
        System.out.println("RESPONSE----------->"+response);
        //"error":{},"success":{"folder":{"id":87,"createAt":1303337873233,"folderName":"Education"}}}
        final JSONObject success = getSucess(response);
        final JSONObject folder = (JSONObject) success.get("folder");
        return folder.get("folderName").toString();
    }

    /**
     * Test update folder name json service.
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void testUpdateFolder() throws ServletException, IOException{
        /** Update poll folder json. **/
        Assert.assertEquals(updateJsonFolder("poll", "My Last Poll Folder",this.pollFolder.getId()),this.pollFolder.getId());

        /** Update tweetPoll folder json. **/
        Assert.assertEquals(updateJsonFolder("tweetPoll", "My Last TweetPoll Folder",this.tweetPollFolder.getId()),this.tweetPollFolder.getId());

        /** Update Survey folder json. **/
        Assert.assertEquals(updateJsonFolder("survey", "My Last Survey Folder",this.surveyFolder.getId()),this.surveyFolder.getId());
      }

    /**
     * Run update folder json service.
     * @param actionType
     * @param folderName
     * @return
     * @throws IOException
     * @throws ServletException
     */
    public Long updateJsonFolder(final String actionType, final String folderName, final Long folderId) throws ServletException, IOException{
        System.out.println("FolderName UPDATE---->"+ folderName);
        System.out.println("Action Type UPDATE---->"+ actionType);
        initService("/api/survey/folder/"+actionType+"/update.json", MethodJson.GET);
        setParameter("folderName", folderName);
        setParameter("folderId", folderId.toString());
        final JSONObject response = callJsonService();
        System.out.println("RESPONSE Update----------->"+response);
        final JSONObject success = getSucess(response);
        final JSONObject folder = (JSONObject) success.get("folder");
        return  (Long) folder.get("id")  ;
    }

    /**
     * Test remove folder json service.
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void testRemoveFolder() throws ServletException, IOException{
        assertSuccessResponse(removeJsonFolder("poll", this.pollFolder.getId()));
    }

    /**
     * Run remove folder json service.
     * @param actionType
     * @param folderId
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public JSONObject removeJsonFolder(final String actionType, final Long folderId ) throws ServletException, IOException{
        initService("/api/survey/folder/"+actionType+"/remove.json", MethodJson.GET);
        setParameter("folderId", folderId.toString());
        final JSONObject response = callJsonService();
        System.out.println("RESPONSE REMOVE----------->"+response);
       return response;
    }

    /**
     * Test move item to folder.
     * @throws ServletException
     * @throws IOException
     */
    public void testMoveItemJsonFolder() throws ServletException, IOException{
        TweetPollFolder tpf = createTweetPollFolder("My third tweetPoll folder", this.user);
        moveItemJsonFolder("tweetPoll", tpf.getId(), this.tweetPoll.getTweetPollId());
        //Assert.assertEquals(updateJsonFolder("survey", "My Last Survey Folder",this.surveyFolder.getId()),this.surveyFolder.getId());

    }

    /**
     * Run move item to folder json service.
     * @param actionType
     * @param folderId
     * @param itemId
     * @throws ServletException
     * @throws IOException
     */
    public JSONObject moveItemJsonFolder(final String actionType, final Long folderId, final Long itemId ) throws ServletException, IOException{
        initService("/api/survey/folder/"+actionType+"/move.json", MethodJson.GET);
        setParameter("folderId", folderId.toString());
        setParameter("itemId", itemId.toString());
        final JSONObject response = callJsonService();
        System.out.println("RESPONSE MOVE----------->"+response);
        return response;
    }

}