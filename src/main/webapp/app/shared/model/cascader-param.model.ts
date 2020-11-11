import { IField } from 'app/shared/model/field.model';
import { IProfileParam } from 'app/shared/model/profile-param.model';
import { IContact } from 'app/shared/model/contact.model';

export interface ICascaderParam {
  id?: number;
  paramTitle?: string;
  peckOrder?: number;
  levelIndex?: number;
  dataSourcers?: IField[];
  dataTypers?: IField[];
  profileParams?: IProfileParam[];
  contacts?: IContact[];
  children?: ICascaderParam[];
  parentId?: number;
  cascaderCascaderName?: string;
  cascaderId?: number;
}

export const defaultValue: Readonly<ICascaderParam> = {};
