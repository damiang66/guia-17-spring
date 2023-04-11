import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UsuarioService } from 'src/app/service/usuario.service';
import  swal  from 'sweetalert2';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  constructor(public service:UsuarioService, private router:Router){}
titulo = "app-noticias"
logout(): void {
  let username = this.service.usuario.username;
  this.service.logout();
  swal.fire('Logout', `Hola ${username}, has cerrado sesión con éxito!`, 'success');
  this.router.navigate(['/login']);
}
}
