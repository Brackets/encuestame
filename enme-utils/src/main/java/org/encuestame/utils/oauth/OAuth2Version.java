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
package org.encuestame.utils.oauth;

/**
 * Enum encapsulating the differences between the various versions of the OAuth2 specification.
 * @author Picado, Juan juanATencuestame.org
 * @since
 */
public enum OAuth2Version {

    STANDARD {
        public String getAuthorizationHeaderValue(String accessToken) {
            return "BEARER " + accessToken;
        }
    },

    DRAFT_10 {
        public String getAuthorizationHeaderValue(String accessToken) {
            return "OAuth " + accessToken;
        }
    },

    DRAFT_8 {
        public String getAuthorizationHeaderValue(String accessToken) {
            return "Token token=\"" + accessToken + "\"";
        }
    };

    public abstract String getAuthorizationHeaderValue(String accessToken);
}