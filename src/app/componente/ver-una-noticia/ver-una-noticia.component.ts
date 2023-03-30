import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Noticia } from 'src/app/modelo/noticia';
import { NoticiaService } from 'src/app/service/noticia.service';

@Component({
  selector: 'app-ver-una-noticia',
  templateUrl: './ver-una-noticia.component.html',
  styleUrls: ['./ver-una-noticia.component.css']
})
export class VerUnaNoticiaComponent implements OnInit { 
  noticia:Noticia;
  constructor(private service:NoticiaService,private ruta:ActivatedRoute){}
ngOnInit(): void {
  this.ruta.paramMap.subscribe(param=>{
   let id:number = +param.get('id');
   if (id){
    this.service.buscarUno(id).subscribe(data=>{
      this.noticia= data;
    })
   }
   
   
  
  })
 
 
  
}
}
