import { Component, OnInit } from '@angular/core';
import { Usuario } from "./usuario"
import swal from "sweetalert2";
import { AuthService } from "./auth.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router) {
    this.usuario = new Usuario();
  }

  titulo: string = 'Por favor Sign In';
  usuario: Usuario;

  login(): void {
    console.log(this.usuario);
    if (this.usuario.username == null || this.usuario.password == null) {
      swal('Error Login', 'Username o password vacíos!', 'error');
      return;
    }

    this.authService.login(this.usuario).subscribe(response => {
      console.log(response);

      this.authService.guardarUsuario(response.access_token);
      this.authService.guardarToken(response.access_token);

      let usuario : Usuario = this.authService.usuario;

      this.router.navigate(['/clientes']);
      swal('Login', `Hola ${usuario.username}, has iniciado sesión con éxito`, 'success');
    }, err => {
      if (err.status == 400) {
        swal('Error Login', 'Usuario o clave incorrectos!', 'error');
      }
    });
  }

  ngOnInit(): void {
    if (this.authService.isAuthenticated()) {
      swal('Login', `Hola ${this.authService.usuario.username} ya estas autenticado`, 'info');
      this.router.navigate(['/clientes']);
    }
  }
}
