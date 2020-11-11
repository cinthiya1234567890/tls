import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import profile, {
  ProfileState
} from 'app/entities/profile/profile.reducer';
// prettier-ignore
import field, {
  FieldState
} from 'app/entities/field/field.reducer';
// prettier-ignore
import profileParam, {
  ProfileParamState
} from 'app/entities/profile-param/profile-param.reducer';
// prettier-ignore
import photo, {
  PhotoState
} from 'app/entities/photo/photo.reducer';
// prettier-ignore
import contact, {
  ContactState
} from 'app/entities/contact/contact.reducer';
// prettier-ignore
import cascader, {
  CascaderState
} from 'app/entities/cascader/cascader.reducer';
// prettier-ignore
import cascaderParam, {
  CascaderParamState
} from 'app/entities/cascader-param/cascader-param.reducer';
// prettier-ignore
import query, {
  QueryState
} from 'app/entities/query/query.reducer';
// prettier-ignore
import queryPlate, {
  QueryPlateState
} from 'app/entities/query-plate/query-plate.reducer';
// prettier-ignore
import plateParam, {
  PlateParamState
} from 'app/entities/plate-param/plate-param.reducer';
// prettier-ignore
import mala, {
  MalaState
} from 'app/entities/mala/mala.reducer';
// prettier-ignore
import malaParam, {
  MalaParamState
} from 'app/entities/mala-param/mala-param.reducer';
// prettier-ignore
import payment, {
  PaymentState
} from 'app/entities/payment/payment.reducer';
// prettier-ignore
import issue, {
  IssueState
} from 'app/entities/issue/issue.reducer';
// prettier-ignore
import vital, {
  VitalState
} from 'app/entities/vital/vital.reducer';
// prettier-ignore
import fieldAttribute, {
  FieldAttributeState
} from 'app/entities/field-attribute/field-attribute.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly profile: ProfileState;
  readonly field: FieldState;
  readonly profileParam: ProfileParamState;
  readonly photo: PhotoState;
  readonly contact: ContactState;
  readonly cascader: CascaderState;
  readonly cascaderParam: CascaderParamState;
  readonly query: QueryState;
  readonly queryPlate: QueryPlateState;
  readonly plateParam: PlateParamState;
  readonly mala: MalaState;
  readonly malaParam: MalaParamState;
  readonly payment: PaymentState;
  readonly issue: IssueState;
  readonly vital: VitalState;
  readonly fieldAttribute: FieldAttributeState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  profile,
  field,
  profileParam,
  photo,
  contact,
  cascader,
  cascaderParam,
  query,
  queryPlate,
  plateParam,
  mala,
  malaParam,
  payment,
  issue,
  vital,
  fieldAttribute,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
});

export default rootReducer;
