import { Injectable } from '@angular/core';
import { CLIENTES } from "./clientes.json";
import { Cliente } from "./cliente";
import { catchError, map, Observable, of, tap, throwError } from "rxjs";
import {HttpClient, HttpEvent, HttpHeaders, HttpRequest} from "@angular/common/http";
import { Router } from "@angular/router";
import swal from "sweetalert2";
import { DatePipe, formatDate } from "@angular/common";

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private urlEndpoint : string = "http://localhost:8080/api/clientes";
  private httpHeader : HttpHeaders = new HttpHeaders({'Content-type': 'application/json'});

  constructor(private http : HttpClient, private router : Router) {}

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
        this.router.navigate(['/clientes']);
        console.error(e.error.mensaje);
        swal(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    );
  }

  create(cliente: Cliente) : Observable<any> {
    return this.http.post<any>(this.urlEndpoint, cliente, {headers: this.httpHeader}).pipe(
      catchError(e => {

        if (e.status == 400) {
          return throwError(e);
        }

        console.error(e.error.mensaje);
        swal(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    );
  }

  update(cliente: Cliente) : Observable<any> {
    return this.http.put<any>(`${this.urlEndpoint}/${cliente.id}`, cliente, {headers: this.httpHeader}).pipe(
      catchError(e => {

        if (e.status == 400) {
          return throwError(e);
        }

        console.error(e.error.mensaje);
        swal(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    );
  }

  delete(id: number) : Observable<Cliente> {
    return this.http.delete<Cliente>(`${this.urlEndpoint}/${id}`, {headers: this.httpHeader}).pipe(
      catchError(e => {
        console.error(e.error.mensaje);
        swal('Error al eliminar al cliente', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }

  subirFoto(archivo: File, id) : Observable<HttpEvent<{}>> {
    let formData  : FormData = new FormData();
    formData.append("archivo", archivo);
    formData.append("id", id);

    const req = new HttpRequest('POST', `${this.urlEndpoint}/upload`, formData, {
      reportProgress: true
    });

    return this.http.request(req);
  }
}
