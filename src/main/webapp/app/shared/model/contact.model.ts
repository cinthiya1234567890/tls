import { Moment } from 'moment';

export interface IContact {
  id?: number;
  initiatedDate?: string;
  updatedDate?: string;
  contactStatusParamTitle?: string;
  contactStatusId?: number;
  senderId?: number;
  receiverId?: number;
}

export const defaultValue: Readonly<IContact> = {};
