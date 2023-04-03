import { Component, OnInit } from '@angular/core';
import { Noticia } from 'src/app/modelo/noticia';
import { ModalService } from 'src/app/serice/modal.service';
import { NoticiaService } from 'src/app/service/noticia.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.css']
})
export class ListarComponent implements OnInit {
  termino:string;
noticias:Noticia[]=[];
noticiaSeleccionada:Noticia = new Noticia();
constructor(public service:NoticiaService,private modal:ModalService){}
  ngOnInit(): void {
    this.todos();
    this.noticiaSeleccionada=new Noticia();
  }
  eliminar(noticia:Noticia){
    Swal.fire({
      title: 'Eliminar?',
      text: "Esta seguro que desea eliminar",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'si, eliminar!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.service.eliminar(noticia.id).subscribe(data=>{
          Swal.fire(
            'Elimiar',
            'la Noticia se elimino con exito.',
            'success'
          )
          this.todos();
        })




      }
    })
  }
  todos(){
    this.service.listar().subscribe(data=>{
      this.noticias= data as Noticia[];
      console.log(data);

    })
    this.modal.notificar.subscribe(noticia=>{
      this.noticias.map(clienteOriginal=>{
        if(noticia.id==clienteOriginal.id){
    clienteOriginal.foto= noticia.foto;
        }
        return clienteOriginal;
      })
    })
      }


  buscar(termino:string){

this.service.buscarPorTitulo(termino).subscribe(data=>{
  this.noticias=data;
},e=>{
  if (e.status==400){
    this.todos();
  }
})
  }


    abrirModel(noticia:Noticia){
  this.noticiaSeleccionada=noticia;
  this.modal.abrirModal();
    }
}
