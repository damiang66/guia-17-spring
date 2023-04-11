import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/modelo/usuario';
import { UsuarioService } from 'src/app/service/usuario.service';
import  Swal  from 'sweetalert2';

@Component({
  selector: 'app-loggin',
  templateUrl: './loggin.component.html',
  styleUrls: ['./loggin.component.css']
})
export class LogginComponent {
  titulo: string = 'Login'
  usuario:Usuario = new Usuario()
  constructor(private service:UsuarioService, private ruta:Router){}
  ngOnInit(): void {
if (this.service.isAuthenticated()){
  Swal.fire('Login','ya se encentra registrado','info');
  this.ruta.navigate(['/noticia']);
}
  }
login(){
console.log(this.usuario)
if(this.usuario.password== null || this.usuario.username===null || this.usuario.username.length==0  || this.usuario.password.length==0){
Swal.fire('Error Login', 'usuario o password vacio','error');
return
}
this.service.login(this.usuario).subscribe(data=>{

  this.service.guardarUsuario(data.access_token);
  this.service.guardarToken(data.access_token);
  let usuario = this.service.usuario;

this.ruta.navigate(['/noticia']);
Swal.fire('Login', `Hola  ${this.usuario.username} Bienvenido`,'success');
},error=>{
  if (error.status==400){
    Swal.fire('Error :', "usuario o contraseña incorrecto", 'error')
  }
})
}

}
