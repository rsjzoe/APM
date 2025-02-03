import {  CreateApplication } from '../../../application-APM/appType';

export type ValueFromGeneral = {
  name: string;
  description: string;
  categorieId: number;
  departementId: number;
};

export type ValueFromCost = Pick<
  CreateApplication,
  'businessValue' | 'costBuild' | 'costRun' | 'userTotal'
>;

export type ValueFromStatus = Pick<
  CreateApplication,
  'startDate' | 'lastUpdate' | 'status'
>;
