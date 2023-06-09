import { Component, Input, OnInit } from '@angular/core';
import { Cliente } from "../cliente";
import { ClienteService } from "../cliente.service";
import swal from "sweetalert2";
import { HttpEventType } from "@angular/common/http";
import { ModalService } from "./modal.service";
import { AuthService } from "../../usuarios/auth.service";
import { FacturaService } from 'src/app/facturas/services/factura.service';
import {Factura} from "../../facturas/models/factura";

@Component({
  selector: 'detalle-cliente',
  templateUrl: './detalle.component.html',
  styleUrls: ['./detalle.component.css']
})
export class DetalleComponent implements OnInit {

  @Input() cliente: Cliente;
  titulo: string = "Detalle del cliente";
  fotoSeleccionada : File;
  progreso: number = 0;

  constructor(private clienteService: ClienteService,
              public modalService: ModalService,
              public authService: AuthService,
              private facturaService: FacturaService) {
  }

  ngOnInit(): void {

  }

  seleccionarFoto(event) {
    this.fotoSeleccionada = event.target.files[0];
    this.progreso = 0;
    console.log(this.fotoSeleccionada);

    if (this.fotoSeleccionada.type.indexOf('image') < 0) {
      swal('Error al seleccionar', 'El archivo debe ser del tipo imagen', 'error');
      this.fotoSeleccionada = null;
    }
  }

  subirFoto() {

    if (!this.fotoSeleccionada) {
      swal('Error al subir', 'Debe seleccionar una foto', 'error');
    } else {
      this.clienteService.subirFoto(this.fotoSeleccionada, this.cliente.id).subscribe(
        event => {
          //this.cliente = cliente;
          if (event.type === HttpEventType.UploadProgress) {
            this.progreso = Math.round((event.loaded/event.total) * 100);
          } else if (event.type == HttpEventType.Response) {
            let response: any = event.body;
            this.cliente = response.cliente as Cliente;
            this.modalService.notificarUpload.emit(this.cliente);
            swal('La foto se ha subido correctamente!', response.mensaje, 'success');
          }
        }
      );
    }
  }

  cerrarModal() {
    this.modalService.cerrarModal();
    this.fotoSeleccionada = null;
    this.progreso = 0;
  }

  public hasRole(role: string) : boolean {
    return this.authService.hasRole(role);
  }

  public delete(factura: Factura) : void {
    swal({
      title: '¿Estás seguro?',
      text: `¿Seguro que desea eliminar la factura ${factura.descripcion}?`,
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6',
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No'
    }).then((result) => {
      if (result.value) {
        this.facturaService.delete(factura.id).subscribe(
          response => {
            this.cliente.facturas = this.cliente.facturas.filter(f => f != factura);
            swal(
              '¡Factura Eliminada!',
              `Factura ${factura.descripcion} eliminada con éxito`,
              'success'
            );
          }
        );
      }
    });
  }
}
