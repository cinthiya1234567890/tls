import React from 'react';
import { Switch } from 'react-router-dom';
import Loadable from 'react-loadable';

import Login from 'app/modules/login/login';
import Register from 'app/modules/account/register/register';
import Activate from 'app/modules/account/activate/activate';
import PasswordResetInit from 'app/modules/account/password-reset/init/password-reset-init';
import PasswordResetFinish from 'app/modules/account/password-reset/finish/password-reset-finish';
import Logout from 'app/modules/login/logout';
import Home from 'app/modules/home/home';
import Entities from 'app/entities';
import PrivateRoute from 'app/shared/auth/private-route';
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';
import PageNotFound from 'app/shared/error/page-not-found';
import { AUTHORITIES } from 'app/config/constants';

import Techno from 'app/matriPages/techno';
import ParamCreator from './matriPages/techno/param-creator';

import BrowseP from 'app/matriPages/browse';
import Browse from './matriPages/browse/browse';

import RegisterP from 'app/matriPages/register';
import RegisterProfile from './matriPages/register/register-profile';

const Account = Loadable({
  loader: () => import(/* webpackChunkName: "account" */ 'app/modules/account'),
  loading: () => <div>loading ...</div>,
});

const Admin = Loadable({
  loader: () => import(/* webpackChunkName: "administration" */ 'app/modules/administration'),
  loading: () => <div>loading ...</div>,
});

const Routes = () => (
  <div className="view-routes">
    <Switch>
      <ErrorBoundaryRoute path="/login" component={Login} />
      <ErrorBoundaryRoute path="/logout" component={Logout} />
      <ErrorBoundaryRoute path="/account/register" component={Register} />
      <ErrorBoundaryRoute path="/account/activate/:key?" component={Activate} />
      <ErrorBoundaryRoute path="/account/reset/request" component={PasswordResetInit} />
      <ErrorBoundaryRoute path="/account/reset/finish/:key?" component={PasswordResetFinish} />

      <PrivateRoute path="/techno" component={Techno} hasAnyAuthorities={[AUTHORITIES.TECHNO]} />
      <PrivateRoute path="/param-creator" component={ParamCreator} hasAnyAuthorities={[AUTHORITIES.TECHNO]} />

      <PrivateRoute path="/browseP" component={BrowseP} hasAnyAuthorities={[AUTHORITIES.USER]} />
      <PrivateRoute path="/browse" component={Browse} hasAnyAuthorities={[AUTHORITIES.USER]} />

      <PrivateRoute path="/registerP" component={RegisterP} hasAnyAuthorities={[AUTHORITIES.USER]} />
      <PrivateRoute path="/register-profile" component={RegisterProfile} hasAnyAuthorities={[AUTHORITIES.USER]} />

      <PrivateRoute path="/admin" component={Admin} hasAnyAuthorities={[AUTHORITIES.ADMIN]} />
      <PrivateRoute path="/account" component={Account} hasAnyAuthorities={[AUTHORITIES.ADMIN, AUTHORITIES.USER]} />


      

      <ErrorBoundaryRoute path="/" exact component={Home} />
      <PrivateRoute path="/" component={Entities} hasAnyAuthorities={[AUTHORITIES.USER]} />


      <ErrorBoundaryRoute component={PageNotFound} />
    </Switch>
  </div>
);

export default Routes;
