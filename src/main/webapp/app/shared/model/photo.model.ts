export interface IPhoto {
  id?: number;
  filename?: string;
  peckOrder?: number;
  profileName?: string;
  profileId?: number;
}

export const defaultValue: Readonly<IPhoto> = {};
