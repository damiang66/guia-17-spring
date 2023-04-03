import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs'
import { Noticia } from '../modelo/noticia';
import Swal from 'sweetalert2';
@Injectable({
  providedIn: 'root'
})
export class NoticiaService {

  private header  = new HttpHeaders({'Content-Type':'application/json'});
  url = 'http://localhost:8080/noticia'
  constructor(private http: HttpClient) { }
  public listar(): Observable<Noticia[]> {
    return this.http.get<Noticia[]>(`${this.url}`).pipe(

    )

  }
  public buscarUno(id: number): Observable<any> {
    return this.http.get<any>(`${this.url}/${id}`).pipe(
      catchError(e => {
        if (e.status == 404) {
          console.log(e);
          Swal.fire('Error:', 'Usuario no encontrado', 'error');
        }
        return throwError(e);
      })
    )

  }
public crear(noticia:Noticia):Observable<any>{
  return this.http.post<any>(this.url,noticia,{headers:this.header})
}
public editar(noticia:Noticia):Observable<any>{
  return this.http.put<any>(`${this.url}/${noticia.id}`,noticia,{headers:this.header})
}
public eliminar(id:number):Observable<any>{
  return this.http.delete<any>(`${this.url}/${id}`,{headers:this.header})
}
public buscarPorTitulo(termino:string):Observable<Noticia[]>{
  return this.http.get<Noticia[]>(`${this.url}/buscar/${termino}`)
}

}
