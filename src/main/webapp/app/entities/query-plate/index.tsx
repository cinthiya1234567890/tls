import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import QueryPlate from './query-plate';
import QueryPlateDetail from './query-plate-detail';
import QueryPlateUpdate from './query-plate-update';
import QueryPlateDeleteDialog from './query-plate-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={QueryPlateUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={QueryPlateUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={QueryPlateDetail} />
      <ErrorBoundaryRoute path={match.url} component={QueryPlate} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={QueryPlateDeleteDialog} />
  </>
);

export default Routes;
