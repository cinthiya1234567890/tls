import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Profile from './profile';
import Field from './field';
import ProfileParam from './profile-param';
import Photo from './photo';
import Contact from './contact';
import Cascader from './cascader';
import CascaderParam from './cascader-param';
import Query from './query';
import QueryPlate from './query-plate';
import PlateParam from './plate-param';
import Mala from './mala';
import MalaParam from './mala-param';
import Payment from './payment';
import Issue from './issue';
import Vital from './vital';
import FieldAttribute from './field-attribute';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}profile`} component={Profile} />
      <ErrorBoundaryRoute path={`${match.url}field`} component={Field} />
      <ErrorBoundaryRoute path={`${match.url}profile-param`} component={ProfileParam} />
      <ErrorBoundaryRoute path={`${match.url}photo`} component={Photo} />
      <ErrorBoundaryRoute path={`${match.url}contact`} component={Contact} />
      <ErrorBoundaryRoute path={`${match.url}cascader`} component={Cascader} />
      <ErrorBoundaryRoute path={`${match.url}cascader-param`} component={CascaderParam} />
      <ErrorBoundaryRoute path={`${match.url}query`} component={Query} />
      <ErrorBoundaryRoute path={`${match.url}query-plate`} component={QueryPlate} />
      <ErrorBoundaryRoute path={`${match.url}plate-param`} component={PlateParam} />
      <ErrorBoundaryRoute path={`${match.url}mala`} component={Mala} />
      <ErrorBoundaryRoute path={`${match.url}mala-param`} component={MalaParam} />
      <ErrorBoundaryRoute path={`${match.url}payment`} component={Payment} />
      <ErrorBoundaryRoute path={`${match.url}issue`} component={Issue} />
      <ErrorBoundaryRoute path={`${match.url}vital`} component={Vital} />
      <ErrorBoundaryRoute path={`${match.url}field-attribute`} component={FieldAttribute} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
