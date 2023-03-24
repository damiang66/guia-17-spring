import { Component } from '@angular/core';
import { Noticia } from 'src/app/modelo/noticia';
import { NoticiaService } from 'src/app/service/noticia.service';

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

}
