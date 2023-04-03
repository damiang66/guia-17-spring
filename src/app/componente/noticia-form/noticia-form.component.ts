import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Noticia } from 'src/app/modelo/noticia';
import { NoticiaService } from 'src/app/service/noticia.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-noticia-form',
  templateUrl: './noticia-form.component.html',
  styleUrls: ['./noticia-form.component.css']
})
export class NoticiaFormComponent implements OnInit {
  titulo=""
  errores=""
  noticia:Noticia=new Noticia();
error:any=""
  constructor(private service:NoticiaService, private ruta:ActivatedRoute,private  router:Router){}
  ngOnInit(): void {
    this.ruta.paramMap.subscribe(data=>{
      let id:number = +data.get('id');
      if (id){
        this.titulo="Editar noticia";
        this.service.buscarUno(id).subscribe(data=>{
          this.noticia=data;
        })
      }else{
        this.titulo="Crear Noticia";
      }
    })
  }
crear(){
  this.service.crear(this.noticia).subscribe(data=>{
    console.log(data);
    Swal.fire('Noticia: ', "La noticia se creo con exito", "success");
    this.router.navigate(['/listar'])
  },e=>{
    if(e.status=404){
      this.error=e.error;
    }
  })

}
editar(noticia:Noticia){
this.service.editar(noticia).subscribe(data=>{
  Swal.fire('Editar', 'Noticia actualizada con exito', 'success');
  this.router.navigate(['/listar']);

},e=>{
  if(e.status=404){
    this.error=e.error;
  }
})
}

}
