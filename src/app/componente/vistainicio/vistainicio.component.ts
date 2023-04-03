import { Component } from '@angular/core';
import { Noticia } from 'src/app/modelo/noticia';
import { NoticiaService } from 'src/app/service/noticia.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-vistainicio',
  templateUrl: './vistainicio.component.html',
  styleUrls: ['./vistainicio.component.css']
})
export class VistainicioComponent  {
  noticias:Noticia[];
  constructor(private service:NoticiaService){}
  ngOnInit(): void {
    this.service.listar().subscribe(n=>{
      console.log(n);

      this.noticias=n as Noticia[];
      console.log(this.noticias);


    })



  }
  registrar(){
    Swal.fire('En reparacion: ', 'Esta seccion esta en reparacion', 'info');
  }

}
