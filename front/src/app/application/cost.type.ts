export type Cost = {
  id: number;
  applicationId: number;
  costBuild: number;
  costRun: number;
  createdAt: string | Date;
};

export type CreateCost = Omit<Cost, 'id' | 'createdAt'>;
export type CreateCostWithoutApp = Omit<CreateCost, 'applicationId'>;
export type UpdateCost = Omit<Cost, 'id'>;
