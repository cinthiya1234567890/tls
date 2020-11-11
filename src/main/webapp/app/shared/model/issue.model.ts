import { Moment } from 'moment';

export interface IIssue {
  id?: number;
  createdTime?: string;
  updatedTime?: string;
  description?: string;
  complaintId?: number;
  adminId?: number;
}

export const defaultValue: Readonly<IIssue> = {};
