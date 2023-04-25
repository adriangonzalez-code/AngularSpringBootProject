import { Component } from "@angular/core";
import { AuthService } from "../usuarios/auth.service";
import { Usuario } from "../usuarios/usuario";
import { Router } from "@angular/router";
import swal from "sweetalert2";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent {
  title: string = 'App Angular';
  private _isAuthenticated: boolean;
  private _usuario : Usuario;

  constructor(private authService : AuthService, private router : Router) {

  }

  public get isAuthenticated() : boolean {
    return this.authService.isAuthenticated();
  }

  public get usuario() : Usuario {
    return this.authService.usuario;
  }

  logout(): void {
    let username = this.authService.usuario.username;
    this.authService.logout();
    swal('Logout', `Hola ${username}, has cerrado sesión con éxito`, 'success');
    this.router.navigate(['/login']);
  }
}
