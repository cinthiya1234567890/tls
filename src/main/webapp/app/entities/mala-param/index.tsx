import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import MalaParam from './mala-param';
import MalaParamDetail from './mala-param-detail';
import MalaParamUpdate from './mala-param-update';
import MalaParamDeleteDialog from './mala-param-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={MalaParamUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={MalaParamUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={MalaParamDetail} />
      <ErrorBoundaryRoute path={match.url} component={MalaParam} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={MalaParamDeleteDialog} />
  </>
);

export default Routes;
