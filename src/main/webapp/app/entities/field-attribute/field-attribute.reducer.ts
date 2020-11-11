import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IFieldAttribute, defaultValue } from 'app/shared/model/field-attribute.model';

export const ACTION_TYPES = {
  FETCH_FIELDATTRIBUTE_LIST: 'fieldAttribute/FETCH_FIELDATTRIBUTE_LIST',
  FETCH_FIELDATTRIBUTE: 'fieldAttribute/FETCH_FIELDATTRIBUTE',
  CREATE_FIELDATTRIBUTE: 'fieldAttribute/CREATE_FIELDATTRIBUTE',
  UPDATE_FIELDATTRIBUTE: 'fieldAttribute/UPDATE_FIELDATTRIBUTE',
  DELETE_FIELDATTRIBUTE: 'fieldAttribute/DELETE_FIELDATTRIBUTE',
  RESET: 'fieldAttribute/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IFieldAttribute>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type FieldAttributeState = Readonly<typeof initialState>;

// Reducer

export default (state: FieldAttributeState = initialState, action): FieldAttributeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_FIELDATTRIBUTE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_FIELDATTRIBUTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_FIELDATTRIBUTE):
    case REQUEST(ACTION_TYPES.UPDATE_FIELDATTRIBUTE):
    case REQUEST(ACTION_TYPES.DELETE_FIELDATTRIBUTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_FIELDATTRIBUTE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_FIELDATTRIBUTE):
    case FAILURE(ACTION_TYPES.CREATE_FIELDATTRIBUTE):
    case FAILURE(ACTION_TYPES.UPDATE_FIELDATTRIBUTE):
    case FAILURE(ACTION_TYPES.DELETE_FIELDATTRIBUTE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_FIELDATTRIBUTE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_FIELDATTRIBUTE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_FIELDATTRIBUTE):
    case SUCCESS(ACTION_TYPES.UPDATE_FIELDATTRIBUTE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_FIELDATTRIBUTE):
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

const apiUrl = 'api/field-attributes';

// Actions

export const getEntities: ICrudGetAllAction<IFieldAttribute> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_FIELDATTRIBUTE_LIST,
  payload: axios.get<IFieldAttribute>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IFieldAttribute> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_FIELDATTRIBUTE,
    payload: axios.get<IFieldAttribute>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IFieldAttribute> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_FIELDATTRIBUTE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IFieldAttribute> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_FIELDATTRIBUTE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IFieldAttribute> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_FIELDATTRIBUTE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
