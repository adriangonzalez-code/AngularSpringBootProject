import {Component, OnInit} from '@angular/core';
import { Cliente } from "./cliente";
import { ClienteService } from "./cliente.service";
import {ActivatedRoute, Router} from "@angular/router";
import swal from 'sweetalert2';
import {Region} from "./Region";

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {

  public cliente: Cliente = new Cliente();
  public titulo: string = "Crear Cliente";
  public errores : string[];
  public regiones: Region[];

  constructor(private clienteService: ClienteService, private router: Router, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
        this.cargarCliente();
    }

  cargarCliente(): void {
    this.activatedRoute.params.subscribe(params => {
      let id = params['id'];
      if (id) {
        this.clienteService.getCliente(id).subscribe((cliente) => this.cliente = cliente);
      }
    });

    this.clienteService.getRegiones().subscribe(regiones => this.regiones = regiones);
  }

  public create(): void {
    this.clienteService.create(this.cliente).subscribe(
      response => {
        this.router.navigate(['/clientes']);
        swal('Nuevo Cliente', `Cliente ${this.cliente.nombre} creado con éxito!`, 'success');
      },
      error => {
        this.errores = error.error.errors as string[];
        console.error("Código del error desde el backend: " + error.status);
        console.error(error.error.errors);
      }
    )
  }

  public update() : void {
    console.log(this.cliente);
    this.cliente.facturas = null;
    this.clienteService.update(this.cliente).subscribe(
      response => {
        this.router.navigate(['/clientes']);
        swal('Cliente Actualizado', `Cliente ${this.cliente.nombre} actualizado con éxito!`, 'success');
      },
      error => {
        this.errores = error.error.errors as string[];
        console.error("Código del error desde el backend: " + error.status);
        console.error(error.error.errors);
      }
    );
  }

  compararRegion(o1: Region, o2: Region): boolean {
    if (o1 === undefined && o2 === undefined) {
      return true;
    }
    return (o1 == null || o2 == null || o1 === undefined || o2 === undefined) ? false : o1.id === o2.id;
  }
}
