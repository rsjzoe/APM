export type Token = {
  accessToken: string;
  refreshToken: string;
  expiresIn: number;
  refreshExpiresIn: number;
};

export type Register = {
  name: string;
  trigramme: string;
  departement: string;
  role: string;
};
