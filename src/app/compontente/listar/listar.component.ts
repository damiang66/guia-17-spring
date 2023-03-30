import { Component, OnInit } from '@angular/core';
import { Noticia } from 'src/app/modelo/noticia';
import { NoticiaService } from 'src/app/service/noticia.service';

@Component({
  selector: 'app-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.css']
})
export class ListarComponent implements OnInit {
noticias:Noticia[]=[];
constructor(private service:NoticiaService){}
  ngOnInit(): void {
    this.service.listar().subscribe(data=>{
      this.noticias= data as Noticia[];
      console.log(data);
      
    })
  }
  eliminar(noticia:Noticia){

  }

}
