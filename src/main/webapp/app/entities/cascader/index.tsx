import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Cascader from './cascader';
import CascaderDetail from './cascader-detail';
import CascaderUpdate from './cascader-update';
import CascaderDeleteDialog from './cascader-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CascaderUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CascaderUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CascaderDetail} />
      <ErrorBoundaryRoute path={match.url} component={Cascader} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CascaderDeleteDialog} />
  </>
);

export default Routes;
