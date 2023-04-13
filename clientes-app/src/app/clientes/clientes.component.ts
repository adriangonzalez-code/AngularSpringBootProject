import { Component, OnInit } from '@angular/core';
import { Cliente } from "./cliente";
import { ClienteService } from "./cliente.service";
import swal from "sweetalert2";
import { tap } from "rxjs";

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html'
})
export class ClientesComponent implements OnInit{

  clientes: Cliente[];

  constructor(private clienteService : ClienteService) {
  }

  ngOnInit(): void {
    this.clienteService.getClientes()
      .pipe(
        tap(clientes => {
          this.clientes = clientes;
          console.log("ClienteService tap 3");
          clientes.forEach(cliente => {
            console.log(cliente.nombre);
          })
        })
      )
      .subscribe();
    //this.clientes = [];
  }

  delete(cliente: Cliente) : void {
    swal({
      title: '¿Estás seguro?',
      text: `¿Seguro que desea eliminar al cliente ${cliente.nombre} ${cliente.apellido}?`,
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6',
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No'
    }).then((result) => {
      if (result.value) {
        this.clienteService.delete(cliente.id).subscribe(
          response => {
            this.clientes = this.clientes.filter(cli => cli != cliente);
            swal(
              '¡Cliente Eliminado!',
              `Cliente ${cliente.nombre} eliminado con éxito`,
              'success'
            );
          }
        );
      }
    })
  }
}
