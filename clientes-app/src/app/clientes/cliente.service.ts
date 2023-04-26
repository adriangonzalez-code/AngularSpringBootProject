import { Injectable } from '@angular/core';
import { CLIENTES } from "./clientes.json";
import { Cliente } from "./cliente";
import { catchError, map, Observable, of, tap, throwError } from "rxjs";
import { HttpClient, HttpEvent, HttpHeaders, HttpRequest } from "@angular/common/http";
import { Router } from "@angular/router";
import { DatePipe, formatDate } from "@angular/common";
import { Region } from "./Region";
import { AuthService } from "../usuarios/auth.service";

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private urlEndpoint : string = "http://localhost:8080/api/clientes";
  //private httpHeader : HttpHeaders = new HttpHeaders({'Content-type': 'application/json'});

  constructor(private http : HttpClient, private router : Router, private authService : AuthService) {}

  /*private agregarAuthorizationHeader() : HttpHeaders {
    let token = this.authService.token;

    if (token != null) {
      return this.httpHeader.append('Authorization', 'Bearer ' + token);
    }

    return this.httpHeader;
  }*/

  getRegiones(): Observable<Region[]> {
    return this.http.get<Region[]>(this.urlEndpoint + '/regiones');
  }

  getClientes(page: number) : Observable<any> {
    // return of(CLIENTES);
    return this.http.get(`${this.urlEndpoint}/page/${page}`).pipe(
      tap((response : any) => {
        console.log("ClienteService tap 1");
        (response.content as Cliente[]).forEach(cliente => {
          console.log(cliente.nombre);
        })
      }),
      map((response : any) => {
        (response.content as Cliente[]).map(cliente => {
          cliente.nombre = cliente.nombre.toUpperCase();
          /*cliente.createAt = formatDate(cliente.createAt, 'dd/MM/yyyy', 'en-US');*/
          /*let datePipe : DatePipe = new DatePipe('es');*/
          /*cliente.createAt = datePipe.transform(cliente.createAt, 'EEEE dd, MMMM yyyy');*/
          return cliente;
        });
        return response;
      }),
      tap(response => {
        console.log("ClienteService tap 2");
        (response.content as Cliente[]).forEach(cliente => {
          console.log(cliente.nombre);
        })
      })
    );
  }

  getCliente(id: number) : Observable<Cliente> {
    return this.http.get<Cliente>(`${this.urlEndpoint}/${id}`).pipe(
      catchError(e => {
        if (e.status != 401 && e.error.mensaje) {
          this.router.navigate(['/clientes']);
          console.error(e.error.mensaje);
        }

        return throwError(e);
      })
    );
  }

  create(cliente: Cliente) : Observable<any> {
    return this.http.post<any>(this.urlEndpoint, cliente).pipe(
      catchError(e => {

        if (e.status == 400) {
          return throwError(e);
        }

        if (e.error.mensaje) {
          console.error(e.error.mensaje);
        }

        return throwError(e);
      })
    );
  }

  update(cliente: Cliente) : Observable<any> {
    return this.http.put<any>(`${this.urlEndpoint}/${cliente.id}`, cliente).pipe(
      catchError(e => {

        if (e.status == 400) {
          return throwError(e);
        }

        if (e.error.mensaje) {
          console.error(e.error.mensaje);
        }

        return throwError(e);
      })
    );
  }

  delete(id: number) : Observable<Cliente> {
    return this.http.delete<Cliente>(`${this.urlEndpoint}/${id}`).pipe(
      catchError(e => {

        if (e.error.mensaje) {
          console.error(e.error.mensaje);
        }

        return throwError(e);
      })
    );
  }

  subirFoto(archivo: File, id) : Observable<HttpEvent<{}>> {
    let formData  : FormData = new FormData();
    formData.append("archivo", archivo);
    formData.append("id", id);

    let httpHeaders :HttpHeaders  = new HttpHeaders();
    let token = this.authService.token;

    if (token != null) {
      httpHeaders = httpHeaders.append('Authorization', 'Bearer ' + token);
    }

    const req : HttpRequest<FormData> = new HttpRequest('POST', `${this.urlEndpoint}/upload`, formData, {
      reportProgress: true,
      headers: httpHeaders
    });

    return this.http.request(req);
  }
}
