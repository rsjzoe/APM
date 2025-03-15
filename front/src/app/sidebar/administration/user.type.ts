export type User = {
  name: string;
  trigramme: string;
  departement: string;
  role: Role;
};
export enum Role {
  admin = "admin",
  editor = "editor",
  visitor = "visitor"
}
