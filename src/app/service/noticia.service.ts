import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs'
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
  public buscarUno(id:number):Observable<any>{
    return this.http.get<any>(`${this.url}/${id}`).pipe(
catchError(e=>{
  if (e.status==404){
    console.log(e);
    
  }
  return throwError(e);
})
    )

  }
  
}