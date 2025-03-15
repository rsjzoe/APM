import { Role } from "../sidebar/administration/user.type";

export type Token = {
  accessToken: string;
  refreshToken: string;
  expiresIn: number;
  refreshExpiresIn: number;
};

export type Register = {
  name : string;
  trigramme : string;
  departement: string;
  role: Role
}
