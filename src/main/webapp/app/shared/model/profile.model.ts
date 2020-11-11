import { Moment } from 'moment';
import { IProfileParam } from 'app/shared/model/profile-param.model';
import { IPhoto } from 'app/shared/model/photo.model';
import { IContact } from 'app/shared/model/contact.model';
import { IQuery } from 'app/shared/model/query.model';
import { IPayment } from 'app/shared/model/payment.model';
import { IIssue } from 'app/shared/model/issue.model';

export interface IProfile {
  id?: number;
  name?: string;
  dateOfBirth?: string;
  phone?: string;
  createdTime?: string;
  loginTime?: string;
  prevLoginTime?: string;
  userLogin?: string;
  userId?: number;
  profileParams?: IProfileParam[];
  photos?: IPhoto[];
  sents?: IContact[];
  receiveds?: IContact[];
  queries?: IQuery[];
  payments?: IPayment[];
  issueds?: IIssue[];
  addresseds?: IIssue[];
}

export const defaultValue: Readonly<IProfile> = {};
