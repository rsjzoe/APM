export type Classe = {
  id: number;
  name: string;
  description: string;
  isDeleted: boolean;
};

export type CreateClasse = Omit<Classe, 'id' | 'isDeleted'>;
export type UpdateClasse = Omit<Classe, 'id' | 'isDeleted'>;
