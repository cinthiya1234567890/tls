import { Moment } from 'moment';

export interface IPayment {
  id?: number;
  paymentDate?: string;
  amount?: number;
  validityDate?: string;
  description?: string;
  payerName?: string;
  payerId?: number;
}

export const defaultValue: Readonly<IPayment> = {};
