export type Application = {
  id: number;
  name: string;
  description: string;
  businessValue: number; // vola napidirin'ilay app
  costBuild: number;
  costRun: number;
  userTeam: string;
  category: Category;
  technologies: Technology[];
  startDate: Date;
  lastUpdate: Date;
  performance: Performance;
  status: Status;
  time: Time;
  userTotal: number;
};

export type Category = 'SI' | 'ODA';
export type Time = 'tolerate' | 'invest' | 'migrate' | 'eliminate';
export type Status = 'development' | 'production' | 'deprecated';
export type Technology = { name: string };
export type Performance = {
  // KPI (Key Performance Indicators)

  responseTimeMs: number; //en ms 2ms par ex
};
