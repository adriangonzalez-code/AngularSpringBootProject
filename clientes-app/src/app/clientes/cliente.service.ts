import { Injectable } from '@angular/core';
import { CLIENTES } from "./clientes.json";
import { Cliente } from "./cliente";
import { map, Observable, of } from "rxjs";
import { HttpClient, HttpHeaders } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private urlEndpoint : string = "http://localhost:8080/api/clientes";
  private httpHeader : HttpHeaders = new HttpHeaders({'Content-type': 'application/json'});

  constructor(private http : HttpClient) {}

  getClientes() : Observable<Cliente[]> {
    // return of(CLIENTES);
    return this.http.get(this.urlEndpoint).pipe(
      map(response => response as Cliente[])
    );
  }

  create(cliente: Cliente) : Observable<Cliente> {
    return this.http.post<Cliente>(this.urlEndpoint, cliente, {headers: this.httpHeader});
  }

  getCliente(id: number) : Observable<Cliente> {
    return this.http.get<Cliente>(`${this.urlEndpoint}/${id}`);
  }

  update(cliente: Cliente) : Observable<Cliente> {
    return this.http.put<Cliente>(`${this.urlEndpoint}/${cliente.id}`, cliente, {headers: this.httpHeader});
  }

  delete(id: number) : Observable<Cliente> {
    return this.http.delete<Cliente>(`${this.urlEndpoint}/${id}`, {headers: this.httpHeader});
  }
}
