/*
 * ProActive Parallel Suite(TM):
 * The Open Source library for parallel and distributed
 * Workflows & Scheduling, Orchestration, Cloud Automation
 * and Big Data Analysis on Enterprise Grids & Clouds.
 *
 * Copyright (c) 2007 - 2017 ActiveEon
 * Contact: contact@activeeon.com
 *
 * This library is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation: version 3 of
 * the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * If needed, contact us to obtain a release under GPL Version 2 or 3
 * or a different license than the AGPL.
 */
package org.ow2.proactive_grid_cloud_portal.scheduler.client.controller;

import org.ow2.proactive_grid_cloud_portal.scheduler.client.model.JobsPaginationModel;
import org.ow2.proactive_grid_cloud_portal.scheduler.client.view.JobsPaginationView;

import com.smartgwt.client.widgets.layout.Layout;


/**
 * Controller for the jobs pagination logic.
 * @author the activeeon team.
 *
 */
public class JobsPaginationController extends PaginationController<JobsPaginationModel> {

    private JobsController itemsController;

    private JobsPaginationView view;

    public JobsPaginationController(JobsController jobsController) {
        super(new JobsPaginationModel());
        this.itemsController = jobsController;
        jobsController.getModel().setPaginationModel(this.model);
    }

    public void fetch(boolean silentFetch) {
        this.itemsController.fetchJobs(silentFetch);
    }

    public Layout buildView() {
        this.view = new JobsPaginationView(itemsController);
        return this.view.build();
    }

    @Override
    public void firstPage() {
        model.setStartCursor(null);
        model.setEndCursor(null);
        model.setLast(false);
        this.fetch(false);
    }

    @Override
    public void lastPage() {
        model.setStartCursor(null);
        model.setEndCursor(null);
        model.setLast(true);
        this.fetch(false);
    }

    @Override
    public void nextPage() {
        model.setStartCursor(model.getCurrentEndCursor());
        model.setEndCursor(null);
        model.setLast(false);
        this.fetch(false);
    }

    @Override
    public void previousPage() {
        model.setStartCursor(null);
        model.setEndCursor(model.getCurrentStartCursor());
        model.setLast(true);
        this.fetch(false);
    }

    @Override
    public boolean hasPrevious() {
        return model.hasPreviousPage();
    }

    @Override
    public boolean hasNext() {
        return model.hasNextPage();
    }

    public JobsPaginationModel getModel() {
        return model;
    }
}
