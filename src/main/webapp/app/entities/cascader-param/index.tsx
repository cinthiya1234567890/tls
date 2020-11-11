import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CascaderParam from './cascader-param';
import CascaderParamDetail from './cascader-param-detail';
import CascaderParamUpdate from './cascader-param-update';
import CascaderParamDeleteDialog from './cascader-param-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CascaderParamUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CascaderParamUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CascaderParamDetail} />
      <ErrorBoundaryRoute path={match.url} component={CascaderParam} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CascaderParamDeleteDialog} />
  </>
);

export default Routes;
