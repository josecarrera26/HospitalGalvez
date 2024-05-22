import { Role } from "./role";

export interface Usuario {
  idUsuario:       number;
  username:        string;
  role:            Role;
  passwordUsuario: string;
}
