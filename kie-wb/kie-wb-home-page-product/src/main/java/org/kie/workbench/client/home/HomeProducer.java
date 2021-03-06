/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package org.kie.workbench.client.home;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import org.kie.workbench.client.resources.i18n.HomePageProductConstants;
import org.kie.workbench.common.screens.home.client.widgets.home.HomeImagesHelper;
import org.kie.workbench.common.screens.home.model.HomeModel;
import org.kie.workbench.common.screens.home.model.Section;
import org.kie.workbench.common.workbench.client.PerspectiveIds;
import org.kie.workbench.common.workbench.client.authz.WorkbenchFeatures;
import org.uberfire.client.mvp.PlaceManager;

/**
 * * Producer method for the Home Page content
 */
@ApplicationScoped
public class HomeProducer {

    private HomePageProductConstants homeConstants = HomePageProductConstants.INSTANCE;

    private HomeModel model;

    @Inject
    private PlaceManager placeManager;

    public void init() {
        final String url = GWT.getModuleBaseURL();
        model = new HomeModel( homeConstants.home_title(),
                               homeConstants.home_subtitle() );

        final Section s1 = new Section( homeConstants.authoring_header(),
                                        homeConstants.authoring_paragraph(),
                                        url + HomeImagesHelper.Images.Authoring.getLocalisedImageUrl() );

        final Section s2 = new Section( homeConstants.deploy_header(),
                                        homeConstants.deploy_paragraph(),
                                        url + HomeImagesHelper.Images.Deploy.getLocalisedImageUrl() );

        final Section s3 = new Section( homeConstants.process_Management_header(),
                                        homeConstants.process_Management_paragraph(),
                                        url + HomeImagesHelper.Images.ProcessManagement.getLocalisedImageUrl() );

        final Section s4 = new Section( homeConstants.tasks_header(),
                                        homeConstants.tasks_paragraph(),
                                        url + HomeImagesHelper.Images.Tasks.getLocalisedImageUrl() );

        final Section s5 = new Section( homeConstants.dashboards_header(),
                                        homeConstants.dashboards_paragraph(),
                                        url + HomeImagesHelper.Images.Dashboard.getLocalisedImageUrl() );

        s1.setPerspectiveId( PerspectiveIds.AUTHORING);
        s2.setPerspectiveId( PerspectiveIds.DEPLOYMENTS );
        s3.setPerspectiveId( PerspectiveIds.PROCESS_DEFINITIONS );
        s4.setPerspectiveId( PerspectiveIds.DATASET_TASKS );
        s5.setPermission( WorkbenchFeatures.MANAGE_DASHBOARDS );

        model.addSection( s1 );
        model.addSection( s2 );
        model.addSection( s3 );
        model.addSection( s4 );
        model.addSection( s5 );
    }

    @Produces
    public HomeModel getModel() {
        return model;
    }

}
