import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListaMedicamentosComponent } from './components/lista-medicamentos/lista-medicamentos.component';
import { RegistrarMedicamentoComponent } from './components/registrar-medicamento/registrar-medicamento.component';
import { MovimientosComponent } from './components/movimientos/movimientos.component';
import { AlertasComponent } from './components/alertas/alertas.component';


const routes: Routes = [
  { path: 'lista', component: ListaMedicamentosComponent },
  { path: 'registrar', component: RegistrarMedicamentoComponent},
  {path: 'movimientos', component: MovimientosComponent},
  {path: 'alertas', component: AlertasComponent},
  { path: '**', redirectTo: 'lista' } // Ruta para el componente Home
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
