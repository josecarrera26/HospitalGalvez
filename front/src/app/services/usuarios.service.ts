import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Usuario } from '../interfaces/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  constructor() { }
  private _htt = inject(HttpClient);
  //Url Login
  private urlLog : string = "http://localhost:8486/apirest/login"

   // Datos actuales
  // Si tienes un valor inicial para Usuario, úsalo aquí. Si no, puedes usar 'null'.
  CurrentUserData: BehaviorSubject<Usuario | null> = new BehaviorSubject<Usuario | null>(null);

  loginUsuario(user: Usuario): Observable<Usuario> {
    return this._htt.post<Usuario>(this.urlLog, user).pipe(
      tap((userdata: Usuario) => {
        this.CurrentUserData.next(userdata);
      }),
    );
  }
}
