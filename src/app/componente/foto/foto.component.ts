import { Component, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Noticia } from 'src/app/modelo/noticia';
import { ModalService } from 'src/app/serice/modal.service';
import { NoticiaService } from 'src/app/service/noticia.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-foto',
  templateUrl: './foto.component.html',
  styleUrls: ['./foto.component.css']
})
export class FotoComponent {
  @Input()noticia:Noticia = new Noticia();
  titulo = 'Detalle de la Noticia';
  fotoSeleccionada!: File;
  constructor(private service:NoticiaService, private ruta: ActivatedRoute, public modal:ModalService){}
   ngOnInit(): void {





     /*
    this.ruta.paramMap.subscribe(parametro=>{
     let id:number = Number(parametro.get('id'));
     if(id){
       this.service.buscarCliente(id).subscribe(cliente=>{
         this.cliente= cliente;
       })
     }
    })
    */
   }
   seleccionarFoto(event:any){
     this.fotoSeleccionada= event.target.files[0];
     if(this.fotoSeleccionada.type.indexOf('image')<0){
       Swal.fire('Error al seleccionar imagen: ', 'El archivo debe ser de tipo imagen','error');
       this.fotoSeleccionada;
     }

   }
   subirFoto(){
     if(!this.fotoSeleccionada){
       Swal.fire('Error: ', 'Debe seleccionar una foto','error');
     }else{
       if(this.fotoSeleccionada.type.indexOf('image')<0){
         Swal.fire('Error al seleccionar imagen: ', 'El archivo debe ser de tipo imagen','error');
         this.fotoSeleccionada;
       }else{
     this.service.subirfoto(this.fotoSeleccionada,this.noticia.id).subscribe(data=>{
       this.noticia= data;
       this.modal.notificar.emit(this.noticia);
       Swal.fire('la foto se cargo correctamente! ',`la  foto se ha subido con exito ${data.foto}`,'info');
     })
   }
 }
 }
 cerrarModal(){
   this.modal.cerrarModal()
 }
}
