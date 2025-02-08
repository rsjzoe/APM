export type Application = {
  id: number;
  name: string;
  description: string;
  businessValue: number; // vola napidirin'ilay app
  costBuild: number;
  category: Category;
  costRun: number;
  startDate: Date;
  lastUpdate: Date;
  status: Status;
  departement: Departement;
  time: Time;
  userTotal: number;
  note: number;
};

export type Category = {
  id: number;
  name: string;
};
export type Departement = {
  id: number;
  name: string;
};
export type Time = 'tolerate' | 'invest' | 'migrate' | 'eliminate';
export type Status = 'development' | 'production' | 'deprecated';

export type Question = {
  id: number;
  text: string;
  borderColor: string;
};

export type CreateApplication = Omit<
  Application,
  'id' | 'note' | 'category' | 'departement'
> & {
  categoryId: number;
  departementId: number;
};
export type UpdateApplication = Omit<
  Application,
  'id' | 'category' | 'departement'
> & {
  categoryId: number;
  departementId: number;
};

export type AppHistory = Application & {
  modifiedAt: Date;
  modifiedBy: string;
  applicationId:number
};


export type CreateQuestion = Omit<Question, 'id'>;
export type UpdateQuestion = Omit<Question, 'id'>;
