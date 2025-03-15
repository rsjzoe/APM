export type TechBusinessValue = {
  id: number;
  applicationId: number;
  businessValue: number;
  technicalDebt: number;
  createdAt: string | Date;
};

export type CreateTechBusinessValue = Omit<
  TechBusinessValue,
  'id' | 'createdAt'
>;
export type CreateTechBusinessValueWithoutApp = Omit<
  CreateTechBusinessValue,
  'applicationId'
>;
export type UpdateTechBusinessValue = Omit<TechBusinessValue, 'id'>;
