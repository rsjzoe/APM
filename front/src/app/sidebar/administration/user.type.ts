export type User = {
  id: number;
  name: string;
  trigramme: string;
  role: Role
};
export type Role = "admin" | "user"

