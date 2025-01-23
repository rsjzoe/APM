export type Application = {
  id: number;
  name: string;
  description: string;
  businessValue: number; // vola napidirin'ilay app
  costBuild: number;
  costRun: number;
  userTeam: string;
  category: Category;
  startDate: Date;
  lastUpdate: Date;
  performance: Performance;
  status: Status;
  time: Time;
  userTotal: number;
};

export type CreateApplication = Omit<Application, 'id'>;
export type UpdateApplication = Omit<Application, 'id'>;
export type Category = 'SI' | 'ODA';
export type Time = 'tolerate' | 'invest' | 'migrate' | 'eliminate';
export type Status = 'development' | 'production' | 'deprecated';
export type Performance = {
  // KPI (Key Performance Indicators)

  responseTimeMs: number; //en ms 2ms par ex
};

export type Question = {
  id: number;
  text: string;
};
export type CreateQuestion = Omit<Question, 'id'>;
export type UpdateQuestion = Omit<Question, 'id'>;
