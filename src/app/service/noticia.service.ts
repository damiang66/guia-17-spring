import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs'
import { Noticia } from '../modelo/noticia';
@Injectable({
  providedIn: 'root'
})
export class NoticiaService {
private header = new Headers({'Content-Type':'application/json'});
url ='http://localhost:8080/noticia'
  constructor(private http:HttpClient) { }
  public listar():Observable<Noticia[]>{
    return this.http.get<Noticia[]>(`${this.url}`).pipe(
     
    )

  }
  
}
