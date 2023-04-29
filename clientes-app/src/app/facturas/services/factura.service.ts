import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Factura } from "../models/factura";

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
}
