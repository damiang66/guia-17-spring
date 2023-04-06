import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/modelo/usuario';
import { UsuarioService } from 'src/app/service/usuario.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent implements OnInit {
  titulo = "Registro de Autores";
  usuario:Usuario = new Usuario();
  errores="";
  error:any;
  constructor(private service:UsuarioService, private router:Router){

  }
  ngOnInit(): void {

  }
  crear(){
this.service.registro(this.usuario).subscribe(data=>{
Swal.fire('Registro ', 'Usuario Registrado con exito','success')
},e=>{
  if (e.status==404){
    this.error=e.error;
  }
  if (e.status==500){
    Swal.fire('ERROR: ', 'Error al realizar el registro ', 'error');
  }
  if (e.status==400){


  Swal.fire('ERROR: ',`${e.error.mensaje}`, 'error');
  }
})
  }
  editar(usuario:Usuario){

  }

}
