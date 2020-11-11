import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICascaderParam, defaultValue } from 'app/shared/model/cascader-param.model';

export const ACTION_TYPES = {
  FETCH_CASCADERPARAM_LIST: 'cascaderParam/FETCH_CASCADERPARAM_LIST',
  FETCH_CASCADERPARAM: 'cascaderParam/FETCH_CASCADERPARAM',
  CREATE_CASCADERPARAM: 'cascaderParam/CREATE_CASCADERPARAM',
  UPDATE_CASCADERPARAM: 'cascaderParam/UPDATE_CASCADERPARAM',
  DELETE_CASCADERPARAM: 'cascaderParam/DELETE_CASCADERPARAM',
  RESET: 'cascaderParam/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICascaderParam>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type CascaderParamState = Readonly<typeof initialState>;

// Reducer

export default (state: CascaderParamState = initialState, action): CascaderParamState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CASCADERPARAM_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CASCADERPARAM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_CASCADERPARAM):
    case REQUEST(ACTION_TYPES.UPDATE_CASCADERPARAM):
    case REQUEST(ACTION_TYPES.DELETE_CASCADERPARAM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_CASCADERPARAM_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CASCADERPARAM):
    case FAILURE(ACTION_TYPES.CREATE_CASCADERPARAM):
    case FAILURE(ACTION_TYPES.UPDATE_CASCADERPARAM):
    case FAILURE(ACTION_TYPES.DELETE_CASCADERPARAM):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CASCADERPARAM_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CASCADERPARAM):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_CASCADERPARAM):
    case SUCCESS(ACTION_TYPES.UPDATE_CASCADERPARAM):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_CASCADERPARAM):
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

const apiUrl = 'api/cascader-params';

// Actions

export const getEntities: ICrudGetAllAction<ICascaderParam> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CASCADERPARAM_LIST,
  payload: axios.get<ICascaderParam>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ICascaderParam> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CASCADERPARAM,
    payload: axios.get<ICascaderParam>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ICascaderParam> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CASCADERPARAM,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICascaderParam> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CASCADERPARAM,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICascaderParam> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CASCADERPARAM,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
