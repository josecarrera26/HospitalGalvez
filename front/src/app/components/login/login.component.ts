import { Component, Inject, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { initFlowbite } from 'flowbite';
import { UsuariosService } from '../../services/usuarios.service';
import { Router, RouterModule } from '@angular/router';
import { DatePipe } from '@angular/common';
import { Role } from '../../interfaces/role';
import { Usuario } from '../../interfaces/usuario';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterModule, ReactiveFormsModule, DatePipe],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit {

  ngOnInit(): void {
    //initFlowbite()
  }
  loginErro: string = "";

  private fb = inject(FormBuilder);
  private router = inject(Router);
  //injectar servicio
  private _apiSerive = inject(UsuariosService);

  //formulario definido usando formbuilder
  form: FormGroup = this.fb.group({
    username: ['', [Validators.required]],
    password: ['', [Validators.required]]
  });

  login() {
    // Acceder a los valores del formulario
    const user = this.form.value;
    if (!user.username || !user.password) {
      alert("Por favor, llene los campos requeridos!");
      return;
    }
    if (this.form.valid) {
      this._apiSerive.loginUsuario(user).subscribe({
        next: (data) => {
          console.log(data);
          if (data.role.nombre === "administrador") {
            this.router.navigateByUrl("/menuadministrador");
          } if (data.role.nombre === "paciente") {
            this.router.navigateByUrl("/menupaciente");
          } },
        error: (errorData) => {
          alert("Usuario o contraseña incorrecta!");
          console.error(errorData);
          this.loginErro = errorData;
        },
        complete: () => {
          alert("Sesión Iniciada!");
          this.form.reset();
        },
      });
    }
  }

}




