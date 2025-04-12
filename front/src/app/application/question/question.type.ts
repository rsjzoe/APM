export type QuestionGroupe = {
  id: number;
  text: string;
  borderColor: string;
  coeff: number;
  questions: Question[];
  type: QuestionGroupeType;
};

export type Question = {
  id: number;
  text: string;
};

export enum QuestionGroupeType {
  businessValue = 'businessValue',
  technicalDebt = 'technicalDebt',
}

export type CreateQuestionGroupe = Omit<QuestionGroupe, 'id' | 'questions'>;
export type CreateQuestion = Omit<Question, 'id'> & { questionGroupId: number };
export type UpdateQuestionGroupe = Omit<QuestionGroupe, 'id' | 'questions'>;
export type UpdateQuestion = Omit<Question, 'id'> & { questionGroupId: number };
