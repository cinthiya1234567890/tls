import { IPlateParam } from 'app/shared/model/plate-param.model';

export interface IQueryPlate {
  id?: number;
  isRange?: boolean;
  isMulti?: boolean;
  peckOrder?: number;
  plateParams?: IPlateParam[];
  queryQueryName?: string;
  queryId?: number;
  fieldFieldName?: string;
  fieldId?: number;
}

export const defaultValue: Readonly<IQueryPlate> = {
  isRange: false,
  isMulti: false,
};
