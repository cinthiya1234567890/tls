export interface IFieldAttribute {
  id?: number;
  attributeName?: string;
  attributeValue?: string;
  fieldFieldName?: string;
  fieldId?: number;
}

export const defaultValue: Readonly<IFieldAttribute> = {};
