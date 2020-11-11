import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Mala from './mala';
import MalaDetail from './mala-detail';
import MalaUpdate from './mala-update';
import MalaDeleteDialog from './mala-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={MalaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={MalaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={MalaDetail} />
      <ErrorBoundaryRoute path={match.url} component={Mala} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={MalaDeleteDialog} />
  </>
);

export default Routes;
