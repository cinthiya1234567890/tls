import { IField } from 'app/shared/model/field.model';
import { ICascaderParam } from 'app/shared/model/cascader-param.model';
import { IPlateParam } from 'app/shared/model/plate-param.model';

export interface ICascader {
  id?: number;
  cascaderName?: string;
  canEnterCustomValue?: boolean;
  fields?: IField[];
  cascaderParams?: ICascaderParam[];
  plateParams?: IPlateParam[];
}

export const defaultValue: Readonly<ICascader> = {
  canEnterCustomValue: false,
};
