import { Component, OnInit } from '@angular/core';
import { Factura } from "./models/factura";
import { ClienteService } from "../clientes/cliente.service";
import { ActivatedRoute } from "@angular/router";
import { flatMap, map, Observable } from "rxjs";
import { FormControl } from "@angular/forms";
import { FacturaService } from "./services/factura.service";
import { Producto } from "./models/producto";
import { MatAutocompleteSelectedEvent } from "@angular/material/autocomplete";
import { ItemFactura } from "./models/item-factura";

@Component({
  selector: 'app-facturas',
  templateUrl: './facturas.component.html'
})
export class FacturasComponent implements OnInit {

  titulo: string = 'Nueva Factura';
  factura: Factura = new Factura();
  productos: string[] = ['Mesa', 'Tablet', 'Bicicleta'];
  autoCompleteControl = new FormControl('');
  productosFiltrados: Observable<Producto[]> | any;

  constructor(private clienteService: ClienteService, private activatedRouter : ActivatedRoute, private facturaService : FacturaService) {}

  ngOnInit(): void {
    this.activatedRouter.paramMap.subscribe(params => {
      let clienteId = +params.get('clienteId');
      this.clienteService.getCliente(clienteId).subscribe(cliente => {
         this.factura.cliente = cliente;
      });
    });

    this.productosFiltrados = this.autoCompleteControl.valueChanges.pipe(
      map((value:any) => typeof value ==='string'? value: value.nombre),
      flatMap(value =>  value != null ? this._filter(value) : [])
    );
  }

  private _filter(value: string): Observable<Producto[]> {
    const filterValue = value.toLowerCase();

    return this.facturaService.filtrarProductos(filterValue);
  }

  mostrarNombre(producto?: Producto) : string | undefined {
    return producto? producto.nombre : undefined;
  }

  seleccionarProducto(event: MatAutocompleteSelectedEvent) : void {
    let producto = event.option.value as Producto;
    console.log(producto);

    if (this.existeItem(producto.id)) {
      this.incrementaCantidad(producto.id);
    } else {
      let nuevoItem = new ItemFactura();
      nuevoItem.producto = producto;
      this.factura.items.push(nuevoItem);
      this.autoCompleteControl.setValue('');
      event.option.focus();
      event.option.deselect();
    }
  }

  actualizarCantidad(id : number, event : any) : void {
    let cantidad : number = event.target.value as number;

    if (cantidad == 0) {
      return this.eliminarItemFactura(id);
    }

    this.factura.items = this.factura.items.map((item: ItemFactura) => {
      if (id === item.producto.id) {
        item.cantidad = cantidad;
      }

      return item;
    });
  }

  existeItem(id: number) : boolean {
    let existe : boolean = false;
    this.factura.items.forEach((item: ItemFactura) => {
      if (id === item.producto.id) {
        existe = true;
      }
    });

    return existe;
  }

  incrementaCantidad(id : number) : void {
    this.factura.items = this.factura.items.map((item: ItemFactura) => {
      if (id === item.producto.id) {
        ++item.cantidad;
      }

      return item;
    });
  }

  eliminarItemFactura(id : number) : void {
    this.factura.items = this.factura.items.filter((item: ItemFactura) => id != item.producto.id);
  }
}
