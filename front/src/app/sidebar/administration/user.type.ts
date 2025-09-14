export type User = {
  name: string;
  trigramme: string;
  departement: string;
  role: string;
};

export type ChangePassword = {
  oldPassword: string;
  newPassword: string;
};

export type UserQuery = {
  search?: string;
  role?: string;
};
