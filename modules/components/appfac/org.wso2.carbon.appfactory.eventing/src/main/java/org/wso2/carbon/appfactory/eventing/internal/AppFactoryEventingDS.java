/*
 * Copyright (c) 2014, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *   WSO2 Inc. licenses this file to you under the Apache License,
 *   Version 2.0 (the "License"); you may not use this file except
 *   in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.wso2.carbon.appfactory.eventing.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.social.core.service.SocialActivityService;

/**
 * @scr.component name="org.wso2.carbon.appfactory.eventing.internal"
 * immediate="true"
 * @scr.reference name="org.wso2.carbon.social.component" interface="org.wso2.carbon.social.core.service.SocialActivityService"
 * cardinality="0..1" policy="dynamic"  bind="setSocialActivityService" unbind="unsetSocialActivityService"
 */
public class AppFactoryEventingDS {
    private static final Log log = LogFactory.getLog(AppFactoryEventingDS.class);

    @SuppressWarnings("UnusedDeclaration")
    protected void activate(ComponentContext context) {
        if (log.isDebugEnabled()) {
            log.debug("appfactory.eventing service bundle is activated");
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    protected void deactivate(ComponentContext ctxt) {
        if (log.isDebugEnabled()) {
            log.debug("appfactory.eventing service bundle is deactivated");
        }
    }

    protected void setSocialActivityService(SocialActivityService socialActivityService) {
        ServiceHolder.setSocialActivityService(socialActivityService);
    }

    protected void unsetSocialActivityService(SocialActivityService socialActivityService) {
        ServiceHolder.setSocialActivityService(null);
    }


}
