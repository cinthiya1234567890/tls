import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IVital, defaultValue } from 'app/shared/model/vital.model';

export const ACTION_TYPES = {
  FETCH_VITAL_LIST: 'vital/FETCH_VITAL_LIST',
  FETCH_VITAL: 'vital/FETCH_VITAL',
  CREATE_VITAL: 'vital/CREATE_VITAL',
  UPDATE_VITAL: 'vital/UPDATE_VITAL',
  DELETE_VITAL: 'vital/DELETE_VITAL',
  RESET: 'vital/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IVital>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type VitalState = Readonly<typeof initialState>;

// Reducer

export default (state: VitalState = initialState, action): VitalState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_VITAL_LIST):
    case REQUEST(ACTION_TYPES.FETCH_VITAL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_VITAL):
    case REQUEST(ACTION_TYPES.UPDATE_VITAL):
    case REQUEST(ACTION_TYPES.DELETE_VITAL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_VITAL_LIST):
    case FAILURE(ACTION_TYPES.FETCH_VITAL):
    case FAILURE(ACTION_TYPES.CREATE_VITAL):
    case FAILURE(ACTION_TYPES.UPDATE_VITAL):
    case FAILURE(ACTION_TYPES.DELETE_VITAL):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_VITAL_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_VITAL):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_VITAL):
    case SUCCESS(ACTION_TYPES.UPDATE_VITAL):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_VITAL):
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

const apiUrl = 'api/vitals';

// Actions

export const getEntities: ICrudGetAllAction<IVital> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_VITAL_LIST,
  payload: axios.get<IVital>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IVital> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_VITAL,
    payload: axios.get<IVital>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IVital> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_VITAL,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IVital> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_VITAL,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IVital> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_VITAL,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
