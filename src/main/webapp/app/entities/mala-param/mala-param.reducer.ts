import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IMalaParam, defaultValue } from 'app/shared/model/mala-param.model';

export const ACTION_TYPES = {
  FETCH_MALAPARAM_LIST: 'malaParam/FETCH_MALAPARAM_LIST',
  FETCH_MALAPARAM: 'malaParam/FETCH_MALAPARAM',
  CREATE_MALAPARAM: 'malaParam/CREATE_MALAPARAM',
  UPDATE_MALAPARAM: 'malaParam/UPDATE_MALAPARAM',
  DELETE_MALAPARAM: 'malaParam/DELETE_MALAPARAM',
  RESET: 'malaParam/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IMalaParam>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type MalaParamState = Readonly<typeof initialState>;

// Reducer

export default (state: MalaParamState = initialState, action): MalaParamState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_MALAPARAM_LIST):
    case REQUEST(ACTION_TYPES.FETCH_MALAPARAM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_MALAPARAM):
    case REQUEST(ACTION_TYPES.UPDATE_MALAPARAM):
    case REQUEST(ACTION_TYPES.DELETE_MALAPARAM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_MALAPARAM_LIST):
    case FAILURE(ACTION_TYPES.FETCH_MALAPARAM):
    case FAILURE(ACTION_TYPES.CREATE_MALAPARAM):
    case FAILURE(ACTION_TYPES.UPDATE_MALAPARAM):
    case FAILURE(ACTION_TYPES.DELETE_MALAPARAM):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_MALAPARAM_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_MALAPARAM):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_MALAPARAM):
    case SUCCESS(ACTION_TYPES.UPDATE_MALAPARAM):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_MALAPARAM):
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

const apiUrl = 'api/mala-params';

// Actions

export const getEntities: ICrudGetAllAction<IMalaParam> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_MALAPARAM_LIST,
  payload: axios.get<IMalaParam>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IMalaParam> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_MALAPARAM,
    payload: axios.get<IMalaParam>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IMalaParam> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_MALAPARAM,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IMalaParam> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_MALAPARAM,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IMalaParam> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_MALAPARAM,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
