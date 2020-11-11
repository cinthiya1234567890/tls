import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FieldAttribute from './field-attribute';
import FieldAttributeDetail from './field-attribute-detail';
import FieldAttributeUpdate from './field-attribute-update';
import FieldAttributeDeleteDialog from './field-attribute-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FieldAttributeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FieldAttributeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FieldAttributeDetail} />
      <ErrorBoundaryRoute path={match.url} component={FieldAttribute} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={FieldAttributeDeleteDialog} />
  </>
);

export default Routes;
