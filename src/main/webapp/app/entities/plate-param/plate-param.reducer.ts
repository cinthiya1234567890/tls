import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPlateParam, defaultValue } from 'app/shared/model/plate-param.model';

export const ACTION_TYPES = {
  FETCH_PLATEPARAM_LIST: 'plateParam/FETCH_PLATEPARAM_LIST',
  FETCH_PLATEPARAM: 'plateParam/FETCH_PLATEPARAM',
  CREATE_PLATEPARAM: 'plateParam/CREATE_PLATEPARAM',
  UPDATE_PLATEPARAM: 'plateParam/UPDATE_PLATEPARAM',
  DELETE_PLATEPARAM: 'plateParam/DELETE_PLATEPARAM',
  RESET: 'plateParam/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPlateParam>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type PlateParamState = Readonly<typeof initialState>;

// Reducer

export default (state: PlateParamState = initialState, action): PlateParamState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PLATEPARAM_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PLATEPARAM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_PLATEPARAM):
    case REQUEST(ACTION_TYPES.UPDATE_PLATEPARAM):
    case REQUEST(ACTION_TYPES.DELETE_PLATEPARAM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_PLATEPARAM_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PLATEPARAM):
    case FAILURE(ACTION_TYPES.CREATE_PLATEPARAM):
    case FAILURE(ACTION_TYPES.UPDATE_PLATEPARAM):
    case FAILURE(ACTION_TYPES.DELETE_PLATEPARAM):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PLATEPARAM_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PLATEPARAM):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_PLATEPARAM):
    case SUCCESS(ACTION_TYPES.UPDATE_PLATEPARAM):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_PLATEPARAM):
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

const apiUrl = 'api/plate-params';

// Actions

export const getEntities: ICrudGetAllAction<IPlateParam> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PLATEPARAM_LIST,
  payload: axios.get<IPlateParam>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IPlateParam> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PLATEPARAM,
    payload: axios.get<IPlateParam>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IPlateParam> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PLATEPARAM,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPlateParam> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PLATEPARAM,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPlateParam> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PLATEPARAM,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
