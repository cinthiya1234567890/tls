import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PlateParam from './plate-param';
import PlateParamDetail from './plate-param-detail';
import PlateParamUpdate from './plate-param-update';
import PlateParamDeleteDialog from './plate-param-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PlateParamUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PlateParamUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PlateParamDetail} />
      <ErrorBoundaryRoute path={match.url} component={PlateParam} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={PlateParamDeleteDialog} />
  </>
);

export default Routes;
