import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IProfileParam, defaultValue } from 'app/shared/model/profile-param.model';

export const ACTION_TYPES = {
  FETCH_PROFILEPARAM_LIST: 'profileParam/FETCH_PROFILEPARAM_LIST',
  FETCH_PROFILEPARAM: 'profileParam/FETCH_PROFILEPARAM',
  CREATE_PROFILEPARAM: 'profileParam/CREATE_PROFILEPARAM',
  UPDATE_PROFILEPARAM: 'profileParam/UPDATE_PROFILEPARAM',
  DELETE_PROFILEPARAM: 'profileParam/DELETE_PROFILEPARAM',
  RESET: 'profileParam/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IProfileParam>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ProfileParamState = Readonly<typeof initialState>;

// Reducer

export default (state: ProfileParamState = initialState, action): ProfileParamState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PROFILEPARAM_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PROFILEPARAM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_PROFILEPARAM):
    case REQUEST(ACTION_TYPES.UPDATE_PROFILEPARAM):
    case REQUEST(ACTION_TYPES.DELETE_PROFILEPARAM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_PROFILEPARAM_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PROFILEPARAM):
    case FAILURE(ACTION_TYPES.CREATE_PROFILEPARAM):
    case FAILURE(ACTION_TYPES.UPDATE_PROFILEPARAM):
    case FAILURE(ACTION_TYPES.DELETE_PROFILEPARAM):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROFILEPARAM_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROFILEPARAM):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_PROFILEPARAM):
    case SUCCESS(ACTION_TYPES.UPDATE_PROFILEPARAM):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_PROFILEPARAM):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/profile-params';

// Actions

export const getEntities: ICrudGetAllAction<IProfileParam> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PROFILEPARAM_LIST,
  payload: axios.get<IProfileParam>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IProfileParam> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PROFILEPARAM,
    payload: axios.get<IProfileParam>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IProfileParam> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PROFILEPARAM,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IProfileParam> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PROFILEPARAM,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IProfileParam> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PROFILEPARAM,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
