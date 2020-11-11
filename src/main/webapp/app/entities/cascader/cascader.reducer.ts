import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICascader, defaultValue } from 'app/shared/model/cascader.model';

export const ACTION_TYPES = {
  FETCH_CASCADER_LIST: 'cascader/FETCH_CASCADER_LIST',
  FETCH_CASCADER: 'cascader/FETCH_CASCADER',
  CREATE_CASCADER: 'cascader/CREATE_CASCADER',
  UPDATE_CASCADER: 'cascader/UPDATE_CASCADER',
  DELETE_CASCADER: 'cascader/DELETE_CASCADER',
  RESET: 'cascader/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICascader>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type CascaderState = Readonly<typeof initialState>;

// Reducer

export default (state: CascaderState = initialState, action): CascaderState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CASCADER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CASCADER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_CASCADER):
    case REQUEST(ACTION_TYPES.UPDATE_CASCADER):
    case REQUEST(ACTION_TYPES.DELETE_CASCADER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_CASCADER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CASCADER):
    case FAILURE(ACTION_TYPES.CREATE_CASCADER):
    case FAILURE(ACTION_TYPES.UPDATE_CASCADER):
    case FAILURE(ACTION_TYPES.DELETE_CASCADER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CASCADER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CASCADER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_CASCADER):
    case SUCCESS(ACTION_TYPES.UPDATE_CASCADER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_CASCADER):
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

const apiUrl = 'api/cascaders';

// Actions

export const getEntities: ICrudGetAllAction<ICascader> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CASCADER_LIST,
  payload: axios.get<ICascader>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ICascader> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CASCADER,
    payload: axios.get<ICascader>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ICascader> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CASCADER,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICascader> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CASCADER,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICascader> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CASCADER,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
