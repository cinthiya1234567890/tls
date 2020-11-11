import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Vital from './vital';
import VitalDetail from './vital-detail';
import VitalUpdate from './vital-update';
import VitalDeleteDialog from './vital-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={VitalUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={VitalUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={VitalDetail} />
      <ErrorBoundaryRoute path={match.url} component={Vital} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={VitalDeleteDialog} />
  </>
);

export default Routes;
