import { IMalaParam } from 'app/shared/model/mala-param.model';

export interface IMala {
  id?: number;
  malaName?: string;
  isEditable?: boolean;
  description?: string;
  malaParams?: IMalaParam[];
}

export const defaultValue: Readonly<IMala> = {
  isEditable: false,
};
