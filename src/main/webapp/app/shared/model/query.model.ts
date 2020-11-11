import { IQueryPlate } from 'app/shared/model/query-plate.model';

export interface IQuery {
  id?: number;
  queryName?: string;
  peckOrder?: number;
  queryPlates?: IQueryPlate[];
  profileName?: string;
  profileId?: number;
}

export const defaultValue: Readonly<IQuery> = {};
