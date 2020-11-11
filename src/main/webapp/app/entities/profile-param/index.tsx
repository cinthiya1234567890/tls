import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ProfileParam from './profile-param';
import ProfileParamDetail from './profile-param-detail';
import ProfileParamUpdate from './profile-param-update';
import ProfileParamDeleteDialog from './profile-param-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ProfileParamUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ProfileParamUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ProfileParamDetail} />
      <ErrorBoundaryRoute path={match.url} component={ProfileParam} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ProfileParamDeleteDialog} />
  </>
);

export default Routes;
