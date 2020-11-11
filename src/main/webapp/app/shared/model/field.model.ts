import { IFieldAttribute } from 'app/shared/model/field-attribute.model';
import { IProfileParam } from 'app/shared/model/profile-param.model';
import { IQueryPlate } from 'app/shared/model/query-plate.model';
import { IMalaParam } from 'app/shared/model/mala-param.model';

export interface IField {
  id?: number;
  fieldName?: string;
  peckOrder?: number;
  fieldAttributes?: IFieldAttribute[];
  profileParams?: IProfileParam[];
  queryPlates?: IQueryPlate[];
  malaParams?: IMalaParam[];
  dataSourceParamTitle?: string;
  dataSourceId?: number;
  dataTypeParamTitle?: string;
  dataTypeId?: number;
  cascaderCascaderName?: string;
  cascaderId?: number;
}

export const defaultValue: Readonly<IField> = {};
