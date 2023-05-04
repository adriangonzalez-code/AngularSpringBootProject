import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Factura } from "../models/factura";
import {Producto} from "../models/producto";

@Injectable({
  providedIn: 'root'
})
export class FacturaService implements OnInit {

  private urlEndpoint: string = 'http://localhost:8080/api/facturas';

  constructor(private http : HttpClient) { }

  ngOnInit(): void {
  }

  getFactura(id: number)  : Observable<Factura> {
    return this.http.get<Factura>(`${this.urlEndpoint}/${id}`);
  }

  delete(id: number) : Observable<void> {
    return this.http.delete<void>(`${this.urlEndpoint}/${id}`);
  }

  filtrarProductos(term: string) : Observable<Producto[]> {
    term = term ? term : 'a';
    return this.http.get<Producto[]>(`${this.urlEndpoint}/filtrar-productos/${term}`);
  }

  create(factura: Factura) : Observable<Factura> {
    return this.http.post<Factura>(this.urlEndpoint, factura);
  }
}
