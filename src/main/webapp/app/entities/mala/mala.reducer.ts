import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IMala, defaultValue } from 'app/shared/model/mala.model';

export const ACTION_TYPES = {
  FETCH_MALA_LIST: 'mala/FETCH_MALA_LIST',
  FETCH_MALA: 'mala/FETCH_MALA',
  CREATE_MALA: 'mala/CREATE_MALA',
  UPDATE_MALA: 'mala/UPDATE_MALA',
  DELETE_MALA: 'mala/DELETE_MALA',
  RESET: 'mala/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IMala>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type MalaState = Readonly<typeof initialState>;

// Reducer

export default (state: MalaState = initialState, action): MalaState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_MALA_LIST):
    case REQUEST(ACTION_TYPES.FETCH_MALA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_MALA):
    case REQUEST(ACTION_TYPES.UPDATE_MALA):
    case REQUEST(ACTION_TYPES.DELETE_MALA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_MALA_LIST):
    case FAILURE(ACTION_TYPES.FETCH_MALA):
    case FAILURE(ACTION_TYPES.CREATE_MALA):
    case FAILURE(ACTION_TYPES.UPDATE_MALA):
    case FAILURE(ACTION_TYPES.DELETE_MALA):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_MALA_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_MALA):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_MALA):
    case SUCCESS(ACTION_TYPES.UPDATE_MALA):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_MALA):
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

const apiUrl = 'api/malas';

// Actions

export const getEntities: ICrudGetAllAction<IMala> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_MALA_LIST,
  payload: axios.get<IMala>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IMala> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_MALA,
    payload: axios.get<IMala>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IMala> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_MALA,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IMala> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_MALA,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IMala> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_MALA,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
