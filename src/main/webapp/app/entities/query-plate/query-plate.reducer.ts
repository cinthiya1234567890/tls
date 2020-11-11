import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IQueryPlate, defaultValue } from 'app/shared/model/query-plate.model';

export const ACTION_TYPES = {
  FETCH_QUERYPLATE_LIST: 'queryPlate/FETCH_QUERYPLATE_LIST',
  FETCH_QUERYPLATE: 'queryPlate/FETCH_QUERYPLATE',
  CREATE_QUERYPLATE: 'queryPlate/CREATE_QUERYPLATE',
  UPDATE_QUERYPLATE: 'queryPlate/UPDATE_QUERYPLATE',
  DELETE_QUERYPLATE: 'queryPlate/DELETE_QUERYPLATE',
  RESET: 'queryPlate/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IQueryPlate>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type QueryPlateState = Readonly<typeof initialState>;

// Reducer

export default (state: QueryPlateState = initialState, action): QueryPlateState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_QUERYPLATE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_QUERYPLATE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_QUERYPLATE):
    case REQUEST(ACTION_TYPES.UPDATE_QUERYPLATE):
    case REQUEST(ACTION_TYPES.DELETE_QUERYPLATE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_QUERYPLATE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_QUERYPLATE):
    case FAILURE(ACTION_TYPES.CREATE_QUERYPLATE):
    case FAILURE(ACTION_TYPES.UPDATE_QUERYPLATE):
    case FAILURE(ACTION_TYPES.DELETE_QUERYPLATE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_QUERYPLATE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_QUERYPLATE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_QUERYPLATE):
    case SUCCESS(ACTION_TYPES.UPDATE_QUERYPLATE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_QUERYPLATE):
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

const apiUrl = 'api/query-plates';

// Actions

export const getEntities: ICrudGetAllAction<IQueryPlate> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_QUERYPLATE_LIST,
  payload: axios.get<IQueryPlate>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IQueryPlate> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_QUERYPLATE,
    payload: axios.get<IQueryPlate>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IQueryPlate> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_QUERYPLATE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IQueryPlate> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_QUERYPLATE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IQueryPlate> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_QUERYPLATE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
